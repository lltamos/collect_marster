
#include "IEIM.h"

const char* IEIM::setAndroidDeviceID(JNIEnv *env,jobject thiz,jobject mContext)
{

    jclass cls_context = (env)->FindClass("android/content/Context");

    jmethodID getSystemService = (env)->GetMethodID(cls_context, "getSystemService", "(Ljava/lang/String;)Ljava/lang/Object;");

    jfieldID TELEPHONY_SERVICE = (env)->GetStaticFieldID( cls_context, "TELEPHONY_SERVICE", "Ljava/lang/String;");

    jobject str = (env)->GetStaticObjectField( cls_context, TELEPHONY_SERVICE);
    jobject telephonymanager = (env)->CallObjectMethod( mContext, getSystemService, str);

    jclass cls_tm = (env)->FindClass( "android/telephony/TelephonyManager");

    jmethodID getDeviceId = (env)->GetMethodID( cls_tm, "getDeviceId", "()Ljava/lang/String;");

    jobject deviceid = (env)->CallObjectMethod( telephonymanager, getDeviceId);

    return (env)->GetStringUTFChars( (jstring)deviceid, 0);
}


