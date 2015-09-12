package io.github.jackmiras.nucleolus.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.github.jackmiras.nucleolus.R;
import io.github.jackmiras.nucleolus.helper.RequiresPresenter;
import io.github.jackmiras.nucleolus.presenters.UsersPresenter;
import io.github.jackmiras.nucleolus.view.NucleolusAppCompatActivity;

/**
 * Created by jackmiras on 05/09/15.
 */
@RequiresPresenter(UsersPresenter.class)
public class UserActivity extends NucleolusAppCompatActivity<UsersPresenter> implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        findViewsById();

        Button button = (Button) findViewById(R.id.button_save);
        button.setOnClickListener(this);
    }

    private void findViewsById() {
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);
    }

    @Override
    public void onClick(View v) {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        getPresenter().saveUser(name, email, password);
    }
}
