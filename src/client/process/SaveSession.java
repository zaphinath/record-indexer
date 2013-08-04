/**
 * 
 */
package client.process;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import client.Session;

/**
 * @author Derek Carr
 *
 */
public class SaveSession {
	private Session session;
	private XStream xstream;
	private FileOutputStream fwrite;
	private File file;
	private String path;
	
	public SaveSession(Session session) {
		this.session = session;
		xstream = new XStream(new DomDriver());
		try {
			path = "./sessions/"+session.getLastName().toLowerCase().trim()+session.getFirstName().toLowerCase().trim()+".session";
			System.out.println(path);
			file = new File(path);
			file.createNewFile();
			fwrite = new FileOutputStream(file);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void writeFile() {
		try {
			xstream.autodetectAnnotations(true);
			xstream.toXML(session, new FileWriter(path));
		} catch (IOException e) {
			System.out.println("UG Buckets:");
			e.printStackTrace();
		}
	}
}
