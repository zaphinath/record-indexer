package servertester.controllers;

import java.util.*;

import client.ClientException;
import client.communication.ClientCommunicator;
import servertester.views.*;
import shared.communication.*;

public class Controller implements IController {

	private IView _view;
	
	public Controller() {
		return;
	}
	
	public IView getView() {
		return _view;
	}
	
	public void setView(IView value) {
		_view = value;
	}
	
	// IController methods
	//
	
	@Override
	public void initialize() {
		getView().setHost("localhost");
		getView().setPort("39640");
		operationSelected();
	}

	@Override
	public void operationSelected() {
		ArrayList<String> paramNames = new ArrayList<String>();
		paramNames.add("User");
		paramNames.add("Password");
		
		switch (getView().getOperation()) {
		case VALIDATE_USER:
			break;
		case GET_PROJECTS:
			break;
		case GET_SAMPLE_IMAGE:
			paramNames.add("Project");
			break;
		case DOWNLOAD_BATCH:
			paramNames.add("Project");
			break;
		case GET_FIELDS:
			paramNames.add("Project");
			break;
		case SUBMIT_BATCH:
			paramNames.add("Batch");
			paramNames.add("Record Values");
			break;
		case SEARCH:
			paramNames.add("Fields");
			paramNames.add("Search Values");
			break;
		default:
			assert false;
			break;
		}
		
		getView().setRequest("");
		getView().setResponse("");
		getView().setParameterNames(paramNames.toArray(new String[paramNames.size()]));
	}

	@Override
	public void executeOperation() {
		switch (getView().getOperation()) {
		case VALIDATE_USER:
			validateUser();
			break;
		case GET_PROJECTS:
			getProjects();
			break;
		case GET_SAMPLE_IMAGE:
			getSampleImage();
			break;
		case DOWNLOAD_BATCH:
			downloadBatch();
			break;
		case GET_FIELDS:
			getFields();
			break;
		case SUBMIT_BATCH:
			submitBatch();
			break;
		case SEARCH:
			search();
			break;
		default:
			assert false;
			break;
		}
	}
	
	private void validateUser() {
		String[] tmp = getView().getParameterValues();
		ClientCommunicator cc = new ClientCommunicator();
		ValidateUser_Params vvp = new ValidateUser_Params();
		vvp.setUsername(tmp[0]);
		vvp.setPassword(tmp[1]);
		try {
			ValidateUser_Result rr = cc.validateUser(vvp);
			getView().setRequest(vvp.toString());
			getView().setResponse(rr.toString());
		} catch (ClientException e) {
			getView().setResponse("FAILED\n");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getProjects() {
		String[] tmp = getView().getParameterValues();
		ClientCommunicator cc = new ClientCommunicator();
		GetProjects_Params gpp = new GetProjects_Params();
		gpp.setUsername(tmp[0]);
		gpp.setPassword(tmp[1]);
		try {
			GetProjects_Result gpr = cc.getProjects(gpp);
			getView().setRequest(tmp[0] + "\n" + tmp[1] + "\n");
			getView().setResponse(gpr.toString());
		} catch (ClientException e) {
			getView().setResponse("FAILED\n");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getSampleImage() {
		String[] tmp = getView().getParameterValues();
		ClientCommunicator cc = new ClientCommunicator();
		GetSampleImage_Params gpp = new GetSampleImage_Params();
		gpp.setUsername(tmp[0]);
		gpp.setPassword(tmp[1]);
		try {
			GetSampleImage_Result gpr = cc.getSampleImage(gpp);
			getView().setRequest(tmp[0] + "\n" + tmp[1] + "\n");
			getView().setResponse(gpr.toString());
		} catch (ClientException e) {
			getView().setResponse("FAILED\n");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void downloadBatch() {
	}
	
	private void getFields() {
	}
	
	private void submitBatch() {
	}
	
	private void search() {
	}

}

