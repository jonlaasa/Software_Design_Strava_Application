package es.deusto.ingenieria.sd.facebook.main;

import java.net.ServerSocket;

import es.deusto.ingenieria.sd.facebook.service.FacebookService;


public class FacebookMain {
	
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # ERROR Usage: TCPSocketFacebookServer [PORT]");
			System.exit(1);
		}
		
		int serverPort = Integer.parseInt(args[0]);
		
		
	
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - FacebookServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				new FacebookService(tcpServerSocket.accept());
				System.out.println(" - FacebookServer: New client connection accepted. Client Number: " + numClients++);
			}
		} catch (Exception e) {
			System.out.println("# FacebookServer: IO error:" + e.getMessage());
		}
	}

}
