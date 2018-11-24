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
import java.util.Stack;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SingleGameScreen extends JPanel implements MouseListener, MouseMotionListener,ActionListener{
	
	
	static int xDragged = 0, yDragged = 0;
	static int xClicked = 0, yClicked = 0;
	SingleGameEngine engine;
	Board board;
	
	int numberOfMoves;
	int levelNo;
	
	boolean x;
	boolean win;
	//Timer myTimer = new Timer();
	public Clip myClip;
	boolean mute;
	File file = new File("OffLimits.wav");
	Stack<Board> Q;
	int level;
	int endGame;
	//
	JButton undoButton = new JButton("UNDO");;
	JButton muteButton = new JButton( "MUTE");;
	
	public SingleGameScreen(SingleGameEngine engine) {
		endGame = -15;
		numberOfMoves = 0;
		this.engine = engine;
		this.board = engine.board;
		Q = engine.stack;
		this.level = engine.level;
		
		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
		setLayout(null);
		
		
		undoButton.setText("UNDO");
		muteButton.setText("MUTE");
		undoButton.setBackground(Color.yellow);
		undoButton.setForeground(Color.red);
		undoButton.addActionListener(this);
		muteButton.addActionListener(this);
		muteButton.setBackground(Color.yellow);
		muteButton.setForeground(Color.red);
		undoButton.setBounds(550,50,100,50);
		muteButton.setBounds(550,200,100,50);
		add(undoButton);
		add(muteButton);
		mute = false;
		play(file,mute);
		
	}

	void paint() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(board.coordinates[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	public void checkWin() throws IOException {
		if(win) {
			
			Object[] options = {"Turn back to main",
                    "Next Level",
                    "EXIT"};
			endGame = JOptionPane.showOptionDialog(null,options,
					"WIN!\nYou finished in "+ numberOfMoves + " number of moves",
							JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[2]);
			System.out.println(endGame);
			
			myClip.stop();
			if(endGame == 0)
	    	{
				this.setVisible(false);
	    		try {
					MainScreen newGame = new MainScreen();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	if(endGame==1) {
	    		this.setVisible(false);
	    		LevelSelection selection = new LevelSelection(2);
	    	}
	    	if(endGame==2) {
	    		this.setVisible(false);
	    		System.exit(0);
	    	}	
			return;
		}	    
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image boardImage = new ImageIcon("board.png").getImage();
		g.drawImage(boardImage, 0, 0, 450, 450, this);
		
		try {
			checkWin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				if (board.coordinates[i][j] == 2) {
					Image rock = new ImageIcon("rock.png").getImage();
					g.drawImage(rock, j * 75, i * 75, 75, 75, this);
				}

				else if (board.coordinates[i][j] == 1) {
					Car tempt = board.searchCoordinates(i, j);

					if (tempt != null && !tempt.direction) {

						if (tempt.carType != 0) {// if car is red
							if (tempt.j2 == 5)
								win = true;
							Image car = new ImageIcon("7.png").getImage();
							g.drawImage(car, j * 75, i * 75, 150, 75, this);
							j++;
							g.setColor(Color.red);
							g.fillRect(425, i * 75, 25, 75);
						}

						else if (tempt.size == 2) {// if car is size 2 // 2
							Image car = new ImageIcon("6.png").getImage();
							g.drawImage(car, j * 75, i * 75, 140, 75, this);
							j++;
						} else if (tempt.size == 3) {// if car is size 3
							Image car = new ImageIcon("4.png").getImage();
							g.drawImage(car, j * 75, i * 75, 225, 75, this);
							j += 2;
						}
					}
				}

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++)

				if (board.coordinates[j][i] == 1) {
					Car tempt =board.searchCoordinates(j, i);
					if (tempt != null && tempt.direction)
						if (tempt.size == 2) {// if car is vertical & size 2
							Image car = new ImageIcon("10.png").getImage();
							g.drawImage(car, i * 75, j * 75, 75, 150, this);
							j++;
						} else if (tempt.size == 3) {// if car is vertical & size 3
							Image car = new ImageIcon("10.png").getImage();
							g.drawImage(car, i * 75, j * 75, 75, 225, this);
							j += 2;
						}
				}	
				repaint();
		/*if(win) {
			this.setVisible(false);
			undoButton.setVisible(false);
			muteButton.setVisible(false);
			myClip.stop();
	    	JOptionPane.showMessageDialog(null, "WIN! \nYou finished in " + numberOfMoves +" number of moves");
		}*/
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
								
		    if( evt.getSource() == undoButton) {
		       System.out.println("num of element:  " + Q.size()); 
		       Q.pop();
	    	   board = Q.peek();
	    	   System.out.println("new :  " + Q.size());
	    	   repaint();
			}
		    if(evt.getSource() == muteButton) {
		    	
		    	if(mute == false) {	
		    		mute = true;
		    		myClip.stop();
		    		return;
		    	}
		    	
		    	if(mute == true) {
		    		mute =false;
		    		play(file,mute);
		    		return;
		    	}  				    	
		    	   
		    	repaint();
		    	
		    }
	}

	
	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = true;
		xClicked = e.getX() / 75;
		yClicked = e.getY() / 75;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		xDragged = e.getX() / 75;
		yDragged = e.getY() / 75;
		if (x) {
			
			if (engine.update(yClicked, xClicked, yDragged, xDragged)) {
				numberOfMoves++;
				//board.Q.push(board);
			}
			x = false;
		}
		repaint();
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


	public void play(File file,Boolean mute) 
	{
	    try
	    {
	        //final Clip
	    	myClip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
	       // myClip = clip;
	        
	        myClip.addLineListener(new LineListener()
	        {
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP)
	                    myClip.close();
	            }
	        });

	        myClip.open(AudioSystem.getAudioInputStream(file));
	        myClip.start();
	        if(mute == true)
	        myClip.stop();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	    
	}


}


