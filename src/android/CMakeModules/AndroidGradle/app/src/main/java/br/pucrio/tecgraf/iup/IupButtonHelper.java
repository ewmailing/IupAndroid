package br.pucrio.tecgraf.iup;
import java.lang.Object;
import android.content.Context;
import android.view.View;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
 
import br.pucrio.tecgraf.iup.IupApplication;
import br.pucrio.tecgraf.iup.IupCommon;

public final class IupButtonHelper
{
	public static Button createButton(long ihandle_ptr)
	{
		Context context = (Context)IupApplication.getIupApplication();
		Button new_button = new Button(context);

		String attrib_string = IupCommon.iupAttribGet(ihandle_ptr, "TITLE");

			android.util.Log.w("IupButtonHelper::createButton", "attrib_string: " + attrib_string);
		
		if(null != attrib_string)
		{
			new_button.setText(attrib_string);
		}
		
		return new_button;
	}
	
}


