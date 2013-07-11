package utils;


import java.io.File;
import shared.model.User;

public class Importer{


  public static void main(String[] args) {
    File xml = new File(args[0]);    
    
    System.out.println(args[0]);

    Parser parse = new Parser(); 
    //User user = parse.parse(xml); 
  }

}
