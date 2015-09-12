package io.github.jackmiras.nucleolus.managers;

import android.os.Bundle;
import android.util.Printer;

import io.github.jackmiras.nucleolus.factories.PresenterFactory;
import io.github.jackmiras.nucleolus.presenters.Presenter;

public abstract class PresenterManager {
    private static PresenterManager instance = new DefaultPresenterManager();

    /**
     * Returns a singleton instance of {@link PresenterManager}
     *
     * @return a singleton instance of {@link PresenterManager}
     */
    public static PresenterManager getInstance() {
        return instance;
    }

    /**
     * This is a testing facility.
     * Use this method to set a custom {@link PresenterManager} instance.
     *
     * @param instance a {@link PresenterManager} instance to set.
     */
    public static void setInstance(PresenterManager instance) {
        PresenterManager.instance = instance;
    }

    /**
     * Finds a Presenter for a given view or restores it from the saved state.
     * There can be three cases when this method is being called:
     * 1. First creation of a view;
     * 2. Restoring of a view when the process has NOT been destroyed (configuration change, other activity recreation cases);
     * 3. Restoring of a view when entire process has been destroyed.
     * <p/>
     *
     * @return a found or created presenter.
     */
    public abstract <T extends Presenter> T provide(PresenterFactory<T> presenterFactory, Bundle savedState);

    /**
     * Creates a bundle that can be used to re-instantiate a presenter. Pass this bundle to {@link #provide}.
     *
     * @param presenter a presenter to obtain restoration bundle for.
     * @return a Bundle that can be used to re-instantiate a presenter.
     */
    public abstract Bundle save(Presenter presenter);

    /**
     * Destroys a presenter, removing all references to it.
     *
     * @param presenter a presenter to destroy.
     */
    public abstract void destroy(Presenter presenter);

    /**
     * Prints a list of presenters and attached views.
     *
     * @param printer a target for printing.
     */
    public abstract void print(Printer printer);
}
