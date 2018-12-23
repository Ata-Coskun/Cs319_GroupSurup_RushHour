package rushHour;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;
public abstract class GameEngine {
	
	
	//public GameEngine() {};
	
	//public GameEngine(Board board) {};
	
	abstract boolean update(int iClicked,int jClicked,int iDragged,int jDragged);
			
}
