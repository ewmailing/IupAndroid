package br.pucrio.tecgraf.iup;
import java.lang.Object;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public final class IupCommon
{

    public native static void RetainIhandle(Object the_widget, long ihandle_ptr);
    public static void retainIhandle(Object the_widget, long ihandle_ptr)
	{
		RetainIhandle(the_widget, ihandle_ptr);
    }


    public native static void ReleaseIhandle(long ihandle_ptr);
    public static void releaseIhandle(long ihandle_ptr)
	{
		ReleaseIhandle(ihandle_ptr);
    }


	public native static Object GetObjectFromIhandle(long ihandle_ptr);
	public static Object getObjectFromIhandle(long ihandle_ptr)
	{
		return GetObjectFromIhandle(ihandle_ptr);
	}


	public static void addWidgetToParent(Object parent_widget, Object child_widget)
	{
		View child_view = null;
		// TODO: Support fragments?
		if(child_widget instanceof android.view.View)
		{
			child_view = (View)child_widget;
		}
		else
		{
			Log.e("Java IupCommon addWidgetToParent", "child_widget is not a supported type");
			return;
		}
		

		if((parent_widget instanceof android.app.Activity))
		{

			// Design assumption: We've added a RelativeLayout as the content view to the Activity.
			// We'll add the widget to that view.
			Activity parent_activity = (Activity)parent_widget;
			// TODO: Consider making a method in our IupActivity that returns the root view instead of fishing like this.
			ViewGroup parent_view = (ViewGroup)parent_activity.getWindow().getDecorView();


			ViewGroup.LayoutParams vg_layout_params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT
			);
			RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(vg_layout_params);
			
			// TODO: Remove this. Setting these margins is how IUP will set the positions during layout.
			layout_params.leftMargin = 500;
			layout_params.topMargin = 500;

			
			parent_view.addView(child_view, layout_params);

		}
		else if((parent_widget instanceof android.view.ViewGroup))
		{

			ViewGroup parent_view = (ViewGroup)parent_widget;

			ViewGroup.LayoutParams vg_layout_params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT
			);
			RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(vg_layout_params);
			
			// TODO: Remove this. Setting these margins is how IUP will set the positions during layout.
			layout_params.leftMargin = 600;
			layout_params.topMargin = 600;

			parent_view.addView(child_view, layout_params);

		}
		else
		{
			Log.e("Java IupCommon addWidgetToParent", "parent_widget is unsupported type");
		}


	}



    public native static String nativeIupAttribGet(long ihandle_ptr, String key_string);
    public static String iupAttribGet(long ihandle_ptr, String key_string)
	{
		return nativeIupAttribGet(ihandle_ptr, key_string);
    }


}

