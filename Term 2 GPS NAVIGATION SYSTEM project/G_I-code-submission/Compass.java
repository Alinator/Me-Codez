package org.openstreetmap.gui.jmapviewer;

/**
 * author mahsa 



 */
import java.awt.Graphics;




import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.swing.JComponent;

public class Compass extends JComponent {

    private Image inputImage;

    private BufferedImage bufferImage;

    private BufferedImage destinationBufferImage = null;

    private Insets frameInsets;

    private int oldLat;
    private int oldLon;
    private int newLat;
    private int newLon;

    public Compass(int oldLat, int oldLon) {
        this.oldLat = oldLat;
        this.oldLon = oldLon;
        setAttributes(0.0);
    }

    public Compass(double angle, int oldLat, int oldLon) {
        this.oldLat = oldLat;
        this.oldLon = oldLon;
        setAttributes(angle);
    }

    public void setAttributes(double angle) {
        addNotify();
        frameInsets = getInsets();
        inputImage = Toolkit.getDefaultToolkit().getImage("compass.png");

        MediaTracker mt = new MediaTracker(this);
        mt.addImage(inputImage, 0);
        try {
            mt.waitForID(0);
        } catch (InterruptedException ie) {
        }

        bufferImage =
                new BufferedImage(inputImage.getWidth(null), inputImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = (Graphics2D) bufferImage.getGraphics();
        g.drawImage(inputImage, 0, 0, null);

        AffineTransform at = new AffineTransform();

        // scale image
        at.scale(2.0, 2.0);

        // rotate angle degrees around image center
        at.rotate(angle * Math.PI / 180.0, bufferImage.getWidth() / 2.0, bufferImage.getHeight() / 2.0);

        /*
         * translate to make sure the rotation doesn't cut off any image data
         */
        AffineTransform translationTransform;
        translationTransform = findTranslation(at, bufferImage);
        at.preConcatenate(translationTransform);

        // instantiate and apply affine transformation filter
        BufferedImageOp bio;
        bio = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        destinationBufferImage = bio.filter(bufferImage, null);

        int frameInsetsHorizontal = frameInsets.right + frameInsets.left;
        int frameInsetsVertical = frameInsets.top + frameInsets.bottom;
        setSize(destinationBufferImage.getWidth() + frameInsetsHorizontal, destinationBufferImage.getHeight()
                + frameInsetsVertical);

        setVisible(true);
    }

    /*
     * find proper translations to keep rotated image correctly displayed
     */
    private AffineTransform findTranslation(AffineTransform at, BufferedImage bi) {
        Point2D p2din, p2dout;

        p2din = new Point2D.Double(0.0, 0.0);
        p2dout = at.transform(p2din, null);
        double ytrans = p2dout.getY();

        p2din = new Point2D.Double(0, bi.getHeight());
        p2dout = at.transform(p2din, null);
        double xtrans = p2dout.getX();

        AffineTransform tat = new AffineTransform();
        tat.translate(-xtrans, -ytrans);
        return tat;
    }

    public void paint(Graphics g) {
        if (destinationBufferImage != null) {
            g.drawImage(destinationBufferImage, frameInsets.left, frameInsets.top, this);
        }
    }

    public void rotateCompass(double angle) {
        AffineTransform at = new AffineTransform();

        // scale image
        at.scale(2.0, 2.0);

        // rotate angle degrees around image center
        at.rotate(angle * Math.PI / 180.0, bufferImage.getWidth() / 2.0, bufferImage.getHeight() / 2.0);

        /*
         * translate to make sure the rotation doesn't cut off any image data
         */
        AffineTransform translationTransform;
        translationTransform = findTranslation(at, bufferImage);
        at.preConcatenate(translationTransform);

        // instantiate and apply affine transformation filter
        BufferedImageOp bio;
        bio = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        destinationBufferImage = bio.filter(bufferImage, null);

        int frameInsetsHorizontal = frameInsets.right + frameInsets.left;
        int frameInsetsVertical = frameInsets.top + frameInsets.bottom;
        setSize(destinationBufferImage.getWidth() + frameInsetsHorizontal, destinationBufferImage.getHeight()
                + frameInsetsVertical);
    }

    public double calculateDirection(int newLat, int newLon) {
        this.newLat = newLat;
        this.newLon = newLon;

        int a = oldLat - newLat;
        int b = oldLon - newLon;
        double c = Math.pow((a * a) + (b * b), 0.5);              

        // Calculation
        double radian = 0;
        double sin = a/c;
        
        radian = Math.asin(sin);
        double angle = radian / (Math.PI /180);    
        System.out.println("rotate radian is"+radian);

        // After calculation is done, we transfer the new position to the old one.        
        this.oldLat = this.newLat;
        this.oldLon = this.newLon;

        return angle;
    }

    public static void main(String[] args) {
        Compass compass = new Compass(90, 90);
        compass.calculateDirection(100, 100);
    }

}
