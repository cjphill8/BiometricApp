package asu.capstone.biometricapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.amazonaws.mobile.client.*;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.auth.core.IdentityHandler;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.auth.core.SignInStateChangeListener;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AWSMobileClient.getInstance().initialize(this).execute();

        // Sign-in listener
        IdentityManager.getDefaultIdentityManager().addSignInStateChangeListener(new SignInStateChangeListener()
        {
            @Override
            public void onUserSignedIn () {
            //Log.d(LOG_TAG, "User Signed In");
            }

            // Sign-out listener
            @Override
            public void onUserSignedOut () {

            //Log.d(LOG_TAG, "User Signed Out");
            showSignIn();
            }
        });

        showSignIn();
    }

    private void showSignIn() {

        //Log.d(LOG_TAG, "showSignIn");

        SignInUI signin = (SignInUI) AWSMobileClient.getInstance().getClient(LoginActivity.this, SignInUI.class);
        signin.login(LoginActivity.this, BaseActivity.class).execute();
    }
}

