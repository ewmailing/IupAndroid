/** \file
 * \brief Label Control
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
#include "iup_label.h"
#include "iup_drv.h"
#include "iup_image.h"
#include "iup_focus.h"

#include "iup_childtree.h"

#include "iupandroid_drv.h"
#include <android/log.h>
#include <jni.h>

typedef enum
{
	IUPANDROIDLABELSUBTYPE_UNKNOWN = -1,
	IUPANDROIDLABELSUBTYPE_SEP_HORIZONTAL,
	IUPANDROIDLABELSUBTYPE_SEP_VERTICAL,
	IUPANDROIDLABELSUBTYPE_IMAGE,
	IUPANDROIDLABELSUBTYPE_TEXT,
} IupAndroidLabelSubType;

/*
 Each IUP list subtype requires a completely different Cocoa native widget.
 This function provides a consistent and centralized way to distinguish which subtype we need.
 */
static IupAndroidLabelSubType androidTextGetSubType(Ihandle* ih)
{
	switch(ih->data->type)
	{
		case IUP_LABEL_SEP_HORIZ:
		{
			return IUPANDROIDLABELSUBTYPE_SEP_HORIZONTAL;
		}
		case IUP_LABEL_SEP_VERT:
		{
			return IUPANDROIDLABELSUBTYPE_SEP_VERTICAL;
		}
		case IUP_LABEL_IMAGE:
		{
			return IUPANDROIDLABELSUBTYPE_IMAGE;
		}
		case IUP_LABEL_TEXT:
		{
			return IUPANDROIDLABELSUBTYPE_TEXT;
		}
		default:
		{
			return IUPANDROIDLABELSUBTYPE_UNKNOWN;
		}
	}

	return IUPANDROIDLABELSUBTYPE_UNKNOWN;
}


void iupdrvLabelAddBorders(Ihandle* ih, int *x, int *y)
{
  (void)ih;
  (void)x;
  (void)y;
}


static int androidLabelSetTitleAttrib(Ihandle* ih, const char* value)
{
	JNIEnv* jni_env = iupAndroid_GetEnvThreadSafe();
	jclass java_class = (*jni_env)->FindClass(jni_env, "br/pucrio/tecgraf/iup/IupLabelHelper");
	jmethodID method_id = NULL;
	char* attribute_value = NULL;
	jobject java_widget = NULL;

	if(NULL == value)
	{
		value = "";
	}


	IupAndroidLabelSubType sub_type = androidTextGetSubType(ih);
	switch(sub_type)
	{
		case IUPANDROIDLABELSUBTYPE_TEXT:
		{
			method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "setText", "(JLandroid/widget/TextView;Ljava/lang/String;)V");

			jstring j_string = (*jni_env)->NewStringUTF(jni_env, value);

			(*jni_env)->CallStaticVoidMethod(jni_env, java_class, method_id,
					(jlong)(intptr_t) ih,
					(jobject)ih->handle,
					j_string

			);

			(*jni_env)->DeleteLocalRef(jni_env, j_string);


			break;
		}


		default:
		{
			break;
		}
	}

	(*jni_env)->DeleteLocalRef(jni_env, java_class);

	return 0; // not sure, 0 or 1?
}

static char* androidLabelGetTitleAttrib(Ihandle* ih)
{
	char* value = NULL;

	JNIEnv* jni_env = iupAndroid_GetEnvThreadSafe();
	jclass java_class = (*jni_env)->FindClass(jni_env, "br/pucrio/tecgraf/iup/IupLabelHelper");
	jmethodID method_id = NULL;


	IupAndroidLabelSubType sub_type = androidTextGetSubType(ih);
	switch(sub_type)
	{
		case IUPANDROIDLABELSUBTYPE_TEXT:
		{
			method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "getText",
					"(JLandroid/widget/TextView;)Ljava/lang/String;");
			jstring j_string = (jstring)(*jni_env)->CallStaticObjectMethod(jni_env, java_class, method_id,
					(jlong)(intptr_t)ih,
					(jobject)ih->handle
			);


			const char* c_string = (*jni_env)->GetStringUTFChars(jni_env, j_string, NULL);
			if(NULL != c_string)
			{
				value = iupStrReturnStr(c_string);
			}
			else
			{
				value = NULL;
			}

			(*jni_env)->ReleaseStringUTFChars(jni_env, j_string, c_string);
			(*jni_env)->DeleteLocalRef(jni_env, j_string);


			break;
		}

		default:
		{
			break;
		}
	}

	(*jni_env)->DeleteLocalRef(jni_env, java_class);


	return value;
}

