package org.openstreetmap.gui.jmapviewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.interfaces.MapLine;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.TileCache;

/**
  * This class was implemented to integrate lines to the code. The difference between this class
  * and its super class JMapViewer is everything needed for displaying lines'
  * 
  * Note that i made some changes in the classes JMapController and DefaultMapController
  * to make sure that those classes use this class instead of the super class JMapViewer
  * I have marked my changes in these classes.
  * 
  * @author Johan W-Schützer
  */

@SuppressWarnings("serial")
public class JMapViewerExtension extends JMapViewer {

    protected List<MapLine> mapLineList;
    protected boolean mapLinesVisible;
    protected List<MapGeocacheDot> mapGeoList;
    protected boolean mapGeosVisible;
    protected List<MapInterestPoint> mapInterestPointList;
    protected boolean mapInterestVisible;
    
    /**
     * Default constructor
     */
    public JMapViewerExtension() {
        this(new MemoryTileCache(), 4);
        new DefaultMapController(this);
        setPreferredSize(new Dimension (1024, 600));
    }
    
    /**
     * Constructor with tileCache and threadCount
     * 
     * @param tileCache
     * @param downloadThreadCount
     */
    public JMapViewerExtension(TileCache tileCache, int downloadThreadCount) {
        super(tileCache, downloadThreadCount);
        mapLineList = new LinkedList<MapLine>();
        mapLinesVisible = true;
        mapGeoList = new LinkedList<MapGeocacheDot>();
        mapGeosVisible = true;
        mapInterestPointList = new LinkedList<MapInterestPoint>();
        mapInterestVisible = true;
        setZoom(13, new Point());
        moveMap(17350, -75805);
        zoomIn();
        zoomIn();
    }
    
    /**
     * Sets the displayed map pane and zoom level so that all map lines are
     * visible.
     */
    public void setDisplayToFitMapLines() {
        if (mapLineList == null || mapLineList.size() == 0)
            return;
        int x_min = Integer.MAX_VALUE;
        int y_min = Integer.MAX_VALUE;
        int x_max = Integer.MIN_VALUE;
        int y_max = Integer.MIN_VALUE;
        int mapZoomMax = tileController.getTileSource().getMaxZoom();
        for (MapMarker marker : mapMarkerList) {
            int x = OsmMercator.LonToX(marker.getLon(), mapZoomMax);
            int y = OsmMercator.LatToY(marker.getLat(), mapZoomMax);
            x_max = Math.max(x_max, x);
            y_max = Math.max(y_max, y);
            x_min = Math.min(x_min, x);
            y_min = Math.min(y_min, y);
        }
        int height = Math.max(0, getHeight());
        int width = Math.max(0, getWidth());
        int newZoom = mapZoomMax;
        int x = x_max - x_min;
        int y = y_max - y_min;
        while (x > width || y > height) {
            newZoom--;
            x >>= 1;
            y >>= 1;
        }
        x = x_min + (x_max - x_min) / 2;
        y = y_min + (y_max - y_min) / 2;
        int z = 1 << (mapZoomMax - newZoom);
        x /= z;
        y /= z;
        setDisplayPosition(x, y, newZoom);
    }
    
    /**
     * Sets the displayed map pane and zoom level so that all map geocache indicators are
     * visible.
     */
    public void setDisplayToFitMapGeos() {
        if (mapGeoList == null || mapGeoList.size() == 0)
            return;
        int x_min = Integer.MAX_VALUE;
        int y_min = Integer.MAX_VALUE;
        int x_max = Integer.MIN_VALUE;
        int y_max = Integer.MIN_VALUE;
        int mapZoomMax = tileController.getTileSource().getMaxZoom();
        for (MapGeocacheDot marker : mapGeoList) {
            int x = OsmMercator.LonToX(marker.getLon(), mapZoomMax);
            int y = OsmMercator.LatToY(marker.getLat(), mapZoomMax);
            x_max = Math.max(x_max, x);
            y_max = Math.max(y_max, y);
            x_min = Math.min(x_min, x);
            y_min = Math.min(y_min, y);
        }
        int height = Math.max(0, getHeight());
        int width = Math.max(0, getWidth());
        int newZoom = mapZoomMax;
        int x = x_max - x_min;
        int y = y_max - y_min;
        while (x > width || y > height) {
            newZoom--;
            x >>= 1;
            y >>= 1;
        }
        x = x_min + (x_max - x_min) / 2;
        y = y_min + (y_max - y_min) / 2;
        int z = 1 << (mapZoomMax - newZoom);
        x /= z;
        y /= z;
        setDisplayPosition(x, y, newZoom);
    }
    
