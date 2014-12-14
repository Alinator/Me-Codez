package org.openstreetmap.gui.jmapviewer;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Calendar;

import org.openstreetmap.gui.jmapviewer.GUI.TimeStd;

/**
 * 
 * @author Johan, Sebastian, Ali
 *
 */
class Clock implements ActionListener {
    
    private int hour;
    private int minute;
    private boolean isAM;
    public void actionPerformed(ActionEvent arg0) {
         setClock();
    }
    
    /**
     * Sets the value of the clock. Depends on the value of the enum
     * TimeStd variable timeStd, 24hours or 12 hours standard
     */
    public void setClock() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        if (GUI.timeStd == TimeStd.TWENTYFOUR) {
            if (hour < 10)
                GUI.clock.setText("0"+hour+":");
            else 
                GUI.clock.setText(hour+":");
            if (minute < 10)
                GUI.clock.setText(GUI.clock.getText() + "0" + minute);
            else
                GUI.clock.setText(GUI.clock.getText() + minute);
        }   
        else {
            if (hour < 13)
                isAM = true;
            if (hour > 12)
                hour = hour%12;
            if (hour != 12)
                if (hour < 10)
                    GUI.clock.setText("0" + hour + ":");
                else
                    GUI.clock.setText(hour + ":");
            else
                GUI.clock.setText(hour + ":");
            if (minute < 10)
                GUI.clock.setText(GUI.clock.getText() + "0" + minute);
            else 
                GUI.clock.setText(GUI.clock.getText() + minute);
            if (isAM)
                GUI.clock.setText(GUI.clock.getText() + " AM");
            else
                GUI.clock.setText(GUI.clock.getText() + " PM");   
        }
    } 
}