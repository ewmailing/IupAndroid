package br.pucrio.tecgraf.iup;
import java.lang.Object;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import br.pucrio.tecgraf.iup.IupApplication;
import br.pucrio.tecgraf.iup.IupCommon;

public final class IupFontHelper
{
	// Metrics:
	// https://stackoverflow.com/questions/5611411/what-is-the-default-text-size-on-android
	public static int getStringWidth(final long ihandle_ptr, Object native_object, int object_type, String str)
	{

		// If there is already a text view, use
		// Paint the_paint = textView.getPaint();
		// 
		IupApplication application_context = IupApplication.getIupApplication();
		TextView text_view = new TextView(application_context);
		Log.i("Text dimensions", "str: "+str);


		Paint the_paint = new Paint();
		//the_paint.setTypeface(Typeface.DEFAULT);
		Typeface default_typeface = text_view.getTypeface();
		the_paint.setTypeface(default_typeface);
		// Yuck: getTextSize returns in different units than what setTextSize uses. We must manually convert.
		float default_text_size = text_view.getTextSize();
		Log.i("Text dimensions", "default_text_size: "+default_text_size);

		// https://stackoverflow.com/questions/3687065/textview-settextsize-behaves-abnormally-how-to-set-text-size-of-textview-dynam
		// https://stackoverflow.com/questions/6263250/convert-pixels-to-sp
		float scaledDensity = application_context.getResources().getDisplayMetrics().scaledDensity;
		Log.i("Text dimensions", "scaledDensity: "+scaledDensity);

		float adjusted_text_size = default_text_size/scaledDensity;
		Log.i("Text dimensions", "adjusted_text_size: "+adjusted_text_size);

		//the_paint.setTextSize(adjusted_text_size);
		//helloWorldTextView2.setTextSize(pixelsToSp(getActivity(), helloWorldTextView.getTextSize()));

		final float densityMultiplier = application_context.getResources().getDisplayMetrics().density;
		Log.i("Text dimensions", "density: "+densityMultiplier);

		// I'm not sure which to use
		/*
		DisplayMetrics#scaledDensity

A scaling factor for fonts displayed on the display. This is the same as density, except that it may be adjusted in smaller increments at runtime based on a user preference for the font size.
DisplayMetrics#Density

The logical density of the display. This is a scaling factor for the Density Independent Pixel unit, where one DIP is one pixel on an approximately 160 dpi screen.

		 */
//		final float scaledPx = default_text_size * densityMultiplier;
		final float scaledPx = default_text_size * scaledDensity;
		Log.i("Text dimensions", "scaledPx: "+scaledPx);

		the_paint.setTextSize(scaledPx);

//
//		Typeface typeface = Typeface.createFromAsset(getAssets(), "Helvetica.ttf");
		//the_paint.setStyle(the_paint.Style.FILL);
		Rect rect_result = new Rect();
		the_paint.getTextBounds(str, 0, str.length(), rect_result);

Log.i("Text dimensions", "Width: "+rect_result.width());


		return rect_result.width();

	}
	
}


