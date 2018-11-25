package com.curious.loadstate.state;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class BaseState {

    private View rootView;
    private Context context;
    private Bundle bundle;
    private OnStateEventListener stateEventListener;

    public BaseState() {
    }

    public BaseState(View rootView, Context context) {
        this(rootView, context, null);
    }

    public BaseState(View rootView, Context context, OnStateEventListener listener) {
        this.rootView = rootView;
        this.context = context;
        this.stateEventListener = listener;
    }

    public void setStateView(View view, Context context) {
        this.rootView = view;
        this.context = context;
    }

    public void setStateEventListener(OnStateEventListener listener) {
        this.stateEventListener = listener;
    }

    public OnStateEventListener getStateEventListener() {
        return this.stateEventListener;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    /**
     * transfer data to state view
     *
     * @param bundle
     */
    public void onBundleChanged(Bundle bundle) {
    }

    protected abstract int onCreateView();

    protected abstract void onViewCreated(Context context, View view, Bundle bundle);

    public View getRootView() {
        if (onCreateView() == 0 && rootView != null) {
            return rootView;
        }
        if (onCreateView() <= 0) {
            throw new IllegalArgumentException("onCreateView resource id is empty");
        }
        rootView = View.inflate(context, onCreateView(), null);
        onViewCreated(context, rootView, bundle);
        return rootView;
    }


}
