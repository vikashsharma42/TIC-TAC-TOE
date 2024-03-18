import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_App 
{
	JFrame fr = new JFrame("TIC TAC TOE");
	JLabel la = new JLabel(new ImageIcon(getClass().getResource("images/tictactoe.png")));
	//JLabel img=	new JLabel(new ImageIcon(getClass().getResource("images/background.jpg")));
	JPanel pa = new JPanel();
	JLabel msg = new JLabel("Please Choose One");
	ButtonGroup bg = new ButtonGroup();
	JRadioButton cb1, cb2;
	JButton start;

	public Main_App()
	{
		fr.setSize(700, 690);
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
		la.add(pa);
		pa.setBounds(40, 100, 600, 470);
		pa.setBackground(Color.BLUE);
		pa.addMouseListener(new Magic());
		addComponents();
	}
	private void addComponents() 
	{
		pa.setLayout(null);
		Font fo = new Font("elephant", 3, 20);
		pa.add(msg);
		msg.setBounds(150, 50, 330, 30);
		msg.setFont(new Font("elephant", 3, 30));
		msg.setForeground(Color.RED);
		cb1 = new JRadioButton("Vs Computer");
		cb1.setBounds(100, 150, 170, 30);
		cb1.setFont(fo);
		cb1.setBackground(Color.MAGENTA);
		cb1.setForeground(Color.WHITE);
		bg.add(cb1);
		pa.add(cb1);
		cb2 = new JRadioButton("Vs Friend");
		cb2.setBounds(350, 150, 150, 30);
		cb2.setFont(fo);
		cb2.setBackground(Color.MAGENTA);
		cb2.setForeground(Color.WHITE);
		bg.add(cb2);
		pa.add(cb2);
		start = new JButton("Let's play");
		start.setBounds(250, 300, 130, 30);
		start.setFont(new Font("plain", 3, 20));
		start.setBackground(Color.RED);
		start.setForeground(Color.GREEN);
		start.setBorder(BorderFactory.createLineBorder(Color.RED,3));
		start.addActionListener(new PlayListener());
		pa.add(start);

	}
	class Magic implements MouseListener
    {
    	public void mouseEntered(MouseEvent evt)
    	{
    		pa.setBackground(Color.ORANGE);
    	}
    	public void mouseExited(MouseEvent evt)
    	{
    		pa.setBackground(Color.BLUE);
    	}
    	public void mouseClicked(MouseEvent evt){}
    	public void mousePressed(MouseEvent evt){}
    	public void mouseReleased(MouseEvent evt){}
    }
    class PlayListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{    
			if(cb1.isSelected())
			{    
			    new VsComputerApp();
			}    
			if(cb2.isSelected())
			{    
				new VsFriendApp();
			}   
		}
	}
	public static void main(String[] args) 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Main_App();
	}
}