
#include <jni.h>
#include <android/log.h>
#include "iupandroid_drv.h"



JNIEXPORT void JNICALL Java_br_pucrio_tecgraf_iup_IupLaunchActivity_IupEntry(JNIEnv* env, jobject thiz, jobject current_activity)
{


	__android_log_print(ANDROID_LOG_INFO,  "IupLaunchActivity", "Calling IupEntry");

	IupEntry();
	
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

