package shooting;

public class EnemyBase extends Enemy{
	public EnemyBase(double x,double y,double vx,double vy) {
		super(x,y,vx,vy);
		life=30+GameWorld.stage*20;//stageが進むほど耐久値up
		score=30;//+GameWorld.stage*20;
	}
	public void move() {
		super.move();
		if(x>300) vx=-GameWorld.stage;
		if(x<100) vx=GameWorld.stage;
		if(Math.random()<0.01) {
			GameWorld.enemies.add(new StraightEnemy(x,y,0,1+GameWorld.stage));
		}
		if(Math.random()<0.01) {
			GameWorld.enemies.add(new RandomEnemy(x,y,GameWorld.stage,GameWorld.stage));
		}
		if(Math.random()<0.01) {
			GameWorld.enemies.add(new CurveEnemy(x,y,GameWorld.stage,GameWorld.stage));
		}
		if(Math.random()<0.01) {
			GameWorld.enemies.add(new DropEnemy(x,y,0,GameWorld.stage));
		}
		if(Math.random()<0.01) {//左右ランダムに発射
			double randomVx = (Math.random() +- 5 +-GameWorld.stage) ; 
			int randomVy = (int)(Math.random() * 4) + 1;
		    GameWorld.enemies.add(new ReflectionEnemy(x, y, randomVx, randomVy));
		}
	}
	
	public void draw(MyFrame f) {
		f.setColor(0, 128,0);
		f.fillOval(x, y, 32, 32);
		f.setColor(200, 200,200);
		f.fillOval(x-16, y+8,64,16);
	}

}
