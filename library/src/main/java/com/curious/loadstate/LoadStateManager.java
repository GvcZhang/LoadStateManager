package com.curious.loadstate;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.curious.loadstate.state.BaseState;
import com.curious.loadstate.state.SuccessState;

import java.util.ArrayList;
import java.util.List;

public class LoadStateManager {

    private Context mContext;
    private View mRootView;

    private LoadLayout mLoadLayout;


    private LoadStateManager(View rootView, Context context) {
        this.mContext = context;
        this.mRootView = rootView;
        mLoadLayout = new LoadLayout(mContext);
        mLoadLayout.setSuccessState(new SuccessState(this.mRootView, context));
    }


    public LoadLayout getLoadLayout() {

        return mLoadLayout;
    }

    public void showStateView(Class<? extends BaseState> clazz) {
        showStateView(clazz, null);
    }

    public void showStateView(Class<? extends BaseState> clazz, Bundle bundle) {
        mLoadLayout.showStateView(clazz, bundle);
    }

    public static class Builder {

        private List<BaseState> stateList = new ArrayList<>();
        private View mRootView;
        private Context mContext;

        public Builder addSupportState(BaseState state) {
            stateList.add(state);
            return this;
        }

        public Builder setRootView(View view, Context context) {
            this.mRootView = view;
            this.mContext = context;
            return this;
        }

        public Builder setContentView(int layoutId, Context context) {
            this.mContext = context;
            this.mRootView = LayoutInflater.from(context).inflate(layoutId, null);
            return this;
        }

        public LoadStateManager build() {
            LoadStateManager manager = new LoadStateManager(this.mRootView, this.mContext);
            if (!stateList.isEmpty()) {
                for (BaseState state : stateList) {
                    manager.getLoadLayout().addSateView(state);
                }
            }
            return manager;
        }
    }

}
