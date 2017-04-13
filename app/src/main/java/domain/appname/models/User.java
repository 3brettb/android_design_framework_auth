/*
 * Created by Brett Brist April 2017
 * https://github.com/3brettb/android_design_framework_auth
 */
package uwyohonors.honorsapp.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import uwyohonors.honorsapp.helpers.Helpers;
import uwyohonors.honorsapp.interfaces.Model;

public class User implements Model {

    public int id;

    /*
     * Define the class variables for the model
     */

    public User(){}

    @Override
    public User map(JSONObject reference) throws JSONException {
        /*
         * Map the values to the class variables defined above
         */
        return this;
    }
}
