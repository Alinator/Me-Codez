package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.Controller;
import View.View;

	public class DraKortTest{
	Controller controller;
	
	
	@Before
	public void preparation(){
		controller = new Controller();
		View newView= new View(controller);
		newView.RunSim();
		
	}

	
	@Test
	public void testDraKortEfterSortering() {
		
		CardStack.sorteraAllavaldakort();
		assertEquals("s#13#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#12#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#11#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#10#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#9#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#8#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#7#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#6#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#5#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#4#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#3#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#2#svart.PNG",CardStack.cardstack.pop().getCardpathname());
		assertEquals("s#1#svart.PNG",CardStack.cardstack.pop().getCardpathname());
	}

}
