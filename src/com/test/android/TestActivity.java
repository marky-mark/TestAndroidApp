package com.test.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends Activity
{
    private Button button;

    private Button webViewButton;

    private EditText edittext;

    private EditText password;
    private Button passwordSubmitButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        final Context context = this;

        //defualt needed!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //TextBox Setup
        addKeyListener();

        //button setup
        addListenerOnButton();

        //Password Submission setup
        addPasswordSubmissionListener();

        //Web View
        button = (Button) findViewById(R.id.buttonUrl);
		button.setOnClickListener(new WebViewClickListener(this));

        //Check if a camera is on the device!
		PackageManager packageManager = context.getPackageManager();

		// if device support camera?
		if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			//yes
			Log.i("camera", "This device has camera!");
		}else{
			//no
			Log.i("camera", "This device has no camera!");
		}
    }

    public class WebViewClickListener implements View.OnClickListener {

        Context context;

        public WebViewClickListener(Context context) {
            this.context = context;
        }

        public void onClick(View view) {
                Intent browserIntent =
                            new Intent(context, WebViewActivity.class);
			    startActivity(browserIntent);
        }
    }

    private void addListenerOnButton() {

        //find the button
		button = (Button) findViewById(R.id.button1);

        //set the action listener
		button.setOnClickListener(new TestClickListener());

	}

    //Test Button Class
    public class TestClickListener implements View.OnClickListener {

        public void onClick(View view) {
                Intent browserIntent =
                            new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			    startActivity(browserIntent);
        }
    }

    private void addKeyListener() {
        // get edittext component
        edittext = (EditText) findViewById(R.id.editText);

        // add a keylistener to keep track user input
        edittext.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // if keydown and "enter" is pressed
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    // display a floating message
                    Toast.makeText(TestActivity.this,
                            edittext.getText(), Toast.LENGTH_LONG).show();
                    return true;

                } else if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_9)) {

                    // display a floating message
                    Toast.makeText(TestActivity.this,
                        "Number 9 is pressed!", Toast.LENGTH_LONG).show();
                    return true;
                }

                return false;
            }
        });
    }

    private void addPasswordSubmissionListener() {

        password = (EditText) findViewById(R.id.txtPassword);

        passwordSubmitButton = (Button) findViewById(R.id.btnSubmit);

        passwordSubmitButton.setOnClickListener(new PasswordSubmitClickListener());
    }

    public class PasswordSubmitClickListener implements View.OnClickListener {

        public void onClick(View view) {
           Toast.makeText(TestActivity.this, password.getText(),
			Toast.LENGTH_SHORT).show();
        }
    }
}
