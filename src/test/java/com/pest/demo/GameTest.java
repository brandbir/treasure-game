package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.Map;
import game.Player;
import game.Position;
import game.Misc;

public class GameTest{

	Map map;
	Player player;
	ArrayList<Player> players;
	
	@Before
	public void before()
	{
		map = new Map();
		map.setSize(4, 5);
		map.generateMap();
		
		player = new Player(1);
		player.setPosition(map, new Position(1,2));
		
		players = new ArrayList<Player>();
	}
	
	/**
	 * ensuring amount of players is as setted
	 */
	@Test
	public void testNoOfPlayers()
	{
		players = Game.setNumPlayers(4, players);
		assertEquals(players.size(),4);
		assertNotNull(players.size());
	}
	/**
	 * Testing that HTML file are generated as expected
	 */
	@Test 
	public void generateHTMLFile() 
	{
		int condition = Misc.writeToFile(map,"external/maps/map_player_1.html",true,player);
		assertEquals(condition,1);
		int condition1 = Misc.writeToFile(map,"external/maps/map_player_1.html",false,player);
		assertEquals(condition1,1);
		
		int condition2 = Misc.writeToFile(map,"external/map/map_player_1.html",false,player);
		assertEquals(condition2,-1);
	}
	
	/**
	 * testing the delete files method
	 * @throws IOException
	 */
	@Test 
	public void testingDeleteFilesMethod() throws IOException
	{
		File folder = new File("external/maps");
		
		/*try
		{
			//if (f.getParentFile().mkdir()) {
			    f.createNewFile();
			//} else {
			//    throw new IOException("Failed to create directory " + f.getParent());
			//}
		}
		catch(Exception e)
		{
			System.out.println("exception" + e.getMessage());
		}*/
		
		String[] subfiles;
		subfiles = folder.list();
		//System.out.println("No of files in directory" + subfiles.length);
		
		Misc.deleteFiles("external/maps/testingaaaaa");
		subfiles = folder.list();
		//System.out.println("No of files in directory" + subfiles.length);
		
		assertEquals(subfiles.length,1);
		
		Misc.deleteFiles("external/maps");
		subfiles = folder.list();
		//System.out.println("No of files in directory" + subfiles.length);
		
		assertEquals(subfiles.length,0);
	}
	
	@Test 
	public void testingGame()
	{
		Game.startGame();
	}
}