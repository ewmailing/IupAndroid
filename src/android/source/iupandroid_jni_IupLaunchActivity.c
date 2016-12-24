#include <stddef.h>
#include <jni.h>
#include <android/log.h>
#include "iupandroid_drv.h"
#include "iup.h"
#include "iupcbs.h"

#include <dlfcn.h>




JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupLaunchActivity_IupEntry(JNIEnv* jni_env, jobject thiz, jobject current_activity, jstring j_entry_function_name)
{


	__android_log_print(ANDROID_LOG_INFO,  "IupLaunchActivity", "Calling IupEntry");

	// Because of the way the other platforms work, I don't think any of them should call IupOpen inside the IupEntry callback.
	// That means we should call IupOpen ourselves for Android.
	IupOpen(0, NULL);

//	IupEntry();
	
	// Invoke the IupEntry callback function to start the user code.
	// I don't see how the user will be able to set ENTRY_POINT in normal startup conditions,
	// but if they did, let's respect it.
	IFentry entry_callback = (IFentry)IupGetFunction("ENTRY_POINT");
	
	// The string parameter passed in may contain the function name we want to call.
	if(NULL == entry_callback)
	{
		if(NULL != j_entry_function_name)
		{
			const char* c_entry_function_name = (*jni_env)->GetStringUTFChars(jni_env, j_entry_function_name, NULL);
			entry_callback = (IFentry)dlsym(RTLD_DEFAULT, c_entry_function_name);
			(*jni_env)->ReleaseStringUTFChars(jni_env, j_entry_function_name, c_entry_function_name);
		}	
	}
	// If no entry point has been defined, we can try to fallback and use dsym to look up a hardcoded function name.
	if(NULL == entry_callback)
	{
		entry_callback = (IFentry)dlsym(RTLD_DEFAULT, "IupEntryPoint");
	}

	if(NULL != entry_callback)
	{
		entry_callback();
	}
	


	__android_log_print(ANDROID_LOG_INFO,  "IupLaunchActivity", "Returned from IupEntry");

	
  //  return JNI_TRUE;
}




JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupLaunchActivity_doPause(JNIEnv* env, jobject thiz)
{
	//LOGD("Java_br_pucrio_tecgraf_iup_IupLaunchActivity_doPause");
}

JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupLaunchActivity_doResume(JNIEnv* env, jobject thiz)
{
	//LOGD("Java_br_pucrio_tecgraf_iup_IupLaunchActivity_doResume");
}

JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupLaunchActivity_doDestroy(JNIEnv* env, jobject thiz)
{
	//LOGD("Java_br_pucrio_tecgraf_iup_IupLaunchActivity_doDestroy");

	
	/* Release the proxy object. */
//	(*env)->DeleteGlobalRef(env, s_proxyObject);
//	s_proxyObject = NULL;

//	LOGD("Java_br_pucrio_tecgraf_iup_IupLaunchActivity_doDestroy end");
	
}

