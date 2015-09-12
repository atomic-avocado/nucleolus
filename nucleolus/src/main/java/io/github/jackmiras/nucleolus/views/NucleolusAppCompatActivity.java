package io.github.jackmiras.nucleolus.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.github.jackmiras.nucleolus.factory.PresenterFactory;
import io.github.jackmiras.nucleolus.factory.ReflectionPresenterFactory;
import io.github.jackmiras.nucleolus.helper.NucleolusPresenterHelper;
import io.github.jackmiras.nucleolus.presenter.Presenter;

public abstract class NucleolusAppCompatActivity<PresenterType extends Presenter> extends AppCompatActivity {
    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private NucleolusPresenterHelper<PresenterType> helper = new NucleolusPresenterHelper(this.getPresenterFactory());

    public NucleolusAppCompatActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.helper.setPresenterState(savedInstanceState.getBundle(PRESENTER_STATE_KEY));
        }
        this.helper.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (helper.getPresenter().getView() == null)
            helper.takeView(this);
    }


    protected void onDestroy() {
        super.onDestroy();
        if (this.isFinishing()) {
            this.destroyPresenter();
        }

    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, this.helper.savePresenter());
    }

    protected void onPause() {
        super.onPause();
        this.helper.dropView(this.isFinishing());
    }

    /**
     * The factory class used to create the presenter. Defaults to {@link ReflectionPresenterFactory} to create the presenter
     * using a no arg constructor.
     * <p/>
     * Subclasses can override this to provide presenters in other ways, like via their dependency injector.
     *
     * @return The {@link PresenterFactory} that can build a {@link Presenter}, or null.
     */
    public PresenterFactory<PresenterType> getPresenterFactory() {
        return ReflectionPresenterFactory.fromViewClass(this.getClass());
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
        return this.helper.getPresenter();
    }

    /**
     * Destroys a presenter that is currently attached to the View.
     */
    public void destroyPresenter() {
        this.helper.destroyPresenter();
    }
}