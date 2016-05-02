#DEBUG=-g
DEBUG=
CXXFLAGS=-Wall -O3 $(DEBUG) -Winline -pipe
LIBRARY_NAME=K2ScreenJNIDriver
BINARIES=K2ScreenDemo
#all goals clean check K2ScreenDemo K2ScreenJNIDriver
GOALS=clean K2ScreenJNIDriver
OBJECTS=K2ScreenJNIDriverImpl.o bdf-font.o graphics.o

# Where our library resides. It is split between includes and the binary
# library in lib
INCDIR=includes


DIR_TARGET=../../../target
DIR_TARGET_CLASSES=../../../target/classes


#Select parameters for the right OS
PLATFORM = $(shell uname -s)
ifeq "$(PLATFORM)" "Linux"
    INCLUDE_DIR=/usr/include
    LIB_EXTENSION=so
    JAVA_HOME=/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt
    JAVA_HOME_INCLUDE=$(JAVA_HOME)/include
    JAVA_HOME_PLATFORM_INCLUDE=$(JAVA_HOME)/include/linux
    CXX=g++
    OS_LIB=-lwiringPi -L/usr/local/lib
    LIB_COMP=-s -shared -lstdc++ -Wl,-soname,lib$(LIBRARY_NAME).$(LIB_EXTENSION) K2ScreenJNIDriver.o $(OBJECTS) -o lib$(LIBRARY_NAME).$(LIB_EXTENSION) $(OS_LIB)
    LIB_COMP_OLD=-shared -soname lib$(LIBRARY_NAME).$(LIB_EXTENSION) K2ScreenJNIDriver.o $(OBJECTS) -o lib$(LIBRARY_NAME).$(LIB_EXTENSION)
    DIR_LIB_TARGET=../resources/lib/linux/arm
else ifeq "$(PLATFORM)" "Darwin"
    INCLUDE_DIR=.
    LIB_EXTENSION=dylib
    JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_66.jdk/Contents/Home
    JAVA_HOME_INCLUDE=$(JAVA_HOME)/include
    JAVA_HOME_PLATFORM_INCLUDE=$(JAVA_HOME)/include/darwin
    CXX=g++
    OS_LIB=-lm -lpthread
    LIB_COMP=-dynamiclib -fPIC -o lib$(LIBRARY_NAME).$(LIB_EXTENSION) K2ScreenJNIDriver.o $(OBJECTS)
    DIR_LIB_TARGET=../resources/lib/mac/x64
else
    $(error Please compile that on Linux ARM or Macosx)
endif



all : $(GOALS)

check:
	##################################CPPCHECK##############################
	cppcheck . 2> err.txt

testDemo: clean check K2ScreenDemo
	cat /proc/meminfo
	free -m
	sudo ./K2ScreenDemo


K2ScreenJNIDriver: $(OBJECTS)
			################################K2ScreenJNIDriver#######################
			javac -d $(DIR_TARGET_CLASSES) ../../../../kubeek-sdk/src/main/java/com/kubeek/utils/Utils.java ../../../../kubeek-sdk/src/main/java/com/kubeek/utils/LibraryLoader.java ../../../../kubeek-sdk/src/main/java/com/kubeek/sdk/device/KScreenDevice.java ../java/com/kubeek/device/k2screendevice/K2Screen.java 
			javah -d $(INCDIR)  -classpath $(DIR_TARGET_CLASSES) com.kubeek.device.k2screendevice.K2Screen
			$(CXX) $(CXXFLAGS) -c -I$(JAVA_HOME_INCLUDE) -I$(JAVA_HOME_PLATFORM_INCLUDE) -I$(INCDIR) -I$(INCLUDE_DIR) K2ScreenJNIDriver.cc -o K2ScreenJNIDriver.o
			$(CXX) $(CXXFLAGS) $(LIB_COMP)
			cp lib$(LIBRARY_NAME).$(LIB_EXTENSION) $(DIR_LIB_TARGET)

K2ScreenDemo: clean K2ScreenDemo.o $(OBJECTS)
			################################K2ScreenDemo############################
			$(CXX) $(CXXFLAGS) K2ScreenDemo.o $(OBJECTS) -o K2ScreenDemo $(OS_LIB)


K2ScreenDemo.o :
			$(CXX) $(CXXFLAGS) -c -I$(INCDIR) -I$(INCLUDE_DIR) K2ScreenDemo.cc -o K2ScreenDemo.o

ImageScrollerDemo: clean ImageScrollerDemo.o $(OBJECTS)
			################################K2ScreenDemo############################
			$(CXX) $(CXXFLAGS) ImageScrollerDemo.o $(OBJECTS) -o ImageScrollerDemo $(OS_LIB)



%.o: %.cc
	$(CXX) -I$(INCDIR) $(CXXFLAGS) -fPIC -c -o $@ $<

clean:
	##################################CLEANING##############################
	rm -f *.o *.dylib *.so *.txt $(BINARIES)
	rm -f -r ../../../target
	mkdir -p $(DIR_TARGET)
	mkdir -p $(DIR_TARGET_CLASSES)
