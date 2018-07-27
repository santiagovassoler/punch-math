import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class EnemyProduct extends Product {
	
	private Animation animation;
	private static final int COLUMNS  =   4;
	private static final int COUNT    =  4;
	private static final int OFFSET_X =  0;
	private static final int OFFSET_Y =  0;
	private static final int WIDTH    = 170;
	private static final int HEIGHT   = 150;
	
	public EnemyProduct(String imageFile, double x, double y) {
		super(imageFile, x, y);
		animate();
		run();	
	}

	public void run(){
		TranslateTransition t = new TranslateTransition(Duration.millis(4000), this);
		t.setByX(-800);
		t.setCycleCount(1);
		t.play();
	}
	
	public void animate()
	{
		this.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
	     animation = new SpriteAnimation(
	            this,
	            Duration.millis(800),
	            COUNT, COLUMNS,
	            OFFSET_X, OFFSET_Y,
	            WIDTH, HEIGHT
	    );
	    this.setY(215);
	    animation.setCycleCount(Animation.INDEFINITE);
	    animation.play();
	}
	
	public boolean intersects(EnemyProduct spr) {
        return spr.getBoundsInLocal().intersects(this.getBoundsInLocal());
    }
}