#include <jni.h>
#include <android/log.h>
#include <stdint.h>

#include "iup.h"
#include "iup_object.h"
#include "iup_attrib.h"
#include "iupandroid_drv.h"

JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupCommon_RetainIhandle(JNIEnv* jni_env, jclass cls, jobject the_widget, jlong ihandle_ptr)
{
	iupAndroid_RetainIhandle(jni_env, the_widget, (Ihandle*)(intptr_t)ihandle_ptr);
}

JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupCommon_ReleaseIhandle(JNIEnv* jni_env, jclass cls, jlong ihandle_ptr)
{
	iupAndroid_ReleaseIhandle(jni_env, (Ihandle*)(intptr_t)ihandle_ptr);
}


JNIEXPORT jobject JNICALL Java_br_pucrio_tecgraf_iup_IupCommon_GetObjectFromIhandle(JNIEnv* jni_env, jclass cls, jlong ihandle_ptr)
{
	Ihandle* ih = (Ihandle*)(intptr_t)ihandle_ptr;
	if(ih && ih->handle)
	{
		return ih->handle;
	}
	return NULL;
}


JNIEXPORT jstring JNICALL Java_br_pucrio_tecgraf_iup_IupCommon_nativeIupAttribGet(JNIEnv* jni_env, jclass cls, jlong ihandle_ptr, jstring j_key_string)
{
	Ihandle* ih = (Ihandle*)(intptr_t)ihandle_ptr;
	if(ih)
	{
		if(NULL != j_key_string)
		{
			const char* key_string = (*jni_env)->GetStringUTFChars(jni_env, j_key_string, NULL);
		__android_log_print(ANDROID_LOG_INFO, "Java_br_pucrio_tecgraf_iup_IupCommon_nativeIupAttribGet", "key_string: %s", key_string); 
			
			char* value_string = iupAttribGet(ih, key_string);
			(*jni_env)->ReleaseStringUTFChars(jni_env, j_key_string, key_string);
		__android_log_print(ANDROID_LOG_INFO, "Java_br_pucrio_tecgraf_iup_IupCommon_nativeIupAttribGet", "value_string: %s", value_string); 

			if((NULL != value_string) && (*value_string != 0))
			{
				jstring j_value_string = (*jni_env)->NewStringUTF(jni_env, value_string);
				return j_value_string;
				
			}
			else
			{
				return NULL;
			}
			
		}	
	}
	return NULL;
}


