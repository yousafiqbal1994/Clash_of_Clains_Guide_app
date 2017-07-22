package com.hyuguideclash.cocgems;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;

/**
 * Created by YouCaf Iqbal on 1/7/2017.
 */

public class StratergiesActivity extends AppCompatActivity {
    // get the VunglePub instance
    final VunglePub vunglePub = VunglePub.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stratergies);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getBaseContext().getResources().getString(R.string.strategiesdetails));
        // Vungle Ads
        final String app_id = "5877abe3d55c5d8b6700042c";
        vunglePub.init(this, app_id);
        vunglePub.setEventListeners(vungleDefaultListener, vungleSecondListener);
        vunglePub.playAd();
        // Flurry Analytics
        //  Logging the analytics
        FlurryAgent.setLogEnabled(true);
        FlurryAgent.setLogEvents(true);
        FlurryAgent.init(this, "PPJW63VDP494JGKZ9JX9");

    }


    @Override
    protected void onStart() {
        super.onStart();
        FlurryAnalytics.startSession(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FlurryAnalytics.stopSession(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        vunglePub.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vunglePub.onResume();
    }
    private final EventListener vungleDefaultListener = new EventListener() {
        @Deprecated
        @Override
        public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis) {
            // This method is deprecated and will be removed. Please use onAdEnd() instead.
            Log.e("ads"," onVideoView vungleDefaultListener ");
        }

        @Override
        public void onAdStart() {
            // Called before playing an ad.
            Log.e("ads"," onAdStart vungleDefaultListener ");
        }

        @Override
        public void onAdUnavailable(String reason) {
            // Called when VunglePub.playAd() was called but no ad is available to show to the user.
            Log.e("ads"," onAdUnavailable vungleDefaultListener because "+ reason);
        }

        @Override
        public void onAdEnd(boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            // Called when the user leaves the ad and control is returned to your application.
            Log.e("ads"," onAdEnd vungleDefaultListener ");
        }

        @Override
        public void onAdPlayableChanged(boolean isAdPlayable) {
            // Called when ad playability changes.
            Log.e("ads", "This is a default eventlistener.");
            Log.e("ads"," onAdPlayableChanged vungleDefaultListener ");
            final boolean enabled = isAdPlayable;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Called when ad playability changes.

                }
            });
        }
    };

    private final EventListener vungleSecondListener = new EventListener() {
        // Vungle SDK allows for multiple listeners to be attached. This secondary event listener is only
        // going to print some logs for now, but it could be used to Pause music, update a badge icon, etc.
        @Deprecated
        @Override
        public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis) {
            Log.e("ads", "onVideoView vungleSecondListener.");
        }

        @Override
        public void onAdStart() {
            Log.e("ads", "onAdStart vungleSecondListener.");

        }

        @Override
        public void onAdUnavailable(String reason) {
            Log.e("ads", "onAdUnavailable vungleSecondListener because "+ reason);
        }

        @Override
        public void onAdEnd(boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            Log.e("ads", "onAdEnd vungleSecondListener.");
        }

        @Override
        public void onAdPlayableChanged(boolean isAdPlayable) {
            Log.e("ads", String.format("This is a second event listener! Ad playability has changed, and is now: %s", isAdPlayable));
            Log.e("ads", "onAdPlayableChanged vungleSecondListener.");
        }
    };

    @Override
    protected void onDestroy() {
        // onDestroy(), remove eventlisteners.
        vunglePub.removeEventListeners(vungleDefaultListener, vungleSecondListener);
        super.onDestroy();
    }
}
