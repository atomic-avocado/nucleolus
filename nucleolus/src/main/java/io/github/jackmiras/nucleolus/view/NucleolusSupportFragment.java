package io.github.jackmiras.nucleolus.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.github.jackmiras.nucleolus.factory.PresenterFactory;
import io.github.jackmiras.nucleolus.factory.ReflectionPresenterFactory;
import io.github.jackmiras.nucleolus.helper.NucleolusPresenterHelper;
import io.github.jackmiras.nucleolus.presenter.Presenter;

public abstract class NucleolusSupportFragment<PresenterType extends Presenter> extends Fragment {

    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private NucleolusPresenterHelper<PresenterType> helper = new NucleolusPresenterHelper(this.getPresenterFactory());

    public NucleolusSupportFragment() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.helper.setPresenterState(bundle.getBundle(PRESENTER_STATE_KEY));
        }
        this.helper.takeView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (helper.getPresenter().getView() == null)
            helper.takeView(this);
    }


    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle(PRESENTER_STATE_KEY, this.helper.savePresenter());
    }

    public void onPause() {
        super.onPause();
        this.helper.dropView(this.getActivity().isFinishing());
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
        return ReflectionPresenterFactory.fromViewClass(this.getClass());
    }

    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onCreate/onPause and onAttachedToWindow/onDetachedFromWindow calls
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
