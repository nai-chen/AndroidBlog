#include <jni.h>
#include "com_blog_demo_jni_NativeCalc.h"
 
JNIEXPORT jdouble JNICALL Java_com_blog_demo_jni_NativeCalc_add
        (JNIEnv *env, jobject obj, jdouble left, jdouble right) {
    return left + right;
}
 
JNIEXPORT jdouble JNICALL Java_com_blog_demo_jni_NativeCalc_subtract
        (JNIEnv *env, jobject obj, jdouble left, jdouble right) {
    return left - right;
}
 
JNIEXPORT jdouble JNICALL Java_com_blog_demo_jni_NativeCalc_multiply
        (JNIEnv *env, jobject obj, jdouble left, jdouble right) {
    return left * right;
}
 
JNIEXPORT jdouble JNICALL Java_com_blog_demo_jni_NativeCalc_divide
        (JNIEnv *env, jobject obj, jdouble left, jdouble right) {
    return left / right;
}
