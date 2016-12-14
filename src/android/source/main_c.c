#include "iup.h"
#include <stddef.h>

void IupEntry()
{
	IupOpen(0, NULL);
	Ihandle* button = IupButton("Iup Button", "");

	Ihandle* vb=IupVbox(button, NULL);
	IupSetAttribute(vb, "GAP", "10");
	IupSetAttribute(vb, "MARGIN", "10x10");
	IupSetAttribute(vb, "ALIGNMENT", "ACENTER");

	Ihandle* dialog = IupDialog(vb);
	//	IupMap(dialog);
	IupSetAttribute(dialog, "TITLE", "Iup Activity Title");


	IupShow(dialog);
}


