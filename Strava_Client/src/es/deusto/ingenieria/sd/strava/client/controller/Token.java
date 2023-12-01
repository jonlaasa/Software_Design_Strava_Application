package es.deusto.ingenieria.sd.strava.client.controller;

public class Token {

    private static Token instance;
    private static long token = -1;

    private Token() { }

    public static synchronized Token getInstance() {
        if (instance == null) {
            instance = new Token();
        }

        return instance;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }
}