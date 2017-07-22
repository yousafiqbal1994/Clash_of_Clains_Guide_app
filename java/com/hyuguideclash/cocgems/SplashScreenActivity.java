package com.hyuguideclash.cocgems;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flurry.android.FlurryAgent;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.interstitial.RevMobFullscreen;

import mehdi.sakout.fancybuttons.FancyButton;


public class SplashScreenActivity extends AppCompatActivity {

    RevMob revmob;
    Activity currentActivity; // for anonymous classes
    RevMobFullscreen fullscreen;
    FancyButton startButton;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.splashscreen);
        currentActivity = this;
        startRevMob();
        startButton = (FancyButton) findViewById(R.id.btn_start);
        startButton.setFontIconSize(20);
        startButton.setTextSize(20);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreenActivity.this, CategoriesList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                showLoadedFullscreen();
                startActivity(intent);
            }
        });
        //  Logging the analytics
        FlurryAgent.setLogEnabled(true);
        FlurryAgent.setLogEvents(true);
        FlurryAgent.init(this, "PPJW63VDP494JGKZ9JX9");
        StartAnimations();
    }

    private void startRevMob() {

        revmob = RevMob.startWithListener(currentActivity, new RevMobAdsListener() {
            @Override
            public void onRevMobSessionStarted() {
                loadFullscreen();
            }
            @Override
            public void onRevMobSessionNotStarted(String message) {
            }
        },"5873cc1534d2cab509bc9f51");

    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);
        startButton.clearAnimation();
        startButton.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 4500) {
                        sleep(100);
                        waited += 100;
                    }
                    // Show permission boxes

                    showPermissionDialogs();
                } catch (InterruptedException e) {
                    // do nothing
                }

            }
        };
        splashTread.start();

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
    protected void onResume()
    {
        super.onResume();
    }



    public void loadFullscreen() {
        runOnAnotherThread(new Runnable() { @Override
        public void run() { fullscreen = revmob.createFullscreen(currentActivity, new RevMobAdsListener() {

            @Override
            public void onRevMobAdReceived() {
            }

            @Override
            public void onRevMobAdNotReceived(String message) {
            }

            @Override
            public void onRevMobAdDismissed() {
            }

            @Override
            public void onRevMobAdClicked() {
            }

            @Override
            public void onRevMobAdDisplayed() {
            }

        });
        }});
    }

    public void showLoadedFullscreen() {
        runOnAnotherThread(new Runnable() {
            @Override
            public void run() {
                if (fullscreen != null) {
                    fullscreen.show();
                }
            } });
    }

    // Auxiliar methods

    void runOnAnotherThread(Runnable action) {
        new Thread(action).start();
    }


    private void showPermissionDialogs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            if((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED))
            {
                ActivityCompat.requestPermissions(SplashScreenActivity.this,new String[]{"android.permission.ACCESS_FINE_LOCATION" }, 2);
            }
            if((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
            {
                ActivityCompat.requestPermissions(SplashScreenActivity.this,new String[]{"android.permission.WRITE_EXTERNAL_STORAGE" }, 3);
            }
        }
    }
}
