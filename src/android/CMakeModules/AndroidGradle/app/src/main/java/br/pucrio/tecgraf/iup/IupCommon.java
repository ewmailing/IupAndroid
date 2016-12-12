package br.pucrio.tecgraf.iup;
import java.lang.Object;

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

}

