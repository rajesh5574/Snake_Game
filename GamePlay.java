import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener , ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int [] snakeXlength = new int[750];
	private int [] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up=false;
	private boolean down=false;
	
	private ImageIcon headRight;
	private ImageIcon headLeft;
	private ImageIcon headUp;
	private ImageIcon headDown;
	
	private int lengthofsnake = 3;
	private Timer timer;
	private int delay = 100;
	
	private ImageIcon tail;
	private int moves=0;
	private int scores=0;
	
	private int[] fruitXpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	private int[] fruitYpos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

	private ImageIcon fruitImage;
	
	private Random random = new Random();
	private int xpos=random.nextInt(25);
	private int ypos=random.nextInt(23);
	private ImageIcon titleImage;
	
	public GamePlay() {
		
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(delay,this);
            timer.start();
	}
	public void paint(Graphics g) {
		
		if(moves==0) {
			snakeXlength[0]=100;
			snakeXlength[1]=75;
			snakeXlength[2]=50;
			
			snakeYlength[0]=100;
			snakeYlength[1]=100;
			snakeYlength[2]=100;
			
		}
		
		//Display title
		titleImage =new ImageIcon("Header.png");
		titleImage.paintIcon(this,g,21,5);

		//Display Comply border		
		g.setColor(Color.black);
		g.drawRect(24,74,851,570);
		
		// Draw Score
		g.setColor(Color.white);
		g.setFont(new Font("areal",Font.PLAIN,14));
		g.drawString("Scores : "+scores, 780, 30);
		
		g.setColor(Color.white);
		g.setFont(new Font("areal",Font.PLAIN,14));
		g.drawString("length : "+lengthofsnake, 780, 50);
		
		//display Comply background
		g.setColor(Color.BLACK);
		g.fillRect(20, 75, 860, 580);
		headRight = new ImageIcon("RightSnake.png");
     	headRight.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
     	
     	for(int i=0;i<lengthofsnake;i++) {
     		if(i==0 && right) {
     			headRight = new ImageIcon("RightSnake.png");
     			headRight.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
     		}
     		if(i==0 && left) {
     			headLeft = new ImageIcon("LeftSnake.png");
     			headLeft.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
     		}
     		if(i==0 && down) {
     			headDown = new ImageIcon("DownSnake.png");
     			headDown.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
     		}
     		if(i==0 && up) {
     			headUp = new ImageIcon("UpSnake.png");
     			headUp.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
     		}
     		if(i!=0) {
     			tail = new ImageIcon("Tail.png");
     			tail.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
     		}
     		
     		fruitImage = new ImageIcon("Picture1.png");
     		if(fruitXpos[xpos] == snakeXlength[0] && fruitYpos[ypos] == snakeYlength[0]) {
     			scores=scores+5;
     			lengthofsnake++;
     			xpos = random.nextInt(34);
     			ypos = random.nextInt(23);		
     		}
     		fruitImage.paintIcon(this, g, fruitXpos[xpos], fruitYpos[ypos]);
     	}
     	
     	for(int i=1;i<lengthofsnake;i++) {
     		if(snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]) {
     			right=false;
     			left=false;
     			up=false;
     			down=false;
     			//GameOver message
     			g.setColor(Color.RED);
     			g.setFont(new Font("areal",Font.BOLD,40));
     			g.drawString("GAME OVER !!! Score : "+scores,250, 300);
     			
     			// Restart message
     			g.setColor(Color.WHITE);
     			g.setFont(new Font("areal",Font.BOLD,20));
     			g.drawString("Enter To ReStart ",350, 340);
     		}
     	}
     	
		g.dispose();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.restart();
		if(right) {
			for(int n=lengthofsnake-1;n>=0;n--) {
				snakeYlength[n+1]= snakeYlength[n];
			}
			for(int n=lengthofsnake-1;n>=0;n--) {
				if(n==0) {
					snakeXlength[n]=snakeXlength[n]+25;
				}
				else {
					snakeXlength[n]=snakeXlength[n-1];
				}
				if(snakeXlength[n] > 850) {
					snakeXlength[n]=25;
				}
			}
			repaint();
		}	
		
		if(left) {
			for(int n=lengthofsnake-1;n>=0;n--) {
				snakeYlength[n+1]= snakeYlength[n];
			}
			for(int n=lengthofsnake-1;n>=0;n--) {
				if(n==0) {
					snakeXlength[n]=snakeXlength[n]-25;
				}
				else {
					snakeXlength[n]=snakeXlength[n-1];
				}
				if(snakeXlength[n] < 25) {
					snakeXlength[n]=850;
				}
			}
			repaint();
		}
		
		if(up) {
			for(int n=lengthofsnake-1;n>=0;n--) {
				snakeXlength[n+1]= snakeXlength[n];
			}
			for(int n=lengthofsnake-1;n>=0;n--) {
				if(n==0) {
					snakeYlength[n]=snakeYlength[n]-25;
				}
				else {
					snakeYlength[n]=snakeYlength[n-1];
				}
				if(snakeYlength[n] < 75) {
					snakeYlength[n]=625;
				}
			}
			repaint();
		}
		
		if(down) {
			for(int n=lengthofsnake-1;n>=0;n--) {
				snakeXlength[n+1]= snakeXlength[n];
			}
			for(int n=lengthofsnake-1;n>=0;n--) {
				if(n==0) {
					snakeYlength[n]=snakeYlength[n]+25;
				}
				else {
					snakeYlength[n]=snakeYlength[n-1];
				}
				if(snakeYlength[n] > 625) {
					snakeYlength[n]=75;
				}
			}
			repaint();
		
	}
		}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			moves=0;
			scores=0;
			lengthofsnake=0;
			repaint();
		}
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			moves++;
			right=true;
			if(!left) {
				right=true;
			}
			else {
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			moves++;
			left=true;
			if(!right) {
				left=true;
			}
			else {
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_UP) {
			moves++;
			up=true;
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
			right=false;
			left=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_DOWN) {
			moves++;
			down=true;
			if(!up) {
				down=true;
			}
			else {
				down=false;
				up=true;
			}
			right=false;
			left=false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
