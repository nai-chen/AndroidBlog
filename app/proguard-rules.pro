# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontpreverify				#混淆时是否做预校验
-optimizationpasses 5		#指定代码的压缩级别
-dontoptimize				#不优化输入的类文件
-dontusemixedcaseclassnames	#混淆时不会产生形形色色的类名
-repackageclasses			#把执行后的类重新放在某一个目录下，后跟一个目录名
-allowaccessmodification	#优化时允许访问并修改有修饰符的类和类的成员

-verbose					#混淆时是否记录日志
-dontskipnonpubliclibraryclasses 		#指定不去忽略非公共的库类
-dontskipnonpubliclibraryclassmembers	#指定不去忽略包可见的库类的成员

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*	# 混淆时所采用的算法
-dontwarn

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

#-libraryjars libs/android-support-v4.jar
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }

# umeng
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

# retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# rx
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# testcase
-dontwarn org.junit.**
-keep class org.junit.** { *; }
-dontwarn android.test.**
-keep class android.test.** { *; }

# andfix
-dontwarn android.annotation
-dontwarn com.alipay.euler.**
-keep class com.alipay.euler.** {*;}
-keep class * extends java.lang.annotation.Annotation

-keep public class com.blog.demo.R$*{
    public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}