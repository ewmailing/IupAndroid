/** \file
 * \brief Timer for the Mac Driver.
 *
 * See Copyright Notice in "iup.h"
 */



 
#include <stdio.h>
#include <stdlib.h>

#include "iup.h"
#include "iupcbs.h"

#include "iup_object.h"
#include "iup_attrib.h"
#include "iup_str.h"
#include "iup_assert.h"
#include "iup_timer.h"

#include "iupandroid_drv.h"
#include <jni.h>
#include <android/log.h>


void iupdrvTimerRun(Ihandle* ih)
{
	JNIEnv* jni_env = iupAndroid_GetEnvThreadSafe();
	jclass java_class;
	jmethodID method_id;
	jobject java_widget;

	java_class = (*jni_env)->FindClass(jni_env, "br/pucrio/tecgraf/iup/IupTimerHelper");


	if(NULL == ih->handle) /* timer not already created */
	{
		method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "createTimer", "(J)Lbr/pucrio/tecgraf/iup/IupTimer;");
		java_widget = (*jni_env)->CallStaticObjectMethod(jni_env, java_class, method_id, (jlong)(intptr_t)ih);
		ih->handle = (jobject)((*jni_env)->NewGlobalRef(jni_env, java_widget));
		(*jni_env)->DeleteLocalRef(jni_env, java_widget);
	}
	else
	{
		java_widget = ih->handle;
	}

	unsigned int time_ms = iupAttribGetInt(ih, "TIME");
	if(time_ms > 0)
	{
		method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "startTimer", "(JLbr/pucrio/tecgraf/iup/IupTimer;J)V");
		(*jni_env)->CallStaticVoidMethod(jni_env, java_class, method_id, (jlong)(intptr_t)ih, java_widget, time_ms);

	}

	(*jni_env)->DeleteLocalRef(jni_env, java_class);
}

void iupdrvTimerStop(Ihandle* ih)
{
	if(NULL != ih->handle)
	{
		JNIEnv* jni_env = iupAndroid_GetEnvThreadSafe();
		jclass java_class;
		jmethodID method_id;
		jobject java_widget = (jobject)ih->handle;

		java_class = (*jni_env)->FindClass(jni_env, "br/pucrio/tecgraf/iup/IupTimerHelper");
		method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "stopTimer", "(JLbr/pucrio/tecgraf/iup/IupTimer;)V");
		(*jni_env)->CallStaticVoidMethod(jni_env, java_class, method_id, (jlong)(intptr_t)ih, java_widget);
		
		(*jni_env)->DeleteLocalRef(jni_env, java_class);
	}
}

static void androidTimerDestroy(Ihandle* ih)
{
	iupdrvTimerStop(ih);

	if(NULL != ih->handle)
	{
		JNIEnv* jni_env = iupAndroid_GetEnvThreadSafe();
		jobject java_widget = (jobject)ih->handle;
		(*jni_env)->DeleteGlobalRef(jni_env, java_widget);

		ih->handle = NULL;
	}
}

void iupdrvTimerInitClass(Iclass* ic)
{
	(void)ic;
	// This must be UnMap and not Destroy because we're using the ih->handle and UnMap will clear the pointer to NULL before we reach Destroy.
	ic->UnMap = androidTimerDestroy;


}


