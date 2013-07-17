package utils;

import java.io.File;

/**
 * 
 * @author zaphinath
 *
 */

public class Importer{


  public static void main(String[] args) {
    File xml = new File(args[0]);    
    
    Parser parse = new Parser(); 
    parse.parse(xml); 
  }

}
