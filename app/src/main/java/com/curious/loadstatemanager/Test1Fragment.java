package com.curious.loadstatemanager;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.curious.loadstate.LoadStateManager;
import com.curious.loadstate.state.BaseState;
import com.curious.loadstate.state.ErrorState;
import com.curious.loadstate.state.LoadingState;
import com.curious.loadstate.state.OnStateEventListener;
import com.curious.loadstate.state.SuccessState;


public class Test1Fragment extends BaseFragment {

    private LoadStateManager loadStateManager;
    private Button mBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test1, container, false);
        mBtn = rootView.findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "2222", Toast.LENGTH_LONG).show();
            }
        });
        loadStateManager = new LoadStateManager.Builder()
                .setContext(getContext())
                .setRootView(rootView)
                .addSupportState(new ErrorState(null, getContext(), new OnStateEventListener() {
                    @Override
                    public void onTrigger(BaseState state, Object... args) {
                        loadStateManager.showStateView(SuccessState.class);
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
