package com.curious.loadstate;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.curious.loadstate.state.BaseState;
import com.curious.loadstate.state.SuccessState;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class LoadLayout extends FrameLayout {

    private Map<Class<? extends BaseState>, BaseState> cacheStateMap = new HashMap<>();
    private BaseState mCurState;

    public LoadLayout(Context context) {
        super(context);
    }

    public void setSuccessState(SuccessState successState) {
        this.mCurState = successState;
        addView(successState.getRootView());
        addSateView(successState);
    }

    public void addSateView(BaseState state) {
        cacheStateMap.put(state.getClass(), state);
    }

    public void showStateView(Class<? extends BaseState> clazz, Bundle bundle) {
        if (mCurState.getClass() == clazz) {
            return;
        }
        removeAllViews();
        BaseState state;
        if (cacheStateMap.get(clazz) == null) {
            try {
                Constructor constructor = clazz.getConstructor();
                state = (BaseState) constructor.newInstance();
                //the new state context is null, so set a context
                state.setStateView(null, getContext());
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Not support State: " + clazz + "--" + e.getMessage());
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Not support State: " + clazz + "--" + e.getMessage());
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("Not support State: " + clazz + "--" + e.getMessage());
            } catch (InstantiationException e) {
                throw new IllegalArgumentException("Not support State: " + clazz + "--" + e.getMessage());
            }
            addSateView(state);
        } else {
            state = cacheStateMap.get(clazz);
        }
        mCurState = state;
        addView(state.getRootView());
        if (bundle != null) {
            state.onBundleChanged(bundle);
        }
    }

    public void showStateView(Class<? extends BaseState> clazz) {
        showStateView(clazz, null);
    }

}
