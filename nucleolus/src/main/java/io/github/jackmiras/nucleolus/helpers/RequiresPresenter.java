package io.github.jackmiras.nucleolus.helpers;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.github.jackmiras.nucleolus.presenters.Presenter;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {
    Class<? extends Presenter> value();
}
