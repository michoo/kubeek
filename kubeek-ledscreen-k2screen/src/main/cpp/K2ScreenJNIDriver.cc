#include <jni.h>
#include "includes/com_kubeek_device_k2screendevice_K2Screen.h"
#include "includes/K2ScreenJNIDriverImpl.h"
#include <string.h>
#include <iostream>


using namespace std;

class K2ScreenJNIDriverImplSingleton
{
  public:
    static K2ScreenJNIDriverImpl *instance();
    static void setRowsChain(int rows, int chain);
    static void deleteInstance();

   
 private:
    static K2ScreenJNIDriverImpl *inst; 
};


K2ScreenJNIDriverImpl *K2ScreenJNIDriverImplSingleton::inst = 0;



K2ScreenJNIDriverImpl *K2ScreenJNIDriverImplSingleton::instance()
{
//    cout << "K2ScreenJNIDriverImplSingleton::instance()" << endl;
    
    if (!inst){
//        cout << "K2ScreenJNIDriverImplSingleton::instance() - New instance " << endl;
        inst = new K2ScreenJNIDriverImpl();
    }else{
//        cout << "K2ScreenJNIDriverImplSingleton::instance() - Instance already exist" << endl;
    }
    
    return inst;
}

void K2ScreenJNIDriverImplSingleton::deleteInstance()
{
//    cout << "K2ScreenJNIDriverImplSingleton::deleteInstance()" << endl;
    if(inst != NULL){
       delete inst; 
    }
    

}


void K2ScreenJNIDriverImplSingleton::setRowsChain(int rows_, int chain_) {
//    cout << "K2ScreenJNIDriverImplSingleton::setRowsChain()" << endl;
//    cout << "K2ScreenJNIDriverImplSingleton::setRowsChain() - to set at rows/chain " << rows_ << "/" << chain_ << endl;
    
    if(inst != NULL){
       delete inst; 
//       cout << "K2ScreenJNIDriverImplSingleton::setRowsChain() - delete inst" << endl;
    }
    
    inst = new K2ScreenJNIDriverImpl(rows_, chain_);
    
}



/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_init
  (JNIEnv *env, jobject obj,jint rows_init, jint chain_init){
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_init" << endl;
    K2ScreenJNIDriverImplSingleton::setRowsChain(rows_init, chain_init);
    K2ScreenJNIDriverImplSingleton::instance();
}


/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_close
  (JNIEnv *env, jobject obj){
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_close" << endl;
    K2ScreenJNIDriverImplSingleton::deleteInstance();
}


