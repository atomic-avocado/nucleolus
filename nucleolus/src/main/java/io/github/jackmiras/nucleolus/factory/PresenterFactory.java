package io.github.jackmiras.nucleolus.factory;

import io.github.jackmiras.nucleolus.presenter.Presenter;

public interface PresenterFactory<P extends Presenter> {
    P createPresenter();
}
