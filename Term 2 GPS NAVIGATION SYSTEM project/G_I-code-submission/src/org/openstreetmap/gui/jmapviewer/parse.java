package org.openstreetmap.gui.jmapviewer;
/**
 *  author: Ali Nazar
 */
public class parse {
   static GPSInfo info = new GPSInfo();
   static double lat;
   static double lon;
   static double latByRMC;
   static double lonByRMC;
	@SuppressWarnings("unused")
    public void checker(String signals){
	  
		String [] GGA;
		String [] GSV;
		String [] RMC;
		String [] GSA;
		/*
		 * GGA gives us  lat, lon, time, alt
		 */
		if(signals.startsWith("$GPGGA")){
			GGA=signals.split(",");
//			String lat1= GGA[2];
			
			String latString1= GGA[2].substring(0,2);
			
			String latString2= GGA[2].substring(2,GGA[2].length());
			String Stringlon1= GGA[4].substring(0,3);
			
			String Stringlon2=GGA[4].substring(3,GGA[4].length());
			
			String time1=GGA[1];
			
			String alt1= GGA[9];

			//parseTO
			parseTo(latString1,latString2,Stringlon1,Stringlon2,time1,alt1);

			/*
			 * GSV provides us: numOfSat
			 */
		}else if(signals.startsWith("$GPGSV")){
			GSV= signals.split(",");
			String numbOfSat1= GSV[3];
			String signalStrength= GSV[7];
			int numbOfSat= Integer.parseInt(numbOfSat1);
			//send to GPSInfo constructor class for further processing
			getInfo().setSatNum(numbOfSat);
			getInfo().setSignalStrength(signalStrength);
			
			/*
			 * RMC: lat,lon,track,date,speed
			 */	
		}else if(signals.startsWith("$GPRMC")){
			RMC=signals.split(",");
//			String latByRMC1= RMC[3];
			String StringlatByRMC1= RMC[3].substring(0,2);
			
			String StringlatByRMC2=RMC[3].substring(2,RMC[3].length());
			String StringlonByRMC1= RMC[5].substring(0,3);
			
			String StringlonByRMC2= RMC[5].substring(3,RMC[5].length());
			
			String track1= RMC[8];
			
			String timeByRMC1= RMC[1];   
			
			String date1= RMC[9];
			
			String speed1= RMC[7];

			parseRMCTo(StringlatByRMC1,StringlatByRMC2,StringlonByRMC1,StringlonByRMC2,track1,timeByRMC1,date1,speed1);
		
		}

	}
    private void parseTo(String latString1, String latString2, String stringlon1, String stringlon2, String time1,
            String alt1) {
        // TODO Auto-generated method stub
        
        // to round the lat and lon to 4 decimals 
        
        
        double lat1 = Double.parseDouble(latString1);
        
        double lat2 = Double.parseDouble(latString2);
        lat=lat1+(lat2/60);
        
         double lon1 = Double.parseDouble(stringlon1);
         
         double lon2 = Double.parseDouble(stringlon2);
         
         lon=lon1+(lon2/60);
       
        float  alt = Float.parseFloat(alt1);
        //send to GPSInfo constructor class for further processing
//      convert to foru decimals and then send lat and lon to GPSInfo class.
        info.setLat(lat);
        info.setLon(lon);
        info.setAltitude(alt);
        info.setTime(time1);
    }
	private void parseRMCTo(String stringlatByRMC1, String stringlatByRMC2, String stringlonByRMC1,
            String stringlonByRMC2, String track1, String timeByRMC1, String date1, String speed1) {
        // TODO Auto-generated method stub
	    
        double latByRMC1= Double.parseDouble(stringlatByRMC1);
        
        double latByRMC2= Double.parseDouble(stringlatByRMC2);
        latByRMC= latByRMC1+(latByRMC2/60);
        
        double lonByRMC1= Double.parseDouble(stringlonByRMC1);
        
        double lonByRMC2= Double.parseDouble(stringlonByRMC2);
        
        lonByRMC= lonByRMC1+(lonByRMC2/60);
        
        float track = Float.parseFloat(track1);
        float speed = Float.parseFloat(speed1);
        
        //send to GPSInfo constructor class for further processing after converting to four decimals for lat,lon
        
        info.setLat(latByRMC);
        info.setLon(lonByRMC);
        info.setTrack(track);
        info.setTime(timeByRMC1);
        info.setDate(date1);
        info.setSpeed(speed);
    }
//	    map.addMapMarker(new MapMarkerDot(Color.RED,getLat(),getLon()));

    public GPSInfo getInfo() {
		return info;
	}
	
	public boolean hasInfo() {
		return true;
	}
    public static double getLat() {
        return lat+latByRMC;
    }
    public static void setLat(double lat,double latByRMC) {
        parse.lat = lat+latByRMC;
    }
    public static double getLon() {
        return lon+lonByRMC;
    }
    public static void setLon(double lon,double lonByRMC) {
        parse.lon = lon+lonByRMC;
    }
}