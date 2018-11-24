package com.curious.loadstatemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.curious.loadstatemanager.state.SuccessState;

public class LoadStateManager {

    private Context mContext;
    private View mRootView;

    private LoadLayout mLoadLayout;


    public LoadStateManager(View rootView, Context context) {
        this.mContext = context;
        this.mRootView = rootView;
        mLoadLayout = new LoadLayout(mContext);
        mLoadLayout.setSuccessState(new SuccessState(this.mRootView, context));
    }


    public View getLoadLayout() {

        return mLoadLayout;
    }

    public void showStateView(Class<? extends BaseState> clazz) {
        showStateView(clazz, null);
    }

    public void showStateView(Class<? extends BaseState> clazz, Bundle bundle) {
        mLoadLayout.showStateView(clazz, bundle);
    }

}
