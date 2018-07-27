import java.util.Random;

public class RandomExpressionGenerator {
	
	private Random rm;
	
	public RandomExpressionGenerator(){
		rm = new Random();
	}
	
	private String randonOperator()
	{
		String[] operators = {"+" , "-" ,"*" , "/"};
		int i = rm.nextInt(4);
		return operators[i];	
	}
	
	private  String randomNumbers()
	{
		return Integer.toString(rm.nextInt((9 - 0 + 1)+0));	 
	}
	
	public String expression(int level){
		if(level==1) return randomNumbers()+ " "+ randonOperator() + " " + randomNumbers();
	     else return " " + expression(level-1) + " "+ randonOperator() + " " + randomNumbers();
	}
	
}
