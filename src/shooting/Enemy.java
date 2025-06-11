package shooting;

public class Enemy extends Character{
	public Enemy(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
	}

	public void draw(MyFrame f) {
		// TODO 自動生成されたメソッド・スタブ
		f.setColor(0, 0, 128);
		f.fillRect(x, y+20, 30, 15);
		f.setColor(0, 0,128);
		f.fillRect(x+10, y+10, 10, 25);
	}

}
