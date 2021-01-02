//
// Created by justa on 1/2/2021.
//

#include <jni.h>

JNIEXPORT jlong JNICALL
Java_id_ac_ui_cs_mobileprogramming_farrilzavierfernaldy_cashtrack_views_WalletListFragment_subtract( JNIEnv* env, jobject thiz, jlong minuend, jlong subtrahend ) {
    long diff = minuend - subtrahend;
    return diff;
}