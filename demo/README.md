# Overview
This code is soley for reading data from an arduino that is running the **Adafruit_DHT** library. The arduino is actually reading data from the physical DHT sensor. 
This demo uses the DHT11 sensor while Team7 has ordered the DHT22 sensor with the only major difference being that the DHT22 is a more accurate measuring sensor 
than its counterparts.

## ttyACM
The raspberry pi is reading serial data coming in from a usb port, this port can very but is usually titled something like ttyACM* or ttyUSB* where * is an identifying number. The arduino can be recognized by running the line `ls /dev/tty*` with the arudino plugged in. Then unplug the arduino and re-run the same line and see what device has gone away.

