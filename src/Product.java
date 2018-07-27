import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Product extends ImageView {
	
	public Product(String imageFile, double x, double y) {
		this.setX(x);
		this.setY(y);
		this.setImage(new Image(Product.class.getResource("resources/"+imageFile).toExternalForm()));
		this.setFitHeight(170);
		this.setFitHeight(150);
	}
	
	public abstract void animate();
	
}
