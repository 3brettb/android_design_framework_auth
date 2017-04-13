/*
    This is a mature class. Do not alter the contents of this class.
    If its necessary to create different functionality, extend this class.

    This class is used to store token data to perform authenticated http requests
 */

package domain.appname.facades.auth;

import java.util.Stack;

import domain.appname.auth.Authenticator;
import domain.appname.clients.SessionClient;
import domain.appname.facades.http.RequestHeader;
import domain.appname.facades.http.RequestParameter;

public class Auth
{

    private Stack<RequestHeader> headers = new Stack<>();

    private Stack<RequestParameter> params = new Stack<>();

    private static Authenticator auth = Authenticator.getInstance();

    private static SessionClient session = SessionClient.getInstance();

    public Auth(){}

    private boolean getAuthenticated(){
        if(auth.logged_in) {
            addHeader("Authorization", session.var("auth_token"));
            addHeader("Accept", "application/json");
            return true;
        }
        return false;
    }

    public RequestHeader[] getHeaders(){
        if(getAuthenticated()) {
            RequestHeader[] out = new RequestHeader[headers.size()];
            int i = 0;
            for (RequestHeader h : headers) {
                out[i] = h;
                i++;
            }
            return out;
        }
        return new RequestHeader[0];
    }

    public RequestParameter[] getParams(){
        if(getAuthenticated()) {
            RequestParameter[] out = new RequestParameter[params.size()];
            int i = 0;
            for(RequestParameter p : params){
                out[i] = p;
                i++;
            }
            return out;
        }
        return new RequestParameter[0];
    }

    public void addHeader(RequestHeader header){
        this.headers.push(header);
    }

    public void addHeader(String key, String value){
        addHeader(new RequestHeader(key, value));
    }

    public void addParameter(RequestParameter parameter){
        this.params.push(parameter);
    }

    public void addParameter(String key, String value){
        addParameter(new RequestParameter(key, value));
    }

}