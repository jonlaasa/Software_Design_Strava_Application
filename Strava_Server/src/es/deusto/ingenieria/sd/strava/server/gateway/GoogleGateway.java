package es.deusto.ingenieria.sd.strava.server.gateway;


import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;


public class GoogleGateway implements IGateway {
	
	private static String BASE_URL ;
	
	
	private static GoogleGateway instance;
	
	public GoogleGateway (String url) {
		this.BASE_URL=url;
		System.out.println(url);
	}	
	
	
	
	//SINGLETON
	public static GoogleGateway getInstance(String url) {
		if (instance == null) {
			instance = new GoogleGateway(url);
			System.out.println("GOOGLE GATEWAY CREATED");
		}

		return instance;
	}


	public boolean login(String email, String password) {
		try {
	        System.out.format("Getting user details by email(%s) and password ...%n", email);

	        
	        HttpClient client = HttpClient.newHttpClient();

	        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
	        String encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8);
	        String url = BASE_URL + "user/details?email=" + encodedEmail + "&password=" + encodedPassword;

	        System.out.println("Request URL: " + url);

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .build();

	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        System.out.println("Response received " + response);

	        if (response.statusCode() == 200) {
	            System.out.println("Successful login");
	            return true;
	        } else {
	            System.out.println("Login failed");
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.format("- ERROR Getting user details by email(%s) and password: %s%n", email, e.getMessage());
	        return false;
	    }
	}






	@Override
	public void register(String email, String pass) throws RemoteException {
	    try {
	        System.out.format("Registering user with email(%s) and password ...%n", email);

	        HttpClient client = HttpClient.newHttpClient();

	        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
	        String encodedPassword = URLEncoder.encode(pass, StandardCharsets.UTF_8);
	        String url = BASE_URL + "user/register";

	        String requestBody = "email=" + encodedEmail + "&password=" + encodedPassword;

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .header("Content-Type", "application/x-www-form-urlencoded")
	                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
	                .build();

	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        System.out.println("Response received " + response);

	        if (response.statusCode() == 200) {
	            System.out.println("User registered successfully");
	        } else {
	            System.out.println("Failed to register user");
	        }
	    } catch (Exception e) {
	        System.out.format("- ERROR Registering user with email(%s) and password: %s%n", email, e.getMessage());
	        throw new RemoteException("Error registering user: " + e.getMessage());
	    }
	}

	


	
}
