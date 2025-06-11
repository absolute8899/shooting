package shooting;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player extends Character implements KeyListener{
	
	private long lastShotTime = 0; // 最後に弾を撃った時刻（ミリ秒）
	private final int shotCooldown = 300; // クールダウン時間（ミリ秒単位、例：300ms = 0.3秒）

	public void draw(MyFrame f) {
		f.setColor(0, 0, 128);
		f.fillRect(x, y+20, 30, 15);
		f.setColor(0, 0,128);
		f.fillRect(x+10, y+10, 10, 25);
	}
	
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
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			long now = System.currentTimeMillis(); // 現在の時刻を取得
			
			//弾の連射速度を制限するためのif
		    if (now - lastShotTime >= shotCooldown) {
		    	GameWorld.playerBullets.add(new PlayerBullet(x,y,2,-10));
				GameWorld.playerBullets.add(new PlayerBullet(x,y,0,-10));
				GameWorld.playerBullets.add(new PlayerBullet(x,y,-2,-10));
				
				System.out.println("弾の数="+GameWorld.playerBullets.size());
				
				lastShotTime=now;
		    }
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
	
	public void keyTyped(KeyEvent e){
	}
	
	public void move() {
		super.move();
		if(x<0) x=0;
		if(x>370) x=370;
	
	}


}
