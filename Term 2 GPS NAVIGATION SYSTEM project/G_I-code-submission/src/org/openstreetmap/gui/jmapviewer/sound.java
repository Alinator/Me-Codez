package org.openstreetmap.gui.jmapviewer;

import java.io.*;
import javax.sound.sampled.*;


/**
 * 
 */
public class sound {
   
      Player audio =new Player();
      
      /**
       * plays .wav and .au audeo files.
       * @param audioFile:the directory of the audio file.
       */
      public void play(String audioFile){
          audio.playAudio(audioFile);//Play the file
      }
     
          

/**
 * uses java inbuilt methods to play sound            

 *
 */
 private class Player  {

  AudioFormat audioFormat;//the format of the audio format(like .wav )
  AudioInputStream audioInputStream;
  SourceDataLine sourceDataLine;
  boolean stopPlayback = false;
  /**
   * plays the audio
   * @param file: the directory for the audio file.
   */
  private void playAudio(String file) {
        try{
          File soundFile =
                       new File(file);
          audioInputStream = AudioSystem.
                      getAudioInputStream(soundFile);
          audioFormat = audioInputStream.getFormat();
    
          DataLine.Info dataLineInfo =
                              new DataLine.Info(
                                SourceDataLine.class,
                                        audioFormat);
    
          sourceDataLine =
                 (SourceDataLine)AudioSystem.getLine(
                                       dataLineInfo);
    
          new PlayThread().start();
        }catch (Exception e) {
          e.printStackTrace();
          System.exit(0);
        }
      }

@SuppressWarnings("unused")
public boolean isStopPlayback() {
    return stopPlayback;
}

@SuppressWarnings("unused")
public void setStopPlayback(boolean stopPlayback) {
    this.stopPlayback = stopPlayback;
}

private class PlayThread extends Thread{
  byte tempBuffer[] = new byte[10000];

  public void run(){
    try{
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      int cnt;
     
      while((cnt = audioInputStream.read(
           tempBuffer,0,tempBuffer.length)) != -1
                       && stopPlayback == false){
        if(cnt > 0){
          sourceDataLine.write(
                             tempBuffer, 0, cnt);
        }
      }
      
      sourceDataLine.drain();
      sourceDataLine.close();

      
    }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
}

}

 }
