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

![alt tag](https://cdn-shop.adafruit.com/1200x900/2345-06.jpg)

#Architecture

Kubeek is based on a Raspberry pi (1,2,3 - maybe 0) with is particular shield or with the "Adafruit RGB Matrix HAT + RTC for Raspberry Pi - Mini Kit"
https://www.adafruit.com/product/2345
and
a LED Matrix 32 (many combination - 32x16, 32x32, multichained,...) Red + Green or RGB


####Origin
One of the LED-matrix library is (c) Henner Zeller h.zeller@acm.org with GNU General Public License Version 2.0 http://www.gnu.org/licenses/gpl-2.0.txt for more details https://github.com/adafruit/rpi-rgb-led-matrix

