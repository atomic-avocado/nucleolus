package io.github.jackmiras.nucleolus.helper;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.github.jackmiras.nucleolus.presenter.Presenter;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {
    Class<? extends Presenter> value();
}
