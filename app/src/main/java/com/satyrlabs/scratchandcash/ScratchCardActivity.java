package com.satyrlabs.scratchandcash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScratchCardActivity extends AppCompatActivity {

    @BindView(R.id.scratch_card)
    ScratchImageView scratchImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scratch_card_layout);

        ButterKnife.bind(this);

        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView scratchImageView) {
                System.out.print("revealed!");
            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView scratchImageView, float percent) {
                if(percent > .80) {
                    System.out.print("revealed percentage!");
                }
            }
        });
    }

}
