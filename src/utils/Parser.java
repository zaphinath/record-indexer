package utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import server.database.UserDB;
import shared.model.User;

/**
 * 
 * @author zaphinath
 *
 */

public class Parser{

  /** 
   * Constructor
   */
  public Parser() {

  }
  /**
   * This takes a record-indexer.xml and parses the values out of it
   * Then it calls the dbConn class to insert into the database
   * @param xml
   */
  public void parse(File xml) {
    try {
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
          int indexedRecords = Integer.parseInt(uElement.getElementsByTagName("indexedrecords").toString());
          User user = new User();
          user.setUsername(username);
          user.setPassword(password);
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setEmail(email);
          user.setIndexedRecords(indexedRecords);
          UserDB db = new UserDB();
          db.add(user);
          //DbConn con = new DbConn();
          //con.insertUser(username, password, firstName, lastName, email, indexedRecords);
        }
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }

}
