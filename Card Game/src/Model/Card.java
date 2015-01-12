package Model;

public class Card {
	private String cardnameandnumber=null;
	private String cardpathname=null;
	private String cardtype=null;
	private int number;
	
	public Card(String cn,String cpn,String cnn,int num){
		this.cardnameandnumber = cn;
		this.cardpathname=cpn;
		this.cardtype= cnn;
		this.number=num;
	}
	
	
	public String getCardType() {
		return cardtype;
	}
	
	public void setCN(String cN) {
		cardtype = cN;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
	public String getCardpathname() {
		return cardpathname;
	}

	public void setCardpathname(String cardpathname) {
		this.cardpathname = cardpathname;
	}

	public String getCardnameandnumber() {
		return cardnameandnumber;
	}

	public void setCardnameandnumber(String cardnameandnumber) {
		this.cardnameandnumber = cardnameandnumber;
	}
}
