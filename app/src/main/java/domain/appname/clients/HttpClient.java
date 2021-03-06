/*
 * Created by Brett Brist April 2017
 * https://github.com/3brettb/android_design_framework_auth
 */

/*
    This is a mature class. Do not alter the contents of this class.
    If its necessary to create different functionality, extend this class.

    This class handles HTTP requests
 */

package domain.appname.clients;

import domain.appname.facades.auth.Auth;
import domain.appname.facades.http.HttpRequest;
import domain.appname.facades.http.RequestHeader;
import domain.appname.facades.http.RequestParameter;
import domain.appname.facades.http.RequestSender;
import domain.appname.callbacks.OnRequestComplete;

public class HttpClient
{

    // Singleton reference
    private static HttpClient self = new HttpClient();

    // Singleton constructor
    private HttpClient(){}

    public static HttpClient getInstance(){
        return self;
    }

    /*
     * Get Requests
     */

    public void get(String uri, OnRequestComplete listener){
        send(new HttpRequest(uri), listener);
    }

    public void get(String uri, RequestHeader[] headers, OnRequestComplete listener){
        send(new HttpRequest(uri, headers), listener);
    }

    public void get(String uri, RequestParameter[] params, OnRequestComplete listener){
        send(new HttpRequest(uri, params), listener);
    }

    public void get(String uri, RequestHeader[] headers, RequestParameter[] params, OnRequestComplete listener){
        send(new HttpRequest(uri, headers, params), listener);
    }

    public void get(HttpRequest request, OnRequestComplete listener){
        send(request, listener);
    }

    /*
     * Get Requests with Auth
     */

    public void get(String uri, Auth auth, OnRequestComplete listener){
        send(new HttpRequest(uri, auth), listener);
    }

    public void get(String uri, RequestHeader[] headers, Auth auth, OnRequestComplete listener){
        send(new HttpRequest(uri, headers, auth), listener);
    }

    public void get(String uri, RequestParameter[] params, Auth auth, OnRequestComplete listener){
        send(new HttpRequest(uri, params, auth), listener);
    }

    public void get(String uri, RequestHeader[] headers, RequestParameter[] params, Auth auth, OnRequestComplete listener){
        send(new HttpRequest(uri, headers, params, auth), listener);
    }

    public void get(HttpRequest request, Auth auth, OnRequestComplete listener){
        send(request, auth, listener);
    }

    /*
     * Post Requests
     */

    public void post(HttpRequest request, OnRequestComplete listener){
        send(request, listener);
    }

    public void post(String uri, OnRequestComplete listener){
        send(new HttpRequest("POST", uri), listener);
    }

    public void post(String uri, RequestHeader[] headers, OnRequestComplete listener){
        send(new HttpRequest("POST", uri, headers), listener);
    }

    public void post(String uri, RequestParameter[] params, OnRequestComplete listener){
        send(new HttpRequest("POST", uri, params), listener);
    }

    public void post(String uri, RequestHeader[] headers, RequestParameter[] params, OnRequestComplete listener){
        send(new HttpRequest("POST", uri, headers, params), listener);
    }

    /*
     * Post Requests with Auth
     */

    public void post(HttpRequest request, Auth auth, OnRequestComplete listener){
        send(request, auth, listener);
    }

    public void post(String uri, Auth auth, OnRequestComplete listener){
        send(new HttpRequest("POST", uri, auth), listener);
    }

    public void post(String uri, RequestHeader[] headers, Auth auth, OnRequestComplete listener){
        send(new HttpRequest("POST", uri, headers, auth), listener);
    }

    public void post(String uri, RequestParameter[] params, Auth auth, OnRequestComplete listener){
        send(new HttpRequest("POST", uri, params, auth), listener);
    }

    public void post(String uri, RequestHeader[] headers, RequestParameter[] params, Auth auth, OnRequestComplete listener){
        send(new HttpRequest("POST", uri, headers, params, auth), listener);
    }

    /*
     * Process Request
     */

    public void send(HttpRequest request, OnRequestComplete listener){
        new RequestSender(request, listener).execute();
    }

    public void send(HttpRequest request, Auth auth, OnRequestComplete listener){
        request.addAuth(auth);
        send(request, listener);
    }

}