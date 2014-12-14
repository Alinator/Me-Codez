
package server;
import java.util.ArrayList;
import java.util.Collections;

public class Predecessors {

	public Integer _startNode;
	private ArrayList<Integer> predecessors;
	private ArrayList<Integer> resultPath, finalDestinations;
	boolean needResultPath;
	boolean resultPathComplete = false;
	// This variable turns console output on/off (output can be very large, up
	// to 4Gb text file)
	private boolean dbgMessages = false;

	public Predecessors(Integer startNode) {
		predecessors = new ArrayList<Integer>();
		_startNode = startNode;
	}

	public void addToPredecessors(int ancestor, int current) {
		if (dbgMessages) {
			System.out
					.println("*Predecessors class: addToPredecessors: adding the nodes to the array: "
							+ predecessors.toString());
		}
		predecessors.add(ancestor);
		if (dbgMessages) {
			System.out
					.println("*Predecessors class: addToPredecessors: added the ancestor node to array: "
							+ ancestor);
		}
		predecessors.add(current);
		if (dbgMessages) {
			System.out
					.println("*Predecessors class: addToPredecessors: added the current node to array: "
							+ current);
		}

	}

	public void printPredecessors() {

	}

	public ArrayList<Integer> getPath(Integer to, boolean needResultPath) {
		if (!needResultPath) {
			resultPath = new ArrayList<Integer>();
			resultPath.add(to);
		}

		for (int i = 0; i < predecessors.size(); i += 2) {

			if (predecessors.get(i + 1) == null) {
				resultPath.clear();
					
			} else if (predecessors.get(i + 1).equals(to)) {
					
				resultPath.add(predecessors.get(i));
					
				if (predecessors.get(i).equals(_startNode)) {

					
					resultPathComplete = true;
				} else {

					needResultPath = true;
					getPath(predecessors.get(i), needResultPath);
				}

			} else {

			}

		}

		if (resultPathComplete) {
			
		}
		return resultPath;
	}
	
	public ArrayList<Integer> getPredecessors(){
		return predecessors;
	}

}
