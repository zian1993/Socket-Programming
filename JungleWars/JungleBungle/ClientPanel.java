//Hassan M. Khan
//Operating Systems Final Project

package JungleBungle;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;



public class ClientPanel extends JPanel {

	//Server connection variables.
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	JFrame frame;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField msguser;

	JLabel opponentselect;
	JLabel userselect;

	JButton lion;
	JButton cobra;
	JButton rabbit;
	
	ImageIcon lionicon;
	ImageIcon cobraicon;
	ImageIcon rabbiticon;

	JLabel label;
	JLabel lblYourSelection;
	JLabel lblOpponentsSelection;

	public ClientPanel(JFrame a) {
		
		//Set up pointer to frame which holds this panel.
		frame = a;
		
		//Start panel and game
		setup();
	}
	
	public void setup()
	{
		setLayout(null);
		
		//Setting up the image objects so that they can be used later.
		lionicon = new ImageIcon(ClientPanel.class.getResource("/JungleBungle/lion1.png"));
		cobraicon = new ImageIcon(ClientPanel.class.getResource("/JungleBungle/cobra.png"));
		rabbiticon = new ImageIcon(ClientPanel.class.getResource("/JungleBungle/rabbit.png"));

		lion = new JButton("");
		lion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Setting up the action handler for this button.
				//Change the user's selection side image
				userselect.setIcon(lionicon);

				//Lock the other buttons
				cobra.setEnabled(false);
				rabbit.setEnabled(false);

				//If user chooses this button, send this to the server side thread.
				out.println("lion");

				//Go to the next function, and wait for the client to send information
				try {
					messageCheck();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//Do nothing
				}

			}
		});
		lion.setIcon(new ImageIcon(ClientPanel.class.getResource("/JungleBungle/lion1.png")));
		lion.setBounds(48, 349, 103, 69);
		add(lion);

		cobra = new JButton("");
		cobra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Setting up the action handler for this button.
				//Change the user's selection side image
				userselect.setIcon(cobraicon);

				//Lock the other buttons
				lion.setEnabled(false);
				rabbit.setEnabled(false);

				//If user chooses this button, send this to the server side thread.
				out.println("cobra");

				//Go to the next function, and wait for the client to send information
				try {
					messageCheck();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//Do nothing
				}

			}
		});
		cobra.setIcon(new ImageIcon(ClientPanel.class.getResource("/JungleBungle/cobra.png")));
		cobra.setBounds(205, 349, 113, 69);
		add(cobra);

		rabbit = new JButton("");
		rabbit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Setting up the action handler for this button.
				//Change the user's selection side image
				userselect.setIcon(rabbiticon);

				//Lock the other buttons
				lion.setEnabled(false);
				cobra.setEnabled(false);

				//If user chooses this button, send this to the server side thread.
				out.println("rabbit");

				//Go to the next function, and wait for the client to send information
				try {
					messageCheck();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//Do nothing
				}
			}
		});
		rabbit.setIcon(new ImageIcon(ClientPanel.class.getResource("/JungleBungle/rabbit.png")));
		rabbit.setBounds(367, 349, 113, 69);
		add(rabbit);

		label = new JLabel("");
		label.setIcon(new ImageIcon(ClientPanel.class.getResource("/JungleBungle/title.png")));
		label.setBounds(37, 23, 403, 114);
		add(label);

		msguser = new JTextField();
		msguser.setEditable(false);
		msguser.setBounds(37, 148, 403, 20);
		add(msguser);
		msguser.setColumns(10);

		lblYourSelection = new JLabel("Your Selection:");
		lblYourSelection.setEnabled(false);
		lblYourSelection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYourSelection.setBounds(37, 202, 113, 25);
		add(lblYourSelection);

		userselect = new JLabel("");
		userselect.setEnabled(false);
		userselect.setBounds(37, 238, 148, 85);
		add(userselect);

		lblOpponentsSelection = new JLabel("Opponent's Selection");
		lblOpponentsSelection.setEnabled(false);
		lblOpponentsSelection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOpponentsSelection.setBounds(279, 202, 161, 25);
		add(lblOpponentsSelection);

		opponentselect = new JLabel("");
		opponentselect.setEnabled(false);
		opponentselect.setBounds(279, 251, 161, 87);
		add(opponentselect);
	}

	//This function is a while loop to keep running while we need input from the server side.
	//The server side can send messages to the client, which are then displayed on the clients label.
	public void messageCheck() throws IOException
	{
		while (true)
		{
			//Display messages from the server side on the label
			String teller = in.readLine();

			//If the server-side sends what the client picked, display picture on label.
			if (teller.equals("lion") || teller.equals("cobra")|| teller.equals("rabbit"))
			{
				if(teller.equals("lion"))
				{
					opponentselect.setIcon(lionicon);
				}

				else if (teller.equals("cobra"))
				{
					opponentselect.setIcon(cobraicon);
				}

				else if (teller.equals("rabbit"))
				{
					opponentselect.setIcon(rabbiticon);
				}
			}

			//Otherwise, display any messages the serverside sends to the label.
			//Then break
			else
			{
				msguser.setText(teller);
				
			}
			
			//if (teller.equals("We are waiting on a player for you!"))
				//break;
			if (teller.equals("Welcome to the game! Choose your animal!"))
				break;

			//Check if the user won or lost
			//If so, the game has ended at this point.
			//Ask the user if they want to replay the game. Break while loop.
			//Close the socket.
			//If user wants to replay, hide current frame, and start a new game.
			if (teller.contains("won")||teller.contains("lost"))
			{
				socket.close();
				
				if(JOptionPane.showInputDialog("Replay game? Answer with yes/no").equals("yes"))
				{
					frame.setVisible(false);
					ClientDude.startClient();
					break;
				}
				
				//Otherwise, exit system and close program.
				else
					System.exit(0);
			}
		}
	}

	public void connectServer(String a) throws UnknownHostException, IOException
	{
		//Set up the socket for the user to connect.
		//Port number is hardcoded on both the server and the client.
		//This is to make sure they connect on the same port.
		socket = new Socket(a, 8079);

		//Setting up the input and output buffer of the client,
		//so that we can read and write to it.
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out= new PrintWriter(socket.getOutputStream(), true);

		//Start game
		messageCheck();
	}

	public void setClientDisplay(String a, String b)
	{
		//Set the display to show the client what the opponent selected
		if (a.equals("lion"))
		{
			opponentselect.setIcon(new ImageIcon(ClientPanel.class.getResource("/JungleBungle/lion1.png")));
		}

		else if (a.equals("rabbit"))
		{
			opponentselect.setIcon(new ImageIcon(ClientPanel.class.getResource("/JungleBungle/rabbit.png")));
		}

		else if (a.equals("cobra"))
		{
			opponentselect.setIcon(new ImageIcon(ClientPanel.class.getResource("/JungleBungle/cobra.png")));
		}

		msguser.setText(b);
	}

}
