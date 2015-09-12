package io.github.jackmiras.nucleolus.presenters;

import io.github.jackmiras.nucleolus.models.User;
import io.github.jackmiras.nucleolus.presenter.Presenter;
import io.github.jackmiras.nucleolus.views.UserActivity;

/**
 * Created by jackmiras on 05/09/15.
 */
public class UsersPresenter extends Presenter<UserActivity>{

    public void saveUser(String name, String email, String password) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.password = password;
        user.save(); //This could be a save() method of a ORM or a custom save() method implementation.
    }
}
