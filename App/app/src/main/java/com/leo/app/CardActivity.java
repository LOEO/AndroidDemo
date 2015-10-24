package com.leo.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.TextView;


/**
 * Created by LT on 2015/10/13 0013.
 */
public class CardActivity extends AppCompatActivity {
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        cardView = (CardView) findViewById(R.id.card_view);
        TextView tv = (TextView) findViewById(R.id.info_text);
    }
}
