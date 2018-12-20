package rushHour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.Timer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SingleGameScreen extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	static int iDragged = 0, jDragged = 0;
	static int iClicked = 0, jClicked = 0;
	SingleGameEngine engine;
	Board board;
	int time;
	int numberOfMoves;
	int levelNo;
	int highScore; // high score
	boolean x;
	boolean win;
	Timer myTimer = new Timer();
	public Clip myClip;
	boolean mute;
	File file = new File("OffLimits.wav");
	Stack<Integer> Q;
	int endGame;
	int theme;
	//
	JButton undoButton;
	JButton muteButton;
	JButton changeTheme;
	JButton hintButton;
	JButton replayButton;
	JLabel NOfMoves;
	File scoreFile;
	File resumeFile;
	PrintWriter writer;
	PrintWriter resumeWriter;
	PrintWriter winWriter;
	Image rock;
	Image boardImage;
	Image redCar;
	Image car2;
	Image car3;
	Image carv2;
	Image carv3;
	BufferedImage background;
	String scoreDatas; // it is our dataset for high scores
	// int timeCounter;

	public SingleGameScreen(SingleGameEngine engine, int theme, Stack<Integer> Q) throws IOException {
		time = 0;

		System.out.println(Q.size());
		this.theme = theme;
		endGame = -15;
		numberOfMoves = 0;
		this.engine = engine;
		this.board = engine.board;
		this.Q = Q;
		this.levelNo = engine.level;
		// System.out.println("engine level: "+ engine.level);
		// time = 10;

		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
		setLayout(null);
		
		undoButton = new JButton("UNDO");
		muteButton = new JButton("MUTE");
		changeTheme = new JButton("CHANGE THEME");
		hintButton = new JButton("HINT");
		replayButton = new JButton("REPLAY");
		NOfMoves = new JLabel();
		
		undoButton.setBackground(Color.yellow);
		undoButton.setForeground(Color.red);
		undoButton.addActionListener(this);
		undoButton.setBounds(525, 50, 100, 50);
		
		replayButton.setBackground(Color.yellow);
		replayButton.setForeground(Color.red);
		replayButton.addActionListener(this);
		replayButton.setBounds(525, 110, 100, 50);
	
		hintButton.setBackground(Color.yellow);
		hintButton.setForeground(Color.red);
		hintButton.addActionListener(this);
		hintButton.setBounds(525, 170, 100, 50);
		
		muteButton.addActionListener(this);
		muteButton.setBackground(Color.yellow);
		muteButton.setForeground(Color.red);
		muteButton.setBounds(525, 230, 100, 50);
		
		changeTheme.addActionListener(this);
		changeTheme.setBackground(Color.yellow);
		changeTheme.setForeground(Color.red);
		changeTheme.setBounds(500, 290, 150, 50);
		
		NOfMoves.setBounds(550, 5, 150, 15);
		
		add(undoButton);
		add(replayButton);
		add(hintButton);
		add(muteButton);
		add(changeTheme);
		add(NOfMoves);
		
		mute = false;
		play(file, mute);

		scoreFile = new File("high_score.txt");
		resumeFile = new File("resume.txt");
		readFile(); // get the high score from file

		// BufferedWriter out = new BufferedWriter(new FileWriter(scoreFile));
		writer = new PrintWriter(scoreFile);
		winWriter = new PrintWriter(scoreFile);
		writeFile(false, highScore);
		resumeWriter = new PrintWriter(resumeFile);
		resumeWriter.println(levelNo + "");
		resumeWriter.close();
	}

	public void readFile() throws IOException {
		Scanner scanner = new Scanner(scoreFile);
		scoreDatas = scanner.nextLine();
		System.out.println("datas: " + scoreDatas);
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
		System.out.println("High score: " + highScore);
		scanner.close();
	}

	public void writeFile(boolean high, int highscore) throws IOException {

		
		if(high == false) {
		   writer.println(scoreDatas);
		   writer.close();
		}
	    else {
		String score = scoreDatas.charAt(0) + "";
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
		if (win) {
			System.out.println("End score: "+engine.calculateScore(time, numberOfMoves));
			if (engine.calculateScore(time, numberOfMoves)+1 > highScore) // new score is highest
				writeFile(true, engine.calculateScore(time, numberOfMoves)+1); // update file
			Object[] options = { "Turn back to main", "Next Level", "EXIT" };
			endGame = JOptionPane.showOptionDialog(null, options,
					"WIN!\nYou finished in " + numberOfMoves + " number of moves", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			System.out.println(endGame);

			myClip.stop();
			if (endGame == 0) {
				this.setVisible(false);
				try {
					new MainScreen(theme, mute);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (endGame == 1) {
				this.setVisible(false);
				new DisplayLevelScreen(2);
			}
			if (endGame == 2) {
				this.setVisible(false);
				System.exit(0);
			}
			return;
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (theme == 1) {
			rock = new ImageIcon("rock.png").getImage();
			boardImage = new ImageIcon("board.png").getImage();
			redCar = new ImageIcon("7.png").getImage();
			car2 = new ImageIcon("6.png").getImage();
			car3 = new ImageIcon("4.png").getImage();
			carv2 = new ImageIcon("10.png").getImage();
			carv3 = new ImageIcon("2.png").getImage();
		}
		if (theme == 2) {
			rock = new ImageIcon("meteor.png").getImage();
			boardImage = new ImageIcon("boarduzay.png").getImage();
			redCar = new ImageIcon("14.png").getImage();
			car2 = new ImageIcon("13.png").getImage();
			car3 = new ImageIcon("11.png").getImage();
			carv2 = new ImageIcon("12.png").getImage();
			carv3 = new ImageIcon("15.png").getImage();
		}
		g.drawImage(boardImage, 0, 0, 450, 450, this);

		JLabel tmLabel = new JLabel();
		tmLabel.setText("Time : " + Integer.toString(time));
		tmLabel.setBounds(550, 20, 100, 30);
		time++;

		/*
		 * TimerTask task = new TimerTask() { public void run() {
		 * 
		 * } }; //myTimer.schedule(task, 0, 1000);
		 */
		add(tmLabel);

		try {
			checkWin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				if (board.coordinates[i][j] == 2) {
					g.drawImage(rock, j * 75, i * 75, 75, 75, this);
				}

				else if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					if (tempt != null && !tempt.direction) {

						if (tempt.carType != 0) {// if car is red
							if (tempt.j2 == 5)
								win = true;
							g.drawImage(redCar, j * 75, i * 75, 150, 75, this);
							j++;
							g.setColor(Color.red);
							g.fillRect(425, i * 75, 25, 75);
						}

						else if (tempt.size == 2) {// if car is size 2 // 2
							g.drawImage(car2, j * 75, i * 75, 140, 75, this);
							j++;
						} else if (tempt.size == 3) {// if car is size 3
							g.drawImage(car3, j * 75, i * 75, 225, 75, this);
							j += 2;
						}
					}
				}

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				if (board.coordinates[j][i] == 1) {
					Car tempt = board.searchCoordinates(j, i);
					if (tempt != null && tempt.direction)
						if (tempt.size == 2) {// if car is vertical & size 2
							g.drawImage(carv2, i * 75, j * 75, 75, 150, this);
							j++;
						} else if (tempt.size == 3) {// if car is vertical & size 3
							g.drawImage(carv3, i * 75, j * 75, 75, 225, this);
							j += 2;
						}
				}
		

		NOfMoves.setText("Number of moves: " + numberOfMoves);
		repaint();
		/*
		 * if(win) { this.setVisible(false); undoButton.setVisible(false);
		 * muteButton.setVisible(false); myClip.stop();
		 * JOptionPane.showMessageDialog(null, "WIN! \nYou finished in " + numberOfMoves
		 * +" number of moves"); }
		 */
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == undoButton && ((levelNo == 1 && Q.size() != 0) || (levelNo == 2 && Q.size() > 24))
				&& engine.update(Q.pop(), Q.pop(), Q.pop(), Q.pop()))
			numberOfMoves--;
		else if (evt.getSource() == hintButton && Q.size() != 0)
			engine.update(Q.pop(), Q.pop(), Q.pop(), Q.pop());
		else if (evt.getSource() == replayButton)
			while (levelNo == 2 && Q.size() > 24)
				engine.update(Q.pop(), Q.pop(), Q.pop(), Q.pop());
		else if (evt.getSource() == muteButton)
			if (mute) {
				mute = false;
				play(file, mute);
				return;
			} else {
				mute = true;
				myClip.stop();
				return;
			}
		else if (evt.getSource() == changeTheme)
			theme = (theme % 2) + 1;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() < 420) {
			x = true;
			iClicked = e.getY() / 75;
			jClicked = e.getX() / 75;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (x) {
			iDragged = e.getY() / 75;
			jDragged = e.getX() / 75;
			if (engine.update(iClicked, jClicked, iDragged, jDragged)) {
				numberOfMoves++;
				Q.push(jClicked);
				Q.push(iClicked);
				Q.push(jDragged);
				Q.push(iDragged);
			}
			x = false;
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
			// final Clip
			myClip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			// myClip = clip;

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