/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    plot
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_plot
(JNIEnv *env, jobject obj, jint x, jint y, jint red, jint green, jint blue) {

//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_plot" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->plot(x, y, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    plotAll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_plotAll
(JNIEnv *env, jobject obj, jint red, jint green, jint blue) {

//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_plotAll" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->plotAll(red, green, blue);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    _plot
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen__1plot
(JNIEnv *env, jobject obj, jint x, jint y, jint red, jint green, jint blue) {

//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen__1plot" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->_plot(x, y, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_update
(JNIEnv *env, jobject obj) {

//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_update" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->update();
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    clear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_clear
(JNIEnv *env, jobject obj) {

//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_clear" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->clear();
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    fade
 * Signature: (CI)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_fade
(JNIEnv *env, jobject obj, jint intensity, jint delayTime) {
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_fade" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->fade(intensity, delayTime);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    setBright
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_setBright
(JNIEnv *env, jobject obj) {
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_setBright" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->setBright();
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    plotLine
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_plotLine
(JNIEnv *env, jobject obj, jint x0, jint y0, jint x1, jint y1, jint red, jint green, jint blue) {
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_plotLine" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->plotLine(x0, y0, x1, y1, red, green, blue);

}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    plotCircle
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_plotCircle
(JNIEnv *env, jobject obj, jint xm, jint ym, jint r, jint red, jint green, jint blue) {
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_plotCircle" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->plotCircle(xm, ym, r, red, green, blue);

}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    plotEllipseRect
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_plotEllipseRect
(JNIEnv *env, jobject obj, jint x0, jint y0, jint x1, jint y1, jint red, jint green, jint blue) {
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_plotEllipseRect" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->plotEllipseRect(x0, y0, x1, y1, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    plotBasicBezier
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_plotBasicBezier
(JNIEnv *env, jobject obj, jint x0, jint y0, jint x1, jint y1, jint x2, jint y2, jint red, jint green, jint blue) {
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_plotBasicBezier" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->plotBasicBezier(x0, y0, x1, y1, x2, y2, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    _putChar
 * Signature: (IICII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen__1putChar
(JNIEnv *env, jobject obj, jint x, jint y, jchar c, jstring fontname, jint red, jint green, jint blue) {
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen__1putChar" << endl;
    const char *temp = (*env).GetStringUTFChars(fontname, 0);
    int taille = strlen(temp);
    char fontname_message[taille + 1];
    strncpy(fontname_message, temp, taille);
    fontname_message[taille]='\0'; 
    K2ScreenJNIDriverImplSingleton::instance()->_putChar(x, y, c, fontname_message, red, green, blue);
    (*env).ReleaseStringUTFChars(fontname, temp);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    putChar
 * Signature: (IICII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_putChar
(JNIEnv *env, jobject obj, jint x, jint y, jchar c, jstring fontname, jint red, jint green, jint blue) {

    
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_putChar" << endl;
    
    const char *temp = (*env).GetStringUTFChars(fontname, 0);
    int taille = strlen(temp);
    char fontname_message[taille+1];
    strncpy(fontname_message, temp, taille);
    fontname_message[taille]='\0'; 
    K2ScreenJNIDriverImplSingleton::instance()->putChar(x, y, c, fontname_message, red, green, blue);
    (*env).ReleaseStringUTFChars(fontname, temp);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    scrollText
 * Signature: (ILjava/lang/String;III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_scrollText
        (JNIEnv *env, jobject obj, jint y, jstring stringJava, jstring fontname , jint delayTime, jint red, jint green, jint blue) {
    
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText" << endl;
    const char *temp = env->GetStringUTFChars(stringJava, NULL);
  
    int taille = strlen(temp);
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - message size: " <<taille<< endl;
    char message[taille];
    strncpy(message, temp,taille);
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - message after copy: " <<message<< endl;
    message[taille]='\0'; 
//    int message_taille = strlen(message);
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - message size after copy: " <<message_taille<< endl;
    
    
    env->ReleaseStringUTFChars(stringJava, temp);
    
    const char *temp2 = env->GetStringUTFChars(fontname, NULL);

    int taille2 = strlen(temp2);
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - fontname size: " <<taille2<< endl;
    char fontname_message[taille2];
    strncpy(fontname_message, temp2,taille2);
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - fontname_message after copy: " <<fontname_message<< endl;
    fontname_message[taille2]='\0'; 
//    int fontname_message_taille = strlen(fontname_message);
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - fontname_message size after copy: " <<fontname_message_taille<< endl;
    
    env->ReleaseStringUTFChars(fontname, temp2);
    

    
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - message : " <<message<< endl;
//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollText - message font: " <<fontname_message<< endl;

    K2ScreenJNIDriverImplSingleton::instance()->scrollText(y, message, fontname_message, delayTime, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k2screendevice_K2Screen
 * Method:    scrollPPM
 * Signature: (Ljava/lang/String;III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k2screendevice_K2Screen_scrollPPM
        (JNIEnv *env, jobject obj, jstring ppmfilename, jint scroll_jump, jint runtime_seconds, jint scroll_ms) {

//    cout << "K2ScreenJNIDriver::Java_com_kubeek_device_k2screendevice_K2Screen_scrollPPM" << endl;
    
    const char *temp = (*env).GetStringUTFChars(ppmfilename, 0);
    int taille = strlen(temp);
    char ppmfilename_message[taille+1];
    strncpy(ppmfilename_message, temp, taille);
    ppmfilename_message[taille]='\0'; 
    
    K2ScreenJNIDriverImplSingleton::instance()->scrollPPM(ppmfilename_message, scroll_jump, runtime_seconds, scroll_ms);
    (*env).ReleaseStringUTFChars(ppmfilename, temp);
}