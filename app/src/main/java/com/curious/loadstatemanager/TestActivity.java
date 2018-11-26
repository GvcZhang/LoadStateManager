package com.curious.loadstatemanager;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import com.curious.loadstate.LoadStateManager;
import com.curious.loadstate.state.*;

public class TestActivity extends AppCompatActivity {

    private LoadStateManager mLoadStateManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadStateManager = new LoadStateManager.Builder()
                .setContentView(R.layout.activity_test,this)
                .addSupportState(new ErrorState(null, this, new OnStateEventListener() {
                    @Override
                    public void onTrigger(BaseState state, Object... args) {
                        mLoadStateManager.showStateView(SuccessState.class);
                    }
                }))
                .build();
        setContentView(mLoadStateManager.getLoadLayout());

        mLoadStateManager.showStateView(LoadingState.class);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ErrorState.KEY, ErrorState.STATE_NETWORK_ERROR);
            mLoadStateManager.showStateView(ErrorState.class, bundle);
        }, 3000);
    }


}
