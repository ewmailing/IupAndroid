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

#include "iupandroid_drv.h"

#include <jni.h>
#include <android/log.h>



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

	int the_width = iupdrvFontGetStringWidth(ih, str);

	__android_log_print(ANDROID_LOG_INFO, "iupdrvFontGetMultiLineStringSize", "the_width:%d", the_width);

  if (w) *w = the_width;
	if (h) *h = 100;
}

int iupdrvFontGetStringWidth(Ihandle* ih, const char* str)
{
	JNIEnv* jni_env = iupAndroid_GetEnvThreadSafe();
	jclass java_class = (*jni_env)->FindClass(jni_env, "br/pucrio/tecgraf/iup/IupFontHelper");
	jmethodID method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "getStringWidth", "(JLjava/lang/Object;ILjava/lang/String;)I");
	jobject native_object = (jobject)ih->handle;
	jstring java_string = (*jni_env)->NewStringUTF(jni_env, str);
	jint j_width = (*jni_env)->CallStaticIntMethod(jni_env, java_class, method_id, (jlong)(intptr_t)ih, native_object, 0, java_string);
	(*jni_env)->DeleteLocalRef(jni_env, java_string);
	(*jni_env)->DeleteLocalRef(jni_env, java_class);
	__android_log_print(ANDROID_LOG_INFO, "iupdrvFontGetStringWidth", "the_width:%d", (int)j_width);

	return ((int)j_width);
}

void iupdrvFontGetCharSize(Ihandle* ih, int *charwidth, int *charheight)
{
	__android_log_print(ANDROID_LOG_INFO, "iupdrvFontGetCharSize", "x:%d, y:%d, w:%d, h:%d", ih->x, ih->y, ih->currentwidth, ih->currentheight);

	int the_width = iupdrvFontGetStringWidth(ih, "WWWWWWWW");

	the_width = (int)((the_width/8.0)+0.5);
	__android_log_print(ANDROID_LOG_INFO, "iupdrvFontGetCharSize", "the_width:%d", the_width);

  if (charwidth)  *charwidth = the_width;
  if (charheight) *charheight = 100;
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



