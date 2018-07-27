public class Factory implements FactoryIF{
	
	@Override
	public Product createProduct(String discrim, double x, double y)
	{
		if (discrim.equals("Enemy"))
			return (new EnemyProduct("dude.png", x, y));
		else if(discrim.equals("Player"))
			return (new PlayerProduct("hero_sprite.png", x, y));
		else
			return null;
	}
}
