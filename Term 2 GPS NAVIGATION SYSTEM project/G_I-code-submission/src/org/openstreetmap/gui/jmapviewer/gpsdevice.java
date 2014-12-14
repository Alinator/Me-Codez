package org.openstreetmap.gui.jmapviewer;
import gnu.io.*;
import java.io.*;
/**
 * 
 * @author Ali
 * this class constructs objects from the RXTX library which is then extended by the commChecker class
 *
 */
public class gpsdevice {


	/** CommPortIdentifier is the main class that controls access to serial ports.
	 * it negotiates with the driver in order to find out which communication ports are available
	 *
	 */
	static CommPortIdentifier port;
	/**
	 * SerialPort defines the interface to a RS-232 device such as a gps device. 
	 */
	SerialPort serialPort;
	
	/**
	 * is needed so we can read data from the GPS device that is connected to a serial port.
	 */
	InputStream inputData;
	/**
	 * a thread in order to run commChecker class on a separate thread 
	 */
	Thread gpsThread;
}