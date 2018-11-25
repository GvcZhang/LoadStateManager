package com.curious.loadstatemanager;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.curious.loadstatemanager.state.BaseState;
import com.curious.loadstatemanager.state.ErrorState;
import com.curious.loadstatemanager.state.LoadingState;
import com.curious.loadstatemanager.state.OnStateEventListener;

public class Test1Fragment extends BaseFragment {

    private LoadStateManager loadStateManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test1, container, false);

        loadStateManager = new LoadStateManager.Builder()
                .setContext(getContext())
                .setRootView(rootView)
                .addSupportState(new ErrorState(null, getContext(), new OnStateEventListener() {
                    @Override
                    public void onTrigger(BaseState state, Object... args) {
                        Toast.makeText(getContext(), "ErrorState Trigger", Toast.LENGTH_LONG).show();
                    }
                }))
                .build();

        return loadStateManager.getLoadLayout();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Handler handler = new Handler();
        loadStateManager.showStateView(LoadingState.class);
        handler.postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ErrorState.KEY, ErrorState.STATE_NETWORK_ERROR);
            loadStateManager.showStateView(ErrorState.class, bundle);
        }, 3000);

//        handler.postDelayed(() -> {
//            Bundle bundle = new Bundle();
//            bundle.putInt(ErrorState.KEY, ErrorState.STATE_SERVER);
//            loadStateManager.showStateView(ErrorState.class, bundle);
//        }, 5000);
    }
}
