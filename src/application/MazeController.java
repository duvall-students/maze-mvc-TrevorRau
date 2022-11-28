package application;

import java.awt.Point;
import searches.BFS;
import searches.DFS;
import searches.Greedy;
import searches.Magic;
import searches.RandomWalk;
import searches.SearchAlgorithm;
import searches.SearchFactory;

public class MazeController {
	private Maze maze;
	private Point start;
	private Point goal;
    private Greedy greedy;				
	//private BFS bfs;
	//private DFS dfs;
	//private RandomWalk rand;
	private Magic magic;
	private boolean paused = false;	
	//private String search = "";
	private MazeDisplay mazeDisplay;
	
	//new Search Algorithm type
	private SearchAlgorithm search;
	
	//SearchFactory 
	private SearchFactory myFactory;
	
	
	
	public MazeController(int numRows, int numColumns, MazeDisplay display) {
		mazeDisplay = display;
		start = new Point(1,1);
		goal = new Point(numRows-2, numColumns-2);
		maze = new Maze(numRows, numColumns);
		myFactory = new SearchFactory();
	}


	/*
	 * resets all the rectangle colors according to the 
	 * current state of that rectangle in the maze.  This 
	 * method assumes the display maze matches the model maze
	 */

	/*
	 * Does a step in the search only if not paused.
	 */
	public void step(double elapsedTime){
		if(!paused) {
			doOneStep(elapsedTime);
		}
	}
	

	/*
	 * Does a step in the search regardless of pause status
	 */
	public void doOneStep(double elapsedTime){
		/*
		if(search.equals("DFS")) dfs.step();
		else if (search.equals("BFS")) bfs.step();
		else if (search.equals("Greedy")) greedy.step();
		else if (search.equals("RandomWalk")) rand.step();
		else if (search.equals("Magic")) magic.step();
		*/
		
		if(search != null) {
			search.step();
			mazeDisplay.redraw();
		}
		
		
	}
	
	public void startSearch(String searchType) {
		search = myFactory.makeSearch(searchType, maze, start, goal);
	}
	
	
	

	public int getCellState(Point position) {
		return maze.get(position);
	}
	
	

	
	public Maze getMaze() {
		return maze;
	}
	

}
