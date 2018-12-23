/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Asuman Aydýn, Muhammet Said Demir
*/

package rushHour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * This is where the real magic happens for multi game. It builds and runs the screen of multi game.
 */
public class MultiGameScreen extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	// Since we're dealing with multi-dimensional array, we've found it more
	// convenient that using i-j (matrix) logic against using x-y
	// The i and j where the player pressed
	static int iPressed = 0, jPressed = 0;
	// The i and j where the player dragged
	static int iReleased = 0, jReleased = 0;

	// engine and board and cards of this screen
	MultiGameEngine engine;
	Board board;
	JButton[] cards;

	JButton changeTheme;
	JButton muteButton;
	JButton returnMulti;
	JLabel text;
	JLabel player1Cards;
	JLabel player2Cards;

	int numberOfMoves;
	int levelNo;
	int theme;
	boolean mute;
	boolean pressed;
	boolean win;

	Clip myClip;

	File theme1file;
	File theme2file;
	File theme3file;
	File settingsFile;

	JFrame f;

	public MultiGameScreen(MultiGameEngine engine) throws IOException {

		// initiliazing the data
		this.engine = engine;
		this.board = engine.board;
		this.levelNo = engine.level;
		setBackground(Color.darkGray);
		addMouseListener(this);
		addMouseMotionListener(this);
		setLayout(null);

		// Frame of this scene
		f = new JFrame("Multi Rush Hour");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setSize(900, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

		// Initializing buttons

		// Player 1 cards
		cards = new JButton[8];
		cards[0] = new JButton("CARDS1[0]");
		cards[0].addActionListener(this);
		cards[0].setBounds(450, 50, 100, 145);
		add(cards[0]);

		cards[1] = new JButton("CARDS1[1]");
		cards[1].addActionListener(this);
		cards[1].setBounds(550, 50, 100, 145);
		add(cards[1]);

		cards[2] = new JButton("CARDS1[2]");
		cards[2].addActionListener(this);
		cards[2].setBounds(650, 50, 100, 145);
		add(cards[2]);

		cards[3] = new JButton("CARDS1[3]");
		cards[3].addActionListener(this);
		cards[3].setBounds(750, 50, 100, 145);
		add(cards[3]);

		cards[4] = new JButton("CARDS2[0]");
		cards[4].addActionListener(this);
		cards[4].setBounds(450, 250, 100, 145);
		add(cards[4]);

		// Player 2 cards
		cards[5] = new JButton("CARDS2[1]");
		cards[5].addActionListener(this);
		cards[5].setBounds(550, 250, 100, 145);
		add(cards[5]);

		cards[6] = new JButton("CARDS2[2]");
		cards[6].addActionListener(this);
		cards[6].setBounds(650, 250, 100, 145);
		add(cards[6]);

		cards[7] = new JButton("CARDS2[3]");
		cards[7].addActionListener(this);
		cards[7].setBounds(750, 250, 100, 145);
		add(cards[7]);

		changeTheme = new JButton("CHANGE THEME");
		changeTheme.addActionListener(this);
		changeTheme.setBackground(Color.yellow);
		changeTheme.setForeground(Color.red);
		changeTheme.setBounds(550, 450, 150, 50);
		add(changeTheme);

		muteButton = new JButton("MUTE");
		muteButton.addActionListener(this);
		muteButton.setBackground(Color.yellow);
		muteButton.setForeground(Color.red);
		muteButton.setBounds(550, 500, 150, 50);
		add(muteButton);

		returnMulti = new JButton("RETURN");
		returnMulti.addActionListener(this);
		returnMulti.setBackground(Color.yellow);
		returnMulti.setForeground(Color.red);
		returnMulti.setBounds(550, 600, 150, 50);
		add(returnMulti);

		// Initializing text labels
		// Instructive text
		text = new JLabel();
		text.setBounds(450, 200, 400, 30);
		text.setForeground(Color.YELLOW);
		add(text);

		player1Cards = new JLabel();
		player1Cards.setBounds(450, 15, 200, 30);
		player1Cards.setText("Player 1's Cards");
		player1Cards.setForeground(Color.YELLOW);
		add(player1Cards);

		player2Cards = new JLabel();
		player2Cards.setBounds(450, 400, 200, 30);
		player2Cards.setText("Player 2's Cards");
		player2Cards.setForeground(Color.YELLOW);
		add(player2Cards);

		settingsFile = new File("settings.txt");
		theme = readFile(1);
		readFile(2);

		if(mute)
			muteButton.setIcon(new ImageIcon(
					new ImageIcon("muted.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		else 
			muteButton.setIcon(new ImageIcon(
					new ImageIcon("mute.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		add(muteButton);
		
		theme1file = new File("OffLimits.wav");
		theme2file = new File("StarWars.wav");
		theme3file = new File("Ankara.wav");

		if (theme == 1)
			play(theme1file, mute);
		else if (theme == 2)
			play(theme2file, mute);
		else
			play(theme3file, mute);

		// rewriting data
		PrintWriter resumeWriter = new PrintWriter(new File("resume.txt"));
		resumeWriter.println(levelNo + "");
		resumeWriter.close();

		PrintWriter settingsWriter = new PrintWriter(settingsFile);
		settingsWriter.println("1,0,");
		settingsWriter.close();
	}

	public int readFile(int readType) throws IOException {
		Scanner scanner = new Scanner(settingsFile);

		String settingsDatas = scanner.nextLine();
		String score = settingsDatas.charAt(0) + "";

		int i = 0;
		int counter = 0;
		int charCounter = 0;
		int result;
		while (counter != readType) {
			if (settingsDatas.charAt(i) == ',')
				counter++;
			if (counter == readType - 1)
				charCounter++;
			i++;
		}
		if (counter > 1)
			charCounter--;
		score = settingsDatas.substring(i - 1 - charCounter, i - 1);
		result = Integer.valueOf(score);
		scanner.close();
		if (readType == 2 && result == 1)
			mute = true;
		else
			mute = false;
		return result;
	}

	public void checkWin() throws IOException {

		Object[] options = { "Turn back to main", "Next Level", "EXIT" };

		int endGame;
		if (engine.turn)
			endGame = JOptionPane.showOptionDialog(null, options, "Player 2 is the Winner",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		else
			endGame = JOptionPane.showOptionDialog(null, options, "Player 1 is the Winner",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

		myClip.stop();
		this.setVisible(false);
		f.dispose();

		if (endGame == 0)
			try {
				new MainScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (endGame == 1)
			new DisplayLevelScreen(levelNo + 1);
		else
			System.exit(0);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		

		if(mute)
			muteButton.setIcon(new ImageIcon(
					new ImageIcon("muted.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		else 
			muteButton.setIcon(new ImageIcon(
					new ImageIcon("mute.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		
		Image background;
		Image rock;
		Image boardImage;
		Image redCar1;
		Image redCar2;
		Image car2;
		Image car3;
		Image carv2;
		Image carv3;

		if (theme == 1) {
			rock = new ImageIcon("rock.png").getImage();
			boardImage = new ImageIcon("backGround.jpg").getImage();
			redCar1 = new ImageIcon("7.png").getImage();
			redCar2 = new ImageIcon("9.png").getImage();
			car2 = new ImageIcon("6.png").getImage();
			car3 = new ImageIcon("4.png").getImage();
			carv2 = new ImageIcon("10.png").getImage();
			carv3 = new ImageIcon("2.png").getImage();
		} else if (theme == 2) {
			rock = new ImageIcon("meteor.png").getImage();
			boardImage = new ImageIcon("uz.png").getImage();
			redCar1 = new ImageIcon("14.png").getImage();
			redCar2 = new ImageIcon("16.png").getImage();
			car2 = new ImageIcon("13.png").getImage();
			car3 = new ImageIcon("11.png").getImage();
			carv2 = new ImageIcon("12.png").getImage();
			carv3 = new ImageIcon("15.png").getImage();
		} else {
			rock = new ImageIcon("Ironhide.png").getImage();
			boardImage = new ImageIcon("ankara.jpg").getImage();
			redCar1 = new ImageIcon("araba2.png").getImage();
			car2 = new ImageIcon("araba3.png").getImage();
			car3 = new ImageIcon("citibus.png").getImage();
			carv2 = new ImageIcon("araba.png").getImage();
			carv3 = new ImageIcon("citybus.png").getImage();
			redCar2 = new ImageIcon("araba2ver.png").getImage();
		}

		g.drawImage(boardImage, 0, 0, 420, 660, this);

		// Loop to draw the board
		for (int j = 0; j < 14; j++)
			for (int i = 0; i < 22; i++)
				if (board.coordinates[i][j] == 3 && (j < 5 || j > 8)) {
					Image leftBoard = new ImageIcon("boardLR.png").getImage();
					g.drawImage(leftBoard, j * 30, i * 30, 150, 240, this);
					i = 21;
					j = j + 4;
				}
		Image leftBoard = new ImageIcon("boardM.png").getImage();
		g.drawImage(leftBoard, 5 * 30, 7 * 30, 120, 240, this);

		// Loop to detect horizontal cars and also obstacles
		for (int i = 0; i < 22; i++)
			for (int j = 0; j < 14; j++)

				// Obstacle case
				if (board.coordinates[i][j] == 2)
					g.drawImage(rock, j * 30, i * 30, 30, 30, this);

				// If there's a car case
				else if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					// If that car exists and horizontal
					if (tempt != null && !tempt.direction)

						// if car is red player 1
						if (tempt.carType == 1) {
							g.drawImage(redCar1, j * 30, i * 30, 60, 30, this);
							j++;
						}
						// if car is red player 2
						else if (tempt.carType == 2) {
							g.drawImage(redCar2, j * 30, i * 30, 60, 30, this);
							j++;
						} else if (tempt.size == 2) {// if car is size 2
							g.drawImage(car2, j * 30, i * 30, 60, 30, this);
							j++;
						} else if (tempt.size == 3) {// if car is size 3
							g.drawImage(car3, j * 30, i * 30, 90, 30, this);
							j += 2;
						}
				}

		// Loop to detect vertical cars
		for (int j = 0; j < 14; j++)
			for (int i = 0; i < 22; i++)

				// If there's a car case
				if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					// If that car exists and vertical
					if (tempt != null && tempt.direction)
						if (tempt.size == 2) {// if car is vertical & size 2
							g.drawImage(carv2, j * 30, i * 30, 30, 60, this);
							i++;
						} else if (tempt.size == 3) {// if car is vertical & size 3
							g.drawImage(carv3, j * 30, i * 30, 30, 90, this);
							i += 2;
						}

				}

		// To draw the cards
		for (int i = 0; i < 4; i++) {
			cards[i].setIcon(new ImageIcon(
					new ImageIcon("cards/" + engine.cards1[i].numberOfMoves + engine.cards1[i].shift + ".png")
							.getImage()));
			cards[i + 4].setIcon(new ImageIcon(
					new ImageIcon("cards/" + engine.cards2[i].numberOfMoves + engine.cards2[i].shift + ".png")
							.getImage()));
		}

		// To give contents to texts.
		if (engine.turn && (engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 1's turn to play.    Remaining Number of Moves: " + engine.numberOfMoves + "   Shift: "
					+ engine.shift);
		else if (engine.turn && !(engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 1's turn to pick a card");
		else if (!engine.turn && (engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 2's turn to play.    Remaining Number of Moves: " + engine.numberOfMoves + "   Shift: "
					+ engine.shift);
		else if (!engine.turn && !(engine.shift || engine.numberOfMoves != 0))
			text.setText("Player 2's turn to pick a card");

		// If the player can move the board, show the lines to help the player.
		if (pressed && engine.shift) {
			g.setColor(Color.CYAN);
			for (int i = 1; i < 22; i++)
				g.drawLine(0, 30 * i, 420, 30 * i);
		}

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// If the player hits the change Theme.
		if (e.getSource() == changeTheme) {
			theme = (theme % 3) + 1;
			if (theme == 1) {
				mute = false;
				myClip.stop();
				play(theme1file, mute);
				return;
			} else if (theme == 2) {
				mute = false;
				myClip.stop();
				play(theme2file, mute);
				return;
			} else {
				mute = false;
				myClip.stop();
				play(theme3file, mute);
				return;
			}
		}
		// If the player hits the return.
		else if (e.getSource() == returnMulti)
			try {
				this.setVisible(false);
				myClip.stop();
				new MainScreen();
				f.dispose();
			} catch (IOException evt) {
				evt.printStackTrace();
			}
		// If the player hits the mute.
		else if (e.getSource() == muteButton) {
			if(!mute)
				muteButton.setIcon(new ImageIcon(
						new ImageIcon("muted.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
			else 
				muteButton.setIcon(new ImageIcon(
						new ImageIcon("mute.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
			add(muteButton);
			if (mute && theme == 1) {
				mute = false;
				play(theme1file, mute);
				return;
			} else if (mute && theme == 2) {
				mute = false;
				play(theme2file, mute);
				return;
			} else if (mute && theme == 3) {
				mute = false;
				play(theme3file, mute);
				return;
			} else {
				mute = true;
				myClip.stop();
				return;
			}
		}
		// If player hits one of the cards. It set the data of the player as the data of
		// the chosen card.
		// To chose a card, player has to have nothing left to do.
		if (engine.numberOfMoves == 0 && !engine.shift)
			// If player 1's turn
			if (engine.turn) {
				if (e.getSource() == cards[0]) {
					engine.numberOfMoves = engine.cards1[0].numberOfMoves;
					engine.shift = engine.cards1[0].shift;
					engine.choice = 0;
				}

				else if (e.getSource() == cards[1]) {
					engine.numberOfMoves = engine.cards1[1].numberOfMoves;
					engine.shift = engine.cards1[1].shift;
					engine.choice = 1;
				}

				else if (e.getSource() == cards[2]) {
					engine.numberOfMoves = engine.cards1[2].numberOfMoves;
					engine.shift = engine.cards1[2].shift;
					engine.choice = 2;
				}

				else if (e.getSource() == cards[3]) {
					engine.numberOfMoves = engine.cards1[3].numberOfMoves;
					engine.shift = engine.cards1[3].shift;
					engine.choice = 3;
				}
			}
			// If player 2's turn
			else {
				if (e.getSource() == cards[4]) {
					engine.numberOfMoves = engine.cards2[0].numberOfMoves;
					engine.shift = engine.cards2[0].shift;
					engine.choice = 0;
				}

				else if (e.getSource() == cards[5]) {
					engine.numberOfMoves = engine.cards2[1].numberOfMoves;
					engine.shift = engine.cards2[1].shift;
					engine.choice = 1;
				}

				else if (e.getSource() == cards[6]) {
					engine.numberOfMoves = engine.cards2[2].numberOfMoves;
					engine.shift = engine.cards2[2].shift;
					engine.choice = 2;
				}

				else if (e.getSource() == cards[7]) {
					engine.numberOfMoves = engine.cards2[3].numberOfMoves;
					engine.shift = engine.cards2[3].shift;
					engine.choice = 3;
				}
			}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	// When user presses a location, we'll check if it's on the game area, and that
	// player can perform something then record the location.
	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getX() < 420 && (engine.numberOfMoves != 0 || engine.shift)) {
			pressed = true;
			iPressed = e.getY() / 30;
			jPressed = e.getX() / 30;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	// When user releases on a location, we'll record the location, then call the
	// update method to do the action, and if the action is done (which is move car)
	// we'll record it to the stack.
	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressed) {
			iReleased = e.getY() / 30;
			jReleased = e.getX() / 30;
			if (engine.update(iPressed, jPressed, iReleased, jReleased)) {
				if (board.win)
					try {
						checkWin();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
			pressed = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void play(File file, Boolean mute) {
		try {
			myClip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

			myClip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP)
						myClip.close();
				}
			});

			myClip.open(AudioSystem.getAudioInputStream(file));
			myClip.start();
			if (mute == true)
				myClip.stop();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}

	}

}
