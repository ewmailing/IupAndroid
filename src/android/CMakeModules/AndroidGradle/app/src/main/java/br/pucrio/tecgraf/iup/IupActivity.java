package br.pucrio.tecgraf.iup;

import android.content.Context;
import android.app.Activity;
import android.view.View;
import android.os.Bundle;
//import android.content.res.AssetManager;
import android.util.Log;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Button;

import android.content.Intent;

import br.pucrio.tecgraf.iup.IupCommon;


// <sigh>: Android's default Activity transitions look terrible and non-sensicle.
// Using res/anim/*.xml files, and overridePendingTransition, we fix can this
// (e.g. slide left and right which makes more sense with a left-facing back button).
// But it only works with the R.java resource system.
// But because we are (correctly) treating Iup as a separate library/package from the main app,
// the R.java namespace is wrong and we must the final app's package namespace,
// which is something we can't know.
// The new AAR libraries for Android might solve this, but everybody is going to have to spend
// time getting their build systems to properly build Iup and then import Iup into their projects.
// For now, comment out the overridingPendingTransitions until we have the infrastructure to support it.
//import net.playcontrol.MyBlurrrIupProject.R;


public class IupActivity extends Activity
{
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
	public native void doPause();
	public native void doResume();
	public native void doDestroy();


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//		setContentView(R.layout.main);

		/* This will pass the HelloAndroidBlurrr activity class
		 * which Blurrr will capture to initialize the Blurrr_RWops system behind the scenes.
		 */
		//        HelloAndroidBlurrr.doStaticActivityInit();


		/* Once upon a time, we needed to pass the AssetManager to Blurrr.
		 * But that has been changed to conform to SDL's new behavior which
		 * requires the Activity class instead.
		 * The asset manager fetched here is no longer used (though Init still initializes Blurrr),
		 * but the code is left here as an example because this pattern is generally useful in Android.
		 */
		Log.i("HelloAndroidIupActivity", "calling doInit");
		Intent the_intent = getIntent();

		long ihandle_ptr = the_intent.getLongExtra("Ihandle", 0);

		// We need to swap the pointers around.
		Object view_group_object = IupCommon.getObjectFromIhandle(ihandle_ptr);
		if(view_group_object instanceof ViewGroup)
		{
			Log.i("HelloAndroidIupActivity", "swapping view group and activity");
			ViewGroup view_group = (ViewGroup)view_group_object;
			setContentView(view_group);
			IupCommon.releaseIhandle(ihandle_ptr);
		}





		IupCommon.retainIhandle(this, ihandle_ptr);



		// Deal with IUP properties
		String attrib_string = IupCommon.iupAttribGet(ihandle_ptr, "TITLE");
		if(null != attrib_string)
		{
			this.setTitle(attrib_string);
		}



		//AssetManager java_asset_manager = this.getAssets();
		//doInit(java_asset_manager, this);
		//IupEntry(this);
		Log.i("HelloAndroidIupActivity", "finished calling doInit");

		//		addButton();


	}

    public void addButton()	
	{
	//	final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
      //     .findViewById(android.R.id.content)).getChildAt(0);
	//	LinearLayout myLayout = (LinearLayout)viewGroup;

		//LinearLayout myLayout = findViewById(android.R.id.content);
//		View myLayout = findViewById(android.R.id.content);
		int dpValue = 5; // margin in dips
//float d = context.getResources().getDisplayMetrics().density;
float d = this.getResources().getDisplayMetrics().density;
int margin = (int)(dpValue * d); // margin in pixels
// flooring for offsets and rounding for widths???

Button myButton = new Button(this);
myButton.setText("My Button!");
/*
myButton.setLayoutParams(new LinearLayout.LayoutParams(
                                     LinearLayout.LayoutParams.MATCH_PARENT,
                                     LinearLayout.LayoutParams.MATCH_PARENT));
*/
//myLayout.addView(myButton);


//LinearLayout root_view = new LinearLayout(this);
final RelativeLayout root_view = new RelativeLayout(this);


setContentView(root_view);

{
RelativeLayout.LayoutParams params;
ViewGroup.LayoutParams vg_layout_params = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
//params = new RelativeLayout.LayoutParams(300, 400);
params = new RelativeLayout.LayoutParams(vg_layout_params);
params.leftMargin = 100;
params.topMargin = 100;

//root_view.addView(myButton);
root_view.addView(myButton, params);


myButton.setOnClickListener(new View.OnClickListener()
{
     @Override
     public void onClick(View v) {
           // put code on click operation
		   		Intent the_intent = new Intent(IupActivity.this, IupActivity.class);
		// Prevent the back button from returning to this "launcher" activity.
		// Note: This AndroidManifest entry will accomplish the same goal: android:noHistory="true"
		//the_intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(the_intent);

     }
});

}



Button myButton2 = new Button(this);
myButton2.setText("My Button2!");

{
RelativeLayout.LayoutParams params;
ViewGroup.LayoutParams vg_layout_params = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
//params = new RelativeLayout.LayoutParams(300, 400);
params = new RelativeLayout.LayoutParams(vg_layout_params);
params.leftMargin = 200;
params.topMargin = 200;

root_view.addView(myButton2, params);
//root_view.addView(myButton2);
}

Button myButton3 = new Button(this);
myButton3.setText("My Button3!");

{
RelativeLayout.LayoutParams params;
ViewGroup.LayoutParams vg_layout_params = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
//params = new RelativeLayout.LayoutParams(300, 400);
params = new RelativeLayout.LayoutParams(vg_layout_params);
params.leftMargin = 300;
params.topMargin = 300;

root_view.addView(myButton3, params);
//root_view.addView(myButton3);
}



	}

	/** Called when the activity is about to be paused. */
	@Override
	protected void onPause()
	{
		Log.i("HelloAndroidIupActivity", "calling onPause");
		
//		doPause();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		Log.i("HelloAndroidIupActivity", "calling onResume");
		
		super.onResume();
//		doResume();
	}

	/** Called when the activity is about to be destroyed. */
	@Override
	protected void onDestroy()
	{
		Log.i("HelloAndroidIupActivity", "calling onDestroy");
//		doDestroy();
		
		Intent the_intent = getIntent();
 		long ihandle_ptr = the_intent.getLongExtra("Ihandle", 0);
		IupCommon.releaseIhandle(ihandle_ptr);

		super.onDestroy();

		Log.i("HelloAndroidIupActivity", "finished calling onDestroy");		
	}

