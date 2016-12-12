# This file is designed to help isolate your specific project settings.
# The variable names are predetermined. DO NOT CHANGE the variable names.
# You may change the values of the variables.


# You have a choice. 
# When you add resource files, you may manually add it here.
# Or you may disable manual mode and have the system automatically create the list.
# The downside to automatic mode is that you must remember to regenerate when you add a new file.
# The upside to manual mode is you can get fancy and case for things like Windows/Android/Mac/iOS/Linux.
# CMake officially recommends manual mode.

set(BLURRR_USE_MANUAL_FILE_LISTING_FOR_RESOURCE_FILES TRUE)
set(BLURRR_USE_MANUAL_FILE_LISTING_FOR_SCRIPT_FILES TRUE)

# Resource files
if(BLURRR_USE_MANUAL_FILE_LISTING_FOR_RESOURCE_FILES)

	# Add your user resource files here
	list(APPEND BLURRR_USER_RESOURCE_FILES
#		${PROJECT_SOURCE_DIR}/resources/icon.bmp
#		${PROJECT_SOURCE_DIR}/resources/VeraMono.ttf

	)
else()
	# We will automatically slurp everything.
	# But you must remember to regenerate every time you add a file.
	set(BLURRR_AUTOMATIC_USER_RESOURCE_FILES_SHOULD_FOLLOW_SYMLINKS TRUE)
	set(BLURRR_AUTOMATIC_USER_RESOURCE_FILES_GLOB_PATTERN "${PROJECT_SOURCE_DIR}/resources/*")
endif()

# Script files
if(BLURRR_USE_MANUAL_FILE_LISTING_FOR_SCRIPT_FILES)

	# This is an example of how to be clever (but still maintainable).
	# We just want the variable BLURRR_USER_SCRIPT_FILES defined.
	# If you have incompatible scripts like using new language features in Lua 5.3 vs. Lua 5.1 (LuaJIT), 
	# then you can conditionalize what gets put into BLURRR_USER_SCRIPT_FILES.

	list(APPEND BLURRR_USER_SCRIPT_FILES_LUA53
		# ${PROJECT_SOURCE_DIR}/scripts/example_native_bitops.lua
		
	)
	list(APPEND BLURRR_USER_SCRIPT_FILES_LUAJIT
		# ${PROJECT_SOURCE_DIR}/scripts/example_jit_control.lua

	)
	list(APPEND BLURRR_USER_SCRIPT_FILES_LUACOMMON
		# mobdebug is often used for Lua debugging such as with ZeroBrane Studio. Uncomment this file for debugging.
		# ${PROJECT_SOURCE_DIR}/scripts/mobdebug.lua

	)
	list(APPEND BLURRR_USER_SCRIPT_FILES_JAVASCRIPT
		# ${PROJECT_SOURCE_DIR}/scripts/exmaple_render_stuff.js

	)

	if(BLURRR_USE_LUA)
		list(APPEND BLURRR_USER_SCRIPT_FILES ${BLURRR_USER_SCRIPT_FILES_LUACOMMON})
		if(BLURRR_USE_LUA53)
			list(APPEND BLURRR_USER_SCRIPT_FILES ${BLURRR_USER_SCRIPT_FILES_LUA53})
		elseif(BLURRR_USE_LUAJIT)
			list(APPEND BLURRR_USER_SCRIPT_FILES ${BLURRR_USER_SCRIPT_FILES_LUAJIT})
		endif()
	elseif(BLURRR_USE_JAVASCRIPT)
		list(APPEND BLURRR_USER_SCRIPT_FILES ${BLURRR_USER_SCRIPT_FILES_JAVASCRIPT})
	endif()


else()
	# We will automatically slurp everything.
	# But you must remember to regenerate every time you add a file.
	set(BLURRR_AUTOMATIC_USER_SCRIPT_FILES_SHOULD_FOLLOW_SYMLINKS TRUE)
	set(BLURRR_AUTOMATIC_USER_SCRIPT_FILES_GLOB_PATTERN "${PROJECT_SOURCE_DIR}/scripts/*")
endif()


