package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Controller.Controller;
import View.View;

public class ShufflingTest {
	
	ArrayList<String> CorrectlySortedArrayList = new ArrayList<>();
	ArrayList<String> copyOfKortlek = new ArrayList<>();
	Controller controller;
	
	@Before
	public void preparation(){
		controller = new Controller();
		View newView= new View(controller);
		newView.RunSim();
		
		// initialize the array in correct order
		CorrectlySortedArrayList.add("h#1#r�d");
		CorrectlySortedArrayList.add("h#2#r�d");
		CorrectlySortedArrayList.add("h#3#r�d");
		CorrectlySortedArrayList.add("h#4#r�d");
		CorrectlySortedArrayList.add("h#5#r�d");
		CorrectlySortedArrayList.add("h#6#r�d");
		CorrectlySortedArrayList.add("h#7#r�d");
		CorrectlySortedArrayList.add("h#8#r�d");
		CorrectlySortedArrayList.add("h#9#r�d");
		CorrectlySortedArrayList.add("h#10#r�d");
		CorrectlySortedArrayList.add("h#11#r�d");
		CorrectlySortedArrayList.add("h#12#r�d");
		CorrectlySortedArrayList.add("h#13#r�d");
		///
		CorrectlySortedArrayList.add("r#1#r�d");
		CorrectlySortedArrayList.add("r#2#r�d");
		CorrectlySortedArrayList.add("r#3#r�d");
		CorrectlySortedArrayList.add("r#4#r�d");
		CorrectlySortedArrayList.add("r#5#r�d");
		CorrectlySortedArrayList.add("r#6#r�d");
		CorrectlySortedArrayList.add("r#7#r�d");
		CorrectlySortedArrayList.add("r#8#r�d");
		CorrectlySortedArrayList.add("r#9#r�d");
		CorrectlySortedArrayList.add("r#10#r�d");
		CorrectlySortedArrayList.add("r#11#r�d");
		CorrectlySortedArrayList.add("r#12#r�d");
		CorrectlySortedArrayList.add("r#13#r�d");
		//
		CorrectlySortedArrayList.add("k#1#svart");
		CorrectlySortedArrayList.add("k#2#svart");
		CorrectlySortedArrayList.add("k#3#svart");
		CorrectlySortedArrayList.add("k#4#svart");
		CorrectlySortedArrayList.add("k#5#svart");
		CorrectlySortedArrayList.add("k#6#svart");
		CorrectlySortedArrayList.add("k#7#svart");
		CorrectlySortedArrayList.add("k#8#svart");
		CorrectlySortedArrayList.add("k#9#svart");
		CorrectlySortedArrayList.add("k#10#svart");
		CorrectlySortedArrayList.add("k#11#svart");
		CorrectlySortedArrayList.add("k#12#svart");
		CorrectlySortedArrayList.add("k#13#svart");
		//
		CorrectlySortedArrayList.add("s#1#svart");
		CorrectlySortedArrayList.add("s#2#svart");
		CorrectlySortedArrayList.add("s#3#svart");
		CorrectlySortedArrayList.add("s#4#svart");
		CorrectlySortedArrayList.add("s#5#svart");
		CorrectlySortedArrayList.add("s#6#svart");
		CorrectlySortedArrayList.add("s#7#svart");
		CorrectlySortedArrayList.add("s#8#svart");
		CorrectlySortedArrayList.add("s#9#svart");
		CorrectlySortedArrayList.add("s#10#svart");
		CorrectlySortedArrayList.add("s#11#svart");
		CorrectlySortedArrayList.add("s#12#svart");
		CorrectlySortedArrayList.add("s#13#svart");
		
	}
	
	@Test
	public void testShufflingOfSortedDeck() {
		CardStack.blandaKortlek();
		
		for(int i=0; i < CardStack.Allavaldakort.size(); i++){
			copyOfKortlek.add(CardStack.Allavaldakort.get(i).getCardnameandnumber());
		}
		
		assertFalse(CorrectlySortedArrayList.equals(copyOfKortlek));
	}
	@After 
	public void clear(){
		copyOfKortlek.clear();
	}
}
