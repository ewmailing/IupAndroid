package br.pucrio.tecgraf.iupweb;
import java.lang.Object;
import java.lang.reflect.Method;

import android.content.Context;
import android.view.View;
import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//import android.widget.LinearLayout;

import br.pucrio.tecgraf.iup.IupApplication;
import br.pucrio.tecgraf.iup.IupCommon;

public final class IupWebViewHelper
{

	public static IupWebView createWebView(final long ihandle_ptr)
	{
		Context the_context = (Context)IupApplication.getIupApplication();
		IupWebView web_view = new IupWebView(the_context, ihandle_ptr);

	//	web_view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 600));

		return web_view;
 	};

	public static void loadUrl(IupWebView web_view, String url_string)
	{
		android.util.Log.v("IupWebViewHelper", "url_string " + url_string);

		web_view.loadUrl(url_string);
	};

	public static int getLoadStatus(final long ihandle_ptr, IupWebView web_view)
	{
		return web_view.getCurrentLoadStatus();
 	};
	
}


