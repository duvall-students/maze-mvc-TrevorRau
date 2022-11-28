package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import application.Maze;

public class Magic extends Greedy {	
	// Keeps up with the child-parent trail so we can recreate the chosen path



		public Magic(Maze mazeBlocks, Point startPoint, Point goalPoint){
			super(mazeBlocks,startPoint,goalPoint);
			childParent = new HashMap<>();
			// For a greedy searcher, we will use a priority queue
			// based on the number of steps away from the goal.		
			data = new PriorityQueue<Point>(15, (p1, p2) -> distanceToGoal(p1)-distanceToGoal(p2));
			data.add(startPoint);
		}

		/*
		 * Algorithm for Greedy Search
		 */
		/*
		public boolean step(){
			// Don't keep computing after goal is reached or determined impossible.
			if(searchOver){
				colorPath();
				return searchResult;
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
			// if no next step is found, mark current 
			// state "visited" and take off queue.
			else{	
				maze.markVisited(current);
				PriorityQueue<Point> queue = (PriorityQueue<Point>) data;
				queue.remove();
			}
			resetCurrent();
			checkSearchOver();
			return searchResult;	
		}
		*/

		private int distanceToGoal(Point p){
			return goal.x-p.x + goal.y-p.y;
		}


		/*
		 * This method defines which "neighbors" or next points can be reached in the maze from
		 * the current one.  
		 * 
		 * In this method, the neighbors are defined as the four squares immediately to the north, south,
		 * east, and west of the current point, but only if they are in the bounds of the maze.  It does 
		 * NOT check to see if the point is a wall, or visited.  
		 * 
		 * Any other definition of "neighbor" indicates the search subclass should override this method.
		 */
	
		
	

		
		/*
		 * Rather than choosing the (first) closest NON-wall, choose 
		 * any of the closest next squares.
		 */
		@Override
		protected Point chooseNeighbor(Collection<Point> neighbors){
			Point closest = closestToGoal(neighbors);
			List<Point> possibles = new ArrayList<>();
			for(Point p: neighbors){
				if(distanceToGoal(p) == distanceToGoal(closest)){
					possibles.add(p);
				}
			}
			if(!possibles.isEmpty()){
				int randIndex = (int)(Math.random()*possibles.size());
				return possibles.get(randIndex);
			}
			return null;
		}

		/*
		 * Of all the neighbors, choose one with the smallest distance to goal.
		 */
		/*
		private Point closestToGoal(Collection<Point> neighbors){
			int smallestDistance = Integer.MAX_VALUE;
			Point next = null;
			for(Point p: neighbors){
				int dist = distanceToGoal(p);
				if(dist < smallestDistance){
					next = p;
					smallestDistance = dist;
				}

			}
			return next;
		}
		*/

		/*
		 * When a next step is found, add it to the queue and remember the child-parent relationship
		 */
		/*
		private void recordLink(Point next){	
			data.add(next);
			childParent.put(next,current);
		}
		*/
		/*
		 * The current node is the one chosen by the priority queue
		 */
		



		/*
		 * Search is over and unsuccessful if there are no more fringe points to consider.
		 * Search is over and successful if the current point is the same as the goal.
		 */


		/*
		 * Tells me when the search is over.
		 */
	


		/*
		 * Use the trail from child to parent to color the actual chosen path
		 */
	

}