if(BLURRR_USE_SWIFT)
	# Add your Swift files here
	list(APPEND BLURRR_USER_SWIFT_COMPILED_FILES
		# This is BlurrrMain.swift, main.swift if applicable, and anything else needed.
		${BLURRR_CORE_SWIFT_SOURCE_MAIN}
	)

	# This is temporary. For now, we put the bindings directly as source 
	# in the project since these are evolving.
	# Eventually when these stabilize, we'll move these into prebuilt modules. 
	list(APPEND BLURRR_USER_COMPILED_FILES
		${BLURRR_CORE_SOURCE_SWIFT_BINDINGS}
	)

endif()

# Add your C/C++/Obj-C files here
list(APPEND BLURRR_USER_COMPILED_FILES
	# This is main_c.c or main_lua.c or main_JavaScriptCore.c
	#	${BLURRR_CORE_SOURCE_MAIN}
#	${PROJECT_SOURCE_DIR}/source/android/main_android_iup.c
	${PROJECT_SOURCE_DIR}/source/main_c.c
#	${PROJECT_SOURCE_DIR}/source/myfile.c	




${PROJECT_SOURCE_DIR}/../iup.c
${PROJECT_SOURCE_DIR}/../iup_array.c
${PROJECT_SOURCE_DIR}/../iup_array.h
${PROJECT_SOURCE_DIR}/../iup_assert.c
${PROJECT_SOURCE_DIR}/../iup_assert.h
${PROJECT_SOURCE_DIR}/../iup_attrib.c
${PROJECT_SOURCE_DIR}/../iup_attrib.h
${PROJECT_SOURCE_DIR}/../iup_backgroundbox.c
${PROJECT_SOURCE_DIR}/../iup_box.c
${PROJECT_SOURCE_DIR}/../iup_box.h
${PROJECT_SOURCE_DIR}/../iup_button.c
${PROJECT_SOURCE_DIR}/../iup_button.h
${PROJECT_SOURCE_DIR}/../iup_callback.c
${PROJECT_SOURCE_DIR}/../iup_canvas.c
${PROJECT_SOURCE_DIR}/../iup_canvas.h
${PROJECT_SOURCE_DIR}/../iup_cbox.c
${PROJECT_SOURCE_DIR}/../iup_childtree.c
${PROJECT_SOURCE_DIR}/../iup_childtree.h
${PROJECT_SOURCE_DIR}/../iup_class.c
${PROJECT_SOURCE_DIR}/../iup_class.h
${PROJECT_SOURCE_DIR}/../iup_classattrib.c
${PROJECT_SOURCE_DIR}/../iup_classbase.c
${PROJECT_SOURCE_DIR}/../iup_classbase.h
${PROJECT_SOURCE_DIR}/../iup_colordlg.c
${PROJECT_SOURCE_DIR}/../iup_config.c
${PROJECT_SOURCE_DIR}/../iup_detachbox.c
${PROJECT_SOURCE_DIR}/../iup_dialog.c
${PROJECT_SOURCE_DIR}/../iup_dialog.h
${PROJECT_SOURCE_DIR}/../iup_dlglist.c
${PROJECT_SOURCE_DIR}/../iup_dlglist.h
${PROJECT_SOURCE_DIR}/../iup_draw.h
${PROJECT_SOURCE_DIR}/../iup_drv.h
${PROJECT_SOURCE_DIR}/../iup_drvfont.h
${PROJECT_SOURCE_DIR}/../iup_drvinfo.h
${PROJECT_SOURCE_DIR}/../iup_expander.c
${PROJECT_SOURCE_DIR}/../iup_filedlg.c
${PROJECT_SOURCE_DIR}/../iup_fill.c
${PROJECT_SOURCE_DIR}/../iup_flatbutton.c
${PROJECT_SOURCE_DIR}/../iup_focus.c
${PROJECT_SOURCE_DIR}/../iup_focus.h
${PROJECT_SOURCE_DIR}/../iup_font.c
${PROJECT_SOURCE_DIR}/../iup_fontdlg.c
${PROJECT_SOURCE_DIR}/../iup_frame.c
${PROJECT_SOURCE_DIR}/../iup_frame.h
${PROJECT_SOURCE_DIR}/../iup_func.c
${PROJECT_SOURCE_DIR}/../iup_func.h
${PROJECT_SOURCE_DIR}/../iup_getparam.c
${PROJECT_SOURCE_DIR}/../iup_globalattrib.c
${PROJECT_SOURCE_DIR}/../iup_globalattrib.h
${PROJECT_SOURCE_DIR}/../iup_gridbox.c
${PROJECT_SOURCE_DIR}/../iup_hbox.c
${PROJECT_SOURCE_DIR}/../iup_image.c
${PROJECT_SOURCE_DIR}/../iup_image.h
${PROJECT_SOURCE_DIR}/../iup_key.c
${PROJECT_SOURCE_DIR}/../iup_key.h
${PROJECT_SOURCE_DIR}/../iup_label.c
${PROJECT_SOURCE_DIR}/../iup_label.h
${PROJECT_SOURCE_DIR}/../iup_layout.c
${PROJECT_SOURCE_DIR}/../iup_layout.h
${PROJECT_SOURCE_DIR}/../iup_layoutdlg.c
${PROJECT_SOURCE_DIR}/../iup_ledlex.c
${PROJECT_SOURCE_DIR}/../iup_ledlex.h
${PROJECT_SOURCE_DIR}/../iup_ledparse.c
${PROJECT_SOURCE_DIR}/../iup_linefile.c
${PROJECT_SOURCE_DIR}/../iup_linefile.h
${PROJECT_SOURCE_DIR}/../iup_link.c
${PROJECT_SOURCE_DIR}/../iup_list.c
${PROJECT_SOURCE_DIR}/../iup_list.h
${PROJECT_SOURCE_DIR}/../iup_mask.c
${PROJECT_SOURCE_DIR}/../iup_mask.h
${PROJECT_SOURCE_DIR}/../iup_maskmatch.c
${PROJECT_SOURCE_DIR}/../iup_maskmatch.h
${PROJECT_SOURCE_DIR}/../iup_maskparse.c
${PROJECT_SOURCE_DIR}/../iup_maskparse.h
${PROJECT_SOURCE_DIR}/../iup_menu.c
${PROJECT_SOURCE_DIR}/../iup_menu.h
${PROJECT_SOURCE_DIR}/../iup_messagedlg.c
${PROJECT_SOURCE_DIR}/../iup_names.c
${PROJECT_SOURCE_DIR}/../iup_names.h
${PROJECT_SOURCE_DIR}/../iup_normalizer.c
${PROJECT_SOURCE_DIR}/../iup_normalizer.h
${PROJECT_SOURCE_DIR}/../iup_object.c
${PROJECT_SOURCE_DIR}/../iup_object.h
${PROJECT_SOURCE_DIR}/../iup_open.c
${PROJECT_SOURCE_DIR}/../iup_predialogs.c
${PROJECT_SOURCE_DIR}/../iup_predialogs.h
${PROJECT_SOURCE_DIR}/../iup_progressbar.c
${PROJECT_SOURCE_DIR}/../iup_progressbar.h
${PROJECT_SOURCE_DIR}/../iup_progressdlg.c
${PROJECT_SOURCE_DIR}/../iup_radio.c
${PROJECT_SOURCE_DIR}/../iup_recplay.c
${PROJECT_SOURCE_DIR}/../iup_register.c
${PROJECT_SOURCE_DIR}/../iup_register.h
${PROJECT_SOURCE_DIR}/../iup_sbox.c
${PROJECT_SOURCE_DIR}/../iup_scanf.c
${PROJECT_SOURCE_DIR}/../iup_scrollbox.c
${PROJECT_SOURCE_DIR}/../iup_show.c
${PROJECT_SOURCE_DIR}/../iup_spin.c
${PROJECT_SOURCE_DIR}/../iup_split.c
${PROJECT_SOURCE_DIR}/../iup_stdcontrols.h
${PROJECT_SOURCE_DIR}/../iup_str.c
${PROJECT_SOURCE_DIR}/../iup_str.h
${PROJECT_SOURCE_DIR}/../iup_strmessage.c
${PROJECT_SOURCE_DIR}/../iup_strmessage.h
${PROJECT_SOURCE_DIR}/../iup_table.c
${PROJECT_SOURCE_DIR}/../iup_table.h
${PROJECT_SOURCE_DIR}/../iup_tabs.c
${PROJECT_SOURCE_DIR}/../iup_tabs.h
${PROJECT_SOURCE_DIR}/../iup_text.c
${PROJECT_SOURCE_DIR}/../iup_text.h
${PROJECT_SOURCE_DIR}/../iup_timer.c
${PROJECT_SOURCE_DIR}/../iup_timer.h
${PROJECT_SOURCE_DIR}/../iup_toggle.c
${PROJECT_SOURCE_DIR}/../iup_toggle.h
${PROJECT_SOURCE_DIR}/../iup_tree.c
${PROJECT_SOURCE_DIR}/../iup_tree.h
${PROJECT_SOURCE_DIR}/../iup_user.c
${PROJECT_SOURCE_DIR}/../iup_val.c
${PROJECT_SOURCE_DIR}/../iup_val.h
${PROJECT_SOURCE_DIR}/../iup_vbox.c
${PROJECT_SOURCE_DIR}/../iup_zbox.c

${PROJECT_SOURCE_DIR}/source/iupandroid_button.c
${PROJECT_SOURCE_DIR}/source/iupandroid_canvas.c
${PROJECT_SOURCE_DIR}/source/iupandroid_clipboard.c
${PROJECT_SOURCE_DIR}/source/iupandroid_colordlg.c
${PROJECT_SOURCE_DIR}/source/iupandroid_common.c
${PROJECT_SOURCE_DIR}/source/iupandroid_dialog.c
${PROJECT_SOURCE_DIR}/source/iupandroid_dragdrop.c
${PROJECT_SOURCE_DIR}/source/iupandroid_draw.c
${PROJECT_SOURCE_DIR}/source/iupandroid_drv.h
${PROJECT_SOURCE_DIR}/source/iupandroid_filedlg.c
${PROJECT_SOURCE_DIR}/source/iupandroid_focus.c
${PROJECT_SOURCE_DIR}/source/iupandroid_font.c
${PROJECT_SOURCE_DIR}/source/iupandroid_fontdlg.c
${PROJECT_SOURCE_DIR}/source/iupandroid_frame.c
${PROJECT_SOURCE_DIR}/source/iupandroid_globalattrib.c
${PROJECT_SOURCE_DIR}/source/iupandroid_help.c
${PROJECT_SOURCE_DIR}/source/iupandroid_image.c
${PROJECT_SOURCE_DIR}/source/iupandroid_info.c
${PROJECT_SOURCE_DIR}/source/iupandroid_jni_IupApplication.c
${PROJECT_SOURCE_DIR}/source/iupandroid_jni_IupActivity.c
${PROJECT_SOURCE_DIR}/source/iupandroid_jni_IupCommon.c
${PROJECT_SOURCE_DIR}/source/iupandroid_jni_IupLaunchActivity.c
#${PROJECT_SOURCE_DIR}/source/iupandroid_key.c
${PROJECT_SOURCE_DIR}/source/iupandroid_label.c
${PROJECT_SOURCE_DIR}/source/iupandroid_list.c
${PROJECT_SOURCE_DIR}/source/iupandroid_loop.c
${PROJECT_SOURCE_DIR}/source/iupandroid_menu.c
${PROJECT_SOURCE_DIR}/source/iupandroid_messagedlg.c
${PROJECT_SOURCE_DIR}/source/iupandroid_open.c
${PROJECT_SOURCE_DIR}/source/iupandroid_progressbar.c
${PROJECT_SOURCE_DIR}/source/iupandroid_tabs.c
${PROJECT_SOURCE_DIR}/source/iupandroid_text.c
${PROJECT_SOURCE_DIR}/source/iupandroid_timer.c
${PROJECT_SOURCE_DIR}/source/iupandroid_tips.c
${PROJECT_SOURCE_DIR}/source/iupandroid_toggle.c
${PROJECT_SOURCE_DIR}/source/iupandroid_tree.c
${PROJECT_SOURCE_DIR}/source/iupandroid_val.c


)

# Add your C/C++/Obj-C include paths here
list(APPEND BLURRR_USER_INCLUDE_PATHS
	${PROJECT_SOURCE_DIR}/source
	${PROJECT_SOURCE_DIR}/..
	${PROJECT_SOURCE_DIR}/../../include
)


# Add your find_package() stuff here as needed, 
# then set BLURRR_USER_LIBRARIES and BLURRR_USER_THIRD_PARTY_LIBRARIES_TO_EMBED
list(APPEND BLURRR_USER_LIBRARIES

)

if(BLURRR_PLATFORM_ANDROID)
	FIND_LIBRARY(ANDROID_LOG_LIBRARY log)
	list(APPEND BLURRR_USER_LIBRARIES
		${ANDROID_LOG_LIBRARY}
	)
endif()

# Remember that system libraries and static libraries don't need to be embedded.
list(APPEND BLURRR_USER_THIRD_PARTY_LIBRARIES_TO_EMBED

)


