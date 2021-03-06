/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_kubeek_device_k3screendevice_K3Screen */

#ifndef _Included_com_kubeek_device_k3screendevice_K3Screen
#define _Included_com_kubeek_device_k3screendevice_K3Screen
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    init
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_init
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_close
  (JNIEnv *, jobject);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plot
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plot
  (JNIEnv *, jobject, jint, jint, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotAll
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotAll
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    _plot
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen__1plot
  (JNIEnv *, jobject, jint, jint, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_update
  (JNIEnv *, jobject);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    clear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_clear
  (JNIEnv *, jobject);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    fade
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_fade
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    setBright
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_setBright
  (JNIEnv *, jobject);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotLine
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotLine
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotCircle
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotCircle
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotEllipseRect
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotEllipseRect
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotBasicBezier
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotBasicBezier
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    _putChar
 * Signature: (IICLjava/lang/String;III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen__1putChar
  (JNIEnv *, jobject, jint, jint, jchar, jstring, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    putChar
 * Signature: (IICLjava/lang/String;III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_putChar
  (JNIEnv *, jobject, jint, jint, jchar, jstring, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    scrollText
 * Signature: (ILjava/lang/String;Ljava/lang/String;IIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_scrollText
  (JNIEnv *, jobject, jint, jstring, jstring, jint, jint, jint, jint);

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    scrollPPM
 * Signature: (Ljava/lang/String;III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_scrollPPM
  (JNIEnv *, jobject, jstring, jint, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
