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
		CorrectlySortedArrayList.add("h#1#röd");
		CorrectlySortedArrayList.add("h#10#röd");
		CorrectlySortedArrayList.add("h#11#röd");
		CorrectlySortedArrayList.add("h#12#röd");
		CorrectlySortedArrayList.add("h#13#röd");
		CorrectlySortedArrayList.add("h#2#röd");
		CorrectlySortedArrayList.add("h#3#röd");
		CorrectlySortedArrayList.add("h#4#röd");
		CorrectlySortedArrayList.add("h#5#röd");
		CorrectlySortedArrayList.add("h#6#röd");
		CorrectlySortedArrayList.add("h#7#röd");
		CorrectlySortedArrayList.add("h#8#röd");
		CorrectlySortedArrayList.add("h#9#röd");
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
		CorrectlySortedArrayList.add("r#1#röd");
		CorrectlySortedArrayList.add("r#10#röd");
		CorrectlySortedArrayList.add("r#11#röd");
		CorrectlySortedArrayList.add("r#12#röd");
		CorrectlySortedArrayList.add("r#13#röd");
		CorrectlySortedArrayList.add("r#2#röd");
		CorrectlySortedArrayList.add("r#3#röd");
		CorrectlySortedArrayList.add("r#4#röd");
		CorrectlySortedArrayList.add("r#5#röd");
		CorrectlySortedArrayList.add("r#6#röd");
		CorrectlySortedArrayList.add("r#7#röd");
		CorrectlySortedArrayList.add("r#8#röd");
		CorrectlySortedArrayList.add("r#9#röd");
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
