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
    cout << "K3ScreenJNIDriverImplSingleton::instance()" << endl;
    
    if (!inst){
        cout << "K3ScreenJNIDriverImplSingleton::instance() - New instance " << endl;
        inst = new K3ScreenJNIDriverImpl();
    }else{
        cout << "K3ScreenJNIDriverImplSingleton::instance() - Instance already exist" << endl;
    }
    
    return inst;
}

void K3ScreenJNIDriverImplSingleton::deleteInstance()
{
    cout << "K3ScreenJNIDriverImplSingleton::deleteInstance()" << endl;
    if(inst != NULL){
       delete inst; 
    }
    

}


void K3ScreenJNIDriverImplSingleton::setRowsChain(int rows_, int chain_) {
    cout << "K3ScreenJNIDriverImplSingleton::setRowsChain()" << endl;
    cout << "K3ScreenJNIDriverImplSingleton::setRowsChain() - to set at rows/chain " << rows_ << "/" << chain_ << endl;
    
    if(inst != NULL){
       delete inst; 
    }
    
    inst = new K3ScreenJNIDriverImpl(rows_, chain_);
    
}

int main(int argc, char *argv[]) {
    
    
    K3ScreenJNIDriverImplSingleton::setRowsChain(16, 2);
    K3ScreenJNIDriverImplSingleton::instance();
    
    
    cout << "K3ScreenDemo::main - main started" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->clear();
    cout << "K3ScreenDemo::main - screen cleaned" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->plot(1, 1, 200,200,200);
    cout << "K3ScreenDemo::main - plot plainted" << endl;
    K3ScreenJNIDriverImplSingleton::instance()->setBright();
    
    
//    string message = "Internet est la première chose que l'homme a créée sans la comprendre, c'est la plus grande expérience en matière d'anarchie jamais réalisée. - Eric Schmidt";
    string message = "Whose woods these are I think I know. His house is in the village, though; He will not see me stopping here. To watch his woods fill up with snow.   My little horse must think it queer. To stop without a farmhouse near. Between the woods and frozen lake. The darkest evening of the year.   He gives his harness bells a shakeTo ask if there is some mistake.The only other sound’s the sweep Of easy wind and downy flake.   The woods are lovely, dark, and deep,But I have promises to keep,And miles to go before I sleep,And miles to go before I sleep. - Fin";
    string fontfile="fonts/6x13.bdf";
    
    
    
    K3ScreenJNIDriverImplSingleton::instance()->scrollText(1, message.c_str(), fontfile.c_str(),10*1000, 200, 1, 25);
    
    string dummy;
    cout << "Enter to finish..." << std::endl;
   getline(std::cin, dummy);
    
    K3ScreenJNIDriverImplSingleton::instance()->clear();
    cout << "K3ScreenDemo::main - screen cleaned" << endl;
    K3ScreenJNIDriverImplSingleton::deleteInstance();
    
    return 1;
}