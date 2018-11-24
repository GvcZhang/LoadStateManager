package com.curious.loadstatemanager.state;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.curious.loadstatemanager.BaseState;
import com.curious.loadstatemanager.R;

public class LoadingState extends BaseState {

    @Override
    protected int onCreateView() {
        return R.layout.loadstate_loading;
    }

    @Override
    protected void onViewCreated(Context context, View view,Bundle bundle) {

    }
}
