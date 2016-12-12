/** \file
 * \brief Button Control
 *
 * See Copyright Notice in "iup.h"
 */


#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <memory.h>
#include <stdarg.h>

#include "iup.h"
#include "iupcbs.h"

#include "iup_object.h"
#include "iup_layout.h"
#include "iup_attrib.h"
#include "iup_str.h"
#include "iup_image.h"
#include "iup_button.h"
#include "iup_drv.h"
#include "iup_drvfont.h"
#include "iup_image.h"
#include "iup_key.h"

#include "iupandroid_drv.h"


#if 0
// the point of this is we have a unique memory address for an identifier
static const void* IUP_android_BUTTON_RECEIVER_OBJ_KEY = "IUP_android_BUTTON_RECEIVER_OBJ_KEY";


@interface IupAndroidButtonReceiver : NSObject
- (IBAction) myButtonClickAction:(id)the_sender;
@end

@implementation IupAndroidButtonReceiver

/*
- (void) dealloc
{
	[super dealloc];
}
*/


- (IBAction) myButtonClickAction:(id)the_sender;
{
	Icallback callback_function;
	Ihandle* ih = (Ihandle*)objc_getAssociatedObject(the_sender, IHANDLE_ASSOCIATED_OBJ_KEY);

	// CONFLICT: Android buttons don't normally do anything for non-primary click. (Second click is supposed to trigger the contextual menu.)
	// Also Android doesn't normall give callbacks for both down and up
	/*
	callback_function = IupGetCallback(ih, "BUTTON_CB");
	if(callback_function)
	{
		if(callback_function(ih) == IUP_CLOSE)
		{
			IupExitLoop();
		}
		
	}
	 */
	
	callback_function = IupGetCallback(ih, "ACTION");
	if(callback_function)
	{
		if(callback_function(ih) == IUP_CLOSE)
		{
			IupExitLoop();
		}
	}
}

@end


#endif


void iupdrvButtonAddBorders(int *x, int *y)
{

	
}

void iupdrvButtonInitClass(Iclass* ic)
{
	/* Driver Dependent Class functions */
//	ic->Map = androidButtonMapMethod;
//	ic->UnMap = androidButtonUnMapMethod;
	
#if 0

	ic->LayoutUpdate = gtkButtonLayoutUpdateMethod;
	
	/* Driver Dependent Attribute functions */
	
	/* Overwrite Common */
	iupClassRegisterAttribute(ic, "STANDARDFONT", NULL, gtkButtonSetStandardFontAttrib, IUPAF_SAMEASSYSTEM, "DEFAULTFONT", IUPAF_NO_SAVE|IUPAF_NOT_MAPPED);
	
	/* Overwrite Visual */
	iupClassRegisterAttribute(ic, "ACTIVE", iupBaseGetActiveAttrib, gtkButtonSetActiveAttrib, IUPAF_SAMEASSYSTEM, "YES", IUPAF_DEFAULT);
	
	/* Visual */
	iupClassRegisterAttribute(ic, "BGCOLOR", NULL, gtkButtonSetBgColorAttrib, IUPAF_SAMEASSYSTEM, "DLGBGCOLOR", IUPAF_DEFAULT);
	
	/* Special */
	iupClassRegisterAttribute(ic, "FGCOLOR", NULL, gtkButtonSetFgColorAttrib, IUPAF_SAMEASSYSTEM, "DLGFGCOLOR", IUPAF_DEFAULT);
	iupClassRegisterAttribute(ic, "TITLE", NULL, gtkButtonSetTitleAttrib, NULL, NULL, IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);
	
	/* IupButton only */
	iupClassRegisterAttribute(ic, "ALIGNMENT", NULL, gtkButtonSetAlignmentAttrib, "ACENTER:ACENTER", NULL, IUPAF_NO_INHERIT);  /* force new default value */
	iupClassRegisterAttribute(ic, "IMAGE", NULL, gtkButtonSetImageAttrib, NULL, NULL, IUPAF_IHANDLENAME|IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);
	iupClassRegisterAttribute(ic, "IMINACTIVE", NULL, gtkButtonSetImInactiveAttrib, NULL, NULL, IUPAF_IHANDLENAME|IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);
	iupClassRegisterAttribute(ic, "IMPRESS", NULL, NULL, NULL, NULL, IUPAF_IHANDLENAME|IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);
	
	iupClassRegisterAttribute(ic, "PADDING", iupButtonGetPaddingAttrib, gtkButtonSetPaddingAttrib, IUPAF_SAMEASSYSTEM, "0x0", IUPAF_NOT_MAPPED);
	iupClassRegisterAttribute(ic, "MARKUP", NULL, NULL, NULL, NULL, IUPAF_DEFAULT);
#endif
	
}
