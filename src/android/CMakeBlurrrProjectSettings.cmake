# This file is designed to help isolate your specific project settings.
# The variable names are predetermined. DO NOT CHANGE the variable names.
# You may change the values of the variables.
# Please do not remove any comment automation markers or put anything in between. They are reserved for future use.

# Set the name of your project here. Avoid spaces and funny characters. (Change the part in quotes, not the variable name BLURRR_USER_PROJECT_NAME)
set(BLURRR_USER_PROJECT_NAME "MyBlurrrIupProject")

# Set the name of your executable here. Avoid spaces and funny characters. (Change the part in quotes, not the variable name BLURRR_USER_PROJECT_NAME)
set(BLURRR_USER_EXECUTABLE_NAME "MyBlurrrIupProject")

# Set the human readable name of program your  here. (Change the part in quotes, not the variable name BLURRR_USER_HUMAN_NAME)
set(BLURRR_USER_HUMAN_NAME "My Blurrr IUP Project")

# Your company identifier
set(BLURRR_USER_COMPANY_IDENTIFIER "net.playcontrol")
# The name of your unique bundle identifer.
set(BLURRR_USER_BUNDLE_IDENTIFIER "${BLURRR_USER_COMPANY_IDENTIFIER}.${BLURRR_USER_EXECUTABLE_NAME}")

# You must create a unique GUID for your application for WinRT. Other platforms can ignore this.
set(BLURRR_USER_PACKAGE_GUID "01234567-89ab-cdef-0123-456789abcdef")

set(BLURRR_VERSION_MAJOR "1")
set(BLURRR_VERSION_MINOR "0")
set(BLURRR_VERSION_PATCH "0")

set(BLURRR_USER_APP_DESCRIPTION "Game made with Blurrr SDK")



# Code Signing: Maybe changing in the IDE directly is sufficent. If not, change it here.
macro(BLURRR_CONFIGURE_CODESIGNING _EXE_NAME)

	if(BLURRR_PLATFORM_IOS)
		# iPhone Developer is the key you use to test on your own devices.
		# iPhone Distribution is the key you use to ship on the iOS App Store.
	
		# This if statement will allow you to specify the variable through the command line without being overridden.
		if(NOT BLURRR_IOS_CODESIGN_IDENTITY)

			set(BLURRR_IOS_CODESIGN_IDENTITY "iPhone Developer")
			# set(BLURRR_IOS_CODESIGN_IDENTITY "iPhone Distribution")
			
		endif()

		set_xcode_property(${_EXE_NAME} CODE_SIGN_IDENTITY "${BLURRR_IOS_CODESIGN_IDENTITY}")


	elseif(BLURRR_PLATFORM_OSX)
		# For Mac: There are multiple options.
		# GateKeeper (Developer ID Application) for signed non-Mac App Store apps
		# Mac Developer for developing Mac App Store apps
		# Mac Distribution (3rd Party Mac Developer Application) for actual Mac App Store deployment
		# And None ("") for no codesigning.
		# For shipping real apps, you should always sign with Developer ID or Mac Distribution.
		# Also, you should generally enable sandboxing out of courtesy for your users (and is required for MAS).
		# And for JavaScript debugging with Safari, you need to codesign to use the com.apple.security.get-task-allow entitlement.
		# But for faster development iteration, you may want to disable codesigning because the build takes longer.

		# This if statement will allow you to specify the variable through the command line without being overridden.
		if(NOT BLURRR_OSX_CODESIGN_IDENTITY)

			set(BLURRR_OSX_CODESIGN_IDENTITY "")
			# set(BLURRR_OSX_CODESIGN_IDENTITY "Developer ID Application")
			# set(BLURRR_OSX_CODESIGN_IDENTITY "Mac Developer")
			# set(BLURRR_OSX_CODESIGN_IDENTITY "3rd Party Mac Developer Application")
		endif()

		set_xcode_property(${_EXE_NAME} CODE_SIGN_IDENTITY "${BLURRR_OSX_CODESIGN_IDENTITY}")

		# This will let you override some of the entitlement defaults in Blurrr-OSX-Template.entitlements.in
		set(OSX_ENTITLEMENT_APP_SANDBOX true)
		set(OSX_ENTITLEMENT_NETWORK_CLIENT false)
		set(OSX_ENTITLEMENT_NETWORK_SERVER false)

		if(BLURRR_USE_JAVASCRIPTCORE)
			# Enable this for JavaScript debugging with Safari. Remember to disable when you ship your app.
			# set(OSX_ENTITLEMENT_GET_TASK_ALLOW true)
		endif()

		

	endif()
endmacro(BLURRR_CONFIGURE_CODESIGNING)


macro(BLURRR_CONFIGURE_APPLICATION_MANIFEST _EXE_NAME)

	if(BLURRR_PLATFORM_APPLE)

		set(MACOSX_BUNDLE_GUI_IDENTIFIER "${BLURRR_USER_BUNDLE_IDENTIFIER}")
		set(MACOSX_BUNDLE_DISPLAY_NAME "${_EXE_NAME}")
		set(MACOSX_BUNDLE_EXECUTABLE_NAME "${_EXE_NAME}")
		set(MACOSX_BUNDLE_SHORT_VERSION_STRING "${BLURRR_VERSION_MAJOR}.${BLURRR_VERSION_MINOR}")
		set(MACOSX_BUNDLE_BUNDLE_VERSION "${BLURRR_VERSION_MAJOR}.${BLURRR_VERSION_MINOR}")
		
	endif()
	
endmacro(BLURRR_CONFIGURE_APPLICATION_MANIFEST)


