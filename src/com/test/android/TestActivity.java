package com.test.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends Activity
{
    Button button;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        //defualt needed!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //button setup
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        //find the button
		button = (Button) findViewById(R.id.button1);

        //set the action listener
		button.setOnClickListener(new TestClickListener());

	}

    //Test Button Class
    private class TestClickListener implements View.OnClickListener {

        public void onClick(View view) {
            			  Intent browserIntent =
                            new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			    startActivity(browserIntent);
        }
    }
}
