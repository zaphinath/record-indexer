package utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import server.ServerException;
import server.database.Database;
import server.database.UserDB;
import shared.model.User;


/**
 * 
 * @author zaphinath
 *
 */

public class Parser{

	private Database db;
	
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
          String username = uElement.getElementsByTagName("username").toString();
          String password = uElement.getElementsByTagName("password").toString();
          String firstName = uElement.getElementsByTagName("firstname").toString();
          String lastName = uElement.getElementsByTagName("lastname").toString();
          String email = uElement.getElementsByTagName("email").toString();
          //int indexedRecords = uElement.getElementsByTagName("indexedrecords").toString());
          User user = new User();
          user.setUsername(username);
          user.setPassword(password);
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setEmail(email);
          db.startTransaction();
          db.getUserDB().add(user);
          db.endTransaction(true);
        }
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }

}
