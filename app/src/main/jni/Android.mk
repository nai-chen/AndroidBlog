LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := calcLibs
LOCAL_SRC_FILES := com_blog_demo_jni_NativeCalc.cpp

include $(BUILD_SHARED_LIBRARY)