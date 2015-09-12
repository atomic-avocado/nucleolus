package io.github.jackmiras.nucleolus.helpers;

import android.os.Bundle;

import io.github.jackmiras.nucleolus.factories.PresenterFactory;
import io.github.jackmiras.nucleolus.managers.PresenterManager;
import io.github.jackmiras.nucleolus.presenters.Presenter;

public class NucleolusPresenterHelper<PresenterType extends Presenter> {

    private PresenterFactory<PresenterType> presenterFactory;
    private Bundle presenterState;

    private PresenterType presenter;

    public NucleolusPresenterHelper(PresenterFactory<PresenterType> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    public void setPresenterState(Bundle presenterState) {
        if (presenter != null)
            throw new IllegalArgumentException("setPresenterState() should be called before getPresenter() and before onTakeView()");
        this.presenterState = presenterState;
    }

    public PresenterType getPresenter() {
        if (presenter == null && presenterFactory != null) {
            presenter = PresenterManager.getInstance().provide(presenterFactory, presenterState);
            presenterState = null;
        }
        return presenter;
    }

    /**
     * Destroys a presenter that is currently attached to the View.
     */
    public void destroyPresenter() {
        if (presenter != null) {
            PresenterManager.getInstance().destroy(presenter);
            presenter = null;
        }
    }

    public Bundle savePresenter() {
        return presenter == null ? null : PresenterManager.getInstance().save(presenter);
    }

    public void takeView(Object view) {
        getPresenter();
        if (presenter != null)
            //noinspection unchecked
            presenter.takeView(view);
    }

    public void dropView(boolean destroy) {
        if (presenter != null)
            presenter.detachView();
        if (destroy)
            destroyPresenter();
    }


}