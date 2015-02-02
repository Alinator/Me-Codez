package org.ali.service;

import java.util.ArrayList;

import org.ali.model.Activity;
import org.springframework.stereotype.Service;

@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService{
	
	public ArrayList<Activity> findAllActivities(){
		ArrayList<Activity> activities= new ArrayList<Activity>();

		Activity run = new Activity();
		run.setDesc("Run");
		activities.add(run);

		Activity bike = new Activity();
		bike.setDesc("bike");
		activities.add(bike);

		Activity swim = new Activity();
		swim.setDesc("swim");
		activities.add(swim);

		return activities;
	}
}
