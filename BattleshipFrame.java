
import javax.swing.JFrame;
public class BattleshipFrame {

	public static void main(String[] args) 
	{
	JFrame frame = new JFrame("Battleship");
	final int WIDTH = 1500;
	final int HEIGHT = 900;
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setSize(WIDTH, HEIGHT);
	frame.getContentPane().add(new BattleshipPanel());
	frame.setVisible(true);
	}

	}
