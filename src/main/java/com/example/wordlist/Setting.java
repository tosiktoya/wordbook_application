package com.example.wordlist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Setting extends Activity  {
    LinearLayout backsetting;
    Button homeBtn;
    Button btnColor1, btnColor2, btnColor3, btnColor4;
    Button btnSize1, btnSize2, btnSize3;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        backsetting = (LinearLayout) findViewById(R.id.backsetting);
        backsetting.setBackgroundColor(Color.WHITE);

        Button homeBtn = (Button) findViewById(R.id.setting);

        Button btnColor1 = (Button) findViewById(R.id.btnColor1);
        Button btnColor2 = (Button) findViewById(R.id.btnColor2);
        Button btnColor3 = (Button) findViewById(R.id.btnColor3);
        Button btnColor4 = (Button) findViewById(R.id.btnColor4);

        Button btnSize1 = (Button) findViewById(R.id.btnSize1);
        Button btnSize2 = (Button) findViewById(R.id.btnSize2);
        Button btnSize3 = (Button) findViewById(R.id.btnSize3);

        btnColor1.setBackgroundColor(Color.YELLOW);
        btnColor2.setBackgroundColor(Color.GREEN);
        btnColor3.setBackgroundColor(Color.WHITE);
        btnColor4.setBackgroundColor(Color.CYAN);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnColor1:
                        backsetting.setBackgroundColor(Color.YELLOW);
                        break;
                    case R.id.btnColor2:
                        backsetting.setBackgroundColor(Color.GREEN);
                        break;
                    case R.id.btnColor3:
                        backsetting.setBackgroundColor(Color.WHITE);
                        break;
                    case R.id.btnColor4:
                        backsetting.setBackgroundColor(Color.CYAN);
                        break;
                }
            }
        };

        btnColor1.setOnClickListener(cl);
        btnColor2.setOnClickListener(cl);
        btnColor3.setOnClickListener(cl);
        btnColor4.setOnClickListener(cl);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}