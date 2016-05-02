```
 __        ___.                  __
|  | ____ _\_ |__   ____   ____ |  | __
|  |/ /  |  \ __ \_/ __ \_/ __ \|  |/ /
|    <|  |  / \_\ \  ___/\  ___/|    <
|__|_ \____/|___  /\___  >\___  >__|_ \
     \/         \/     \/     \/     \/
                        http://www.kubeek.com
```


Hi kubeekers!

Kubeek is a project to share messages over a RGB LED Matrix on a Raspberry pi. You can access with REST Api to plot points, circles,... and send text messages. You can also develop your applications (in Java - some examples available in repo) to give you the info you expect.
So enjoy this GPL software!
```
$ cd dist/kubeek/
$ ./kubeek_start.sh
```

![alt tag](http://www.kubeek.com/fr/wp-content/uploads/2012/11/gallery_MG_7209.png)

#Architecture

Kubeek is based on a Raspberry pi (1,2,3 - maybe 0) with is particular shield or with the "Adafruit RGB Matrix HAT + RTC for Raspberry Pi - Mini Kit"
https://www.adafruit.com/product/2345
and
a LED Matrix 32 (many combination - 32x16, 32x32, multichained,...) Red + Green or RGB


#Build

Server (Java 8)
```
$ mvn clean package
```

Drivers (cpp to compile on RPi!)
Inside kubeek_project/kubeek-ledscreen-XXXXX/src/main/cpp
```
$ make clean XXXXScreenJNIDriver
```
change the names with  K2 for K2ScreenJNIDriver or K3 for K3ScreenJNIDriver

(look at Makefile, maybe you'll have to set the right path to your Java installation - JNI)

#Instalalalalation

##RPi image prep

First download last version of the raspbian jessie img "lite": https://www.raspberrypi.org/downloads/
Installation of an image is described here https://www.raspberrypi.org/documentation/installation/installing-images/

#####SD prep
format in fat32 + MBR to start with something clean

I'll continu with Macosx example, you'll find out other ways...

list volumes:
```
$ diskutil list
```
Unmount volume "n" of the identified SD card:

```
$ diskutil unmountDisk /dev/diskn
```

Copy image to sd
```
$ sudo dd if=2016-03-18-raspbian-jessie-lite.img of=/dev/rdiskn bs=1m
```


Now you can put your SD inside your Rpi and start it.


##Configuration of raspberry pi

You can start to configure you Pi:
```
$ sudo raspi-config
```
expand, change user password, server name, video card 0, overclock 900 (or more it depends of your Pi - but 900 is enough), ssh enable


####Configuration of a wifi access (optional)

Setup your wifi network card (USB)
https://www.raspberrypi.org/documentation/configuration/wireless/wireless-cli.md

```
$ sudo nano /etc/wpa_supplicant/wpa_supplicant.conf
```

add at the end of file your network as :
```
network={
    ssid="The_ESSID_from_earlier"
    psk="Your_wifi_password"
}
```

Set ip static (eth0 or wlan0)

```
$ sudo nano /etc/network/interfaces
```
and update your network like this example:
```
allow-hotplug wlan0
iface wlan0 inet static
address 192.168.1.148
gateway 192.168.1.1
netmask 255.255.255.0
    wpa-conf /etc/wpa_supplicant/wpa_supplicant.conf
```

####Root password
set root password
```
$ sudo passwd root
```


####Motd
Set message of the day
```
$ sudo nano /etc/motd
```
and add:
```
 __        ___.                  __
|  | ____ _\_ |__   ____   ____ |  | __
|  |/ /  |  \ __ \_/ __ \_/ __ \|  |/ /
|    <|  |  / \_\ \  ___/\  ___/|    <
|__|_ \____/|___  /\___  >\___  >__|_ \
     \/         \/     \/     \/     \/
```

####Updates
```
$ sudo apt-get update && sudo apt-get upgrade
```


####Installing wiring pi
Library to drive the led screen
```
$ sudo apt-get install git-core
$ cd Workspace/wiringPi
$ git clone git://git.drogon.net/wiringPi
$ cd wiringPi
$ ./build
```

####Installing Oracle jdk 8

```
$ sudo apt-get install oracle-java8-installer
$ sudo update-alternatives --config java
```

####Origin
One of the LED-matrix library is (c) Henner Zeller h.zeller@acm.org with GNU General Public License Version 2.0 http://www.gnu.org/licenses/gpl-2.0.txt for more details https://github.com/adafruit/rpi-rgb-led-matrix

