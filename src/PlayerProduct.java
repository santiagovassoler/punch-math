import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerProduct extends Product{

	float dx=1;
	private Animation animation;
	private static final int COLUMNS  =   5;
	private static final int COUNT    =  5;
	private static final int OFFSET_X =  0;
	private static final int OFFSET_Y =  0;
	private static final int WIDTH    = 95;
	private static final int HEIGHT   = 150;
	Image punchImage;
	
	public PlayerProduct(String imageFile, double x, double y) {
		super(imageFile, x, y);
		animate();
	}

	public void animate()
	{
		this.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

	     animation = new SpriteAnimation(
	            this,
	            Duration.millis(800),
	            2, 2,
	            OFFSET_X, OFFSET_Y,
	            WIDTH, HEIGHT
	    );
	    this.setY(255);
	    this.setX(300);
	    animation.setCycleCount(Animation.INDEFINITE);
	    animation.play();
	    
	}
	
	public void punch()
	{
		this.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		punchImage = new Image(PlayerProduct.class.getResource("resources/hero_sprite.png").toExternalForm());
		this.setImage(punchImage);
		animation = new SpriteAnimation(
	            this,
	            Duration.millis(400),
	            COUNT, COLUMNS,
	            OFFSET_X, OFFSET_Y,
	            WIDTH, HEIGHT
	    );
	    this.setY(255);
	    this.setX(300);
	    animation.setCycleCount(1);
	    animation.play();
	    
	    animation.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				animation.stop();
			}
		});
	  	
	}
	
	public boolean intersects(PlayerProduct spr) {
        return spr.getBoundsInLocal().intersects(this.getBoundsInLocal());
    }
	
}
