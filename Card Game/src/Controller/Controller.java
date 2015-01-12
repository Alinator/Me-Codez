package Controller;

import Model.Card;
import Model.Decks;
import Model.Kortlek;

public class Controller {
	public Decks myDecks= new Decks();
	
	public void callForAddingDeck(){
		Kortlek nyKortlek = new Kortlek();
		nyKortlek.skapaKortlek();
		myDecks.addNewDeck(nyKortlek);
	}
//	public String callForPopCard(){
//		return firstDeck.draKort();
//	}
//	public void callForShuffleOfDecks(){
//		myDecks.blandaAllaDecks();
//	}
	public void callForBuildDeck(String kortlek){
		myDecks.skapaAllaKortlekar(kortlek);
	}
//	public void callForSorting(String kortlekar){
//		myDecks.sorteraAllaDecks(kortlekar);
//	}
}
