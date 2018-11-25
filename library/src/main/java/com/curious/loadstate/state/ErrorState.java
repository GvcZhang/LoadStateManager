package com.curious.loadstate.state;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.curious.loadstate.R;

public class ErrorState extends BaseState {

    private TextView mStateInfoTV;
    public static final String KEY = "state";
    //common error state code
    //network error
    public static final int STATE_NETWORK_ERROR = 1;
    //no data
    public static final int STATE_NO_DATA = 2;
    //timeout tip
    public static final int STATE_TIMEOUT = 3;
    //unknown error
    public static final int STATE_UNKNOWN = 4;
    //api server error
    public static final int STATE_SERVER = 4;

    public ErrorState(View rootView, Context context) {
        super(rootView, context);
    }

    public ErrorState(View rootView, Context context, OnStateEventListener listener) {
        super(rootView, context, listener);
    }

    @Override
    protected int onCreateView() {
        return R.layout.loadstate_error;
    }

    @Override
    protected void onViewCreated(Context context, View view, Bundle bundle) {
        mStateInfoTV = view.findViewById(R.id.state_info_tv);
        view.findViewById(R.id.reload_btn).setOnClickListener(v -> {
            OnStateEventListener listener = getStateEventListener();
            if (listener != null) {
                listener.onTrigger(ErrorState.this);
            }
        });
        onBundleChanged(bundle);
    }

    @Override
    public void onBundleChanged(Bundle bundle) {
        if (bundle != null) {
            int state = bundle.getInt(KEY, STATE_UNKNOWN);
            switch (state) {
                case STATE_NETWORK_ERROR:
                    mStateInfoTV.setText(R.string.state_network_error);
                    break;
                case STATE_NO_DATA:
                    mStateInfoTV.setText(R.string.state_no_data);
                    break;
                case STATE_TIMEOUT:
                    mStateInfoTV.setText(R.string.state_timeout);
                    break;
                case STATE_SERVER:
                    mStateInfoTV.setText(R.string.state_server);
                    break;
                default:
                    mStateInfoTV.setText(R.string.state_unknown);
                    break;
            }
        }
    }
}
