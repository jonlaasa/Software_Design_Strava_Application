package es.deusto.ingenieria.sd.facebook.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class FacebookService extends Thread implements IFacebookService {
	
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	private static Map<String, String> userMap;
	
	
	public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			userMap = new HashMap<>();
			//ADD DEMO VALUES
			userMap.put("jon.lasa@opendeusto.es", "44f878afe53efc66b76772bd845eb65944ed8232");
			userMap.put("iker.ruesgas@opendeusto.es", "e165f1f439f2c92b7fd8f906c98f84677a6b45bb");
			userMap.put("a", "44f878afe53efc66b76772bd845eb65944ed8232");
			this.start();
		} catch (Exception e) {
			System.out.println("# FacebookService - TCPConnection IO error:" + e.getMessage());
		}
	}
	
	public void run() {
		//Echo server
		try {
			//Read request from the client
			String email = this.in.readUTF();	
			String pass = this.in.readUTF();	
			System.out.println("   - FacebookService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + email + " : " + pass + "'");		
			
			String action = this.in.readUTF();
			
			//Send response to the client
			String resp;
			if(action.equals("0")) {
				regist(email,pass);
				System.out.println("User registered");
				
			}else {
			resp = login(email, pass);
			this.out.writeUTF(resp);			
			System.out.println("   - FacebookService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + resp + "'");
			}
			
		}catch (Exception e) {
			System.out.println("   # FacebookService error" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (Exception e) {
				System.out.println("   # FacebookService error:" + e.getMessage());
			}
		}
	}


	@Override
	public String login(String email, String pass) {
		System.out.println("Contenido del Mapa:");
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }
		
		if(userMap.containsKey(email)) {
			if(pass.equals(userMap.get(email))) {
				return "1";
			}
		}
		return "0";
		
	}

	@Override
	public void regist(String email, String pass) {
		userMap.put(email, pass);
		System.out.println("Contenido del Mapa:");
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }
		
		
		
	}
	
	
		
	
	
	

}
