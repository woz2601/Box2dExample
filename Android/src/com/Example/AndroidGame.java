package com.Example;

import com.badlogic.gdx.backends.android.AndroidApplication;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class AndroidGame extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
     	// Do the stuff that initialize() would do for you
  		requestWindowFeature(Window.FEATURE_NO_TITLE);

  		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
  				WindowManager.LayoutParams.FLAG_FULLSCREEN);
  		getWindow().clearFlags(
  				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
  		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  		initialize(new Game(), false);
    }
}