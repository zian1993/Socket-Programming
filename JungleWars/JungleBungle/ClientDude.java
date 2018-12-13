//Hassan M. Khan
//Operating Systems Final Project

package JungleBungle;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientDude {


	public static void main(String[] args) throws HeadlessException, UnknownHostException, IOException {
		// TODO Auto-generated method stub

		//Start the client frame, panel and game.
		startClient();

	}

	public static void startClient() throws HeadlessException, UnknownHostException, IOException
	{
		//Start the client's frame.
		JFrame frame = new JFrame("Jungle Bungle!");

		//Initiate the client panel, and add it to a Jframe.
		ClientPanel panel = new ClientPanel(frame);

		//Setting up the frame.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(620,520));
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

		//Lets start the game.
		//Connect with the server.
		//Get the server ip the user wants to connect to.
		panel.connectServer(JOptionPane.showInputDialog("Hello! Enter the IP address of server!:"));
	}

}
