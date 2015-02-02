package org.ali.controller;

import java.awt.List;
import java.util.ArrayList;

import org.ali.model.Activity;
import org.ali.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ali.model.*;
import org.ali.service.ExerciseService;

@Controller
public class MinutesController {

	@Autowired
	private ExerciseService exerciseService;
	
	
	@RequestMapping(value="/addMinutes")
	public String addMinutes(@ModelAttribute ("exercise") Exercise exercise){

		System.out.println("exercise:"+ exercise.getMinutes());
		
		
		return "addMinutes";
	}


	@RequestMapping(value="/activities", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Activity> findAllActivities(){
		
		return exerciseService.findAllActivities();


	}
	//	@RequestMapping(value="/addMoreMinutes")
	//	public String addMoreMinutes(@ModelAttribute ("exercise") Exercise exercise){
	//		
	//		System.out.println("exercising:"+ exercise.getMinutes());
	//		return "addMinutes";
	//	}
}
