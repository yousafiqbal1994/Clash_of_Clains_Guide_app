package com.hyuguideclash.cocgems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flurry.android.FlurryAgent;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * Created by YouCaf Iqbal on 1/7/2017.
 */

public class CategoriesList extends AppCompatActivity {

    FancyButton aboutButton,howtoplayButton,newsButton,tipsButton,strategiesButton,morecoolappsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        aboutButton = (FancyButton) findViewById(R.id.about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesList.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        howtoplayButton = (FancyButton) findViewById(R.id.howtoplay);
        howtoplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesList.this,
                        HowToPlayActivity.class);
                startActivity(intent);
            }
        });
        newsButton = (FancyButton) findViewById(R.id.news);
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesList.this,
                        NewsActivity.class);
                startActivity(intent);
            }
        });
        tipsButton = (FancyButton) findViewById(R.id.tips);
        tipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesList.this,
                        TipsActivity.class);
                startActivity(intent);
            }
        });
        strategiesButton = (FancyButton) findViewById(R.id.strategies);
        strategiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesList.this,
                        StratergiesActivity.class);
                startActivity(intent);
            }
        });
        morecoolappsButton = (FancyButton) findViewById(R.id.morecoolapps);
        morecoolappsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesList.this,
                        MoreCoolAppsActivity.class);
                startActivity(intent);
            }
        });
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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
