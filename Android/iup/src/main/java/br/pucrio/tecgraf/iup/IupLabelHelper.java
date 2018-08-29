package br.pucrio.tecgraf.iup;

import android.graphics.Bitmap;
import android.view.View;
import java.lang.Object;
import android.content.Context;
import android.view.View;
//import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.pucrio.tecgraf.iup.IupApplication;
import br.pucrio.tecgraf.iup.IupCommon;

public final class IupLabelHelper
{
	// value must be final in order to access in inner class
	public static TextView createLabelText(final long ihandle_ptr)
	{
		Context context = (Context)IupApplication.getIupApplication();
		TextView text_view = new TextView(context);

		/*
//		String attrib_string = IupCommon.iupAttribGet(ihandle_ptr, "TITLE");

//		android.util.Log.w("IupButtonHelper::createButton", "attrib_string: " + attrib_string);

		if(null != attrib_string)
		{
			new_button.setText(attrib_string);
		}
*/




		return text_view;
	}

	public static void setText(final long ihandle_ptr, TextView text_view, String the_text)
	{
		text_view.setText(the_text);
	}

	public static String getText(final long ihandle_ptr, TextView text_view)
	{
		return text_view.getText().toString();
	}


	public static ImageView createLabelImage(final long ihandle_ptr)
	{
		Context context = (Context)IupApplication.getIupApplication();
		ImageView image_view = new ImageView(context);
		image_view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//		image_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
		image_view.setAdjustViewBounds(true);
//		image_view.getLayoutParams().width = 40;
//		image_view.getLayoutParams().height = 40;
//		image_view.requestLayout();
		return image_view;
	}

	public static void setImage(final long ihandle_ptr, ImageView image_view, Bitmap bitmap_image)
	{
		image_view.setImageBitmap(bitmap_image);
		if(null == bitmap_image)
		{
			return;
		}
		// FIXME: I don't know what the IUP policy is about setting the ImageView size
		image_view.getLayoutParams().width = bitmap_image.getWidth();
		image_view.getLayoutParams().height = bitmap_image.getHeight();
		image_view.requestLayout();
	}
	
}

