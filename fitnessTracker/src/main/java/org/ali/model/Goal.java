package org.ali.model;

import org.hibernate.validator.constraints.Range;

public class Goal {
	
	
	private int minutes;
	
	@Range(min = 1, max = 120)
	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
