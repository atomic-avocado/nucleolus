package io.github.jackmiras.nucleolus.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import io.github.jackmiras.nucleolus.factories.PresenterFactory;
import io.github.jackmiras.nucleolus.factories.ReflectionPresenterFactory;
import io.github.jackmiras.nucleolus.helpers.NucleolusPresenterHelper;
import io.github.jackmiras.nucleolus.presenters.Presenter;

public abstract class NucleolusActivity<PresenterType extends Presenter> extends Activity {

    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private NucleolusPresenterHelper<PresenterType> helper = new NucleolusPresenterHelper<>(getPresenterFactory());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            helper.setPresenterState(savedInstanceState.getBundle(PRESENTER_STATE_KEY));
        helper.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (helper.getPresenter().getView() == null)
            helper.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing())
            destroyPresenter();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, helper.savePresenter());
    }

    @Override
    protected void onPause() {
        super.onPause();
        helper.dropView(isFinishing());
    }

    // The following section can be copy & pasted into any View class, just update their description if needed.

    /**
     * The factory class used to create the presenter. Defaults to {@link ReflectionPresenterFactory} to create the presenter
     * using a no arg constructor.
     * <p/>
     * Subclasses can override this to provide presenters in other ways, like via their dependency injector.
     *
     * @return The {@link PresenterFactory} that can build a {@link Presenter}, or null.
     */
    public PresenterFactory<PresenterType> getPresenterFactory() {
        return ReflectionPresenterFactory.fromViewClass(getClass());
    }

    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onResume/onPause and onAttachedToWindow/onDetachedFromWindow calls
     * if the presenter factory returns a non-null value.
     *
     * @return a currently attached presenter or null.
     */
    public PresenterType getPresenter() {
        return helper.getPresenter();
    }

    /**
     * Destroys a presenter that is currently attached to the View.
     */
    public void destroyPresenter() {
        helper.destroyPresenter();
    }
}