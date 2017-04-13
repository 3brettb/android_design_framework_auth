/*
 * Created by Brett Brist April 2017
 * https://github.com/3brettb/android_design_framework_auth
 */
package domain.appname.auth;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import domain.appname.callbacks.OnLoginCallback;
import domain.appname.callbacks.OnRequestComplete;
import domain.appname.clients.SessionClient;
import domain.appname.facades.http.HttpRequest;
import domain.appname.facades.http.HttpResponse;
import domain.appname.models.User;
import domain.appname.preferences.ApplicationSettings;

public class Authenticator {

    // Grab Settings Variables
    private static final String LOGIN_URI = ApplicationSettings.LOGIN_URI;
    private static final String USER_DATA_URI = ApplicationSettings.USER_DATA_URI;
    private static final String CLIENT_ID = ApplicationSettings.API_CLIENT_ID;
    private static final String CLIENT_SECRET = ApplicationSettings.API_CLIENT_SECRET;

    // Auth Token data
    private HashMap<String, String> token = new HashMap<>();

    public boolean logged_in = false;

    // Session Client to handle our session variables
    private static SessionClient session = SessionClient.getInstance();

    // Store reference for singleton
    private static Authenticator self = new Authenticator();

    // Callback after login
    private OnLoginCallback onLogin;

    // Store authentication error message
    private String error = "";

    // Logged in user
    public User user = null;

    // Singleton constructor
    private Authenticator(){}

    public static Authenticator getInstance(){
        return self;
    }

    public void login(String username, String password, OnLoginCallback onLogin){
        
        // Store callback reference
        this.onLogin = onLogin;

        // Create and send login request
        HttpRequest request = new HttpRequest("POST", LOGIN_URI);
        /* 
            TODO: Add the necessary parameters to the request
            Add parameters using the following syntax:
                
                request.addParam("name", "value");
        */
        request.send(new OnRequestComplete() {
            
            @Override
            public void onComplete(HttpResponse response) {
                
                verify_login(response);
            
            }

        });

    }

    public void logout(){
        
        // Clear items on logout
        self.token.clear();
        self.session.clear();
        self.logged_in = false;

    }

    private void verify_login(HttpResponse response){
        
        // If response
        if(response != null) {

            // On success
            if (response.success) {
                
                // TODO: Add custom response validation

                // TODO: Set any necessary session variables: 
                // session.var("name", "value");

                // Get user data. Send token 
                String auth_token = "Temporary";
                set_user(auth_token);

                // Store auth_token in session 
                session.var("auth_token", auth_token);
                
            } else {

                // TODO: Handle when response.success is false
            
            }

        }
        else{
            process_error("Error Message Here", null);
        }

    }

    /*
     * Called when an error is caught
     */
    private void process_error(String message, HttpResponse response){
        
        // Store and log error message
        this.error = message;
        Log.d("Login Process Error", message);

        // Execute callback function
        this.onLogin.onComplete(this.error);
    }

    /*
     * Set our authetication token data
     */
    private void set_token(JSONObject content) throws JSONException {
        
        // Make sure Map is empty
        token.clear();
        
        // TODO: Store token data in this.token
    }

    /*
     * Get the logged_in user
     */
    private void set_user(String token) throws JSONException {
        
        // Request to get user data
        HttpRequest request = new HttpRequest(USER_DATA_URI);
        /*
            TODO: Add request headers for authenticated request

            request.addHeader("Authorization", token);
        */
        request.send(new OnRequestComplete() {
            
            @Override
            public void onComplete(HttpResponse response) {
                
                if(response.success) {
                    
                    // TODO: Add custom response validation
                    
                    try {
                        // Map user data and get new User model
                        self.user = new User().map(response.content);
                        if (self.user != null) {
                            self.logged_in = true;
                        } else {
                            process_error("Login failed with null user data.", response);
                        }

                        // Post Login. Execute Auth Callback
                        if(self.onLogin != null){
                            self.onLogin.onComplete(self.error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

        });
    }

}
