#include "iup.h"
#include "iup_config.h"
#include <stddef.h>
#include <stdio.h>

#if __ANDROID__
#include <android/log.h>
void MyPrintf(const char* fmt, ...)
{
	va_list ap;	
	va_start(ap, fmt);
	__android_log_vprint(ANDROID_LOG_INFO, "IupTest", fmt, ap);
	va_end(ap);
}
#else
void MyPrintf(const char* fmt, ...)
{
	va_list ap;	
	va_start(ap, fmt);
	fprintf(stderr, fmt, ap);
	va_end(ap);
}
#endif
int OnButtonCallback()
{
	MyPrintf("OnButtonCallback()\n");

	char temp_string[1024];
	static int button_count = 0;
	snprintf(temp_string, 1024, "Iup Button %d", button_count);
	Ihandle* button = IupButton(temp_string, "");

	IupSetCallback(button, "ACTION", (Icallback)OnButtonCallback);
	Ihandle* dialog = IupDialog(button);
	snprintf(temp_string, 1024, "Iup Activity Title %d", button_count);

	IupSetStrAttribute(dialog, "TITLE", temp_string);
	


	IupShow(dialog);

	button_count++;

	return IUP_DEFAULT;
}

void IupEntryPoint()
{
//	IupOpen(0, NULL);
	int ret_val;

	Ihandle* config_file = IupConfig();
	IupSetStrAttribute(config_file, "APP_NAME", "TestApp");
	ret_val = IupConfigLoad(config_file);

	const char* config_value;
	if(ret_val == 0)
	{
		const char* config_value = IupConfigGetVariableStrDef(config_file, "Group1", "Key1", "");
		printf("config value is %s\n", config_value);
	}
	else
	{
		printf("config file not found\n");
	}
	IupConfigSetVariableStr(config_file, "Group1", "Key1", "Value1");
	IupConfigSave(config_file);
	config_value = IupConfigGetVariableStrDef(config_file, "Group1", "Key1", "");
	printf("retrieved saved config value is %s\n", config_value);

	IupDestroy(config_file);
	config_file = NULL;



	Ihandle* button = IupButton("Iup Button", "");
	IupSetCallback(button, "ACTION", (Icallback)OnButtonCallback);

	Ihandle* vb=IupVbox(button, NULL);
	IupSetAttribute(vb, "GAP", "10");
	IupSetAttribute(vb, "MARGIN", "10x10");
	IupSetAttribute(vb, "ALIGNMENT", "ACENTER");

	Ihandle* dialog = IupDialog(vb);
	//	IupMap(dialog);
	IupSetAttribute(dialog, "TITLE", "Iup Activity Title");


	IupShow(dialog);
}


