/*
*	Author : Ata Coþkun, Zeynep Nur Öztürk, Muhammet Said Demir
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
import java.util.Stack;

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
import javax.swing.Timer;

/*
 * This is where the real magic happens for single game. It builds and runs the screen of single game.
 */
public class SingleGameScreen extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	// Since we're dealing with multi-dimensional array, we've found it more
	// convenient that using i-j (matrix) logic against using x-y
	// The i and j where the player clicked
	static int iPressed = 0, jPressed = 0;
	// The i and j where the player dragged
	static int iReleased = 0, jReleased = 0;

	// engine and board and the stack of movements of this screen
	SingleGameEngine engine;
	Board board;
	Stack<Integer> Q;

	JButton undoButton;
	JButton muteButton;
	JButton changeTheme;
	JButton hintButton;
	JButton replayButton;
	JButton returnButton;
	JLabel NOfMoves;
	JLabel tmLabel;

	int time;
	int numberOfMoves;
	int levelNo;
	int highScore;
	int theme;
	boolean mute;
	boolean pressed;

	Timer myTimer;
	Clip myClip;

	File theme1file;
	File theme2file;
	File theme3file;
	File scoreFile;
	File settingsFile;

	PrintWriter writer;
	PrintWriter winWriter;
	String scoreDatas;

	JFrame f;

	public SingleGameScreen(SingleGameEngine engine, Stack<Integer> Q) throws IOException {

		// initiliazing the data
		time = 0;
		numberOfMoves = 0;
		this.engine = engine;
		this.board = engine.board;
		this.Q = Q;
		this.levelNo = engine.level;
		setBackground(Color.BLACK);
		addMouseListener(this);
		addMouseMotionListener(this);
		setLayout(null);

		// Frame of this scene
		f = new JFrame("Single Rush Hour");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setSize(700, 485);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

		// Initializing buttons
		undoButton = new JButton("UNDO");
		undoButton.setBackground(Color.yellow);
		undoButton.setForeground(Color.red);
		undoButton.addActionListener(this);
		undoButton.setBounds(525, 50, 100, 50);
		add(undoButton);

		replayButton = new JButton("REPLAY");
		replayButton.setBackground(Color.yellow);
		replayButton.setForeground(Color.red);
		replayButton.addActionListener(this);
		replayButton.setBounds(525, 110, 100, 50);
		add(replayButton);

		hintButton = new JButton("HINT");
		hintButton.setBackground(Color.yellow);
		hintButton.setForeground(Color.red);
		hintButton.addActionListener(this);
		hintButton.setBounds(525, 170, 100, 50);
		add(hintButton);

		muteButton = new JButton();
		muteButton.addActionListener(this);
		muteButton.setBackground(Color.yellow);
		muteButton.setBounds(525, 230, 100, 50);
		
		
		changeTheme = new JButton("CHANGE THEME");
		changeTheme.addActionListener(this);
		changeTheme.setBackground(Color.yellow);
		changeTheme.setForeground(Color.red);
		changeTheme.setBounds(500, 290, 150, 50);
		add(changeTheme);

		returnButton = new JButton("RETURN");
		returnButton.setBackground(Color.yellow);
		returnButton.setForeground(Color.red);
		returnButton.addActionListener(this);
		returnButton.setBounds(525, 400, 100, 50);
		add(returnButton);

		// Initializing text labels
		NOfMoves = new JLabel();
		NOfMoves.setBounds(520, 5, 150, 15);
		NOfMoves.setForeground(Color.YELLOW);
		add(NOfMoves);

		tmLabel = new JLabel();
		tmLabel.setBounds(555, 20, 100, 30);
		tmLabel.setForeground(Color.cyan);
		myTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time++;
				if (time < 100000)
					tmLabel.setText("Time: " + Integer.toString(time));
				else
					((Timer) (e.getSource())).stop();
			}
		});
		myTimer.setInitialDelay(0);
		myTimer.start();
		add(tmLabel);

		scoreFile = new File("high_score.txt");
		settingsFile = new File("settings.txt");


		
		// get the high score from file
		readFile(1, levelNo);
		theme = readFile(0, 1);
		readFile(0, 2);

		add(muteButton);
		
		theme1file = new File("OffLimits.wav");
		theme2file = new File("StarWars.wav");
		theme3file = new File("Ankara.wav");

		// the music
		if (theme == 1)
			play(theme1file, mute);
		else if (theme == 2)
			play(theme2file, mute);
		else
			play(theme3file, mute);

		// rewriting data
		writer = new PrintWriter(scoreFile);
		winWriter = new PrintWriter(scoreFile);
		writeFile(false, highScore);
		PrintWriter resumeWriter = new PrintWriter(new File("resume.txt"));
		resumeWriter.println(levelNo + "");
		resumeWriter.close();

		PrintWriter settingsWriter = new PrintWriter(settingsFile);
		settingsWriter.println("1,0,");
		settingsWriter.close();
	}

	public int readFile(int readType, int levelNo) throws IOException {
		if (readType == 1) {
			Scanner scanner = new Scanner(scoreFile);
			scoreDatas = scanner.nextLine();
			String score = scoreDatas.charAt(0) + "";

			int i = 0;
			int counter = 0;
			int charCounter = 0;
			while (counter != levelNo) {
				if (scoreDatas.charAt(i) == ',')
					counter++;
				if (counter == levelNo - 1)
					charCounter++;
				i++;
			}
			if (counter > 1)
				charCounter--;
			score = scoreDatas.substring(i - 1 - charCounter, i - 1);
			highScore = Integer.valueOf(score);
			scanner.close();
			return highScore;
		} else {
			Scanner scanner = new Scanner(settingsFile);
			String settingsDatas = scanner.nextLine();
			String score = settingsDatas.charAt(0) + "";

			int i = 0;
			int counter = 0;
			int charCounter = 0;
			int result;
			while (counter != levelNo) {
				if (settingsDatas.charAt(i) == ',')
					counter++;
				if (counter == levelNo - 1)
					charCounter++;
				i++;
			}
			if (counter > 1)
				charCounter--;
			score = settingsDatas.substring(i - 1 - charCounter, i - 1);
			result = Integer.valueOf(score);
			scanner.close();
			if (levelNo == 2 && result == 1)
				mute = true;
			else
				mute = false;
			return result;
		}
	}

	public void writeFile(boolean high, int highscore) throws IOException {

		if (high == false) {
			writer.println(scoreDatas);
			writer.close();
		} else {
			String temp1;
			String temp2;
			int i = 0;
			int counter = 0;
			int charCounter = 0;
			while (counter != levelNo) {
				if (scoreDatas.charAt(i) == ',')
					counter++;
				if (counter == levelNo - 1)
					charCounter++;
				i++;
			}
			if (counter > 1)
				charCounter--;

			temp1 = scoreDatas.substring(0, i - 1 - charCounter);
			temp2 = scoreDatas.substring(i - 1, scoreDatas.length());
			temp1 += highscore;

			winWriter.println(temp1 + temp2);
			winWriter.close();
		}

	}

	public void checkWin() throws IOException {
		myTimer.stop();
		System.out.println("score:" +engine.calculateScore(time, numberOfMoves) );
		// new score is highest
		if (100 * engine.calculateScore(time, numberOfMoves) > highScore)
			writeFile(true, engine.calculateScore(time, numberOfMoves));

		Object[] options = { "Turn back to main", "Next Level", "EXIT" };
		int endGame = JOptionPane.showOptionDialog(null, options,
				"WIN!\nYou finished in " + numberOfMoves + " number of moves", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

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
		
		Image rock;
		Image boardImage;
		Image redCar;
		Image car2;
		Image car3;
		Image carv2;
		Image carv3;
		Image redCarv;

		if (theme == 1) {
			rock = new ImageIcon("rock.png").getImage();
			boardImage = new ImageIcon("board.png").getImage();
			redCar = new ImageIcon("7.png").getImage();
			car2 = new ImageIcon("6.png").getImage();
			car3 = new ImageIcon("4.png").getImage();
			carv2 = new ImageIcon("10.png").getImage();
			carv3 = new ImageIcon("2.png").getImage();
			redCarv = new ImageIcon("17.png").getImage();
		} else if (theme == 2) {
			rock = new ImageIcon("meteor.png").getImage();
			boardImage = new ImageIcon("boarduzay.png").getImage();
			redCar = new ImageIcon("14.png").getImage();
			car2 = new ImageIcon("13.png").getImage();
			car3 = new ImageIcon("11.png").getImage();
			carv2 = new ImageIcon("12.png").getImage();
			carv3 = new ImageIcon("15.png").getImage();
			redCarv = new ImageIcon("14up.png").getImage();
		} else {
			rock = new ImageIcon("trex.png").getImage();
			boardImage = new ImageIcon("yol.png").getImage();
			redCar = new ImageIcon("araba2.png").getImage();
			car2 = new ImageIcon("araba3.png").getImage();
			car3 = new ImageIcon("citibus.png").getImage();
			carv2 = new ImageIcon("araba.png").getImage();
			carv3 = new ImageIcon("citybus.png").getImage();
			redCarv = new ImageIcon("araba2v.png").getImage();
		}

		g.drawImage(boardImage, 0, 0, 450, 450, this);

		// Loop to detect horizontal cars and also obstacles
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				// Obstacle case
				if (board.coordinates[i][j] == 2) {
					g.drawImage(rock, j * 75, i * 75, 75, 75, this);
				}

				// If there's a car case
				else if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					// If that car exists and horizontal
					if (tempt != null && !tempt.direction)

						// if car is red
						if (tempt.carType != 0) {
							g.drawImage(redCar, j * 75, i * 75, 150, 75, this);
							j++;

							// Drawing the exit if there is no portal in this level
							if (board.portals == null)
								g.drawImage(new ImageIcon("exitv.png").getImage(), 450, i * 75, 25, 75, this);
						} else if (tempt.size == 2) {// if car is size 2
							g.drawImage(car2, j * 75, i * 75, 140, 75, this);
							j++;
						} else if (tempt.size == 3) {// if car is size 3
							g.drawImage(car3, j * 75, i * 75, 225, 75, this);
							j += 2;
						}
				}

		// Loop to detect vertical cars
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				// If there's a car case
				if (board.coordinates[j][i] == 1) {
					Car tempt = board.searchCoordinates(j, i);

					// If that car exists and vertical
					if (tempt != null && tempt.direction)
						if (tempt.carType != 0) {// if car is red, this is valid only for levels with portal
							g.drawImage(redCarv, i * 75, j * 75, 75, 150, this);
							j++;
						} else if (tempt.size == 2) {// if car is vertical & size 2
							g.drawImage(carv2, i * 75, j * 75, 75, 150, this);
							j++;
						} else if (tempt.size == 3) {// if car is vertical & size 3
							g.drawImage(carv3, i * 75, j * 75, 75, 225, this);
							j += 2;
						}
				}

		// If this is a level with portal
		if (board.portals != null) {
			Image portal = new ImageIcon("Portal.png").getImage();
			g.drawImage(portal, 425, board.portals.i1 * 75, 25, 75, this);
			g.drawImage(portal, board.portals.j2 * 75, 0, 75, 25, this);

			// Draw exit for the levels with portal
			Image exith = new ImageIcon("exith.png").getImage();
			g.drawImage(exith, board.portals.j2 * 75, 425, 75, 25, this);
		}

		// Update the number of movements text
		NOfMoves.setText("Number of moves: " + numberOfMoves);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// If the player hits the undo. The conditions are hard coded. If the first 2
		// levels had hard coded solutions too, then their conditions would be different
		if (evt.getSource() == undoButton && ((levelNo <= 2 && Q.size() != 0) || (levelNo == 4 && Q.size() > 24)
				|| (levelNo == 3 && Q.size() > 168)) && engine.update(Q.pop(), Q.pop(), Q.pop(), Q.pop()))
			numberOfMoves--;
		// If the player hits the hint.
		else if (evt.getSource() == hintButton && Q.size() != 0) {
			engine.update(Q.pop(), Q.pop(), Q.pop(), Q.pop());
			// Since the player can win with hints, this part exists.
			if (board.win)
				try {
					checkWin();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

		}
		// If the player hits the replay. The conditions are hard coded.
		else if (evt.getSource() == replayButton)
			while (((levelNo <= 2 && Q.size() > 0) || (levelNo == 4 && Q.size() > 24)
					|| (levelNo == 3 && Q.size() > 168)) && engine.update(Q.pop(), Q.pop(), Q.pop(), Q.pop()))
				numberOfMoves--;
		// If the player hits the mute.
		else if (evt.getSource() == muteButton) {
			if(!mute)
				muteButton.setIcon(new ImageIcon(
						new ImageIcon("muted.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
			else 
				muteButton.setIcon(new ImageIcon(
						new ImageIcon("mute.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
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
		// If the player hits the change Theme.
		else if (evt.getSource() == changeTheme) {
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
		else if (evt.getSource() == returnButton)
			try {
				this.setVisible(false);
				myClip.stop();
				new MainScreen();
				f.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	// When user presses a location, we'll check if it's on the game area, then
	// record the location.
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() < 420) {
			// this pressed is to ensure we're clicking only once.
			pressed = true;
			iPressed = e.getY() / 75;
			jPressed = e.getX() / 75;
		}
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
			iReleased = e.getY() / 75;
			jReleased = e.getX() / 75;
			if (engine.update(iPressed, jPressed, iReleased, jReleased)) {
				numberOfMoves++;
				Q.push(jPressed);
				Q.push(iPressed);
				Q.push(jReleased);
				Q.push(iReleased);
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
