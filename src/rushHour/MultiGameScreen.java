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
import java.io.FileNotFoundException;
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

public class MultiGameScreen extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
	static int iDragged = 0, jDragged = 0;
	static int iClicked = 0, jClicked = 0;
	Board board;
	String settingsDatas;
	int numberOfMoves;
	int theme;
	int levelNo;
	boolean x;
	boolean win;
	boolean mute;
	public Clip myClip;
	MultiGameEngine engine;
	JButton[] cards;
	JLabel text, p1, p2;
	PrintWriter resumeWriter;
	File resumeFile;
	File settingsFile;

	int endGame;

	Image background;
	Image rock;
	Image boardImage;
	Image redCar1;
	Image redCar2;
	Image car2;
	Image car3;
	Image carv2;
	Image carv3;
	JButton changeTheme;
	JButton muteButton;
	JButton returnMulti;
	PrintWriter settingsWriter;
	File theme1file = new File("OffLimits.wav");
	File theme2file = new File("StarWars.wav");
	File theme3file = new File("Ankara.wav");
	JFrame f;
	public MultiGameScreen(MultiGameEngine engine) throws IOException {
		
		f = new JFrame("Multi Rush Hour");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setSize(900, 700);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		
		this.engine = engine;
		this.board = engine.board;
		this.levelNo = engine.level;
		endGame = -15;
		System.out.println("multi engine level: " + engine.level);
		setLayout(null);
		cards = new JButton[8];
		setLayout(null);

		returnMulti = new JButton("RETURN");
		returnMulti.addActionListener(this);

		cards[0] = new JButton("CARDS1[0]");
		cards[1] = new JButton("CARDS1[1]");
		cards[2] = new JButton("CARDS1[2]");
		cards[3] = new JButton("CARDS1[3]");

		cards[4] = new JButton("CARDS2[0]");
		cards[5] = new JButton("CARDS2[1]");
		cards[6] = new JButton("CARDS2[2]");
		cards[7] = new JButton("CARDS2[3]");

		cards[0].addActionListener(this);
		cards[1].addActionListener(this);
		cards[2].addActionListener(this);
		cards[3].addActionListener(this);

		cards[4].addActionListener(this);
		cards[5].addActionListener(this);
		cards[6].addActionListener(this);
		cards[7].addActionListener(this);

		changeTheme = new JButton("CHANGE THEME");
		changeTheme.addActionListener(this);
		changeTheme.setBackground(Color.yellow);
		changeTheme.setForeground(Color.red);
		changeTheme.setBounds(550, 450, 150, 50);

		muteButton = new JButton("MUTE");
		muteButton.addActionListener(this);
		muteButton.setBackground(Color.yellow);
		muteButton.setForeground(Color.red);
		muteButton.setBounds(550, 550, 150, 50);

		returnMulti.setBounds(550, 500, 150, 50);
		returnMulti.setBackground(Color.yellow);
		returnMulti.setForeground(Color.red);

		p1 = new JLabel();
		p1.setBounds(450, 15, 200, 30);
		p1.setText("Player 1's Cards");
		p1.setForeground(Color.YELLOW);
		text = new JLabel();
		text.setBounds(450, 200, 400, 30);
		text.setForeground(Color.YELLOW);
		p2 = new JLabel();
		p2.setBounds(450, 400, 200, 30);
		p2.setText("Player 2's Cards");
		p2.setForeground(Color.YELLOW);

		cards[0].setBounds(450, 50, 100, 145);
		cards[1].setBounds(550, 50, 100, 145);
		cards[2].setBounds(650, 50, 100, 145);
		cards[3].setBounds(750, 50, 100, 145);

		cards[4].setBounds(450, 250, 100, 145);
		cards[5].setBounds(550, 250, 100, 145);
		cards[6].setBounds(650, 250, 100, 145);
		cards[7].setBounds(750, 250, 100, 145);

		add(p1);
		add(p2);
		add(text);

		add(cards[0]);
		add(cards[1]);
		add(cards[2]);
		add(cards[3]);

		add(cards[4]);
		add(cards[5]);
		add(cards[6]);
		add(cards[7]);

		add(changeTheme);
		add(muteButton);
		add(returnMulti);

		setBackground(Color.darkGray);
		addMouseListener(this);
		addMouseMotionListener(this);

		resumeFile = new File("resume.txt");
		settingsFile = new File("settings.txt");
		theme = readFile(1);
		readFile(2);

		if (theme == 1)
			play(theme1file, mute);
		if (theme == 2)
			play(theme2file, mute);
		if (theme == 3)
			play(theme3file, mute);

		System.out.println("multi theme" + levelNo);
		resumeWriter = new PrintWriter(resumeFile);
		resumeWriter.println(levelNo + "");
		resumeWriter.close();

		settingsWriter = new PrintWriter(settingsFile);
		settingsWriter.println("1,0,");
		settingsWriter.close();
	}

	public int readFile(int readType) throws IOException {
		Scanner scanner = new Scanner(settingsFile);
		settingsDatas = scanner.nextLine();
		System.out.println("settingDatas: " + settingsDatas);
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
		if (engine.turn)
			endGame = JOptionPane.showOptionDialog(null, options, "Player 2 is the Winner",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		else			
			endGame = JOptionPane.showOptionDialog(null, options, "Player 1 is the Winner",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		
		myClip.stop();
		if (endGame == 0) {
			this.setVisible(false);
			try {
				new MainScreen();
				f.dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (endGame == 1) {
			this.setVisible(false);
			new DisplayLevelScreen(levelNo + 1);
			f.dispose();
		}
		if (endGame == 2) {
			this.setVisible(false);
			f.dispose();
			System.exit(0);
		}
		return;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (theme == 1) {
			rock = new ImageIcon("rock.png").getImage();
			boardImage = new ImageIcon("backGround.jpg").getImage();
			redCar1 = new ImageIcon("7.png").getImage();
			redCar2 = new ImageIcon("9.png").getImage();
			car2 = new ImageIcon("6.png").getImage();
			car3 = new ImageIcon("4.png").getImage();
			carv2 = new ImageIcon("10.png").getImage();
			carv3 = new ImageIcon("2.png").getImage();
		}
		if (theme == 2) {
			rock = new ImageIcon("meteor.png").getImage();
			boardImage = new ImageIcon("uz.png").getImage();
			redCar1 = new ImageIcon("14.png").getImage();
			redCar2 = new ImageIcon("16.png").getImage();
			car2 = new ImageIcon("13.png").getImage();
			car3 = new ImageIcon("11.png").getImage();
			carv2 = new ImageIcon("12.png").getImage();
			carv3 = new ImageIcon("15.png").getImage();
		}
		if (theme == 3) {
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

		// ******Draw the board**********
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
		// *******Draw the car************
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 14; j++) {

				if (board.coordinates[i][j] == 2) {
					g.drawImage(rock, j * 30, i * 30, 30, 30, this);
				}

				else if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					if (tempt != null && !tempt.direction) {// horizontal

						if (tempt.carType == 1) {// if car is red
							g.drawImage(redCar1, j * 30, i * 30, 60, 30, this);
							j++;
						} else if (tempt.carType == 2) {// if car is red
							g.drawImage(redCar2, j * 30, i * 30, 60, 30, this);
							j++;
						} else if (tempt.size == 2) {// if car is size 2 // 2
							g.drawImage(car2, j * 30, i * 30, 60, 30, this);
							j++;
						} else if (tempt.size == 3) {// if car is size 3
							g.drawImage(car3, j * 30, i * 30, 90, 30, this);
							j += 2;
						}
					}
				}
			}
		}
		for (int j = 0; j < 14; j++) {
			for (int i = 0; i < 22; i++) {

				if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);
					if (tempt != null && tempt.direction)
						if (tempt.size == 2) {// if car is vertical & size 2
							g.drawImage(carv2, j * 30, i * 30, 30, 60, this);
							i++;
						} else if (tempt.size == 3) {// if car is vertical & size 3
							g.drawImage(carv3, j * 30, i * 30, 30, 90, this);
							i += 2;
						}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			cards[i].setIcon(new ImageIcon(
					new ImageIcon("cards/" + engine.cards1[i].numberOfMoves + engine.cards1[i].shift + ".png")
							.getImage()));
			cards[i + 4].setIcon(new ImageIcon(
					new ImageIcon("cards/" + engine.cards2[i].numberOfMoves + engine.cards2[i].shift + ".png")
							.getImage()));
		}

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

		if (x && engine.shift) {
			g.setColor(Color.CYAN);
			for (int i = 1; i < 22; i++)
				g.drawLine(0, 30 * i, 420, 30 * i);
		}

		repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == changeTheme) {
			theme = (theme % 3) + 1;
			if (theme == 1) {
				mute = false;
				myClip.stop();
				play(theme1file, mute);
				return;
			}
			if (theme == 2) {
				mute = false;
				myClip.stop();
				play(theme2file, mute);
				return;
			}

			if (theme == 3) {
				mute = false;
				myClip.stop();
				play(theme3file, mute);
				return;
			}

		}
		if (e.getSource() == returnMulti) {
			try {
				this.setVisible(false);
				myClip.stop();
				new MainScreen();
				f.dispose();
			} catch (IOException evt) {
				// TODO Auto-generated catch block
				evt.printStackTrace();
			}
		}
		if (e.getSource() == muteButton) {
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
		if (engine.numberOfMoves == 0 && !engine.shift)
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
			} else {
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

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getX() < 420 && (engine.numberOfMoves != 0 || engine.shift)) {
			x = true;
			iClicked = e.getY() / 30;
			jClicked = e.getX() / 30;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (x) {
			iDragged = e.getY() / 30;
			jDragged = e.getX() / 30;
			if (!board.getWin()) {
				engine.update(iClicked, jClicked, iDragged, jDragged);
				if (board.win)
					try {
						checkWin();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
