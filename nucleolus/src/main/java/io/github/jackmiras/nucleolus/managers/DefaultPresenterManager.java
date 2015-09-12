package io.github.jackmiras.nucleolus.managers;

import android.os.Bundle;
import android.util.Printer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.github.jackmiras.nucleolus.factories.PresenterFactory;
import io.github.jackmiras.nucleolus.presenters.Presenter;

public class DefaultPresenterManager extends PresenterManager {
    private static final String PRESENTER_ID_KEY = "id";
    private static final String PRESENTER_STATE_KEY = "state";
    private HashMap<String, Presenter> idToPresenter = new HashMap();
    private HashMap<Presenter, String> presenterToId = new HashMap();
    private int nextId;

    public DefaultPresenterManager() {
    }

    public <T extends Presenter> T provide(PresenterFactory<T> presenterFactory, Bundle savedState) {
        String id = this.providePresenterId(savedState);
        if (this.idToPresenter.containsKey(id)) {
            return (T) this.idToPresenter.get(id);
        } else {
            Presenter presenter = presenterFactory.createPresenter();
            this.idToPresenter.put(id, presenter);
            this.presenterToId.put(presenter, id);
            presenter.create(savedState == null ? null : savedState.getBundle(PRESENTER_STATE_KEY));
            return (T) presenter;
        }
    }

    public Bundle save(Presenter presenter) {
        Bundle bundle = new Bundle();
        bundle.putString(PRESENTER_ID_KEY, this.presenterToId.get(presenter));
        Bundle presenterState = new Bundle();
        presenter.save(presenterState);
        bundle.putBundle(PRESENTER_STATE_KEY, presenterState);
        return bundle;
    }

    public void destroy(Presenter presenter) {
        presenter.destroy();
        this.idToPresenter.remove(this.presenterToId.remove(presenter));
    }

    public void print(Printer printer) {
        Iterator i$ = this.idToPresenter.entrySet().iterator();

        while (i$.hasNext()) {
            Map.Entry entry = (Map.Entry) i$.next();
            Object view = ((Presenter) entry.getValue()).getView();
            printer.println("id: " + entry.getKey() + (view == null ? "" : " => view: " + view.toString()));
        }

    }

    private String providePresenterId(Bundle savedState) {
        return savedState != null ? savedState.getString(PRESENTER_ID_KEY) : "" + this.nextId++ + "/" + System.nanoTime() + "/" + (int) (Math.random() * 2.147483647E9D);
    }
}