    /**
     * Sets the displayed map pane and zoom level so that all map Interest Point indicators are
     * visible.
     * Added by Sebastian & Johan
     */
    public void setDisplayToFitMapInterest() {
        if (mapInterestPointList == null || mapInterestPointList.size() == 0)
            return;
        int x_min = Integer.MAX_VALUE;
        int y_min = Integer.MAX_VALUE;
        int x_max = Integer.MIN_VALUE;
        int y_max = Integer.MIN_VALUE;
        int mapZoomMax = tileController.getTileSource().getMaxZoom();
        for (MapInterestPoint marker : mapInterestPointList) {
            int x = OsmMercator.LonToX(marker.getLon(), mapZoomMax);
            int y = OsmMercator.LatToY(marker.getLat(), mapZoomMax);
            x_max = Math.max(x_max, x);
            y_max = Math.max(y_max, y);
            x_min = Math.min(x_min, x);
            y_min = Math.min(y_min, y);
        }
        int height = Math.max(0, getHeight());
        int width = Math.max(0, getWidth());
        int newZoom = mapZoomMax;
        int x = x_max - x_min;
        int y = y_max - y_min;
        while (x > width || y > height) {
            newZoom--;
            x >>= 1;
            y >>= 1;
        }
        x = x_min + (x_max - x_min) / 2;
        y = y_min + (y_max - y_min) / 2;
        int z = 1 << (mapZoomMax - newZoom);
        x /= z;
        y /= z;
        setDisplayPosition(x, y, newZoom);
    }
    
    /**
     * The same as getMapPosition, but for line. Wont set the points to null
     * even if they are outside, so the line wont disappear if one point is outside
     * added by Johan W-Schützer
     */
    public Point getMapPositionLine(double lat, double lon) {
        int x = OsmMercator.LonToX(lon, zoom);
        int y = OsmMercator.LatToY(lat, zoom);
        x -= center.x - getWidth() / 2;
        y -= center.y - getHeight() / 2;
        return new Point(x, y);
    }
    
    /**
     * Overrides same method from super. This one includes some code for the lines.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        try {
            if (mapLinesVisible && mapLineList != null) {
                for (MapLine line : mapLineList) {
                    paintLine(g, line);
                }
            }
        } catch (Exception e) {}
        try {
            if (mapGeosVisible && mapGeoList != null) {
                int i = 0;
                for (MapGeocacheDot geo : mapGeoList) {
                    paintGeoMarker(g, geo, i);
                    i++;
                }
            }
        } catch (Exception e) {}
        
        try {
            if (mapInterestVisible && mapInterestPointList != null) {
                int i = 0;
                for (MapInterestPoint point : mapInterestPointList) {
                    paintInterestPoint(g, point, i);
                    i++;
                }
            }
        } catch (Exception e) {}
        
        paintAttribution(g);
    }
    
    /**
     * Paint a single geomarker
     */
    protected void paintGeoMarker(Graphics g, MapGeocacheDot geo, int i) { 
        Point p = getMapPosition(geo.getLat(), geo.getLon());
        geo.paint(g, p, i);
    }
    /**
     * Paint a single Interest Point
     */
    protected void paintInterestPoint(Graphics g, MapInterestPoint point, int i) { 
        Point p = getMapPosition(point.getLat(), point.getLon());
        point.paint(g, p, i);
    }
    
    /**
     * Paint a single line
     */
    protected void paintLine(Graphics g, MapLine line) {
        Point p1 = getMapPositionLine(line.getLat1(), line.getLon1());
        Point p2 = getMapPositionLine(line.getLat2(), line.getLon2());
        
        if (p1.x < 0 || p1.y < 0 || p1.x > getWidth() || p1.y > getHeight())
            if (p2.x < 0 || p2.y < 0 || p2.x > getWidth() || p2.y > getHeight()) {
                p1 = null;
                p2 = null;
            }
        
        if (p1 !=null && p2 != null) {
            Graphics2D g2 = (Graphics2D)g;
            line.paint(g2, p1, p2);
        }
    }
    
    /**
     * @return
     *         if the lines are visible
     */
    public boolean getMapLinesVisible() {
        return mapLinesVisible;
    }
    
    /**
     * @return
     *         if the geodots are visible
     */
    public boolean getMapGeosVisible() {
        return mapGeosVisible;
    }
    /**
     * @return
     *         if the Interest Points are visible
     */
    public boolean getInterestPointVisible() {
        return mapInterestVisible;
    }
    
