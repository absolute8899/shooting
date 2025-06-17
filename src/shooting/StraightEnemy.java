package shooting;

public class StraightEnemy extends Enemy{
	public StraightEnemy(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
		life=5;
	}
	
	public void draw(MyFrame f) {
		f.setColor(0, 0, 0);
		f.fillRect(x, y, 14, 15);
		f.setColor(0, 0, 0);
		f.fillRect(x+2, y+15, 10, 15);
	}

}
