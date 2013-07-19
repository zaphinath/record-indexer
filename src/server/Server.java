package server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import server.database.Database;
import server.handler.ServerHandler;
import shared.communication.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author Derek Carr
 * 
 */
public class Server {

	private static int PORT = 39640;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private static Logger logger;
	
	private HttpServer server;
	private XStream  xmlStream;

	
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
		xmlStream = new XStream(new DomDriver());
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
			server = HttpServer.create(new InetSocketAddress(PORT),
											MAX_WAITING_CONNECTIONS);
		} 
		catch (IOException e) {
			
			//System.out.println("Could not create HTTP server: " + e.getMessage());
			//e.printStackTrace();
			
			logger.log(Level.SEVERE, e.getMessage(), e);
			
			return;
		}

		server.setExecutor(null); // use the default executor
		
		server.createContext("/ValidateUser", validateUserHandler);
		server.createContext("/GetProjects", getProjectsHandler);
		server.createContext("/GetSampleProject", getSampleBatchProjectHandler);
		server.createContext("/DownloadBatch", downloadBatchHandler);
		server.createContext("/SubmitBatch", submitBatchHandler);
		server.createContext("/GetFields", getFieldsHandler);
		server.createContext("/Search", searchHandler);
		server.createContext("/DownloadFile", downloadFileHandler);
		
		logger.info("Starting HTTP Server");

		server.start();
	}
	
	private HttpHandler validateUserHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			ValidateUser_Params param = (ValidateUser_Params) xmlStream.fromXML(exchange.getRequestBody());
			ServerHandler serverHandler;
			try {
				//vHandler.makeQuery(param);
				serverHandler = new ServerHandler(param);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
				return;
			}
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			ValidateUser_Result vvr = serverHandler.validateUser();
			
			xmlStream.toXML(vvr, exchange.getResponseBody());
			exchange.close();
		}
	};

	private HttpHandler getProjectsHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			GetProjects_Params param = (GetProjects_Params) xmlStream.fromXML(exchange.getRequestBody());
			ServerHandler serverHandler;
			try {
				serverHandler = new ServerHandler(param);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
				return;
			}
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			GetProjects_Result gpr = serverHandler.getProjects(param);
			
			xmlStream.toXML(gpr, exchange.getResponseBody());
			exchange.close();
		}
	};

	private HttpHandler getSampleBatchProjectHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			GetSampleImage_Params param = (GetSampleImage_Params) xmlStream.fromXML(exchange.getRequestBody());
			ServerHandler serverHandler;
			try {
				serverHandler = new ServerHandler(param);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
				return;
			}
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			GetSampleImage_Result gsir = serverHandler.getSampleImage(param);
			exchange.close();
		}
	};

	private HttpHandler downloadBatchHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			DownloadBatch_Params param = (DownloadBatch_Params) xmlStream.fromXML(exchange.getRequestBody());
			ServerHandler serverHandler;
			try {
				serverHandler = new ServerHandler(param);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
				return;
			}
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			DownloadBatch_Result dbr = serverHandler.downloadBatch(param);
			exchange.close();
		}
	};
	
	private HttpHandler submitBatchHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			SubmitBatch_Params param = (SubmitBatch_Params) xmlStream.fromXML(exchange.getRequestBody());
			ServerHandler serverHandler;
			try {
				serverHandler = new ServerHandler(param);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
				return;
			}
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			SubmitBatch_Params sbr = serverHandler.submitBatch(param);
			exchange.close();
		}
	};
	
	private HttpHandler getFieldsHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Process DeleteContact request

			// Database db = new Database();
			// db.startTransaction();
			// ...
			// db.endTransaction();
		}
	};
	
	private HttpHandler searchHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Process DeleteContact request

			// Database db = new Database();
			// db.startTransaction();
			// ...
			// db.endTransaction();
		}
	};
	
	private HttpHandler downloadFileHandler = new HttpHandler() {

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
		if (args.length > 0) {
			PORT = Integer.parseInt(args[0]);
		}
		new Server().run();
	}
	
	
}
