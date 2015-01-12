package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import javax.imageio.ImageIO;

public class Kortlek{
	
	final String[] filtypes = new String[]{
	         "PNG"
	    };
	ArrayList<Card> kortlek = new ArrayList<>();
	ArrayList<Card> temparray = new ArrayList<>();
	ArrayList<Card> temp2list = new ArrayList<>();
	final File folder = new File("src/images");
	
	public Kortlek(){
		
	}
	
	final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : filtypes) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
	
	public void skapaKortlek(){
		if(folder.isDirectory()){
			for (final File f : folder.listFiles(IMAGE_FILTER)) {
			BufferedImage kortimg = null;
			
			try {
				kortimg= ImageIO.read(f);
				String cn= f.getName().substring(0, f.getName().length()-4);
				String [] namearray = cn.split("#");
				Card newCard= new Card(cn,f.getName(),namearray[0],Integer.parseInt(namearray[1]));
				CardStack.Allavaldakort.add(newCard);
				System.out.println(cn);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
	
}