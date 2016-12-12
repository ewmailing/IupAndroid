package br.pucrio.tecgraf.iup;

import android.content.Context;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.view.View;
import android.os.Bundle;
//import android.content.res.AssetManager;
import android.util.Log;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Button;

import android.content.Intent;



public class IupLaunchActivity extends Activity
{

    /**
     * This method is called before loading the native shared libraries.
	 * This is inspired from SDL and allows you to list library names
	 * without the explict lib prefix and .so suffix.
	 * It is intended to be overridden by a user subclass for their own 
	 * application so they may add other libraries without having to modify
	 * this file directly.
     * The default implementation returns the defaults. It never returns null.
     * An array returned by a new implementation must at least contain "iup".
     * Also keep in mind that the order the libraries are loaded may matter.
     * @return names of shared libraries to be loaded (e.g. "iup", "iupImage").
     */
    protected String[] getLibraries()
	{
		return new String[]
		{
			"iup",
		};
	}

	// Load the .so
	public void loadLibraries()
	{
		for(String a_lib : getLibraries())
		{
			System.loadLibrary(a_lib);
		}
	}

	/*
	   static
	   {
	   System.loadLibrary("c++_shared");
	   System.loadLibrary("icudataswift");
	   System.loadLibrary("icuucswift");
		System.loadLibrary("icui18nswift");
		System.loadLibrary("swiftCore");
		System.loadLibrary("swiftSwiftOnoneSupport");
		System.loadLibrary("MySDLMainActivity");
	}
	*/

	/* A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
//    public static native boolean doStaticActivityInit();
    public native void IupEntry(IupLaunchActivity this_activity);
    public native void doPause();
    public native void doResume();
    public native void doDestroy();

	private boolean loadLibraryFailed = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

        // Load shared libraries
        String error_msg = "";
        try
		{
            loadLibraries();
        }
		catch(UnsatisfiedLinkError e)
		{
            System.err.println(e.getMessage());
            loadLibraryFailed = true;
            error_msg = e.getMessage();
        }
		catch(Exception e)
		{
            System.err.println(e.getMessage());
            loadLibraryFailed = true;
            error_msg = e.getMessage();
        }

		if(loadLibraryFailed)
		{
			AlertDialog.Builder alert_dialog  = new AlertDialog.Builder(this);
			alert_dialog.setMessage("An error occurred while trying to start the application after calling System.LoadLibrary()."
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ "Error: " + error_msg
			);
			alert_dialog.setTitle("IUP Error");
			alert_dialog.setPositiveButton("Exit",
				new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog,int id)
					{
						// if this button is clicked, close current activity
						IupLaunchActivity.this.finish();
					}
				}
			);
			alert_dialog.setCancelable(false);
			alert_dialog.create().show();

			return;
		}


//		setContentView(R.layout.main);

		/* This will pass the HelloAndroidIupLaunch activity class
		 * which IupLaunch will capture to initialize the IupLaunch_RWops system behind the scenes.
		 */
//        HelloAndroidIupLaunch.doStaticActivityInit();
		

		/* Once upon a time, we needed to pass the AssetManager to IupLaunch.
		 * But that has been changed to conform to SDL's new behavior which
		 * requires the Activity class instead.
		 * The asset manager fetched here is no longer used (though Init still initializes IupLaunch),
		 * but the code is left here as an example because this pattern is generally useful in Android.
		 */
		Log.i("HelloAndroidIupLaunchActivity", "calling doInit");
		//AssetManager java_asset_manager = this.getAssets();
		//doInit(java_asset_manager, this);
		IupEntry(this);
		Log.i("HelloAndroidIupLaunchActivity", "finished calling doInit");

//		addButton();

/*
		Intent the_intent = new Intent(this, IupActivity.class);
		// Prevent the back button from returning to this "launcher" activity.
		// Note: This AndroidManifest entry will accomplish the same goal: android:noHistory="true"
		//the_intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(the_intent);
*/
	}


	/** Called when the activity is about to be paused. */
	@Override
	protected void onPause()
	{
		Log.i("HelloAndroidIupLaunchActivity", "calling onPause");
		
//		doPause();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		Log.i("HelloAndroidIupLaunchActivity", "calling onResume");
		
		super.onResume();
//		doResume();
	}

	/** Called when the activity is about to be destroyed. */
	@Override
	protected void onDestroy()
	{
		Log.i("HelloAndroidIupLaunchActivity", "calling onDestroy");
//		doDestroy();
		
		super.onDestroy();
		Log.i("HelloAndroidIupLaunchActivity", "finished calling onDestroy");		
	}


}

