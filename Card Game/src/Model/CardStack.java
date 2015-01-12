package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class CardStack {

	public static ArrayList<Card> Allavaldakort = new ArrayList<>();
	public static Stack<Card> cardstack = new Stack<Card>();
	public static ArrayList<Card> temparray = new ArrayList<>();
	public static ArrayList<Card> temp2list = new ArrayList<>();
	
	
	public static void sorteraAllavaldakort(){
		System.out.println("**********efter sortering************");
		// för hjärtor
		for (int i = 0; i < Allavaldakort.size(); i++) {
			String element = Allavaldakort.get(i).getCardType();
			if(element.equalsIgnoreCase("h")){
				temparray.add(Allavaldakort.get(i));
			}
		}
		
		QS.sort(temparray);
		for(int i=0; i < temparray.size(); i++){
			temp2list.add(temparray.get(i));
		}
		
		temparray.clear();
		
		// för rutor
		for (int i = 0; i < Allavaldakort.size(); i++) {
			String element = Allavaldakort.get(i).getCardType();
			if(element.equalsIgnoreCase("r")){
				temparray.add(Allavaldakort.get(i));
				
			}
		}
		
		QS.sort(temparray);
		for(int i=0; i < temparray.size(); i++){
			temp2list.add(temparray.get(i));
		}
		
		temparray.clear();
		
		// för klöver
		for (int i = 0; i < Allavaldakort.size(); i++) {
			String element = Allavaldakort.get(i).getCardType();
			if(element.equalsIgnoreCase("k")){
				temparray.add(Allavaldakort.get(i));
				
			}
		}
		
		QS.sort(temparray);
		for(int i=0; i < temparray.size(); i++){
			temp2list.add(temparray.get(i));
		}
		
		temparray.clear();
		
		// för spader
		for (int i = 0; i < Allavaldakort.size(); i++) {
			String element = Allavaldakort.get(i).getCardType();
			if(element.equalsIgnoreCase("s")){
				temparray.add(Allavaldakort.get(i));
				
			}
		}
		
		QS.sort(temparray);
		for(int i=0; i < temparray.size(); i++){
			temp2list.add(temparray.get(i));
		}
		
		temparray.clear();
		Allavaldakort.clear();
		
		for(int a=0; a < temp2list.size(); a++){
				Allavaldakort.add(temp2list.get(a));	
		}
		
		for(int x=0; x < Allavaldakort.size();x++){
			System.out.println(Allavaldakort.get(x).getCardnameandnumber());
			CardStack.cardstack.add(Allavaldakort.get(x));
		}
		temp2list.clear();
	}
	public static void blandaKortlek(){
		System.out.println("**********after shuffling************");
		Collections.shuffle(Allavaldakort);
		CardStack.cardstack.clear();
		for (int i = 0; i < Allavaldakort.size(); i++) {
			System.out.println(Allavaldakort.get(i).getCardnameandnumber());
			CardStack.cardstack.add(Allavaldakort.get(i));
		}
	}
	
}
