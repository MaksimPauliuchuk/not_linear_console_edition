package not_linear.utils;

import java.math.BigDecimal;
import java.math.MathContext;

public class TridiagonalMatrixSolution {
	public static double[] Solve(final double[][] matrix, final double[] f) {
		double[][] AdditionalValues = new double[2][matrix.length];
		AdditionalValues[0][0] = -matrix[0][1] / matrix[0][0];
		AdditionalValues[1][0] = f[0] / matrix[0][0];
		for (int i = 1; i < matrix.length; i++) {
			if (!(i == matrix.length - 1)) {
				AdditionalValues[0][i] = -matrix[i][i + 1]
						/ (matrix[i][i] + matrix[i][i - 1] * AdditionalValues[0][i - 1]);
			}
			AdditionalValues[1][i] = (-matrix[i][i - 1] * AdditionalValues[1][i - 1] + f[i])
					/ (matrix[i][i] + matrix[i][i - 1] * AdditionalValues[0][i - 1]);
		}

		double[] x = new double[matrix.length];
		x[x.length - 1] = AdditionalValues[1][x.length - 1];
		for (int i = x.length - 2; i >= 0; i--) {
			x[i] = AdditionalValues[0][i] * x[i + 1] + AdditionalValues[1][i];
		}

		return x;
	}

	public static double[] Solve(final double[] left, final double[] center, final double[] right, final double[] f) {
		double[][] AdditionalValues = new double[2][center.length];

		AdditionalValues[0][0] = -right[0] / center[0];
		AdditionalValues[1][0] = f[0] / center[0];
		for (int i = 1; i < center.length; i++) {
			if (!(i == center.length - 1)) {
				AdditionalValues[0][i] = -right[i] / (center[i] + left[i - 1] * AdditionalValues[0][i - 1]);
			}
			AdditionalValues[1][i] = (-left[i - 1] * AdditionalValues[1][i - 1] + f[i])
					/ (center[i] + left[i - 1] * AdditionalValues[0][i - 1]);
		}
		double[] x = new double[center.length];
		x[x.length - 1] = AdditionalValues[1][x.length - 1];
		for (int i = x.length - 2; i >= 0; i--) {
			x[i] = AdditionalValues[0][i] * x[i + 1] + AdditionalValues[1][i];
		}

		return x;

	}

	public static double[] SolveBD(final double[] left, final double[] center, final double[] right, final double[] f) {
		BigDecimal[] leftBD = new BigDecimal[left.length];
		BigDecimal[] centerBD = new BigDecimal[center.length];
		BigDecimal[] rightBD = new BigDecimal[right.length];
		BigDecimal[] fBD = new BigDecimal[f.length];

		for (int i = 0; i < leftBD.length; i++) {
			leftBD[i] = new BigDecimal(left[i], MathContext.UNLIMITED);
			centerBD[i] = new BigDecimal(center[i], MathContext.UNLIMITED);
			rightBD[i] = new BigDecimal(right[i], MathContext.UNLIMITED);
			fBD[i] = new BigDecimal(f[i], MathContext.UNLIMITED);
		}
		centerBD[leftBD.length] = new BigDecimal(center[left.length]);
		fBD[leftBD.length] = new BigDecimal(f[leftBD.length]);

		BigDecimal[][] AdditionalValues = new BigDecimal[2][center.length];
		AdditionalValues[0][0] = rightBD[0].negate().divide(centerBD[0]);
		AdditionalValues[1][0] = fBD[0].divide(centerBD[0]);

		for (int i = 1; i < center.length; i++) {
			if (!(i == center.length - 1)) {
				AdditionalValues[0][i] = rightBD[i].negate().divide(
						centerBD[i].add(leftBD[i - 1].multiply(AdditionalValues[0][i - 1])), MathContext.DECIMAL128);
			}
			AdditionalValues[1][i] = leftBD[i - 1].negate().multiply(AdditionalValues[1][i - 1]).add(fBD[i]).divide(
					centerBD[i].add(leftBD[i - 1].multiply(AdditionalValues[0][i - 1])), MathContext.DECIMAL128);
		}

		BigDecimal[] x = new BigDecimal[center.length];
		x[x.length - 1] = AdditionalValues[1][x.length - 1];
		for (int i = x.length - 2; i >= 0; i--) {
			x[i] = AdditionalValues[0][i].multiply(x[i + 1]).add(AdditionalValues[1][i]);
		}

		double[] answ = new double[center.length];
		for (int i = 0; i < answ.length; i++) {
			answ[i] = x[i].doubleValue();
		}

		return answ;
	}

	public static void Print(final double[][] matrix) {
		System.out.println("Matrix demension is " + matrix.length + "x" + matrix[0].length + ".");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%9.3f ", matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void Print(final double[] f) {
		System.out.println("Demension is " + f.length + ".");
		for (int i = 0; i < f.length; i++) {
			System.out.printf("%9.13f ", f[i]);
		}
		System.out.println();
		System.out.println();
	}

	public static void Print(final double[][] matrix, final double[] f) {
		System.out.println("Matrix demension is " + matrix.length + "x" + matrix[0].length + ".");
		System.out.println("Free row demension is " + f.length + ".");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%6.2f ", matrix[i][j]);
			}
			System.out.print(" | ");
			System.out.printf("%7.2f ", f[i]);
			System.out.println();
		}
		System.out.println();
	}
}
