package com.curious.loadstate.state;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class SuccessState extends BaseState {


    public SuccessState(View rootView, Context context) {
        super(rootView, context);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }

    @Override
    protected void onViewCreated(Context context, View view, Bundle bundle) {

    }
}
