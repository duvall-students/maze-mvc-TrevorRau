package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import application.Maze;

public abstract class SearchAlgorithm {
	
	protected Maze maze;					// The maze being solved
	protected Point goal;					// The goal Point - will let us know when search is successful
	protected Collection<Point> data;		// Data structure used to keep "fringe" points
	private boolean searchOver = false;	// Is search done?
	protected boolean searchResult = false;	// Was it successful?
	protected Point current;				// Current point being explored
	
	public SearchAlgorithm(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		maze = mazeBlocks;
		goal = goalPoint;
		current = startPoint;
		maze.markPath(current);
	}
	
	//template pattern step
	public boolean step() {
		if(searchOver){
			return endSearch();
		}
		// Find possible next steps
		Collection<Point> neighbors = getNeighbors();
		// Choose one to be a part of the path
		Point next = chooseNeighbor(neighbors);
		// mark the next step
		if(next!=null){
			maze.markPath(next);
			recordLink(next);
		}
		// if no next step is found
		else{	
			visitPoint();
		}
		resetCurrent();
		checkSearchOver();
		return searchResult;	
	}
	
	//when a new is chosen, push it on the stack
	protected abstract void recordLink(Point next);
	
	protected abstract void resetCurrent();
	
	protected abstract Point chooseNeighbor(Collection<Point> neighbors);
	
	protected abstract void visitPoint();
	
	protected abstract boolean endSearch();
	
	//can be concrete
	private Collection<Point> getNeighbors(){
		List<Point> maybeNeighbors = new ArrayList<>();
		maybeNeighbors.add(new Point(current.x-1,current.y));
		maybeNeighbors.add(new Point(current.x+1,current.y));
		maybeNeighbors.add(new Point(current.x,current.y+1));
		maybeNeighbors.add(new Point(current.x,current.y-1));
		List<Point> neighbors = new ArrayList<>();
		for(Point p: maybeNeighbors){
			if(maze.inBounds(p)){
				neighbors.add(p);
			}
		}
		return neighbors;
	}
	
	//same in dfs, bfs, but not random walk 
	
	
	//same in all three methods
	private boolean isGoal(Point square){
		return square!= null && square.equals(goal);
	}
	
	//same in dfs,bfs, and random walk concrete
	private void checkSearchOver(){
		if(data!= null && data.isEmpty()) {
			searchOver = true;
			searchResult = false;
		}
		if(isGoal(current)){
			searchOver = true;
			searchResult = true;
		}
	}
	
	

}
