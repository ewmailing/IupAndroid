package br.pucrio.tecgraf.iup;

import android.view.View;
import java.lang.Object;
import android.content.Context;
import android.view.View;
//import android.app.Activity;
import android.util.Log;
import android.widget.EditText;

import br.pucrio.tecgraf.iup.IupApplication;
import br.pucrio.tecgraf.iup.IupCommon;

public final class IupTextHelper
{
	// value must be final in order to access in inner class
	public static EditText createMultiLineText(final long ihandle_ptr)
	{
		Context context = (Context)IupApplication.getIupApplication();
		EditText text_view = new EditText(context);

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

	public static EditText createSingleLineText(final long ihandle_ptr)
	{
		Context context = (Context)IupApplication.getIupApplication();
		EditText text_view = new EditText(context);

		/*
//		String attrib_string = IupCommon.iupAttribGet(ihandle_ptr, "TITLE");

//		android.util.Log.w("IupButtonHelper::createButton", "attrib_string: " + attrib_string);

		if(null != attrib_string)
		{
			new_button.setText(attrib_string);
		}
*/
		text_view.setMaxLines(1);




		return text_view;
	}

	// FIXME: This is just a single line TextView.
	public static EditText createSpinnerText(final long ihandle_ptr)
	{
		Context context = (Context)IupApplication.getIupApplication();
		EditText text_view = new EditText(context);

		/*
//		String attrib_string = IupCommon.iupAttribGet(ihandle_ptr, "TITLE");

//		android.util.Log.w("IupButtonHelper::createButton", "attrib_string: " + attrib_string);

		if(null != attrib_string)
		{
			new_button.setText(attrib_string);
		}
*/
		text_view.setMaxLines(1);




		return text_view;
	}
	public static void setText(final long ihandle_ptr, EditText text_view, String the_text)
	{
		text_view.setText(the_text);
	}

	public static String getText(final long ihandle_ptr, EditText text_view)
	{
		return text_view.getText().toString();
	}
}

