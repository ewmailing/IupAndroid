package br.pucrio.tecgraf.iup;


import android.app.Application;
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

}

