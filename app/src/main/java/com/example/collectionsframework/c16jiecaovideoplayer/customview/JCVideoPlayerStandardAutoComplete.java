package com.example.collectionsframework.c16jiecaovideoplayer.customview;

import android.content.Context;
import android.util.AttributeSet;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class JCVideoPlayerStandardAutoComplete extends JCVideoPlayerStandard {
    public JCVideoPlayerStandardAutoComplete(Context context) {
        super(context);
    }

    public JCVideoPlayerStandardAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAutoCompletion() {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            setUiWitStateAndScreen(CURRENT_STATE_AUTO_COMPLETE);
        } else {
            super.onAutoCompletion();
        }

    }
}
