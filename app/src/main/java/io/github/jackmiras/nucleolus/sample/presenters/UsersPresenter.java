package io.github.jackmiras.nucleolus.sample.presenters;


import io.github.jackmiras.nucleolus.presenter.Presenter;
import io.github.jackmiras.nucleolus.sample.models.User;
import io.github.jackmiras.nucleolus.sample.views.UserActivity;

/**
 * Created by jackmiras on 05/09/15.
 */
public class UsersPresenter extends Presenter<UserActivity> {

    public void saveUser(String name, String email, String password) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.password = password;
        user.save(getActivity()); //This could be a save() method of a ORM or a custom save() method implementation.
    }
}
