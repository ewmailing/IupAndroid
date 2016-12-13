package br.pucrio.tecgraf.iup;


import android.app.Application;
import android.app.Activity;
import android.view.View;
import android.os.Bundle;
//import android.content.res.AssetManager;
import android.util.Log;


public class IupApplication extends Application
{
	static
	{
		System.loadLibrary("MySDLMainActivity");
	}

	/* A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
//    public static native boolean doStaticActivityInit();
    public native boolean doInit(IupApplication my_app);
    public native void doPause();
    public native void doResume();
    public native void doDestroy();


    /** Called when the activity is first created. */
    @Override
    //public void onCreate(Bundle savedInstanceState)
    public void onCreate()
    {
		super.onCreate();
        registerActivityLifecycleCallbacks(new IupActivityLifecycleHandler());
		
//		setContentView(R.layout.main);

		/* This will pass the HelloAndroidALmixer activity class
		 * which ALmixer will capture to initialize the ALmixer_RWops system behind the scenes.
		 */
//        HelloAndroidALmixer.doStaticActivityInit();
		

		/* Once upon a time, we needed to pass the AssetManager to ALmixer.
		 * But that has been changed to conform to SDL's new behavior which
		 * requires the Activity class instead.
		 * The asset manager fetched here is no longer used (though Init still initializes ALmixer),
		 * but the code is left here as an example because this pattern is generally useful in Android.
		 */
		Log.i("HelloAndroidIupApplication", "calling doInit");
		//AssetManager java_asset_manager = this.getAssets();
		//doInit(java_asset_manager, this);
	//	doInit(this);
		Log.i("HelloAndroidIupApplication", "finished calling doInit");
	}
			/** Called when the activity is about to be paused. */
/*
	@Override
	protected void onPause()
	{
		Log.i("HelloAndroidIupApplication", "calling onPause");
		
		doPause();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		Log.i("HelloAndroidIupApplication", "calling onResume");
		
		super.onResume();
		doResume();
	}
*/
	/** Called when the activity is about to be destroyed. */
/*
	@Override
	protected void onDestroy()
	{
		Log.i("HelloAndroidIupApplication", "calling onDestroy");
		doDestroy();
		
		super.onDestroy();
		Log.i("HelloAndroidIupApplication", "finished calling onDestroy");		
	}
*/
/*
	@Override
	protected void onStart()
	{
		Log.i("HelloAndroidIupApplication", "calling onStart");
		
		super.onStart();
				Log.i("HelloAndroidIupApplication", "calling doInit");

		AssetManager java_asset_manager = this.getAssets();
		doInit(java_asset_manager);
		Log.i("HelloAndroidIupApplication", "finished calling doInit");
	}

	@Override
	protected void onStop()
	{
		Log.i("HelloAndroidIupApplication", "calling onStop");
		doDestroy();
		
		super.onStop();
		Log.i("HelloAndroidIupApplication", "finished calling onStop");
		
	}
*/
	/*
    public void myClickHandler(View the_view)
	{
		switch(the_view.getId())
		{
			default:
			{
				break;
			}
		}
	} 
	*/

/*	
	public void HelloAndroidALmixer_MyJavaPlaybackFinishedCallbackTriggeredFromNDK(int which_channel, int channel_source, boolean finished_naturally) 
	{
		Log.i("HelloAndroidIupApplication", "HelloAndroidALmixer_MyJavaPlaybackFinishedCallbackTriggeredFromNDK: channel:" + which_channel + ", source:" + channel_source + ", finishedNaturally:" + finished_naturally);
	}
*/


	// Requires API 14 and replaces onLowMemory
	@Override
	public void onTrimMemory(int level)
	{
		// Not sure which 
		if(TRIM_MEMORY_COMPLETE == level)
		{
		}
		else if(TRIM_MEMORY_RUNNING_CRITICAL == level)
		{
		}
		else if(TRIM_MEMORY_RUNNING_LOW == level)
		{
		}
		else
		{
		}

	}
		

	// http://stackoverflow.com/questions/3667022/checking-if-an-android-application-is-running-in-the-background/13809991#13809991
	private final class IupActivityLifecycleHandler implements ActivityLifecycleCallbacks
	{
		// I use four separate variables here. You can, of course, just use two and
		// increment/decrement them instead of using four and incrementing them all.
		private int resumedCount;
		private int pausedCount;
		private int startedCount;
		private int stoppedCount;

		@Override
		public void onActivityCreated(Activity activity, Bundle saved_instance_state)
		{
		}

		@Override
		public void onActivityDestroyed(Activity activity)
		{
		}

		@Override
		public void onActivityResumed(Activity activity)
		{
			resumedCount += 1;
			android.util.Log.w("onActivityResumed", "application is in foreground: " + (resumedCount > pausedCount));
			android.util.Log.w("onActivityResumed", "application is in background: " + isApplicationInBackground());
		}

		@Override
		public void onActivityPaused(Activity activity)
		{
			pausedCount += 1;
			android.util.Log.w("onActivityPaused", "application is in foreground: " + (resumedCount > pausedCount));
			android.util.Log.w("onActivityPaused", "application is in background: " + isApplicationInBackground());
			
		}

		@Override
		public void onActivitySaveInstanceState(Activity activity, Bundle outState)
		{
		}

		@Override
		public void onActivityStarted(Activity activity)
		{
			startedCount += 1;
			android.util.Log.w("onActivityStarted", "application is visible: " + (startedCount > stoppedCount));
			android.util.Log.w("onActivityStarted", "application is in background: " + isApplicationInBackground());
			
		}

		@Override
		public void onActivityStopped(Activity activity)
		{
			stoppedCount += 1;
			android.util.Log.w("onActivityStopped", "application is visible: " + (startedCount > stoppedCount));
			android.util.Log.w("onActivityStopped", "application is in background: " + isApplicationInBackground());

		}

		public boolean isApplicationVisible()
		{
			return startedCount > stoppedCount;
		}

		public boolean isApplicationInForeground()
		{
			return resumedCount > pausedCount;
		}

		public boolean isApplicationInBackground()
		{
			return (!isApplicationVisible() && !isApplicationInForeground());
		}


	}

}

