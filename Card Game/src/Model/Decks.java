package Model;

import java.util.ArrayList;

public class Decks {
	
	ArrayList<Kortlek> allaKortlek = new ArrayList<>();
	
	public Decks(){
	}

	public ArrayList<Kortlek> getAllaKortlek() {
		return allaKortlek;
	}
	
	public void addNewDeck(Kortlek kl){
		getAllaKortlek().add(kl);
	}
	
	public void skapaAllaKortlekar(String kortlek){
		int number= Integer.parseInt(kortlek.substring(kortlek.length()-1))-1;
		getAllaKortlek().get(number).skapaKortlek();
	}
//	public void blandaAllaDecks(){
//		for(int i=0; i< getAllaKortlek().size(); i++){
//			getAllaKortlek().get(i).blandaKortlek();
//		}
//	}
	
}
