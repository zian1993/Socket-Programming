//Hassan M. Khan
//Operating Systems Final Project

package JungleBungle;

import java.net.Socket;

public class TheGame {

	//Each game can only have two animals.
	Animal one;
	Animal two;
	
	//The two clients who will be playing the game
	GameRunner first;
	GameRunner second;
	
	//The response that the game will generate
	String response;
	
	public TheGame(GameRunner a, GameRunner b)
	{
		//Accept the clients into the game
		first = a;
		second = b;
		
		//Initializing the animals with ids
		one = new Animal("dudeone");
		two = new Animal("dudetwo");
		
	}
	
	public void setSelection(String a, String b)
	{
		//Setting the selection for the right animal
		if (a.equals("one"))
		{
			one.selection = b;
		}
		
		else
			two.selection = b;
		
		//If both players have selected, then play game
		if (!one.selection.equals("") && !two.selection.equals(""))
		{
			//Tell the users what opponent selected.
			first.tellUser(second.selection);
			second.tellUser(first.selection);
			play();
		}
		
		//If only one player selected, and the other is waiting, let them know!
		else if (one.selection.equals("") && !two.selection.equals(""))
		{
			second.tellUser("Waiting on opponent!");
		}
		
		else if (!one.selection.equals("") && two.selection.equals(""))
		{
			first.tellUser("Waiting on opponent!");
		}
	}
	
	//Setting up the game rules.
	///////////////////////////////////////////////
	
	public void play()
	{
	//If both users have the same selection, its a tie.
	if (one.selection.equals(two.selection))
	{
		//Tell users its a draw.
		first.tellUser("It's a draw!");
		second.tellUser("It's a draw!");
	}
	
	//Lion and rabbit setup
	//Lion beats rabbit.
	if (one.selection.equals("lion")&&two.selection.equals("rabbit"))
	{
		first.tellUser("You won! Lions eats rabbits!");
		second.tellUser("You lost! Lions eats rabbits!");
	}
	
	if (two.selection.equals("lion")&&one.selection.equals("rabbit"))
	{
		second.tellUser("You won! Lions eats rabbits!");
		first.tellUser("You lost! Lions eats rabbits!");
	}
	
	//Cobra and lion setup
	//Cobra beats lion.
	if (one.selection.equals("cobra")&&two.selection.equals("lion"))
	{
		first.tellUser("You won! Cobras bite lions!");
		second.tellUser("You lost! Cobras bite lions!");
	}
	
	if (two.selection.equals("cobra")&&one.selection.equals("lion"))
	{
		second.tellUser("You won! Cobras bite lions!");
		first.tellUser("You lost! Cobras bite lions!");
	}
	
	//Rabbit and cobra setup
	//Rabbits can run from cobras
	if (one.selection.equals("rabbit")&&two.selection.equals("cobra"))
	{
		first.tellUser("You won! Rabbits can run from cobras!");
		second.tellUser("You lost! Rabbits can run from cobras!");
	}
	
	if (two.selection.equals("rabbit")&&one.selection.equals("cobra"))
	{
		second.tellUser("You won! Rabbits can run from cobras!");
		first.tellUser("You lost! Rabbits can run from cobras!");
	}
	
	}

	//Each user will be able to select an animal, by clicking a button.
	//The following is the class declaration for the animal.
	public class Animal
	{
		//Each animal will have the following components.
		String id;
		String selection;
		
		public Animal(String c)
		{
			id = c;
			selection = "";
		}
		
		//Setter for the selection of the user's choice
		public void setSelection(String a)
		{
			selection = a;
		}
		
	}
}

