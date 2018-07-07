package com.satyrlabs.scratchandcash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.launch_scratch_card)
    Button scratchCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.launch_scratch_card)
    public void launchScratchCard() {
        Intent intent = new Intent(this, ScratchCardActivity.class);
        startActivity(intent);
    }
}
