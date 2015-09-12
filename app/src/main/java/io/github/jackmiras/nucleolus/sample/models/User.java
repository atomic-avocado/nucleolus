package io.github.jackmiras.nucleolus.sample.models;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jackmiras on 12/09/15.
 */
public class User {
    public String name;
    public String email;
    public String password;

    public void save(Context context) {
        Toast.makeText(context, "User saved", Toast.LENGTH_LONG).show();
    }
}
