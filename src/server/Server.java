package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import server.database.Database;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * @author Derek Carr
 * 
 */
public class Server {

	private static final int SERVER_PORT_NUMBER = 8080;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private static Logger logger;
	
	private HttpServer server;
	
	static {
		try {
			initLog();
		}
		catch (IOException e) {
			System.out.println("Could not initialize log: " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private static void initLog() throws IOException {
		
		Level logLevel = Level.FINE;
		
		logger = Logger.getLogger("contactmanager"); 
		logger.setLevel(logLevel);
		logger.setUseParentHandlers(false);
		
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(logLevel);
		consoleHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(consoleHandler);

		FileHandler fileHandler = new FileHandler("log.txt", false);
		fileHandler.setLevel(logLevel);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
	}
	
	private Server() {
		return;
	}
	
	private void run() throws ClassNotFoundException, SQLException {
		
		logger.info("Initializing Database");
		
		try {
			Database.initialize();		
		}
		catch (ServerException e) {
			
			//System.out.println("Could not initialize database: " + e.getMessage());
			//e.printStackTrace();

			logger.log(Level.SEVERE, e.getMessage(), e);
			
			return;
		}
		
		logger.info("Initializing HTTP Server");
		
		try {
			server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER),
											MAX_WAITING_CONNECTIONS);
		} 
		catch (IOException e) {
			
			//System.out.println("Could not create HTTP server: " + e.getMessage());
			//e.printStackTrace();
			
			logger.log(Level.SEVERE, e.getMessage(), e);
			
			return;
		}

		server.setExecutor(null); // use the default executor
		
		server.createContext("/GetAllContacts", getAllContactsHandler);
		server.createContext("/AddContact", addContactHandler);
		server.createContext("/UpdateContact", updateContactHandler);
		server.createContext("/DeleteContact", deleteContactHandler);
		
		logger.info("Starting HTTP Server");

		server.start();
	}
	
	private HttpHandler getAllContactsHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Process GetAllContacts request

			// Database db = new Database();
			// db.startTransaction();
			// ...
			// db.endTransaction();
		}
	};

	private HttpHandler addContactHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Process AddContact request

			// Database db = new Database();
			// db.startTransaction();
			// ...
			// db.endTransaction();
		}
	};

	private HttpHandler updateContactHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Process UpdateContact request

			// Database db = new Database();
			// db.startTransaction();
			// ...
			// db.endTransaction();
		}
	};

	private HttpHandler deleteContactHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Process DeleteContact request

			// Database db = new Database();
			// db.startTransaction();
			// ...
			// db.endTransaction();
		}
	};
	
	/**
	 * Starts the server
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new Server().run();
	}
	
	
}
