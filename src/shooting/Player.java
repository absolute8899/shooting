package shooting;
import java.awt.event.KeyEvent;

public class Player extends Character{
	public Player(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			vx=-5;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			vx=+5;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			vx=0;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			vx=0;
		}
	}
	
	


}
