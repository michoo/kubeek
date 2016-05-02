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

This project is a platform sandbox for messages display on Kubeek box or on other LED interfaces or maybe more...

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


####Installing Oracle jdk 8 - other way but problem with sudo....
Download from Oracle the right version of Oracle jdk 8 and then on your RPi:
```
$ sudo tar zxvf jdk-8-linux-arm-vfp-hflt.tar.gz -C /opt
```

Set default java, javac and javah to the new installed jdk8.
```
$ sudo update-alternatives --install /usr/bin/javac javac /opt/jdk1.8.0/bin/javac 1
$ sudo update-alternatives --install /usr/bin/java java /opt/jdk1.8.0/bin/java 1
$ sudo update-alternatives --install /usr/bin/java javah /opt/jdk1.8.0/bin/javah 1

$ sudo update-alternatives --config javac
$ sudo update-alternatives --config java
$ sudo update-alternatives --config javah
```
After all, check java -version.
```
$ java -version
```

###Kubeek at startup


Change owner of jar file
```
$ sudo chown root:root kubeek-service-rest-1.0-SNAPSHOT.jar
$ sudo ln -s /home/pi/Workspace/kubeek/kubeek.jar /etc/init.d/kubeek
```

test with:
```
$ sudo /etc/init.d/kubeek start
```

To install service
```
$ sudo update-rc.d kubeek defaults
```
you can set priorities at the end

To remove
```
$ sudo update-rc.d kubeek remove
```



Some action available
```
sudo /etc/init.d/kubeek status
sudo /etc/init.d/kubeek start
sudo /etc/init.d/kubeek stop
```




### Other way //TODO finalize this one

Create a file under  **/etc/init.d/**   with nano or vi and paste the example script below. ex.
```
$ sudo nano /etc/init.d/kubeek
````

```
#!/bin/sh
### BEGIN INIT INFO
# Provides:          KUBEEK
# Required-Start:    $local_fs $network $remote_fs
# Required-Stop:     $local_fs $network $remote_fs
# Should-Start:      $NetworkManager
# Should-Stop:       $NetworkManager
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: starts instance of Kubeek
# Description:       starts instance of kubeek using start-stop-daemon
### END INIT INFO

### START EDIT INFO
SERVICE_NAME=kubeek
PATH_TO_JAR=/home/pi/Workspace/kubeek/kubeek*.jar
PID_PATH_NAME=/tmp/kubeek-pid

### STOP EDIT INFO
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -jar $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -jar $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac
```


Modify the SERVICE_NAME, PATH_TO_JAR, and choose a PID_PATH_NAME for the file you are going to use to store your service ID.
Write the file and give execution permisions ex.
```
$ sudo chmod +x /etc/init.d/kubeek
```

enable the service
```
sudo systemctl enable kubeek.service
```

to see all the services linked by name
```
ls -la /etc/{rc?.d,init.d,default}/*kubeek
```


Test that it runs ex.
```
$ sudo service kubeek start
```

Test that it stops ex.
```
$ sudo service kubeek stop
```

Test that it restarts ex.
```
$ sudo service kubeek restart
``` 




####License

```
See COPYING.txt for details of the GNU GENERAL PUBLIC LICENSE
```
