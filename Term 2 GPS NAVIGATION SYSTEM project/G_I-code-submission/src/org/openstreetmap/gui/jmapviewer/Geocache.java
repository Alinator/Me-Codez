package org.openstreetmap.gui.jmapviewer;

/**
 * 
 * @author Ali,Mahsa
 *
 *this class provides the necessary methods to create geocache node on the map.
 *it recieves two geocache coordinates in the form of a latitude and longitude
 *
 */
public class Geocache{

   protected static double geocacheLat; 
   protected static double geocacheLon;

   @SuppressWarnings("static-access")
public Geocache(){
       this.geocacheLat=0.0;
       this.geocacheLon=0.0;
   }
   
   public Geocache(double geocacheLatIn, double geocacheLonIn){
       Geocache.geocacheLat=geocacheLatIn;
       Geocache.geocacheLon=geocacheLonIn;
   }

public static double getGeocacheLat() {
    return geocacheLat;
}
public void setGeocacheLat(double geocacheLat) {
    Geocache.geocacheLat = geocacheLat;
}
public static double getGeocacheLon() {
    return geocacheLon;
}
public void setGeocacheLon(double geocacheLon) {
    Geocache.geocacheLon = geocacheLon;
}

public static void print() {
    // TODO Auto-generated method stub
    System.out.println(geocacheLat+" "+geocacheLon);
}
   
   
}