    /**
     * Sets the lines visibility
     * @param mapLinesVisible
     */
    public void setMapLineVisible(boolean mapLinesVisible) {
        this.mapLinesVisible = mapLinesVisible;
        repaint();
    }
    
    /**
     * Sets the geos visibility
     * @param mapGeosVisible
     */
    public void setMapGeoVisible(boolean mapGeosVisible) {
        this.mapGeosVisible = mapGeosVisible;
        repaint();
    }
    
    /**
     * Sets the Interest Point visibility
     * @param mapGeosVisible
     */
    public void setMapInterestVisible(boolean mapInterestVisible) {
        this.mapInterestVisible = mapInterestVisible;
        repaint();
    }
    
    /**
     * sets the maplinelist
     * @param mapLineList
     */
    public void setMapLineList(List<MapLine> mapLineList) {
        this.mapLineList = mapLineList;
        repaint();
    }
    
    /**
     * sets the mapgeolist
     * @param mapGeoList
     */
    public void setMapGeoList(List<MapGeocacheDot> mapGeoList) {
        this.mapGeoList = mapGeoList;
        repaint();
    }
    
    /**
     * sets the map interest List
     * @param mapInterestList
     */
    public void setInterestList(List<MapInterestPoint> mapInterestList) {
        this.mapInterestPointList = mapInterestList;
        repaint();
    }

    /**
     * @return
     *         returns the maplinelist
     */
    public List<MapLine> getMapLineList() {
        return mapLineList;
    }
    
    /**
     * @return
     *         returns the mapgeolist
     */
    public List<MapGeocacheDot> getMapGeoList() {
        return mapGeoList;
    }
    
    /**
     * @return
     *         returns the map interest list.
     */
    public List<MapInterestPoint> getInterestPointList() {
        return mapInterestPointList;
    }
    
    /**
     * adds the given line
     * @param line
     */
    public void addMapLine(MapLine line) {
        mapLineList.add(line);
        repaint();
    }
    
    /**
     * adds the given geodot
     * @param dot
     */
    public void addMapGeocacheDot(MapGeocacheDot dot) {
        mapGeoList.add(dot);
        repaint();
    }
    
    /**
     * adds the given interest point
     * @param dot
     */
    public void addMapInterestPoint(MapInterestPoint point) {
        mapInterestPointList.add(point);
        repaint();
    }
    
    /**
     * adds a given MapGeocacheDot at the given index
     * @param dot
     * @param index
     */
    public void addMapGeocacheDot(MapGeocacheDot dot, int index) {
        mapGeoList.add(index, dot);
        repaint();
    }
    
    /**
     * adds a given MapInterestPoint at the given index
     * @param point
     * @param index
     */
    public void addMapInterestPoint(MapInterestPoint point, int index) {
        mapInterestPointList.add(index, point);
        repaint();
    }
    
    /**
     * adds a MapLine at the given index
     * @param line
     * @param index
     */
    public void addMapLine(MapLine line, int index) {
        mapLineList.add(index, line);
        repaint();
    }

    /**
     * removes the given line
     * @param line
     */
    public void removeMapLine(MapLine line) {
        mapLineList.remove(line);
        repaint();
    }
    
    /**
     * removes the given Interest Point
     * @param line
     */
    public void removeMapInterestPoint(MapInterestPoint point) {
        mapInterestPointList.remove(point);
        repaint();
    }
    
    /**
     * removes the given geodot
     * @param dot
     */
    public void removeMapGeo(MapGeocacheDot dot) {
        mapGeoList.remove(dot);
        repaint();
    }
    
    /**
     * removes all the lines
     */
    public void removeAllLines() {
        mapLineList.clear();
        repaint();
    }
    
    /**
     * removes all the InterestPoints
     */
    public void removeAllInterestPoint() {
        mapInterestPointList.clear();
        repaint();
    }
    
    /**
     * removes all the geodots
     */
    public void removeAllGeodots() {
        mapGeoList.clear();
        repaint();
    }
    
    /**
     * Makes sure all geomarkers are taken away from the screen
     */
    public void removeGeoMarkers() {
        if (MapGeocacheDot.geoLabel != null) {
            MapGeocacheDot.geoLabel.setVisible(false);
            MapGeocacheDot.geoLabel = null;
        }
    }
    
    /**
     * Makes sure all interest points are taken away from the screen
     */
    public void removeInterestPoints() {
        for (JLabel label: MapInterestPoint.interestLabelList) {
            label.setVisible(false);
            label = null;
        }
        MapInterestPoint.interestLabelList = null;
    }
}
