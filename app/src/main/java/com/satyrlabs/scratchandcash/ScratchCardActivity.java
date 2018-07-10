package com.satyrlabs.scratchandcash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScratchCardActivity extends AppCompatActivity {

    @BindView(R.id.scratch_card)
    ScratchImageView scratchImageView;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    protected ApplicationComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setComponent(component());

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
                    //Winning card ends activity and updates the users total points
                    sharedPreferencesManager.setUserPointTotal(10);
                    finish();
                }
            }
        });
    }

    public ApplicationComponent component() {
        if(component == null) {
            setComponent(DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(this))
                    .build());
        }

        return component;
    }

    public void setComponent(final ApplicationComponent component) {
        this.component = component;
        this.component.inject(this);
    }

}
