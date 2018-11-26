package rushHour;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainScreen implements ActionListener  {
	
	//RushHour game;
	
	JButton getHelpBtn;
	JPanel panel2;
	JButton playBtn;
	JButton settingsBtn;
	JButton credits;
	BufferedImage logoGame;
	JLabel logoLabel;
	JFrame f3 ;

	public MainScreen(int theme, boolean sound) throws IOException
	{
		if(theme == 1) {
			if(sound == true)
			{
				//initiliaze
				 getHelpBtn = new JButton();
				// game = new RushHour();
				 panel2 = new JPanel();
				 playBtn = new JButton();
				 settingsBtn = new JButton();
				 credits = new JButton();
				 logoGame = ImageIO.read(new File("Logo11.png"));
				 logoLabel= new JLabel(new ImageIcon(logoGame));
				 f3 = new JFrame("Rush Hour");
				 
					//main panel
					panel2.setLayout(null);
					playBtn.setVisible(true);
					playBtn.setBounds(350, 200, 300, 100);
					playBtn.setForeground(Color.red);
					playBtn.setBackground(Color.ORANGE);
					playBtn.addActionListener(this);
					
					settingsBtn.setVisible(true);
					settingsBtn.setBounds(350, 350, 300, 100);
					settingsBtn.setForeground(Color.red);
					settingsBtn.setBackground(Color.ORANGE);
					settingsBtn.addActionListener(this);

					getHelpBtn.setVisible(true);
					getHelpBtn.setBounds(350,500,300,100);
					getHelpBtn.setForeground(Color.red);
					getHelpBtn.setBackground(Color.ORANGE);
					getHelpBtn.addActionListener(this);

					credits.setVisible(true);
					credits.setBounds(350,650, 300, 100);
					credits.setForeground(Color.red);
					credits.setBackground(Color.ORANGE);
					//logo
					logoLabel.setBounds(200, 5, 600, 200);
					
					f3.setBackground(Color.MAGENTA);
					f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f3.setBounds(900, 800, 900, 800);
					//f3.add(game);
					f3.setResizable(false);


					f3.add(panel2);
					f3.setLocationRelativeTo(null);
					f3.setSize(1000,1000);
					f3.setVisible(true);
					
					playBtn.setText("Play");
					playBtn.setFont(new Font("Serif", Font.ITALIC,25));
					settingsBtn.setText("Settings");
					settingsBtn.setFont(new Font("Serif", Font.ITALIC,25));

					getHelpBtn.setText("Get Help");
					getHelpBtn.setFont(new Font("Serif", Font.ITALIC,25));

					credits.setText("Credits");
					credits.setFont(new Font("Serif", Font.ITALIC,25));

					panel2.setBackground(Color.white);
					panel2.add(playBtn);
					panel2.add(settingsBtn);
					panel2.add(getHelpBtn);
					panel2.add(credits);
					panel2.add(logoLabel);
			}
			if(sound == false)
			{
				//initiliaze
				 getHelpBtn = new JButton();
				// game = new RushHour();
				 panel2 = new JPanel();
				 playBtn = new JButton();
				 settingsBtn = new JButton();
				 credits = new JButton();
				 logoGame = ImageIO.read(new File("Logo11.png"));
				 logoLabel= new JLabel(new ImageIcon(logoGame));
				 f3 = new JFrame("Rush Hour");
				 
					//main panel
					panel2.setLayout(null);
					playBtn.setVisible(true);
					playBtn.setBounds(350, 200, 300, 100);
					playBtn.setForeground(Color.red);
					playBtn.setBackground(Color.ORANGE);
					playBtn.addActionListener(this);
					
					settingsBtn.setVisible(true);
					settingsBtn.setBounds(350, 350, 300, 100);
					settingsBtn.setForeground(Color.red);
					settingsBtn.setBackground(Color.ORANGE);
					settingsBtn.addActionListener(this);

					getHelpBtn.setVisible(true);
					getHelpBtn.setBounds(350,500,300,100);
					getHelpBtn.setForeground(Color.red);
					getHelpBtn.setBackground(Color.ORANGE);
					getHelpBtn.addActionListener(this);

					credits.setVisible(true);
					credits.setBounds(350,650, 300, 100);
					credits.setForeground(Color.red);
					credits.setBackground(Color.ORANGE);
					//logo
					logoLabel.setBounds(200, 5, 600, 200);
					
					f3.setBackground(Color.MAGENTA);
					f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f3.setBounds(900, 800, 900, 800);
					//f3.add(game);
					f3.setResizable(false);


					f3.add(panel2);
					f3.setLocationRelativeTo(null);
					f3.setSize(1000,1000);
					f3.setVisible(true);
					
					playBtn.setText("Play");
					playBtn.setFont(new Font("Serif", Font.ITALIC,25));
					settingsBtn.setText("Settings");
					settingsBtn.setFont(new Font("Serif", Font.ITALIC,25));

					getHelpBtn.setText("Get Help");
					getHelpBtn.setFont(new Font("Serif", Font.ITALIC,25));

					credits.setText("Credits");
					credits.setFont(new Font("Serif", Font.ITALIC,25));

					panel2.setBackground(Color.white);
					panel2.add(playBtn);
					panel2.add(settingsBtn);
					panel2.add(getHelpBtn);
					panel2.add(credits);
					panel2.add(logoLabel);
			}
		
		}
		if(theme == 2)
		{
			if(sound == true)
			{
				//initiliaze
				 getHelpBtn = new JButton();
				// game = new RushHour();
				 panel2 = new JPanel();
				 playBtn = new JButton();
				 settingsBtn = new JButton();
				 credits = new JButton();
				 logoGame = ImageIO.read(new File("Logo11.png"));
				 logoLabel= new JLabel(new ImageIcon(logoGame));
				 f3 = new JFrame("Rush Hour");
				 
					//main panel
					panel2.setLayout(null);
					playBtn.setVisible(true);
					playBtn.setBounds(350, 200, 300, 100);
					playBtn.setForeground(Color.red);
					playBtn.setBackground(Color.ORANGE);
					playBtn.addActionListener(this);
					
					settingsBtn.setVisible(true);
					settingsBtn.setBounds(350, 350, 300, 100);
					settingsBtn.setForeground(Color.red);
					settingsBtn.setBackground(Color.ORANGE);
					settingsBtn.addActionListener(this);

					getHelpBtn.setVisible(true);
					getHelpBtn.setBounds(350,500,300,100);
					getHelpBtn.setForeground(Color.red);
					getHelpBtn.setBackground(Color.ORANGE);
					getHelpBtn.addActionListener(this);

					credits.setVisible(true);
					credits.setBounds(350,650, 300, 100);
					credits.setForeground(Color.red);
					credits.setBackground(Color.ORANGE);
					//logo
					logoLabel.setBounds(200, 5, 600, 200);
					
					f3.setBackground(Color.MAGENTA);
					f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f3.setBounds(900, 800, 900, 800);
					//f3.add(game);
					f3.setResizable(false);


					f3.add(panel2);
					f3.setLocationRelativeTo(null);
					f3.setSize(1000,1000);
					f3.setVisible(true);
					
					playBtn.setText("Play");
					playBtn.setFont(new Font("Serif", Font.ITALIC,25));
					settingsBtn.setText("Settings");
					settingsBtn.setFont(new Font("Serif", Font.ITALIC,25));

					getHelpBtn.setText("Get Help");
					getHelpBtn.setFont(new Font("Serif", Font.ITALIC,25));

					credits.setText("Credits");
					credits.setFont(new Font("Serif", Font.ITALIC,25));

					panel2.setBackground(Color.white);
					panel2.add(playBtn);
					panel2.add(settingsBtn);
					panel2.add(getHelpBtn);
					panel2.add(credits);
					panel2.add(logoLabel);
			}
			if(sound == false)
			{
				//initiliaze
				 getHelpBtn = new JButton();
				// game = new RushHour();
				 panel2 = new JPanel();
				 playBtn = new JButton();
				 settingsBtn = new JButton();
				 credits = new JButton();
				 logoGame = ImageIO.read(new File("Logo11.png"));
				 logoLabel= new JLabel(new ImageIcon(logoGame));
				 f3 = new JFrame("Rush Hour");
				 
					//main panel
					panel2.setLayout(null);
					playBtn.setVisible(true);
					playBtn.setBounds(350, 200, 300, 100);
					playBtn.setForeground(Color.red);
					playBtn.setBackground(Color.ORANGE);
					playBtn.addActionListener(this);
					
					settingsBtn.setVisible(true);
					settingsBtn.setBounds(350, 350, 300, 100);
					settingsBtn.setForeground(Color.red);
					settingsBtn.setBackground(Color.ORANGE);
					settingsBtn.addActionListener(this);

					getHelpBtn.setVisible(true);
					getHelpBtn.setBounds(350,500,300,100);
					getHelpBtn.setForeground(Color.red);
					getHelpBtn.setBackground(Color.ORANGE);
					getHelpBtn.addActionListener(this);

					credits.setVisible(true);
					credits.setBounds(350,650, 300, 100);
					credits.setForeground(Color.red);
					credits.setBackground(Color.ORANGE);
					//logo
					logoLabel.setBounds(200, 5, 600, 200);
					
					f3.setBackground(Color.MAGENTA);
					f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f3.setBounds(900, 800, 900, 800);
					//f3.add(game);
					f3.setResizable(false);


					f3.add(panel2);
					f3.setLocationRelativeTo(null);
					f3.setSize(1000,1000);
					f3.setVisible(true);
					
					playBtn.setText("Play");
					playBtn.setFont(new Font("Serif", Font.ITALIC,25));
					settingsBtn.setText("Settings");
					settingsBtn.setFont(new Font("Serif", Font.ITALIC,25));

					getHelpBtn.setText("Get Help");
					getHelpBtn.setFont(new Font("Serif", Font.ITALIC,25));

					credits.setText("Credits");
					credits.setFont(new Font("Serif", Font.ITALIC,25));

					panel2.setBackground(Color.white);
					panel2.add(playBtn);
					panel2.add(settingsBtn);
					panel2.add(getHelpBtn);
					panel2.add(credits);
					panel2.add(logoLabel);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == getHelpBtn)
		{
		 try {
			 f3.setVisible(false);
			GetHelpScreen helpinfo = new GetHelpScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		if(evt.getSource() == playBtn)
		{
			try {
				 f3.setVisible(false);

				GameModeSelectionScreen gameMode = new GameModeSelectionScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(evt.getSource() == settingsBtn)
		{
			try {
				
				Settings settings =  new Settings();
				f3.setVisible(false); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(evt.getSource() == credits)
		{
			try {
				Credits credits = new Credits();
				f3.setVisible(false); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
