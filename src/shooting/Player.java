package shooting;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player extends Character implements KeyListener{
	
	private long lastShotTime = 0; // 最後に弾を撃った時刻（ミリ秒）
	private final int shotCooldown = 100; // クールダウン時間（ミリ秒単位、例：300ms = 0.3秒）
	
	private boolean aPressed = false;
	private boolean dPressed = false;
	private boolean jPressed = false;
	
	

	public void draw(MyFrame f) {
		f.setColor(0, 0, 128);
		f.fillRect(x, y+20, 30, 15);
		f.setColor(0, 0,128);
		f.fillRect(x+10, y+10, 10, 25);
	}
	
	public Player(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
	}
	
	int type=1;
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				aPressed = true;
				break;
			case KeyEvent.VK_D:
				dPressed = true;
				break;
			case KeyEvent.VK_J:
				jPressed = true;
				break;
			case KeyEvent.VK_SPACE:
				// spaceキーが押された瞬間にタイプを切り替える
				type = (type == 1) ? 3 : 1;
				System.out.println("弾タイプ切り替え: type=" + type);
				break;
			case KeyEvent.VK_ENTER:
				System.out.println("Enterキーが押されました");
				GameWorld.enterPressed = true;
				break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				aPressed = false;
				break;
			case KeyEvent.VK_D:
				dPressed = false;
				break;
			
			case KeyEvent.VK_J:
				jPressed = false;
				break;
		}
	}
	
	
	public void keyTyped(KeyEvent e) {
	}

	public void move() {
		// 移動処理
		vx = 0;
		if (aPressed) {
			vx = -5;
		}
		if (dPressed) {
			vx = 5;
		}
		super.move();

		// 移動範囲制限
		if (x < 0) x = 0;
		if (x > 370) x = 370;
		
		
		// 射撃処理
		if (jPressed) {
			long now = System.currentTimeMillis();
			if (type == 3 && now - lastShotTime >= shotCooldown) {
				GameWorld.playerBullets.add(new PlayerBullet(x, y, 2, -10));
				GameWorld.playerBullets.add(new PlayerBullet(x, y, 0, -10));
				GameWorld.playerBullets.add(new PlayerBullet(x, y, -2, -10));
				lastShotTime = now;
			} else if (type == 1 && now - lastShotTime >= shotCooldown / 3) {
				GameWorld.playerBullets.add(new PlayerBullet(x, y, 0, -10));
				lastShotTime = now;
			}
		}
	}
	
	
}
