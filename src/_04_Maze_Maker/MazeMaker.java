package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Cell cell = maze.getCell(randGen.nextInt(maze.cells.length), randGen.nextInt(maze.cells.length));

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(cell);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (unvisitedNeighbors.isEmpty() == false) {
			// C1. select one at random.
			Cell cell = unvisitedNeighbors.get(randGen.nextInt(unvisitedNeighbors.size()));
			// C2. push it to the stack
			uncheckedCells.push(cell);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, cell);
			// C4. make the new cell the current cell and mark it as visited
			// cell.setBeenVisited(true);
			currentCell = cell;
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (uncheckedCells.isEmpty() == false) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell

				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		} else {
			if (c1.getX() > c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

		if (c.getY() != 0) {
			if (maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.getCell(c.getX(), c.getY() - 1));
			}
		}

		if (c.getX() != maze.getWidth() - 1) {
			if (maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.getCell(c.getX() + 1, c.getY()));
			}
		}

		if (c.getY() != maze.getHeight() - 1) {
			if (maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.getCell(c.getX(), c.getY() + 1));
			}
		}

		if (c.getX() != 0) {
			if (maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.getCell(c.getX() - 1, c.getY()));
			}
		}
		return unvisitedNeighbors;
	}
}
