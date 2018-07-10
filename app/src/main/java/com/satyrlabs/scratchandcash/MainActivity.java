package com.satyrlabs.scratchandcash;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    private static final int RC_SIGN_IN = 123;

    @BindView(R.id.launch_scratch_card)
    Button scratchCardButton;
    @BindView(R.id.total_cash_amount)
    TextView totalCashAmount;

    private final Map<Integer, Fragment> fragments = new ArrayMap<>(3);

    protected ApplicationComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setComponent(component());

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragments.put(R.id.cards_layout, new ScratchCardListFragment());

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for(final Integer id: fragments.keySet()) {
            fragmentTransaction.add(id, fragments.get(id));
        }
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();

        if( FirebaseAuth.getInstance().getCurrentUser() == null) {
            signIn();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalCashAmount.setText(String.valueOf(sharedPreferencesManager.getUserPointTotal()));
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


    private void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build()
        );

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(), RC_SIGN_IN);
    }

    @OnClick(R.id.launch_scratch_card)
    public void launchScratchCard() {
        Intent intent = new Intent(this, ScratchCardActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if(resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                System.out.print("signed in");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.print("signed out");
                            }
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
