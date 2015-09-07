package io.github.jackmiras.nucleolus.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a base class for all presenters. Subclasses can override
 * {@link #onCreate}, {@link #onDestroy}, {@link #onSave},
 * {@link #onTakeView}, {@link #onDetachView}.
 * <p/>
 * {@link Presenter.OnDestroyListener} can also be used by external classes
 * to be notified about the need of freeing resources.
 *
 * @param <View> a type of view to return with {@link #getView()}.
 */

public class Presenter<View> {
    @Nullable
    private View view;
    private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList<>();
    private boolean isDetached;

    /**
     * This method is called after presenter construction.
     * <p/>
     * This method is intended for overriding.
     *
     * @param savedState If the presenter is being re-instantiated after a process restart then this Bundle
     *                   contains the data it supplied in {@link #onSave}.
     */
    protected void onCreate(@Nullable Bundle savedState) {
    }

    /**
     * This method is being called when a user leaves view.
     * <p/>
     * This method is intended for overriding.
     */
    protected void onDestroy() {
    }

    /**
     * A returned state is the state that will be passed to {@link #onCreate} for a new presenter instance after a process restart.
     * <p/>
     * This method is intended for overriding.
     *
     * @param state a non-null bundle which should be used to put presenter's state into.
     */
    protected void onSave(Bundle state) {
    }

    /**
     * This method is being called when a view gets attached to it.
     * Normally this happens during {@link Activity#onCreate(Bundle savedState)}, {@link Fragment#onCreate(Bundle savedState)}
     * and {@link android.view.View#onAttachedToWindow()}.
     * <p/>
     * This method is intended for overriding.
     *
     * @param view a view that should be taken
     */
    protected void onTakeView(View view) {
    }

    /**
     * This method is being called when a view gets detached from the presenter.
     * Normally this happens during {@link Activity#onPause()} ()}, {@link Fragment#onPause()} ()}
     * and {@link android.view.View#onDetachedFromWindow()}.
     * <p/>
     * This method is intended for overriding.
     */
    protected void onDetachView() {
    }

    /**
     * A callback to be invoked when a presenter is about to be destroyed.
     */
    public interface OnDestroyListener {
        /**
         * Called before {@link Presenter#onDestroy()}.
         */
        void onDestroy();
    }

    /**
     * Returns a current view attached to the presenter or null.
     *
     * @return a current attached view.
     */
    @Nullable
    public View getView() {
        return view;
    }

    /**
     * Returns a current activity attached to the presenter or null.
     *
     * @return a current attached activity.
     */
    @Nullable
    public Activity getActivity() {
        if (view instanceof Activity) {
            return (Activity) view;
        } else if (view instanceof android.support.v4.app.Fragment) {
            android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) view;
            return fragment.getActivity();
        } else {
            Fragment fragment = (Fragment) view;
            return fragment.getActivity();
        }
    }

    /**
     * Initializes the presenter.
     */
    public void create(Bundle bundle) {
        onCreate(bundle);
    }

    /**
     * Destroys the presenter, calling all {@link Presenter.OnDestroyListener} callbacks.
     */
    public void destroy() {
        for (OnDestroyListener listener : onDestroyListeners)
            listener.onDestroy();
        onDestroy();
    }

    /**
     * Return a value that inform if presenter was detached from view.
     */
    public boolean isDetached() {
        return isDetached;
    }

    /**
     * Saves the presenter.
     */
    public void save(Bundle state) {
        onSave(state);
    }

    /**
     * Attaches a view to the presenter.
     *
     * @param view a view to attach.
     */
    public void takeView(View view) {
        isDetached = false;
        this.view = view;
        onTakeView(view);
    }

    /**
     * Detaches the presenter from a view.
     */
    public void detachView() {
        isDetached = true;
        onDetachView();
        this.view = null;
    }
}
