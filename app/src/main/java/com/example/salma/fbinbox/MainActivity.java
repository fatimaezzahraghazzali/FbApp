package com.example.salma.fbinbox;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends ActionBarActivity {

    private UiLifecycleHelper uiHelper;
    private GoogleApiClient client;
    private TextView username;
    private LoginButton loginBtn;
    private Button sendMsg;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        username = (TextView) findViewById(R.id.username);
        loginBtn = (LoginButton) findViewById(R.id.login_button);
        sendMsg = (Button) findViewById(R.id.postMessagetoFriends);


        // To maintain FB Login session
        uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        loginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {

            public void onUserInfoFetched(GraphUser user) {

                if (user != null) {

                    username.setText("You are currently logged in as " + user.getName());
                    sendMsg.setVisibility(View.VISIBLE);

                } else {

                    username.setText("You are not logged in.");
                    sendMsg.setVisibility(View.INVISIBLE);

                }

            }

        });


    }


    // When Send Message button is clicked
    public void sendMessagetoFriends(View v) {

        FacebookDialog.MessageDialogBuilder builder = new FacebookDialog.MessageDialogBuilder(
                this)
                .setName("Android Facebook Social App Tutorial");


        // If the Facebook Messenger app is installed and we can present the share dialog
        if (builder.canPresent()) {
            // Enable button or other UI to initiate launch of the Message Dialog
            FacebookDialog dialog = builder.build();
            uiHelper.trackPendingDialogCall(dialog.present());
        } else {
            // Disable button or other UI for Message Dialog
            Toast.makeText(getApplicationContext(), "Facebook Messenger app is not installed in your device, so disabling the button", Toast.LENGTH_SHORT).show();
            v.setEnabled(false);
        }
    }

    // After Facebook Messenger dialog is closed
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        uiHelper.onActivityResult(requestCode, resultCode, data,
                new FacebookDialog.Callback() {

                    @Override
                    public void onError(FacebookDialog.PendingCall pendingCall,
                                        Exception error, Bundle data) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Error OccurednMost Common Errors:n1. Device not connected to Internetn2.Faceboook APP Id is not changed in Strings.xml",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete(
                            FacebookDialog.PendingCall pendingCall, Bundle data) {
                        Toast.makeText(getApplicationContext(), "Done!!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.salma.fbinbox/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.salma.fbinbox/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}