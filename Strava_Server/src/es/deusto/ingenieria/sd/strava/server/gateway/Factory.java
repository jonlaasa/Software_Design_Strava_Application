package es.deusto.ingenieria.sd.strava.server.gateway;

public class Factory {
	
private static Factory instance;
private String[] listo=new String[4];
	
	private Factory(String[] lista) {
		listo[0]=lista[0];
		listo[1]=lista[1];
		listo[2]=lista[2];
		listo[3]=lista[3];
	}
	
	
	public static Factory getInstance(String[] lista) {
		if(instance == null) {
			instance = new Factory(lista);
			
		}
		return instance;
	}
	
	public IGateway createGateway(String provider) {
		if(provider.equals("GOOGLE")) {
			return  GoogleGateway.getInstance(listo[3]);
	}else {
			return  FacebookGateway.getInstance(listo[0],listo[1],listo[2]);	
		}
	}
	
	
	
	
	
	

}
