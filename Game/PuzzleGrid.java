package Game;

import java.util.ArrayList;

import Game.ImageFactory.Player;
import Game.ImageFactory.Type;

/**
 * Holds the information for an initial state (including the dimensions and the layout of the
 * puzzle as an array of image types) of a puzzle generated by the PuzzleGridGenerator
 * which can be passed to the PuzzlePanel
 * @field shadowMode: boolean for if the level has shadow mode enabled
 * @field shadowMode: boolean for if the level is a multiplayer level
 * @field levelID: integer to identify each puzzlegrid level
 * @field rows: The number of rows of the grid
 * @field columns: The number of columns in the grid
 * @field playerOne: PuzzleLabel object to keep track of player one in the level
 * @field playerTwo: PuzzleLabel object to keep track of player two in the level
 * @field labelSequence: ArrayList of PuzzleLable objects that specify what each tile of the map will be
 * @field highScore: the current highscore for the level
 */
public class PuzzleGrid
{
	private boolean multiplayer;
	private int levelID;
	private int rows;
	private int columns;
	private PuzzleLabel playerOne;
	private PuzzleLabel playerTwo;
	private ArrayList<PuzzleLabel> labelSequence;
	private int highScore;
	
	/**
	 * Constructor for making the PuzzleGrid in its starting state
	 * @param multiPlayer: is multiplayer enabled
	 * @param ID: number ID of the level
	 * @param rows: number of rows in the grid
	 * @param columns: number of columns in the grid
	 * @param startingLabelTypes: Array of Type enums that specify what goes in each tile of the grid initially 
	 */
	public PuzzleGrid(boolean multiPlayer, int ID, int rows, int columns, Type[] startingLabelTypes)
	{
		this.multiplayer = multiPlayer;
		this.levelID = ID;
		this.rows = rows;
		this.columns = columns;
		this.labelSequence = initializeStartingLabels(startingLabelTypes);
		this.highScore = -1;
	}
	
	/**
	 * Constructor for updating the PuzzleGrid when a change is made through PuzzleManager
	 * @param rows: number of rows in the grid
	 * @param columns: number of columns in the grid
	 * @param labelSequence: ArrayList of PuzzleLable objects that specify what each tile of the new map will be
	 * @field playerOne: PuzzleLabel object to keep track of player one in the level
	 * @field playerTwo: PuzzleLabel object to keep track of player two in the level
	 */
	public PuzzleGrid(int rows, int columns, ArrayList<PuzzleLabel> labelSequence, PuzzleLabel playerOne, PuzzleLabel playerTwo)
	{
		this.rows = rows;
		this.columns = columns;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.labelSequence = labelSequence;
	}
	
	/**
	 * Converts an array of type enums into an arraylist of puzzlepanels
	 * @precondition: Single player grids contain exactly one Type.P1_RIGHT and multiplayer grids contain exactly 
	 * one Type.P1_RIGHT and one Type.P2_RIGHT
	 * @param startingLabelTypes: Array of Type enums that specify what goes in each tile of the grid initially 
	 * @return ArrayList of PuzzlePanels
	 */
	private ArrayList<PuzzleLabel> initializeStartingLabels(Type[] startingLabelTypes)
	{
		ArrayList<PuzzleLabel> labels = new ArrayList<PuzzleLabel>();
		
		for(Type type : startingLabelTypes)
		{
			switch(type) 
			{
				case P2_RIGHT:
				case P1_RIGHT:	labels.add(registerPlayer(type));										break;
				default:		labels.add(new PuzzleLabel(type));		break;
			}
		}
		
		return labels;
	}
	
	/**
	 * Method to assign players and establish other fields when a specific player Type enum is encountered
	 * @param t: the input Type enum which is used to assign players
	 * @return PuzzleLabel for the player that is being assigned
	 */
	private PuzzleLabel registerPlayer(Type t)
	{
		PuzzleLabel player = new PuzzleLabel(t);
		this.playerOne = t == Type.P1_RIGHT ? player : playerOne;
		this.playerTwo = t == Type.P2_RIGHT ? player : playerTwo;
		return player;
	}
	
	/**
	 * @return: The number of rows for this grid
	 */
	public int getRows()
	{
		return rows;
	}
	
	/**
	 * @return: The number of columns of this grid
	 */
	public int getColumns()
	{
		return columns;
	}

	
	public int getLevelID() 
	{
		return levelID;
	}
	
	public int getHighScore() 
	{
		return highScore;
	}

	public void setHighScore(int highScore) 
	{
		this.highScore = highScore;
	}
	
	public boolean isMultiplayer()
	{
		return this.multiplayer;
	}

	/**
	 * @return The reference for the player object
	 */
	public PuzzleLabel getPlayer(Player playerNumber)
	{
		return playerNumber == Player.ONE ? playerOne : playerNumber == Player.TWO ? playerTwo : null;
	}
	
	/**
	 * Adds a set number of puzzle squares to the labels array list. It adds labels in the order
	 * that they should be displayed starting at the top of the panel and going from left to right
	 * adding the number of labels for each column in that row, then goes to the next row until
	 * it reaches the bottom
	 * @return labels: The array list with the ordered puzzle squares
	 */
	public ArrayList<PuzzleLabel> getLabelSequence()
	{
		return labelSequence;
	}

}