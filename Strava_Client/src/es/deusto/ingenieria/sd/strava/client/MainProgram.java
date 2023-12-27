package es.deusto.ingenieria.sd.strava.client;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.gui.VentanaBienvenida;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;


public class MainProgram {

	public static void main(String[] args) throws ParseException, RemoteException {	
		ServiceLocator serviceLocator = new ServiceLocator();
		
		
		
		
		
		
		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginController controller = new LoginController(serviceLocator);
					VentanaBienvenida frame = new VentanaBienvenida(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	
	}
}