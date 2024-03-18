import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VsFriendApp
{
    JFrame fr=new JFrame("Playing with Friend");
	JLabel la=new JLabel(new ImageIcon(getClass().getResource("images/tictactoe.png")));
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/circle.png"));
    ImageIcon icon2=new ImageIcon(getClass().getResource("images/close.png"));
    JLabel msg=new JLabel("First Player Term....");
    JLabel label=new JLabel();
    JPanel[] pa=new JPanel[4];
    JButton reset=new JButton("RESET");
    JButton[] bt=new JButton[9];
    Boolean winnerFound=false;
    int player=1;
    int count=0;
    String time;
    int minute;
    int second;
    TimeThread th;
    
    public VsFriendApp()
	{
	    fr.setSize(700,690);
	   	fr.setLocationRelativeTo(null);
	   	fr.setResizable(false);
	   	fr.setDefaultCloseOperation(3);
	   	fr.add(la);
    	addPanels();
    	fr.setVisible(true);
	}
    private void addPanels()
    {
    	la.setLayout(null);
    	for(int i=0;i<4;i++)
    	{
    		pa[i]=new JPanel();
    		la.add(pa[i]);
    	}
    	pa[0].setBounds(540,5,100,40);
    	pa[0].setBackground(Color.WHITE);
    	pa[0].setBorder(BorderFactory.createLineBorder(Color.ORANGE,5));
    	pa[1].setBounds(40,50,600,30);
    	pa[1].setBackground(Color.cyan);
    	pa[2].setBounds(40,100,600,470);
    	pa[3].setBounds(40,580,600,30);
    	pa[3].setOpaque(false);
    	addTime();
    }	
    private void addTime()
    {
    	pa[0].setLayout(new FlowLayout(FlowLayout.CENTER));	
	   	label.setFont(new Font("arial",Font.BOLD,20));
	   	label.setForeground(Color.RED);
    	pa[0].add(label);
    	addComponents();
    }
	
    private void addComponents()
    {
    	pa[1].add(msg);
    	msg.setFont(new Font("elephant",3,20));
    	msg.setForeground(Color.red);
    	pa[3].add(reset);
    	reset.setFont(new Font("arial",2,16));
    	reset.setForeground(Color.blue);
    	reset.setForeground(Color.magenta);
    	reset.setEnabled(false);
    	reset.addActionListener(new ResetListener());
    	addButtons();
    }
	private void addButtons()
    {
    	pa[2].setLayout(new GridLayout(3,3));
    	TicListener listener=new TicListener();
    	//Code to start time thread start
    	th=new TimeThread();
    	th.start();
    	
    	for(int i=0;i<9;i++)
    	{
    		bt[i]=new JButton();
    		bt[i].addActionListener(listener);
    		bt[i].setBackground(Color.yellow);
    		bt[i].setBorder(BorderFactory.createLineBorder(Color.RED,5));
    		pa[2].add(bt[i]);
    	}
    }
	class TicListener implements ActionListener
	{
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent evt)
		{
		   JButton bb=(JButton)evt.getSource();
		   if(bb.getIcon()!= null || winnerFound)
		   {
			   JOptionPane.showMessageDialog(fr,"Wrong Click");
			   return;
		   }
		   if(player==1)
		   {
			   bb.setIcon(icon1);
			   msg.setText("Second Player Term....");
			   msg.setForeground(Color.blue);
			   pa[1].setBackground(Color.red);
			   player=2;
		   }
		   else if(player==2)
		   {
			   bb.setIcon(icon2);
			   msg.setText("First Player Term....");
			   msg.setForeground(Color.red);
			   pa[1].setBackground(Color.cyan);
			   player=1;
		   }
		   findWinner();
		   count++;
		   if(count==9 && !winnerFound)
		   {
			   reset.setEnabled(true);
			   new BlinkThread().start();
			   msg.setText("GAME OVER......");
			   pa[1].setBackground(Color.green);
			   JOptionPane.showMessageDialog(fr,"NO ONE IS WINNER");
			   th.stop();
			   dissabledButtons();
		   }  
		}
	}
	private void findWinner()
	{
		if(bt[0].getIcon()==icon1 && bt[1].getIcon()==icon1 && bt[2].getIcon()==icon1) 
		{
			announceWinner(0,1,2,"First");
		}
		else if(bt[0].getIcon()==icon2 && bt[1].getIcon()==icon2 && bt[2].getIcon()==icon2) 
		{
			announceWinner(0,1,2,"Second");
		}
		else if(bt[3].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[5].getIcon()==icon1) 
		{
			announceWinner(3,4,5,"First");
		}
		else if(bt[3].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[5].getIcon()==icon2) 
		{
			announceWinner(3,4,5,"Second");
		}
		else if(bt[6].getIcon()==icon1 && bt[7].getIcon()==icon1 && bt[8].getIcon()==icon1) 
		{
			announceWinner(6,7,8,"First");
		}
		else if(bt[6].getIcon()==icon2 && bt[7].getIcon()==icon2 && bt[8].getIcon()==icon2) 
		{
			announceWinner(6,7,8,"Second");
			dissabledButtons();
		}
		
		
		else if(bt[0].getIcon()==icon1 && bt[3].getIcon()==icon1 && bt[6].getIcon()==icon1) 
		{
			announceWinner(0,3,6,"First");
		}
		else if(bt[0].getIcon()==icon2 && bt[3].getIcon()==icon2 && bt[6].getIcon()==icon2) 
		{
			announceWinner(0,3,6,"Second");
		}
		else if(bt[1].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[7].getIcon()==icon1) 
		{
			announceWinner(1,4,7,"First");
		}
		else if(bt[1].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[7].getIcon()==icon2) 
		{
			announceWinner(1,4,7,"Second");
		}
		else if(bt[2].getIcon()==icon1 && bt[5].getIcon()==icon1 && bt[8].getIcon()==icon1) 
		{
			announceWinner(2,5,8,"First");
		}
		else if(bt[2].getIcon()==icon2 && bt[5].getIcon()==icon2 && bt[8].getIcon()==icon2) 
		{
			announceWinner(2,5,8,"Second");
		}
		else if(bt[2].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[6].getIcon()==icon1) 
		{
			announceWinner(2,4,6,"First");
		}
		else if(bt[2].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[6].getIcon()==icon2) 
		{
			announceWinner(2,4,6,"Second");
		}
		else if(bt[0].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[8].getIcon()==icon1) 
		{
			announceWinner(0,4,8,"First");
		}
		else if(bt[0].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[8].getIcon()==icon2) 
		{
			announceWinner(0,4,8,"Second");
		}
	}
	
	@SuppressWarnings("deprecation")
	private void announceWinner(int i,int j, int k,String str)
	{
		bt[i].setBackground(Color.red);
		bt[j].setBackground(Color.red);
		bt[k].setBackground(Color.red);
		new BlinkThread().start();
		msg.setText(str+" Player won");
		JOptionPane.showMessageDialog(fr,"CONGRATULATIONS !!!!!!!!\n\n "+str+" Player won in "+time+" second...");
		msg.setText("GAME OVER");
		pa[1].setBackground(Color.green);
		reset.setEnabled(true);		
		winnerFound=true;
		th.stop();
		dissabledButtons();
	}
	private void dissabledButtons()
	{
		for(JButton b:bt)
			b.setEnabled(false);
	}
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			for(JButton button:bt)
			{
				button.setEnabled(true);
				button.setIcon(null);
				button.setBackground(Color.yellow);
		    }
			count=0; 
		    player=1;
		    msg.setText("First Player term....");
		    msg.setForeground(Color.MAGENTA);
		    winnerFound=false;
		    reset.setEnabled(false);
		    minute=second=0;
			th=new TimeThread();
		    th.start();
		}	
	}
	class TimeThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				time=(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second);
				label.setText(time);
				try 
				{
					Thread.sleep(1000);
				}
				catch(Exception ex) 
				{
					System.out.println(ex);
				}
				second++;
				if(second==60)
				{
					second=0;
					minute++;
				}
			} 	
		}
	}
	class BlinkThread extends Thread
	{
		public void run()
		{
			int count=0;
			while(true)
			{
				msg.setVisible(true);
				try
				{
					Thread.sleep(100);
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
				msg.setVisible(false);
				try
				{
					Thread.sleep(100);
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
				count++;
				if(count==25)
				{
					msg.setVisible(true);
					break;
				}
			}
		}
	}
}