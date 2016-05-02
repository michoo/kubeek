#include <jni.h>
#include "includes/com_kubeek_device_k3screendevice_K3Screen.h"
#include "includes/K3ScreenJNIDriverImpl.h"
#include <string.h>
#include <iostream>


using namespace std;

class K3ScreenJNIDriverImplSingleton
{
  public:
    static K3ScreenJNIDriverImpl *instance();
    static void setRowsChain(int rows, int chain);
    static void deleteInstance();

   
 private:
    static K3ScreenJNIDriverImpl *inst; 
};


K3ScreenJNIDriverImpl *K3ScreenJNIDriverImplSingleton::inst = 0;



K3ScreenJNIDriverImpl *K3ScreenJNIDriverImplSingleton::instance()
{
    //cout << "K3ScreenJNIDriverImplSingleton::instance()" << endl;
    
    if (!inst){
        //cout << "K3ScreenJNIDriverImplSingleton::instance() - New instance " << endl;
        inst = new K3ScreenJNIDriverImpl();
    }else{
        //cout << "K3ScreenJNIDriverImplSingleton::instance() - Instance already exist" << endl;
    }
    
    return inst;
}

void K3ScreenJNIDriverImplSingleton::deleteInstance()
{
    //cout << "K3ScreenJNIDriverImplSingleton::deleteInstance()" << endl;
    if(inst != NULL){
       delete inst; 
    }
    

}


void K3ScreenJNIDriverImplSingleton::setRowsChain(int rows_, int chain_) {
    //cout << "K3ScreenJNIDriverImplSingleton::setRowsChain()" << endl;
    //cout << "K3ScreenJNIDriverImplSingleton::setRowsChain() - to set at rows/chain " << rows_ << "/" << chain_ << endl;
    
    deleteInstance();
    
    inst = new K3ScreenJNIDriverImpl(rows_, chain_);
    
}



/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_init
  (JNIEnv *env, jobject obj,jint rows_init, jint chain_init){
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_init" << endl;
    K3ScreenJNIDriverImplSingleton::setRowsChain(rows_init, chain_init);
    K3ScreenJNIDriverImplSingleton::instance();
}


/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_close
  (JNIEnv *env, jobject obj){
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_close" << endl;
    K3ScreenJNIDriverImplSingleton::deleteInstance();
}


