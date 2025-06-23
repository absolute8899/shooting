package shooting;

public class ReflectionEnemy  extends Enemy{
	public ReflectionEnemy(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
		life=4+GameWorld.stage;
	}
	public void move() {
		super.move();
		if(x>350) vx=-10;
		if(x<0) vx=10;
		
	}
	
	public void draw(MyFrame f) {
		f.setColor(128, 0, 128);
		f.fillRect(x+10, y+15, 30, 7);
		f.setColor(128, 0, 128);
		f.fillOval(x+5, y+12, 15, 15);
		f.setColor(128, 0, 128);
		f.fillOval(x+30, y+12, 15, 15);
	}

}
