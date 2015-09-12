package io.github.jackmiras.nucleolus.factories;

import io.github.jackmiras.nucleolus.presenters.Presenter;

public interface PresenterFactory<P extends Presenter> {
    P createPresenter();
}