/*
	@Override
	protected void onStart()
	{
		Log.i("HelloAndroidIupActivity", "calling onStart");
		
		super.onStart();
				Log.i("HelloAndroidIupActivity", "calling doInit");

		AssetManager java_asset_manager = this.getAssets();
		doInit(java_asset_manager);
		Log.i("HelloAndroidIupActivity", "finished calling doInit");
	}

	@Override
	protected void onStop()
	{
		Log.i("HelloAndroidIupActivity", "calling onStop");
		doDestroy();
		
		super.onStop();
		Log.i("HelloAndroidIupActivity", "finished calling onStop");
		
	}
*/

	@Override
	public void finish()
	{
		super.finish();
		// <sigh>: Android's default Activity transitions look terrible and non-sensicle.
		// Using res/anim/*.xml files, and overridePendingTransition, we can fix this
		// (e.g. slide left and right which makes more sense with a left-facing back button).
		// But it only works with the R.java resource system.
		// But because we are (correctly) treating Iup as a separate library/package from the main app,
		// the R.java namespace is wrong and we must the final app's package namespace,
		// which is something we can't know.
		// The new AAR libraries for Android might solve this, but everybody is going to have to spend
		// time getting their build systems to properly build Iup and then import Iup into their projects.
		// For now, comment out the overridingPendingTransitions until we have the infrastructure to support it.
//		overridePendingTransition(R.anim.iup_slide_from_left, R.anim.iup_slide_to_right);
	}

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

/*	
	public void HelloAndroidBlurrr_MyJavaPlaybackFinishedCallbackTriggeredFromNDK(int which_channel, int channel_source, boolean finished_naturally) 
	{
		Log.i("HelloAndroidIupActivity", "HelloAndroidBlurrr_MyJavaPlaybackFinishedCallbackTriggeredFromNDK: channel:" + which_channel + ", source:" + channel_source + ", finishedNaturally:" + finished_naturally);
	}
*/



	/* 
	 * These Static methods are intended for C calling back into Java to do things.
	 */

	public static ViewGroup createActivity(Activity parent_activity, long ihandle_ptr)
	{
		Log.i("HelloAndroidIupActivity", "createActivity");		
		Intent the_intent = new Intent(parent_activity, IupActivity.class);
		the_intent.putExtra("Ihandle", ihandle_ptr);
        parent_activity.startActivity(the_intent);

		// <sigh>: Android's default Activity transitions look terrible and non-sensicle.
		// Using res/anim/*.xml files, and overridePendingTransition, we can fix this
		// (e.g. slide left and right which makes more sense with a left-facing back button).
		// But it only works with the R.java resource system.
		// But because we are (correctly) treating Iup as a separate library/package from the main app,
		// the R.java namespace is wrong and we must the final app's package namespace,
		// which is something we can't know.
		// The new AAR libraries for Android might solve this, but everybody is going to have to spend
		// time getting their build systems to properly build Iup and then import Iup into their projects.
		// For now, comment out the overridingPendingTransitions until we have the infrastructure to support it.
//		parent_activity.overridePendingTransition(R.anim.iup_slide_from_right, R.anim.iup_slide_to_left);

		RelativeLayout root_view = new RelativeLayout(parent_activity);
		return root_view;
		
	}

	public static void unMapActivity(Object activity_or_viewgroup, long ihandle_ptr)
	{
		// Because of the ViewGroup dance/trick I do, I need to check the type
		if(activity_or_viewgroup instanceof Activity)
		{
			// Do I need to call finish() for the case where IupDestroy() is called
			// but the Activity has not been popped from the navigation stack?
			Activity the_activity = (Activity)activity_or_viewgroup;
			the_activity.finish();

		Log.i("HelloAndroidIupActivity", "calling finish() in unMapActivity");		
			
		}

	}
}


