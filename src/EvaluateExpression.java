import java.util.Stack;

public class EvaluateExpression {
	
	public Double evaluate(String expression)
	{
		char[] tokens = expression.toCharArray();
		Stack<Double> values = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++)
		{
			if (tokens[i] == ' ')
				continue;
			if (tokens[i] >= '0' && tokens[i] <= '9')
			{
				StringBuffer sbuf = new StringBuffer();
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);
					values.push(Double.parseDouble(sbuf.toString()));
			}
			else if (tokens[i] == '(')
				operators.push(tokens[i]);
			else if (tokens[i] == ')')
			{
				while (operators.peek() != '(')
				values.push(applyOp(operators.pop(), values.pop(), values.pop()));
				operators.pop();
			}
			else if (tokens[i] == '+' || tokens[i] == '-' ||
					tokens[i] == '*' || tokens[i] == '/')
			{
				while (!operators.empty() && hasPrecedence(tokens[i], operators.peek()))
				values.push(applyOp(operators.pop(), values.pop(), values.pop()));
				operators.push(tokens[i]);
			}
		}
		while (!operators.empty())
			values.push(applyOp(operators.pop(), values.pop(), values.pop()));
			return values.pop();
	}
	public static boolean hasPrecedence(char op1, char op2)
	{
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}
	public static Double applyOp(char operators, double b, double a)
	{
		switch (operators)
		{
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		}
		return 0.0d;
	}
}
