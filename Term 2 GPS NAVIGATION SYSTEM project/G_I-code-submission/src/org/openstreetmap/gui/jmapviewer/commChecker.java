package org.openstreetmap.gui.jmapviewer;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.util.TooManyListenersException;

/**
 * 
 * @author Ali
 *  
 *  this class checks for data in the gps ones a device has been detected.
 *  if the device that has been detected has data available, it will start to scan the device
 *  and send the data to the 
 */
public class commChecker extends gpsdevice implements Runnable{


		@SuppressWarnings("static-access")
        public commChecker() {
            /**
             * if you want to be able to open the serial port, you have to be close the GPSINFO program or
             * other programs that may be scanning for inputs, that means 
             * it should not be scanning for coordinates while you are trying to connect to it from java.
             * if you do this it will give you "åtkomst Nekad" error. 
             */
			try{
				serialPort=(SerialPort) port.open(" commChecker ", 2000 );
			}catch(PortInUseException p){
				System.out.println(p.getLocalizedMessage());
			}
			// read the data
			try{
				inputData= serialPort.getInputStream();
			}catch(IOException ex){
				System.out.println(ex.getLocalizedMessage());
			}
			// eventlistener
			try{
				serialPort.addEventListener(new SerialPortEventListener(){

                    public void serialEvent(SerialPortEvent e) {
                        switch(e.getEventType()){
                        // if data is available
                        case SerialPortEvent.DATA_AVAILABLE:
                            StringBuffer buffer= new StringBuffer();
                            int carrier; 
                            try{
                                while((carrier= inputData.read()) !=10 ){
                                    if (carrier != 13)
                                        buffer.append((char) carrier);
                                }
                                String input= buffer.toString();

                                parse parser = new parse();
                                parser.checker(input);
                                inputData.close();  
                            
                                }catch(IOException ex){
                                    System.out.println(ex.getLocalizedMessage());
                                    break;
                                }
                    }
                    }
				    
				});
			}catch(TooManyListenersException e){
				System.out.println("to many listeners");
			}
				serialPort.notifyOnDataAvailable(true);
			
			/**
			 * now we need to define the properties of our device.properties of
			 * the gps device should be specified in the manual.
			 * the code has been adjusted to the gps device settings:
			 * Be sure your Baud rate is configured correctly at:
				Baud Rate: 4800
				Data bit: 8
				Parity: None
				Stop Bit: 1
				Flow Control: None 
			 */
			try{
				serialPort.setSerialPortParams(
						4800, serialPort.DATABITS_8 ,serialPort.STOPBITS_1 , serialPort.PARITY_NONE);
				
				  serialPort.setDTR(false);
		          serialPort.setRTS(false);
			}catch(UnsupportedCommOperationException e){
				gpsThread= new Thread(this);
				gpsThread.start();
			}
		
		}
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                
            }
        }
		
	}


