package shooting;

public class PlayerBullet extends Character{
	public PlayerBullet(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
	}
	public void draw(MyFrame f) {
		f.fillRect(x+12.5,y,5,10);
	}
}
