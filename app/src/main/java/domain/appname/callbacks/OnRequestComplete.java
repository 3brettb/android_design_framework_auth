/*
    This is a mature interface. Do not alter the contents of this interface.
 */

package domain.appname.callbacks;

import domain.appname.facades.http.HttpResponse;

public interface OnRequestComplete {
    void onComplete(HttpResponse response);
}
