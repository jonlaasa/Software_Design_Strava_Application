package es.deusto.ingenieria.sd.strava.server;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.strava.server.remote.IRemoteFacade;
import es.deusto.ingenieria.sd.strava.server.remote.RemoteFacade;

public class MainProgram {

	public static void main(String[] args) {	
		//Activate Security Manager. It is needed for RMI.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
				
		 String[] listaStrings = new String[4];
		 listaStrings[0]=args[3];
		 listaStrings[1]=args[4];
		 listaStrings[2]=args[5];
		 listaStrings[3]=args[6];
		 
		//Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade(listaStrings);			
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Strava Server v1 '" + name + "' started!!");
		} catch (Exception ex) {
			System.err.println(" Strava  Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
