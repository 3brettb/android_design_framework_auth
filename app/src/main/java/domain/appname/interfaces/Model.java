/*
    This is a mature interface. Do not alter the contents of this interface.
 */

package domain.appname.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface Model {

    Model map(JSONObject reference) throws JSONException;

}
