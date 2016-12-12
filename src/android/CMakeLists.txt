# Copyright PlayControl Software LLC.
# Get the Blurrr SDK at http://blurrrsdk.com

IF(APPLE)
	# Need @rpath support introduced in 2.8.12
	CMAKE_MINIMUM_REQUIRED(VERSION 2.8.12)
ELSE()
	# I'm using file(COPY). I think that requires 2.8.
	CMAKE_MINIMUM_REQUIRED(VERSION 2.8)
ENDIF()

if(NOT BLURRR_SDK_PATH)
	message(FATAL_ERROR "This project requires the Blurrr SDK. Please use Blurrr's genproj_* wrapper scripts or BlurrrGenProj GUI tool instead of using direct cmake invocation.")
endif()

# Make sure CMake can find the Blurrr modules, both in this project and the SDK.
SET(CMAKE_MODULE_PATH 
	"${CMAKE_SOURCE_DIR}/CMakeModules/" 
	"${BLURRR_SHARED_CMAKE_SOURCE_DIR}/CMakeModules/" 
	"${CMAKE_SOURCE_DIR}/" 
	"${BLURRR_SHARED_CMAKE_SOURCE_DIR}/" 
	${CMAKE_MODULE_PATH}
)

# The main logic starts in the file below.
# This file will be read from inside the SDK, 
# unless your own copy is in the project (as an override).
INCLUDE(BlurrrApplication)

