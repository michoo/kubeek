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
    cout << "K2ScreenJNIDriverImplSingleton::instance()" << endl;
    
    if (!inst){
        cout << "K2ScreenJNIDriverImplSingleton::instance() - New instance " << endl;
        inst = new K2ScreenJNIDriverImpl();
    }else{
        cout << "K2ScreenJNIDriverImplSingleton::instance() - Instance already exist" << endl;
    }
    
    return inst;
}

void K2ScreenJNIDriverImplSingleton::deleteInstance()
{
    cout << "K2ScreenJNIDriverImplSingleton::deleteInstance()" << endl;
    if(inst != NULL){
       delete inst; 
    }
    

}


void K2ScreenJNIDriverImplSingleton::setRowsChain(int rows_, int chain_) {
    cout << "K2ScreenJNIDriverImplSingleton::setRowsChain()" << endl;
    cout << "K2ScreenJNIDriverImplSingleton::setRowsChain() - to set at rows/chain " << rows_ << "/" << chain_ << endl;
    
    if(inst != NULL){
       delete inst; 
    }
    
    inst = new K2ScreenJNIDriverImpl(rows_, chain_);
    
}

int main(int argc, char *argv[]) {
    
    
    K2ScreenJNIDriverImplSingleton::setRowsChain(16, 2);
    K2ScreenJNIDriverImplSingleton::instance();
    
    
    cout << "K2ScreenDemo::main - main started" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->clear();
    cout << "K2ScreenDemo::main - screen cleaned" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->plot(1, 1, 200,200,200);
    cout << "K2ScreenDemo::main - plot plainted" << endl;
    K2ScreenJNIDriverImplSingleton::instance()->setBright();
    
    
//    string message = "Internet est la première chose que l'homme a créée sans la comprendre, c'est la plus grande expérience en matière d'anarchie jamais réalisée. - Eric Schmidt";
    string message = "Whose woods these are I think I know. His house is in the village, though; He will not see me stopping here. To watch his woods fill up with snow.   My little horse must think it queer. To stop without a farmhouse near. Between the woods and frozen lake. The darkest evening of the year.   He gives his harness bells a shakeTo ask if there is some mistake.The only other sound’s the sweep Of easy wind and downy flake.   The woods are lovely, dark, and deep,But I have promises to keep,And miles to go before I sleep,And miles to go before I sleep. - Fin";
    string fontfile="fonts/6x13.bdf";
    
    
    
    K2ScreenJNIDriverImplSingleton::instance()->scrollText(1, message.c_str(), fontfile.c_str(),10*1000, 200, 1, 25);
    
    string dummy;
    cout << "Enter to finish..." << std::endl;
    getline(std::cin, dummy);
    
    K2ScreenJNIDriverImplSingleton::instance()->clear();
    cout << "K2ScreenDemo::main - screen cleaned" << endl;
    K2ScreenJNIDriverImplSingleton::deleteInstance();
    
    return 1;
}