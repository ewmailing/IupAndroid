package br.pucrio.tecgraf.iup;

import android.view.View;
import java.lang.Object;
import android.content.Context;
import android.view.View;
//import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
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

}

