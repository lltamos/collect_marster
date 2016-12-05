//
// Created by Litao-pc on 2016/11/8.
//

#include <jni.h>
#include <cmath>
#include <android/log.h>
#include <string>
#include <cstring>
#define LOGW(Message) __android_log_print(ANDROID_LOG_INFO   , "JNILog", Message)

using namespace std;
extern "C" {
//内置函数
inline string returnstring(JNIEnv* env, jstring jstr);

inline jstring returnjstring(JNIEnv* env, const char* pat);

jstring Java_com_example_JNI_jni_fun(JNIEnv* env, jobject thiz, jobject context)

{

    return returnjstring(env, "");
}

//jstring 转换 string
std::string returnstring(JNIEnv* env, jstring jstr) {
    char* rtn = NULL;
//     获取java下的string类
    jclass clsstring = env->FindClass("java/lang/String");
//     创建一个JNI下的jstring类
    jstring strencode = env->NewStringUTF("GB2312");
//     获取java下的函数getBytes的ID,参数为函数名称和string类
    jmethodID mid = env->GetMethodID(clsstring, "getBytes",
                                     "(Ljava/lang/String;)[B");
//     调用getBytes函数,参数为string字符串,函数ID和函数的参数类型,返回一个byte数组
    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, strencode);
//     获取返回的Byte数组的长度
    jsize alen = env->GetArrayLength(barr);
//     获取数组元素
    jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
    if (alen > 0) {
//         分配内存
        rtn = (char*) malloc(alen + 1);
//         字符串的拷贝
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    env->ReleaseByteArrayElements(barr, ba, 0);
    std::string stemp(rtn);
//    释放指针内存
    free(rtn);
    return stemp;
}
//char* 转换 jstring
jstring returnjstring(JNIEnv* env, const char* pat) {
    //定义java String类 strClass
    jclass strClass = (env)->FindClass("java/lang/String");
    //获取String(byte[],String)的构造器,用于将本地byte[]数组转换为一个新String
    jmethodID ctorID = (env)->GetMethodID(strClass, "<init>",
                                          "([BLjava/lang/String;)V");
    //建立byte数组
    jbyteArray bytes = (env)->NewByteArray(strlen(pat));
    //将char* 转换为byte数组
    (env)->SetByteArrayRegion(bytes, 0, strlen(pat), (jbyte*) pat);
    // 设置String, 保存语言类型,用于byte数组转换至String时的参数
    jstring encoding = (env)->NewStringUTF("GB2312");
    //将byte数组转换为java String,并输出
    return (jstring) (env)->NewObject(strClass, ctorID, bytes, encoding);
}

}