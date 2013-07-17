package utils;

import java.io.File;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import server.ServerException;
import server.database.Database;
import server.database.UserDB;
import server.database.FieldDB;
import shared.model.Field;
import shared.model.Project;
import shared.model.User;

/**
 * 
 * @author zaphinath
 *
 */

public class Parser extends DefaultHandler{

	private Database db;
	private User user;
	private Project project;
	private Field field;
	private String tmp;
	private int projectId = -1;
	private int fieldId;

	
  /** 
   * Constructor
   */
  public Parser() {
  	db = new Database();
  	try {
			db.initialize();
		} catch (ClassNotFoundException | ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  /**
   * This takes a record-indexer.xml and parses the values out of it
   * Then it calls the dbConn class to insert into the database
   * @param xml
   */
  public void parse(File xml) {
  	SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
    	SAXParser parser = factory.newSAXParser();
    	System.out.println(xml.toString());
    	parser.parse(xml, this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }
  @Override
  public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
  	if (elementName.equalsIgnoreCase("user")) {
  		user = new User(); 		
  	} else if (elementName.equalsIgnoreCase("project")) {
  		project = new Project();
  	} else if (elementName.equalsIgnoreCase("field")) {
  		field = new Field();
  	}
  }
  
  @Override
  public void endElement(String s, String s1, String element) throws SAXException {
  	if (element.equalsIgnoreCase("user")) {
  		//System.out.println(user.getUsername() + "  "+ user.getEmail());
  		try {
				db.startTransaction();
				db.getUserDB().add(user);
	  		db.endTransaction(true);
			} catch (ServerException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	}
  	else if (element.equalsIgnoreCase("username")) {
  		user.setUsername(tmp);
  	} else if (element.equalsIgnoreCase("password")) {
  		user.setPassword(tmp);
  	} else if (element.equalsIgnoreCase("firstname")) {
  		user.setFirstName(tmp);
  	} else if (element.equalsIgnoreCase("lastname")) {
  		user.setLastName(tmp);
  	} else if (element.equalsIgnoreCase("email")) {
  		user.setEmail(tmp);
  	} else if (element.equalsIgnoreCase("indexedrecords")) {
  		user.setIndexedRecords(Integer.parseInt(tmp));
  	}
  	if (element.equalsIgnoreCase("project")) {
  		try {
				db.startTransaction();
				Project tmpProject = db.getProjectDB().add(project);
				this.projectId = tmpProject.getId();
				System.out.println(this.projectId);
	  		db.endTransaction(true);
			} catch (ServerException | SQLException e) {
				e.printStackTrace();
			}
  	} else if (element.equalsIgnoreCase("title")) {
  		project.setTitle(tmp);
  	} else if (element.equalsIgnoreCase("recordsperimage")) {
  		project.setRecordsPerImage(Integer.parseInt(tmp));
  	} else if (element.equalsIgnoreCase("firstycoord")) {
  		project.setFirstYCoord(Integer.parseInt(tmp));
  	} else if (element.equalsIgnoreCase("recordheight")) {
  		project.setRecordHeight(Integer.parseInt(tmp));
  	}
  	if (element.equalsIgnoreCase("field")) {
  		System.out.println("FIELD: " + field.getTitle());
  		try {
				db.startTransaction();
				Field tmpField = db.getFieldDB().add(field);
				this.fieldId = tmpField.getId();
	  		db.endTransaction(true);
			} catch (ServerException | SQLException e) {
				e.printStackTrace();
			}
  	} else if (element.equalsIgnoreCase("title")) {
  		System.out.println(projectId);
  		field.setProjectId(projectId);
  		field.setTitle(tmp);
  	} else if (element.equalsIgnoreCase("xcoord")) {
  		field.setXcoord(Integer.parseInt(tmp));
  	} else if (element.equalsIgnoreCase("width")) {
  		field.setWidth(Integer.parseInt(tmp));
  	} else if (element.equalsIgnoreCase("helphtml")) {
  		field.setHtmlHelp(tmp);
  	} else if (element.equalsIgnoreCase("knowndata")) {
  		field.setKnownData(tmp);
  	}
  	
  }
  @Override
  public void characters(char[] ac, int i, int j) throws SAXException {
  	tmp = new String(ac, i, j);
  	//System.out.println(tmp);
  }



}
