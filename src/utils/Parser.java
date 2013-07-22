package utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.*;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import server.ServerException;
import server.database.Database;
import shared.model.Batch;
import shared.model.Field;
import shared.model.Project;
import shared.model.RecordValue;
import shared.model.User;


/**
 * 
 * @author zaphinath
 *
 */

public class Parser{

private Database db;
	private ArrayList<Integer> fieldIds;
	
  /** 
   * Constructor
   */
  public Parser() {
  	
  	try {
			Database.initialize();
			db = new Database();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
  	} catch (ServerException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
  }
  /**
   * This takes a record-indexer.xml and parses the values out of it
   * Then it calls the dbConn class to insert into the database
   * @param xml
   */
  public void parse(File xml) {
  	String parent = getParentPath(xml);
    try {
    	Database db = new Database();
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(xml);
  
      doc.getDocumentElement().normalize();

      NodeList users = doc.getElementsByTagName("user");
      
      for (int i = 0; i < users.getLength(); i++) {
        Node uNode = users.item(i);
        if (uNode.getNodeType() == Node.ELEMENT_NODE) {
          Element uElement = (Element) uNode;
          Element usernameE = (Element) uElement.getElementsByTagName("username").item(0);
          Element passwordE = (Element) uElement.getElementsByTagName("password").item(0);
          Element firstNameE = (Element) uElement.getElementsByTagName("firstname").item(0);
          Element lastNameE = (Element) uElement.getElementsByTagName("lastname").item(0);
          Element emailE = (Element) uElement.getElementsByTagName("email").item(0);
          Element indexedRecordsE = (Element) uElement.getElementsByTagName("indexedrecords").item(0);
          User user = new User();
          user.setUsername(usernameE.getTextContent());
          user.setPassword(passwordE.getTextContent());
          user.setFirstName(firstNameE.getTextContent());
          user.setLastName(lastNameE.getTextContent());
          user.setEmail(emailE.getTextContent());
          user.setIndexedRecords(Integer.parseInt(indexedRecordsE.getTextContent()));
          db.startTransaction();
          db.getUserDB().add(user);
          db.endTransaction(true);
        }
      }
      
      NodeList projects = doc.getElementsByTagName("project");
      for (int i = 0; i < users.getLength(); i++) {
      	fieldIds = new ArrayList<Integer>();
        Node uNode = projects.item(i);
        if (uNode.getNodeType() == Node.ELEMENT_NODE) {
          Element uElement = (Element) uNode;
          Element title = (Element) uElement.getElementsByTagName("title").item(0);
          Element recPerImg = (Element) uElement.getElementsByTagName("recordsperimage").item(0);
          Element firstYCoord = (Element) uElement.getElementsByTagName("firstycoord").item(0);
          Element recHeight = (Element) uElement.getElementsByTagName("recordheight").item(0);
          Project project = new Project(-1, 
          		title.getTextContent(), 
          		Integer.parseInt(recPerImg.getTextContent()),
          		Integer.parseInt(firstYCoord.getTextContent()),
          		Integer.parseInt(recHeight.getTextContent()));
          db.startTransaction();
          project = db.getProjectDB().add(project);
          db.endTransaction(true);
          int projectId = project.getId();
        	
          
          NodeList fields = uElement.getElementsByTagName("field");
        	for (int j = 0; j < fields.getLength(); j++) {
        		Node fNode = fields.item(j);
            if (fNode.getNodeType() == Node.ELEMENT_NODE) {
              Element fElement = (Element) fNode;
              Element titleF = (Element) fElement.getElementsByTagName("title").item(0);
              Element xcoord = (Element) fElement.getElementsByTagName("xcoord").item(0);
              Element width = (Element) fElement.getElementsByTagName("width").item(0);
              Element helpHtml = (Element) fElement.getElementsByTagName("helphtml").item(0);
              Element knownData;
              String kData;
              if (fElement.getElementsByTagName("knowndata").item(0) != null) {
              	knownData = (Element) fElement.getElementsByTagName("knowndata").item(0);
              	kData = knownData.getTextContent();
              	//Copy known data
              	File kdSrc = new File(parent + "/" + kData);
              	File idst = new File("./files/" + kData);
              	
              	FileInputStream kdIn = new FileInputStream(kdSrc);
              	FileOutputStream kdOut = new FileOutputStream(idst);
              	IOUtils.copy(kdIn, kdOut);
              } else {
              	kData = null;
              }
              Field field = new Field(-1, projectId, titleF.getTextContent(),
              		Integer.parseInt(xcoord.getTextContent()), 
              		Integer.parseInt(width.getTextContent()),
              		helpHtml.getTextContent(),
              		kData);
              db.startTransaction();
              field = db.getFieldDB().add(field);
              db.endTransaction(true);
              int fieldId = field.getId();
              fieldIds.add(fieldId);
              //Copy fieldHelp
              String fieldSrc = parent + "/" + field.getHtmlHelp();
              String fieldDest = "./files/" + field.getHtmlHelp();
              File fieldIn = new File(fieldSrc);
              File fieldOut = new File(fieldDest);
              FileInputStream fin = new FileInputStream(fieldIn);
              FileOutputStream fout = new FileOutputStream(fieldOut);
              IOUtils.copy(fin, fout);
            }
          }
        	//System.out.println(fieldIds.toString());
        	
        	NodeList images = uElement.getElementsByTagName("image");
        	for (int k = 0; k < images.getLength(); k++) {
        		Node iNode = images.item(k);
            if (iNode.getNodeType() == Node.ELEMENT_NODE) {
              Element iElement = (Element) iNode;
              Element imageFile = (Element) iElement.getElementsByTagName("file").item(0);
              Batch batch = new Batch(-1, projectId, imageFile.getTextContent(), 0);
              db.startTransaction();
              batch = db.getBatchDB().addBatch(batch);
              db.endTransaction(true);
              int batchId = batch.getId();
              //Copy image files to dest
              String isrc = parent + "/" +batch.getFile();
              String idest = "./files/" + batch.getFile();
              File imageIn = new File(isrc);
              File imageOut = new File (idest);
              FileInputStream isIn = new FileInputStream(imageIn);
              FileOutputStream isOut = new FileOutputStream(imageOut);
              IOUtils.copy(isIn, isOut);
              
              NodeList values = iElement.getElementsByTagName("value");
              int count = 0;
              int countRecNum = 1;
              int recordNumber = values.getLength()/fieldIds.size();
              for (int l = 0; l < values.getLength(); l++) {
              	if (count > fieldIds.size()-1) count = 0;
              	if (countRecNum > recordNumber) countRecNum = 1;
              	Node valNode = values.item(l);
              	if (valNode.getNodeType() == Node.ELEMENT_NODE) {
              		Element val = (Element) valNode;
              		//Element val = (Element) x.getElementsByTagName("value");
              		RecordValue recordValue = new RecordValue(-1, batchId,
              				fieldIds.get(count), val.getTextContent(), countRecNum);
              		db.startTransaction();
              		recordValue = db.getRecordValueDB().addRecordValue(recordValue);
              		db.endTransaction(true);
              		count++;
              		countRecNum++;
              	}
              }
            }
        	}
        }
      }
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }
  private String getParentPath(File file) {
  	String path = null;
  	file = file.getParentFile();
		path = file.getAbsolutePath();
  	System.out.println(path);
  	return path;
  }
  

}
