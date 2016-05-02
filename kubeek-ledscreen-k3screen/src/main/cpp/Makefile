#DEBUG=-g
DEBUG=
CXXFLAGS=-Wall -O3 $(DEBUG) -fno-strict-aliasing
LIBRARY_NAME=K3ScreenJNIDriver
BINARIES=K3ScreenDemo
#all goals clean check K3ScreenDemo K3ScreenJNIDriver
GOALS=clean K3ScreenJNIDriver
OBJECTS=K3ScreenJNIDriverImpl.o ImageScroller.o gpio.o led-matrix.o framebuffer.o thread.o bdf-font.o graphics.o

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
    JAVA_HOME=/opt/jdk1.8.0_65
    JAVA_HOME_INCLUDE=$(JAVA_HOME)/include
    JAVA_HOME_PLATFORM_INCLUDE=$(JAVA_HOME)/include/linux
    CXX=g++
    OS_LIB=-lrt -lm -lpthread
    LIB_COMP=-s -shared -lstdc++ -Wl,-soname,lib$(LIBRARY_NAME).$(LIB_EXTENSION) K3ScreenJNIDriver.o $(OBJECTS) -o lib$(LIBRARY_NAME).$(LIB_EXTENSION) $(OS_LIB) 
    LIB_COMP_OLD=-shared -soname lib$(LIBRARY_NAME).$(LIB_EXTENSION) K3ScreenJNIDriver.o $(OBJECTS) -o lib$(LIBRARY_NAME).$(LIB_EXTENSION)
    DIR_LIB_TARGET=../resources/lib/linux/arm
else ifeq "$(PLATFORM)" "Darwin"
    INCLUDE_DIR=.
    LIB_EXTENSION=dylib
    JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_66.jdk/Contents/Home
    JAVA_HOME_INCLUDE=$(JAVA_HOME)/include
    JAVA_HOME_PLATFORM_INCLUDE=$(JAVA_HOME)/include/darwin
    CXX=g++
    OS_LIB=-lm -lpthread
    LIB_COMP=-dynamiclib -fPIC -o lib$(LIBRARY_NAME).$(LIB_EXTENSION) K3ScreenJNIDriver.o $(OBJECTS)
    DIR_LIB_TARGET=../resources/lib/mac/x64
else
    $(error Please compile that on Linux ARM or Macosx)
endif

all : $(GOALS)

check: 
	##################################CPPCHECK############################## 
	cppcheck . 2> err.txt



testDemo: clean check K3ScreenDemo  
	cat /proc/meminfo
	free -m
	sudo ./K3ScreenDemo


K3ScreenJNIDriver: $(OBJECTS)
			################################K3ScreenJNIDriver#######################
			javac -d $(DIR_TARGET_CLASSES) ../../../../kubeek-sdk/src/main/java/com/kubeek/utils/Utils.java ../../../../kubeek-sdk/src/main/java/com/kubeek/utils/LibraryLoader.java ../../../../kubeek-sdk/src/main/java/com/kubeek/sdk/device/KScreenDevice.java ../java/com/kubeek/device/k2screendevice/K3Screen.java 
			javah -d $(INCDIR)  -classpath $(DIR_TARGET_CLASSES) com.kubeek.device.k3screendevice.K3Screen
			$(CXX) $(CXXFLAGS) -c -I$(JAVA_HOME_INCLUDE) -I$(JAVA_HOME_PLATFORM_INCLUDE) -I$(INCDIR) -I$(INCLUDE_DIR) K3ScreenJNIDriver.cc -o K3ScreenJNIDriver.o
			$(CXX) $(CXXFLAGS) $(LIB_COMP)
			cp lib$(LIBRARY_NAME).$(LIB_EXTENSION) $(DIR_LIB_TARGET)
	
K3ScreenDemo: clean K3ScreenDemo.o $(OBJECTS)
			################################K3ScreenDemo############################
			$(CXX) $(CXXFLAGS) K3ScreenDemo.o $(OBJECTS) -o K3ScreenDemo $(OS_LIB)
			

K3ScreenDemo.o :
			$(CXX) $(CXXFLAGS) -c -I$(INCDIR) -I$(INCLUDE_DIR) K3ScreenDemo.cc -o K3ScreenDemo.o
	
ImageScrollerDemo: clean ImageScrollerDemo.o $(OBJECTS)
			################################K3ScreenDemo############################
			$(CXX) $(CXXFLAGS) ImageScrollerDemo.o $(OBJECTS) -o ImageScrollerDemo $(OS_LIB)
			

ImageScrollerDemo.o : ImageScroller.o
			$(CXX) $(CXXFLAGS) -c -I$(INCDIR) -I$(INCLUDE_DIR) ImageScrollerDemo.cc -o ImageScrollerDemo.o


%.o: %.cc
	$(CXX) -I$(INCDIR) $(CXXFLAGS) -DADAFRUIT_RGBMATRIX_HAT -fPIC -c -o $@ $<

clean:
	##################################CLEANING##############################
	rm -f *.o *.dylib *.so *.txt $(BINARIES) 
	rm -f -r ../../../target
	mkdir -p $(DIR_TARGET)
	mkdir -p $(DIR_TARGET_CLASSES)
