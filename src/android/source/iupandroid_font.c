/** \file
 * \brief MAC Font mapping
 *
 * See Copyright Notice in "iup.h"
 */


#include <stdlib.h>
#include <stdio.h>


#include "iup.h"

#include "iup_str.h"
#include "iup_array.h"
#include "iup_attrib.h"
#include "iup_object.h"
#include "iup_drv.h"
#include "iup_drvfont.h"
#include "iup_assert.h"

//#include "iupmac_info.h"



char* iupdrvGetSystemFont(void)
{
  static char systemfont[200] = "";
#if 0
  NSFont *font = [NSFont systemFontOfSize:0];
	NSLog(@"systemfont: %@", font);
  char *name = [[font familyName] UTF8String];
  if(*name)
    strcpy(systemfont,name);
  else
    strcpy(systemfont, "Tahoma, 10");
#endif
  return systemfont;
}


int iupdrvSetStandardFontAttrib(Ihandle* ih, const char* value)
{
#if 0 // iupBaseUpdateSizeFromFont missing
  ImacFont* macfont = macFontCreateNativeFont(ih, value);
  if (!macfont)
    return 1;

	/* If FONT is changed, must update the SIZE attribute */
	iupBaseUpdateAttribFromFont(ih);

  /* FONT attribute must be able to be set before mapping, 
      so the font is enable for size calculation. */
  if (ih->handle && (ih->iclass->nativetype != IUP_TYPEVOID)) {
	
  }
#endif
  return 1;
}

void iupdrvFontGetMultiLineStringSize(Ihandle* ih, const char* str, int *w, int *h)
{
	   return;

//  if (w) *w = max_w;
//	if (h) *h = max_h;
}

int iupdrvFontGetStringWidth(Ihandle* ih, const char* str)
{
	
	return 40;
	
}

void iupdrvFontGetCharSize(Ihandle* ih, int *charwidth, int *charheight)
{

//  if (charwidth)  *charwidth = macfont->charwidth; 
//  if (charheight) *charheight = macfont->charheight;
}

void iupdrvFontInit(void)
{
}

void iupdrvFontFinish(void)
{
}


int iupdrvSetFontAttrib(Ihandle* ih, const char* value)
{
	return 1;
}

const char* iupdrvGetFontAttrib(Ihandle* ih)
{
	return NULL;
}



