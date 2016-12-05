//
// Created by Litao-pc on 2016/11/8.
//

#ifndef COLLECT_MARSTER_IEIM_H
#define COLLECT_MARSTER_IEIM_H

#endif
#ifndef IEIM_H_
#define IEIM_H_

#include <jni.h>



class IEIM {

public:
    IEIM();
    virtual ~IEIM();
    const char* setAndroidDeviceID(JNIEnv *env,jobject thiz, jobject mContext);
};

#endif /* IEIM_H_ */