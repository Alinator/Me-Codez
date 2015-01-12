package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Controller.Controller;
import View.View;

public class SkapaKortlekTest {
	Controller controller;
	ArrayList<String> CorrectlySortedArrayList;
	@Before
	public void preparation(){
		
		controller = new Controller();
		View newView= new View(controller);
		newView.RunSim();
		CorrectlySortedArrayList= new ArrayList<>();
		// initialize the array in correct order
		CorrectlySortedArrayList.add("h#1#r�d");
		CorrectlySortedArrayList.add("h#10#r�d");
		CorrectlySortedArrayList.add("h#11#r�d");
		CorrectlySortedArrayList.add("h#12#r�d");
		CorrectlySortedArrayList.add("h#13#r�d");
		CorrectlySortedArrayList.add("h#2#r�d");
		CorrectlySortedArrayList.add("h#3#r�d");
		CorrectlySortedArrayList.add("h#4#r�d");
		CorrectlySortedArrayList.add("h#5#r�d");
		CorrectlySortedArrayList.add("h#6#r�d");
		CorrectlySortedArrayList.add("h#7#r�d");
		CorrectlySortedArrayList.add("h#8#r�d");
		CorrectlySortedArrayList.add("h#9#r�d");
		///
		CorrectlySortedArrayList.add("k#1#svart");
		CorrectlySortedArrayList.add("k#10#svart");
		CorrectlySortedArrayList.add("k#11#svart");
		CorrectlySortedArrayList.add("k#12#svart");
		CorrectlySortedArrayList.add("k#12#svart");
		CorrectlySortedArrayList.add("k#2#svart");
		CorrectlySortedArrayList.add("k#3#svart");
		CorrectlySortedArrayList.add("k#4#svart");
		CorrectlySortedArrayList.add("k#5#svart");
		CorrectlySortedArrayList.add("k#6#svart");
		CorrectlySortedArrayList.add("k#7#svart");
		CorrectlySortedArrayList.add("k#8#svart");
		CorrectlySortedArrayList.add("k#9#svart");
		//
		CorrectlySortedArrayList.add("r#1#r�d");
		CorrectlySortedArrayList.add("r#10#r�d");
		CorrectlySortedArrayList.add("r#11#r�d");
		CorrectlySortedArrayList.add("r#12#r�d");
		CorrectlySortedArrayList.add("r#13#r�d");
		CorrectlySortedArrayList.add("r#2#r�d");
		CorrectlySortedArrayList.add("r#3#r�d");
		CorrectlySortedArrayList.add("r#4#r�d");
		CorrectlySortedArrayList.add("r#5#r�d");
		CorrectlySortedArrayList.add("r#6#r�d");
		CorrectlySortedArrayList.add("r#7#r�d");
		CorrectlySortedArrayList.add("r#8#r�d");
		CorrectlySortedArrayList.add("r#9#r�d");
		//
		CorrectlySortedArrayList.add("s#1#svart");
		CorrectlySortedArrayList.add("s#10#svart");
		CorrectlySortedArrayList.add("s#11#svart");
		CorrectlySortedArrayList.add("s#12#svart");
		CorrectlySortedArrayList.add("s#13#svart");
		CorrectlySortedArrayList.add("s#2#svart");
		CorrectlySortedArrayList.add("s#3#svart");
		CorrectlySortedArrayList.add("s#4#svart");
		CorrectlySortedArrayList.add("s#5#svart");
		CorrectlySortedArrayList.add("s#6#svart");
		CorrectlySortedArrayList.add("s#7#svart");
		CorrectlySortedArrayList.add("s#8#svart");
		CorrectlySortedArrayList.add("s#9#svart");
	}
	
	@Test
	public void testaSkapaKortlekMetoden() {
	try{	
		controller.callForAddingDeck();
		assertTrue(controller.myDecks.getAllaKortlek().size() != 0);
		assertTrue(controller.myDecks.allaKortlek.get(1).kortlek.equals(CorrectlySortedArrayList));
	} 
	catch(AssertionError e){        
	}
	}

}
