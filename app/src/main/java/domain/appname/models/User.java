package uwyohonors.honorsapp.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import uwyohonors.honorsapp.helpers.Helpers;
import uwyohonors.honorsapp.interfaces.Model;

public class User implements Model {

    public int id;

    public String username;

    public String firstname;

    public String lastname;

    public Date dob;

    public boolean international;

    public String email;

    public String cell;

    public String wnumber;

    public User(){}

    @Override
    public User map(JSONObject reference) throws JSONException {
        this.id = reference.getInt("id");
        this.username = reference.getString("username");
        this.firstname = reference.getString("firstname");
        this.lastname = reference.getString("lastname");
        this.dob = Helpers.stringToDate(reference.getString("dob"), "yyyy-MM-dd");
        this.international = (reference.getString("international") == "1") ? true : false;
        this.email = reference.getString("email");
        this.cell = reference.getString("cell");
        this.wnumber = reference.getString("wnumber");
        return this;
    }
}
