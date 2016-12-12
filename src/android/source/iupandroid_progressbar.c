/** \file
* \brief Progress bar Control
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
#include "iup_progressbar.h"
#include "iup_drv.h"

#include "iupandroid_drv.h"

// TODO: API: I think we're going to need a separate start/stop key.
// android Indeterminate is for progresses you don't know the range for, but are still animated when in progress.

// TODO: FEATURE: android provides spinner style




void iupdrvProgressBarInitClass(Iclass* ic)
{
  /* Driver Dependent Class functions */
//	ic->Map = androidProgressBarMapMethod;
//	ic->UnMap = androidProgressBarUnMapMethod;

  /* Driver Dependent Attribute functions */
  
  /* Visual */
//  iupClassRegisterAttribute(ic, "BGCOLOR", NULL, iupdrvBaseSetBgColorAttrib, IUPAF_SAMEASSYSTEM, "DLGBGCOLOR", IUPAF_DEFAULT);
  
  /* Special */
//  iupClassRegisterAttribute(ic, "FGCOLOR", NULL, NULL, NULL, NULL, IUPAF_DEFAULT);
#if 0

  /* IupProgressBar only */
  iupClassRegisterAttribute(ic, "VALUE",  iProgressBarGetValueAttrib,  androidProgressBarSetValueAttrib,  NULL, NULL, IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);

	
  iupClassRegisterAttribute(ic, "ORIENTATION", NULL, NULL, IUPAF_SAMEASSYSTEM, "HORIZONTAL", IUPAF_NO_INHERIT);
  iupClassRegisterAttribute(ic, "MARQUEE",     NULL, androidProgressBarSetMarqueeAttrib, NULL, NULL, IUPAF_NO_INHERIT);
  iupClassRegisterAttribute(ic, "DASHED",      NULL, NULL, NULL, NULL, IUPAF_NO_INHERIT);
#endif
	
}
