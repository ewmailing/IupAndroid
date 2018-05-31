package br.pucrio.tecgraf.iup;

//import android.util.Log;
import android.os.Handler;


public final class IupTimer extends Handler
{
	private boolean isStarted = false;
	private long startTime = 0;
	private Runnable runnableCode = null;
	private long iHandlePtr = 0;
		
	public boolean isStarted()
	{
		return isStarted;
	}

	private void startElapsedTime()
	{
		startTime = System.currentTimeMillis();
	}

	private void stopElapsedTime()
	{
		// startTime = 0;
	}
	
	public long getElapsedTime()
	{
		long elapsed_time = System.currentTimeMillis() - startTime;
		return elapsed_time;
	}

	public void startTimer(final long ihandle_ptr, final long interval_period)
	{
		if(isStarted)
		{
			return;
		}
		iHandlePtr = ihandle_ptr;

		Runnable runnable_code = new Runnable()
		{
			@Override
			public void run()
			{
				IupTimer.this.postDelayed(this, interval_period);
				IupCommon.iupAttribSetInt(ihandle_ptr, "ELAPSEDTIME", (int)getElapsedTime());
				int ret_val = IupCommon.handleIupCallback(ihandle_ptr, "ACTION_CB");
				
			}
		};



		runnableCode = runnable_code;
		startElapsedTime();
		postDelayed(runnable_code, interval_period);
		isStarted = true;
		
	}

	public void stopTimer()
	{
		removeCallbacks(runnableCode);
		stopElapsedTime();
		runnableCode = null;
		isStarted = false;
	}


}

