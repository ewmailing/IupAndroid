package br.pucrio.tecgraf.iupweb;

import android.content.Context;
import android.view.View;
import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
 
import br.pucrio.tecgraf.iup.IupApplication;
import br.pucrio.tecgraf.iup.IupCommon;



// Sigh. I need to subclass because I want access to the WebViewClient, but the getter isn't introduced until API 26.
public class IupWebView extends WebView
{
	private IupWebViewClient iupWebViewClient;


	public IupWebView(Context the_context)
	{
		super(the_context);

		this.getSettings().setJavaScriptEnabled(true);
//		this.getSettings().setLoadWithOverviewMode(true);
//		this.getSettings().setUseWideViewPort(true);


		IupWebViewClient web_client = new IupWebViewClient();
		this.setWebViewClient(web_client);
		iupWebViewClient = web_client;
	}

	/*
	// Named to avoiding clash with API 26
	IupWebViewClient getIupWebViewClient()
	{
		return iupWebViewClient;
	}
*/

	public int getCurrentLoadStatus()
	{
		return iupWebViewClient.getCurrentLoadStatus();
	}

	private static final int LoadStatusFinished = 0;
	private static final int LoadStatusFailed = 1;
	private static final int LoadStatusLoading = 2;

    private class IupWebViewClient extends WebViewClient
	{



		private int currentLoadStatus = LoadStatusFinished;
		public int getCurrentLoadStatus()
		{
			return currentLoadStatus;
		}

		@Override
		public void onPageStarted(WebView web_view, String the_url, android.graphics.Bitmap favicon)
		{
			android.util.Log.v("IupWebViewClient", "onPageStarted " + the_url);
			currentLoadStatus = LoadStatusLoading;
		}

		@Override
		public void onPageFinished(WebView web_view, String the_url)
		{
			android.util.Log.v("IupWebViewClient", "onPageFinished " + the_url);
			currentLoadStatus = LoadStatusFinished;

		}

		// Deprecated in API 23, replaced by void onReceivedError (WebView view, WebResourceRequest request, WebResourceError error)
		@Override
		public void onReceivedError(WebView web_view, int error_code, String error_description, String failing_url)
		{
			android.util.Log.v("IupWebViewClient", "onReceivedError " + failing_url);
			currentLoadStatus = LoadStatusFailed;
			
		}

		// deprecated in API level 24, replaced with shouldOverrideUrlLoading(WebView, WebResourceRequest)
		@Override
		public boolean shouldOverrideUrlLoading(WebView web_view, String the_url)
		{
			return false;
		}


	}

}

