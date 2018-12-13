//Hassan M. Khan
//Operating Systems Final Project

package JungleBungle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDude {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//First things first, I need a socket server to listen for clients
		//wanting to connect.
		//Set server socket to listen at port 8079 for clients wanting to connect.
		ServerSocket gatekeeper = new ServerSocket(8079);
		
		//Print confirmation that the server is now online
		System.out.println("Server is up and running my friend!");
		
		//Now, server need to keep listening for connections.
		//When two players connect, let them start a game.
	try {
		while (true)
		{
			//Accepts two sockets, and then starts a game with the two.
			GameRunner first = new GameRunner(gatekeeper.accept(), "one");
			
			//Tell the first one, that we are waiting on a player for him.
			first.tellUser("We are waiting on a player for you!");
			
			GameRunner second = new GameRunner(gatekeeper.accept(),"two");
			
			System.out.println("Two gamers found!");
			
			//Start the game object
			TheGame game = new TheGame(first, second);
			
			System.out.println("New game Started!");
			
			//Set the game object for each gamerunner, which is the thread for each client,
			//so that the individual client threads can interact with the game.
			first.setGame(game);
			second.setGame(game);
			
			//Lets start the game
			//Both threads started here, so that at this point we have two players.
			first.start();
			second.start();
			
		}
	}
	//If the while loop breaks, then the socketserver needs to close.
	finally
	{
			gatekeeper.close();
	}

}}
