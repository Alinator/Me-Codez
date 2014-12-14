package r15;
public class Button {

	int x,y,w,h;
	String text;
	public boolean pushed = false;
	
	Button (int ix, int iy, String str){
		x = ix;
		y = iy;
		
		w = 120;
		h = 32;
		
		text = str;
		
	}
	
	boolean pushed() {
		return pushed;
		
	}
	
}
