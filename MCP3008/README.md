# Information Pertaining to the MCP3008 Analog to digital converter for use with the raspberry pi

[mcp3008 datasheet](https://cdn-shop.adafruit.com/datasheets/MCP3008.pdf)  
[mcp3008 RPi wiring diagram](https://forge.codesys.com/drv/mcp3008/home/Home/attachment/MCP3008_schem.png)  

Import and initial setup information for MCP3008.  

```python
import busio
import digitalio
import board
import adafruit_mcp3xxx.mcp3008 as MCP
from adafruit_mcp3xxx.analog_in import AnalogIn
spi = busio.SPI(clock=board.SCK, MISO=board.MISO, MOSI=board.MOSI)
cs = digitalio.DigitalInOut(board.D5)
mcp = MCP.MCP3008(spi, cs)
```
