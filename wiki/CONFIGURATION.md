#Configuration

To configure the application, a file is available for that (Thanks Spring boooot).

```
application.properties
```

##Screen configuration

Select your driver: <br/>
+ **KSimulatorScreen** is a simple logger to test on my dev machine<br/>
+ **K2Screen** is a Red + Green LED Screen specific (nice cube home made)<br/>
+ **K3Screen** is a RGB LED Screen from Ladyada <br/> https://learn.adafruit.com/32x16-32x32-rgb-led-matrix/overview
And this shield: https://www.adafruit.com/products/2345

```
com.kubeek.screen = KSimulatorScreen or K2SCreen or K3Screen
```

Indicate the rows: <br/>
+ 16 for a screen 32x16
+ 32 for a screen 32x32<br/>

Set the chain value of chained screen (horizontally). You have one screen so the value is 1, if you have 2 screens so it's 2, and so on...
(Not working on K2Screen)
```
com.kubeek.screen.rows=16
com.kubeek.screen.chain=2
```

Set defaults directories for font and ppm. default font not used at this moment, but you have only to set the name of the file without extension (.bdf)
```
com.kubeek.screen.font=fonts
com.kubeek.screen.font.default=6x13
com.kubeek.screen.ppm=ppm
```

##Mail configuration

It's only tested with gmail in SSL , you only have to reduce security access for apps to your account (google...)

```
com.kubeek.mail.host = smtp.gmail.com
com.kubeek.mail.username = mail
com.kubeek.mail.password = password
com.kubeek.mail.port = 465
com.kubeek.mail.from = mail_from@gmail.com
```

##Server configuration

Change the default web port: <br/>
```
server.port=8081
```


Logging level can be set to TRACE, DEBUG, WARN, INFO. All log will be available in /var/log/kubeek.log (if installed as a service - look at README.md)

```
logging.level.com.kubeek = INFO
```
Logs with a specific pattern in a dasedir from tomcat can be enabled to monitor all access GET, POST,... on your server.
```
server.tomcat.accesslog.enabled=true
server.tomcat.accesslog.pattern=%h %p %t "%r" %s %b
server.tomcat.basedir=server
```

To define the threadpool of app and task threads
```
com.kubeek.threadpool.corepoolsize=5
com.kubeek.threadpool.maxpoolsize=20
```

##App specific

###EXAMPLE
This application is an example to help you to start developing on the kubeek<br/>
appcolor is the color of the app is in hexadecimal RRGGBB (without the #)<br/>
speed is the speed of the scrolled text.<br/>
appcolor and speed apre parameters available for all apps
```
com.kubeek.app.example.appcolor=FF0000
com.kubeek.app.example.speed=15000
```

###TWITTER
This application keep you update from your last tweet from your timeline. To define key and secret go to: https://apps.twitter.com/
```
com.kubeek.app.twitter.consumerkey=
com.kubeek.app.twitter.consumersecret=
com.kubeek.app.twitter.accesstoken=
com.kubeek.app.twitter.accesstokensecret=
com.kubeek.app.twitter.appcolor=FF0000
com.kubeek.app.twitter.speed=15000
```

###CLOCK
This application show you the date and time of your computer/RPi
```
com.kubeek.app.clock.appcolor=FF0000
com.kubeek.app.clock.speed=15000
```
###BINARY CLOCK
It's a binary clock
```
com.kubeek.app.binaryclock.upcolor=FF0000
com.kubeek.app.binaryclock.downcolor=00FF00
com.kubeek.app.binaryclock.speed=15000
```
###STOCK EXCHANGE
This application shows you the crazy EURCAD change...fuck

```
com.kubeek.app.stockexchange.appcolor=FF0000
com.kubeek.app.stockexchange.speed=15000
```
###WEATHER
It's the weather from Montreal
```
com.kubeek.app.weather.appcolor=FF0000
com.kubeek.app.weather.speed=15000
```