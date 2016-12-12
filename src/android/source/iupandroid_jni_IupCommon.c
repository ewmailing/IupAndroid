#include <jni.h>
#include <android/log.h>
#include <stdint.h>

#include "iup.h"
//#include "iup_object.h"
#include "iupandroid_drv.h"

JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupCommon_RetainIhandle(JNIEnv* jni_env, jclass cls, jobject the_widget, jlong ihandle_ptr)
{
	iupAndroid_RetainIhandle(jni_env, the_widget, (Ihandle*)(intptr_t)ihandle_ptr);
}

JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupCommon_ReleaseIhandle(JNIEnv* jni_env, jclass cls, jlong ihandle_ptr)
{
	iupAndroid_ReleaseIhandle(jni_env, (Ihandle*)(intptr_t)ihandle_ptr);
}