static int androidLabelMapMethod(Ihandle* ih)
{
	JNIEnv* jni_env = iupAndroid_GetEnvThreadSafe();
	jclass java_class = (*jni_env)->FindClass(jni_env, "br/pucrio/tecgraf/iup/IupLabelHelper");
	jmethodID method_id = NULL;
//	char* attribute_value = NULL;
	jobject java_widget = NULL;

	char* value;
	// using id because we may be using different types depending on the case

	value = iupAttribGet(ih, "SEPARATOR");
	if (value)
	{
		if (iupStrEqualNoCase(value, "HORIZONTAL"))
		{
			ih->data->type = IUP_LABEL_SEP_HORIZ;


			
		}
		else /* "VERTICAL" */
		{
			ih->data->type = IUP_LABEL_SEP_VERT;


		}
	}
	else
	{
		value = iupAttribGet(ih, "IMAGE");
		if (value)
		{
			ih->data->type = IUP_LABEL_IMAGE;
			
			char *name;
			int make_inactive = 0;
			
			if (iupdrvIsActive(ih))
    name = iupAttribGet(ih, "IMAGE");
			else
			{
    name = iupAttribGet(ih, "IMINACTIVE");
    if (!name)
	{
		name = iupAttribGet(ih, "IMAGE");
		make_inactive = 1;
	}
			}
			
			


		}
		else
		{
			ih->data->type = IUP_LABEL_TEXT;







					method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "createLabelText",
															  "(J)Landroid/widget/TextView;");
					java_widget = (*jni_env)->CallStaticObjectMethod(jni_env, java_class, method_id,
																	 (jlong) (intptr_t) ih);











			}



		
		}



	(*jni_env)->DeleteLocalRef(jni_env, java_class);

	if(!java_widget)
	{
		return IUP_ERROR;
	}

	ih->handle = (jobject) ((*jni_env)->NewGlobalRef(jni_env, java_widget));

	(*jni_env)->DeleteLocalRef(jni_env, java_widget);

	iupAndroid_AddWidgetToParent(jni_env, ih);


	
	
	/* add to the parent, all GTK controls must call this. */
//	iupgtkAddToParent(ih);
	
	
//	Ihandle* ih_parent = ih->parent;
//	id parent_native_handle = ih_parent->handle;

	iupAndroid_AddWidgetToParent(jni_env, ih);

	
	/* configure for DRAG&DROP of files */
	if (IupGetCallback(ih, "DROPFILES_CB"))
	{
		iupAttribSet(ih, "DROPFILESTARGET", "YES");
	}

	return IUP_NOERROR;
}


static void androidLabelUnMapMethod(Ihandle* ih)
{
	if(ih && ih->handle)
	{
		JNIEnv* jni_env;
		jclass java_class;
		jmethodID method_id;
		jni_env = iupAndroid_GetEnvThreadSafe();

		java_class = (*jni_env)->FindClass(jni_env, "br/pucrio/tecgraf/iup/IupCommon");
		method_id = (*jni_env)->GetStaticMethodID(jni_env, java_class, "removeWidgetFromParent", "(J)V");
		(*jni_env)->CallStaticVoidMethod(jni_env, java_class, method_id, (jlong)(intptr_t)ih);
		(*jni_env)->DeleteLocalRef(jni_env, java_class);


		(*jni_env)->DeleteGlobalRef(jni_env, ih->handle);
		ih->handle = NULL;
	}
}

void iupdrvLabelInitClass(Iclass* ic)
{
  /* Driver Dependent Class functions */
//  ic->Map = androidLabelMapMethod;
//	ic->UnMap = androidLabelUnMapMethod;

#if 0

  /* Driver Dependent Attribute functions */

  /* Overwrite Visual */
  iupClassRegisterAttribute(ic, "ACTIVE", iupBaseGetActiveAttrib, gtkLabelSetActiveAttrib, IUPAF_SAMEASSYSTEM, "YES", IUPAF_DEFAULT);

  /* Visual */
  iupClassRegisterAttribute(ic, "BGCOLOR", iupBaseNativeParentGetBgColorAttrib, gtkLabelSetBgColorAttrib, IUPAF_SAMEASSYSTEM, "DLGBGCOLOR", IUPAF_DEFAULT);

  /* Special */
  iupClassRegisterAttribute(ic, "FGCOLOR", NULL, iupdrvBaseSetFgColorAttrib, IUPAF_SAMEASSYSTEM, "DLGFGCOLOR", IUPAF_DEFAULT);
	
#endif

	iupClassRegisterAttribute(ic, "TITLE", androidLabelGetTitleAttrib, androidLabelSetTitleAttrib, NULL, NULL, IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);


#if 0
  /* IupLabel only */
  iupClassRegisterAttribute(ic, "ALIGNMENT", NULL, gtkLabelSetAlignmentAttrib, "ALEFT:ACENTER", NULL, IUPAF_NO_INHERIT);  /* force new default value */
  iupClassRegisterAttribute(ic, "IMAGE", NULL, gtkLabelSetImageAttrib, NULL, NULL, IUPAF_IHANDLENAME|IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);
  iupClassRegisterAttribute(ic, "PADDING", iupLabelGetPaddingAttrib, gtkLabelSetPaddingAttrib, IUPAF_SAMEASSYSTEM, "0x0", IUPAF_NOT_MAPPED);

  /* IupLabel GTK and Motif only */
  iupClassRegisterAttribute(ic, "IMINACTIVE", NULL, gtkLabelSetImInactiveAttrib, NULL, NULL, IUPAF_IHANDLENAME|IUPAF_NO_DEFAULTVALUE|IUPAF_NO_INHERIT);

  /* IupLabel Windows and GTK only */
  iupClassRegisterAttribute(ic, "WORDWRAP", NULL, gtkLabelSetWordWrapAttrib, NULL, NULL, IUPAF_DEFAULT);
  iupClassRegisterAttribute(ic, "ELLIPSIS", NULL, gtkLabelSetEllipsisAttrib, NULL, NULL, IUPAF_DEFAULT);

  /* IupLabel GTK only */
  iupClassRegisterAttribute(ic, "MARKUP", NULL, NULL, NULL, NULL, IUPAF_DEFAULT);
#endif
}
