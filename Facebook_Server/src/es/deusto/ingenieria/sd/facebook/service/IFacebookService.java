package es.deusto.ingenieria.sd.facebook.service;

public interface IFacebookService {
	
	public String login(String email, String pass);
	public void regist(String email, String pass);

}
