/*
 * Created by Brett Brist April 2017
 * https://github.com/3brettb/android_design_framework_auth
 */

/*
    This is a mature class. Do not alter the contents of this class.
    If its necessary to create different functionality, extend this class.

    This class handles user sessions.
 */

package domain.appname.clients;

import java.util.Date;
import java.util.HashMap;

import domain.appname.preferences.ApplicationSettings;

public class SessionClient
{

    private HashMap<String, String> variables = new HashMap<>();

    private Date start_time;

    private Date timeout_time;

    private boolean active = false;

    private static final String DEFAULT_TIMEOUT = ApplicationSettings.DEFAULT_TIMEOUT_MINUTES;

    private static final long ONE_MINUTE_IN_MILLIS = 60000;

    private static SessionClient self = new SessionClient();

    private SessionClient(){}

    public static SessionClient getInstance(){
        return self;
    }

    public void var(String key, String value){
        variables.put(key, value);
    }

    public String var(String key){
        return variables.get(key);
    }

    public void start(){
        start(Integer.parseInt(DEFAULT_TIMEOUT));
    }

    public void start(int timeout){
        active = true;
        start_time = new Date();
        timeout_time = new Date(start_time.getTime() + (timeout * ONE_MINUTE_IN_MILLIS));
    }

    public void refresh(){
        start();
    }

    public void clear(){
        variables.clear();
        start_time = null;
        timeout_time = null;
        active = false;
    }

    public void clearAndStart(){
        clear();
        start();
    }

    public boolean active(){
        if(!active){return false;}
        if(timeout_time.compareTo(new Date()) < 0){
            this.active = false;
            return false;
        }
        return true;
    }

}