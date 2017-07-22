package com.hyuguideclash.cocgems;

import android.content.Context;

import com.flurry.android.FlurryAgent;

/**
 * Created by YouCaf Iqbal on 1/7/2017.
 */

public class FlurryAnalytics {
    public static void startSession(Context context) {
        FlurryAgent.onStartSession(context,"PPJW63VDP494JGKZ9JX9");
    }
    public static void stopSession(Context context) {
        FlurryAgent.onEndSession(context);
    }
}
