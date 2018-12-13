//Hassan M. Khan
//Operating Systems Final Project

package JungleBungle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//This will be the thread that will be running for each client connect to
//the server.

//It will handle the communication between this client and the game.
//It has access to the clients socket, and can therefore send and receive messages
//from the client, which can be communicated to the game object.

public class GameRunner extends Thread{
	
	//Data variables of this class.
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	TheGame game;
	
	String id;
	String selection;
	
	ClientPanel user;
	
	//Implementing the run method of the thread.
	public GameRunner(Socket a, String c) throws IOException
	{
		//Setting up the socket number the client is connected on.
		socket = a;
		
		//Setting up the id for this client
		id = c;
		
		//Setting up the input and output buffer of the client,
		//so that we can read and write to it.
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out= new PrintWriter(socket.getOutputStream(), true);
	}
	
	//Implementing the run method of the thread.
	public void run()
	{
		//Get the client's selection first.
		try {
			getSelection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Send the selection to the game.
		game.setSelection(id, selection);
	}
	
	public void getSelection() throws IOException
	{
		//Get the client's choice of animal.
		out.println("Welcome to the game! Choose your animal!");
		selection = in.readLine();
	}
	
	public void setGame(TheGame a)
	{
		//Connecting this client to the game
		game = a;
	}
	
	public void tellUser(String a)
	{
		out.println(a);
	}

}
