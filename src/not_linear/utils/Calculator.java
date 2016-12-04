package not_linear.utils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculator {
	public static double mu_left(final String function, final double x, final double t) {
		Expression expression = new ExpressionBuilder(function).variables("x", "t").build().setVariable("x", x)
				.setVariable("t", t);
		return expression.evaluate();
	}

	public static double mu_down(final String function, final double x, final double t) {
		Expression expression = new ExpressionBuilder(function).variables("x", "t").build().setVariable("x", x)
				.setVariable("t", t);
		return expression.evaluate();
	}

	public static double mu_right(final String function, final double x, final double t) {
		Expression expression = new ExpressionBuilder(function).variables("x", "t").build().setVariable("x", x)
				.setVariable("t", t);
		return expression.evaluate();
	}

	public static double k(final String function, final double x, final double t, final double u) {
		Expression expression = new ExpressionBuilder(function).variables("x", "t", "u").build().setVariable("x", x)
				.setVariable("t", t).setVariable("u", u);
		return expression.evaluate();
	}

	public static double k_u(final String function, final double x, final double t, final double u) {
		Expression expression = new ExpressionBuilder(function).variables("x", "t", "u").build().setVariable("x", x)
				.setVariable("t", t).setVariable("u", u);
		return expression.evaluate();
	}

	public static double g(final String function, final double x, final double t, final double u) {
		Expression expression = new ExpressionBuilder(function).variables("x", "t", "u").build().setVariable("x", x)
				.setVariable("t", t).setVariable("u", u);
		return expression.evaluate();
	}

	public static double real(final String function, final double x, final double t) {
		Expression expression = new ExpressionBuilder(function).variables("x", "t").build().setVariable("x", x)
				.setVariable("t", t);
		return expression.evaluate();
	}

	public static double norma(double[] vector1, double[] vector2) {
		double norma = 0.0;
		for (int i = 0; i < vector1.length; i++) {
			norma += Math.pow(vector1[i] - vector2[i], 2);
		}
		norma = Math.sqrt(norma);
		return norma;
	}
}
