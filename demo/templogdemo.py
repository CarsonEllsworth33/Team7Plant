#! /usr/bin/env python3

import serial
from datetime import datetime
import os
import sys

if (__name__ == "__main__"):
    ser = serial.Serial('/dev/ttyACM0',9600, timeout=1)
    ser.flush()
    if(os.path.exists("tmplog.txt")):
        log = open("tmplog.txt","a+")
    else:
        log = open("tmplog.txt","a+")
        log.write("Humidity(%),Temp(C),Temp(F),Heat Ind(C),Heat Ind(F),Date,Time\n")
    count=0
    while(True):

        if(ser.in_waiting > 0):
            line = ser.readline().decode('utf-8').rstrip()
            sep = line.split(" ")
            num_line = ""
            if(count > 2):
                for x in range(len(sep)):    
                    if(sep[x]):
                        try:
                            int(sep[x][0]) 
                            #num found
                            num_line+=sep[x][:5]+"," 
                        except ValueError:
                            pass
                num_line += str(datetime.now().strftime("%m/%d/%Y, %H:%M:%S")) +"\n"
                print(num_line)
                log.write(num_line)
                log.close()
                sys.exit(0)
            count+=1
            print(line)