/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plot
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plot
(JNIEnv *env, jobject obj, jint x, jint y, jint red, jint green, jint blue) {

    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_plot" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->plot(x, y, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotAll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotAll
(JNIEnv *env, jobject obj, jint red, jint green, jint blue) {

    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_plotAll" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->plotAll(red, green, blue);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    _plot
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen__1plot
(JNIEnv *env, jobject obj, jint x, jint y, jint red, jint green, jint blue) {

    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen__1plot" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->_plot(x, y, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_update
(JNIEnv *env, jobject obj) {

    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_update" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->update();
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    clear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_clear
(JNIEnv *env, jobject obj) {

    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_clear" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->clear();
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    fade
 * Signature: (CI)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_fade
(JNIEnv *env, jobject obj, jint intensity, jint delayTime) {
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_fade" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->fade(intensity, delayTime);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    setBright
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_setBright
(JNIEnv *env, jobject obj) {
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_setBright" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->setBright();
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotLine
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotLine
(JNIEnv *env, jobject obj, jint x0, jint y0, jint x1, jint y1, jint red, jint green, jint blue) {
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_plotLine" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->plotLine(x0, y0, x1, y1, red, green, blue);

}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotCircle
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotCircle
(JNIEnv *env, jobject obj, jint xm, jint ym, jint r, jint red, jint green, jint blue) {
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_plotCircle" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->plotCircle(xm, ym, r, red, green, blue);

}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotEllipseRect
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotEllipseRect
(JNIEnv *env, jobject obj, jint x0, jint y0, jint x1, jint y1, jint red, jint green, jint blue) {
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_plotEllipseRect" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->plotEllipseRect(x0, y0, x1, y1, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    plotBasicBezier
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_plotBasicBezier
(JNIEnv *env, jobject obj, jint x0, jint y0, jint x1, jint y1, jint x2, jint y2, jint red, jint green, jint blue) {
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_plotBasicBezier" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->plotBasicBezier(x0, y0, x1, y1, x2, y2, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    _putChar
 * Signature: (IICII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen__1putChar
(JNIEnv *env, jobject obj, jint x, jint y, jchar c, jstring fontname, jint red, jint green, jint blue) {
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen__1putChar" << endl;
    const char *temp = (*env).GetStringUTFChars(fontname, 0);
    int taille = strlen(temp);
    char fontname_message[taille + 1];
    strncpy(fontname_message, temp, taille);
    fontname_message[taille]='\0'; 
    K3ScreenJNIDriverImplSingleton::instance()->_putChar(x, y, c, fontname_message, red, green, blue);
    (*env).ReleaseStringUTFChars(fontname, temp);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    putChar
 * Signature: (IICII)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_putChar
(JNIEnv *env, jobject obj, jint x, jint y, jchar c, jstring fontname, jint red, jint green, jint blue) {

    
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_putChar" << endl;
    
    const char *temp = (*env).GetStringUTFChars(fontname, 0);
    int taille = strlen(temp);
    char fontname_message[taille+1];
    strncpy(fontname_message, temp, taille);
    fontname_message[taille]='\0'; 
    K3ScreenJNIDriverImplSingleton::instance()->putChar(x, y, c, fontname_message, red, green, blue);
    (*env).ReleaseStringUTFChars(fontname, temp);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    scrollText
 * Signature: (ILjava/lang/String;III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_scrollText
        (JNIEnv *env, jobject obj, jint y, jstring stringJava, jstring fontname , jint delayTime, jint red, jint green, jint blue) {
    
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText" << endl;
    const char *temp = env->GetStringUTFChars(stringJava, NULL);
  
    int taille = strlen(temp);
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - message size: " <<taille<< endl;
    char message[taille];
    strncpy(message, temp,taille);
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - message after copy: " <<message<< endl;
    message[taille]='\0'; 
    //int message_taille = strlen(message);
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - message size after copy: " <<message_taille<< endl;
    
    
    env->ReleaseStringUTFChars(stringJava, temp);
    
    const char *temp2 = env->GetStringUTFChars(fontname, NULL);

    int taille2 = strlen(temp2);
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - fontname size: " <<taille2<< endl;
    char fontname_message[taille2];
    strncpy(fontname_message, temp2,taille2);
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - fontname_message after copy: " <<fontname_message<< endl;
    fontname_message[taille2]='\0'; 
    //int fontname_message_taille = strlen(fontname_message);
    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - fontname_message size after copy: " <<fontname_message_taille<< endl;
    
    env->ReleaseStringUTFChars(fontname, temp2);
    

    
//    cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - message : " <<message<< endl;
//    cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollText - message font: " <<fontname_message<< endl;

    K3ScreenJNIDriverImplSingleton::instance()->scrollText(y, message, fontname_message, delayTime, red, green, blue);
}

/*
 * Class:     com_kubeek_device_k3screendevice_K3Screen
 * Method:    scrollPPM
 * Signature: (Ljava/lang/String;III)V
 */
JNIEXPORT void JNICALL Java_com_kubeek_device_k3screendevice_K3Screen_scrollPPM
        (JNIEnv *env, jobject obj, jstring ppmfilename, jint scroll_jump, jint runtime_seconds, jint scroll_ms) {

    //cout << "K3ScreenJNIDriver::Java_com_kubeek_device_k3screendevice_K3Screen_scrollPPM" << endl;
    
    const char *temp = (*env).GetStringUTFChars(ppmfilename, 0);
    int taille = strlen(temp);
    char ppmfilename_message[taille+1];
    strncpy(ppmfilename_message, temp, taille);
    ppmfilename_message[taille]='\0'; 
    
    K3ScreenJNIDriverImplSingleton::instance()->scrollPPM(ppmfilename_message, scroll_jump, runtime_seconds, scroll_ms);
    (*env).ReleaseStringUTFChars(ppmfilename, temp);
}


