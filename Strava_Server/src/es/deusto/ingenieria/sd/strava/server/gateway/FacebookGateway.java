package es.deusto.ingenieria.sd.strava.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import es.deusto.ingenieria.sd.strava.server.data.dto.UserAssembler;

public class FacebookGateway implements IGateway {
	
	//WE USE THIS IN ORDER TO HARDCODE THE DIRECTION, WE WILL GET FROM A TXT STORED IN REOSURCES.
	
	String host;
	int port;
	String service;
	private static FacebookGateway instance;
	//private IFacebookService facebookServer;
	
	private FacebookGateway(String ip,String h, String serv) {
		try {
			this.host=ip;
			this.port=Integer.parseInt(h);
			this.service=serv;
			String URL = String.format("//%s:%d/%s", this.host, this.port, this.service);
			//this.googleServer = (IFacebookService) Naming.lookup(URL);
		} catch (Exception ex) {
			System.out.println("error al cargar ruta FACEBOOK");
			System.err.println("# Error locating remote service: " + ex);
		}
	}
	
	//SINGLETON
	public static FacebookGateway getInstance(String ip,String h, String serv) {
		if (instance == null) {
			instance = new FacebookGateway(ip,h,serv);
		}

		return instance;
	}

	
	public boolean login(String email, String pass) throws RemoteException {
		System.out.println("TRYING TO LOGIN WITH FACEBOOK" + "\n");

		

		//Declaration of the socket to send/receive information to/from the server (an IP and a Port are needed)
		try (Socket tcpSocket = new Socket(host, port);
			 //Streams to send and receive information are created from the Socket
		     DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
			 DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){
			
			//Send email and pass to the Facebook server through SOCKETS
			out.writeUTF(email);
			out.writeUTF(pass);
			out.writeUTF("1");
			//this means that is LOGIN OPERATION
			System.out.println("- Strava SENDING INFO to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + email + " : " + pass + "'");
			
			//Read answer and INTERPRET IT
			//IF 1 - TRUE, ELSE FALSE
			String data = in.readUTF();	
			System.out.println("- Strava RECEIVING INFO FROM '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
			if(data.equals("1")) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}
	
	public void register(String email, String pass) throws RemoteException {
		System.out.println("TRYING TO REGIST WITH FACEBOOK" + "\n");
		
		try (Socket tcpSocket = new Socket(host, port);
			 //Streams to send and receive information are created from the Socket
		     DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
			 DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){
			
			//Send email and pass to the Facebook server through SOCKETS
			out.writeUTF(email);
			out.writeUTF(pass);
			//this means that is REGISTRATION OPERATION
			out.writeUTF("0");
			System.out.println("- Strava SENDING INFO to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + email + " : " + pass + "'");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}


	


	
	
	
	
	
	
	
		
	
}