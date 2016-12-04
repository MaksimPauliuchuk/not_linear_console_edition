package not_linear.models;

public class Parametrs {
	private double lengthT;
	private double lengthX;
	private double tFrom;
	private double tTo;
	private double xFrom;
	private double xTo;
	private int fragmentationT;
	private int fragmentationX;
	private double eSystemSquare;
	private double eSystemCube;
	private double eRunge;
	private int maxIterProgonka;
	private int maxIterBigDecimal;

	public double getLengthT() {
		return lengthT;
	}

	public void setLengthT(double lengthT) {
		this.lengthT = lengthT;
	}

	public double getLengthX() {
		return lengthX;
	}

	public void setLengthX(double lengthX) {
		this.lengthX = lengthX;
	}

	public double gettFrom() {
		return tFrom;
	}

	public void settFrom(double tFrom) {
		this.tFrom = tFrom;
	}

	public double gettTo() {
		return tTo;
	}

	public void settTo(double tTo) {
		this.tTo = tTo;
	}

	public double getxFrom() {
		return xFrom;
	}

	public void setxFrom(double xFrom) {
		this.xFrom = xFrom;
	}

	public double getxTo() {
		return xTo;
	}

	public void setxTo(double xTo) {
		this.xTo = xTo;
	}

	public int getFragmentationT() {
		return fragmentationT;
	}

	public void setFragmentationT(int fragmentationT) {
		this.fragmentationT = fragmentationT;
	}

	public int getFragmentationX() {
		return fragmentationX;
	}

	public void setFragmentationX(int fragmentationX) {
		this.fragmentationX = fragmentationX;
	}

	public double geteSystemSquare() {
		return eSystemSquare;
	}

	public void seteSystemSquare(double eSystemSquare) {
		this.eSystemSquare = eSystemSquare;
	}

	public double geteSystemCube() {
		return eSystemCube;
	}

	public void seteSystemCube(double eSystemCube) {
		this.eSystemCube = eSystemCube;
	}

	public double geteRunge() {
		return eRunge;
	}

	public void seteRunge(double eRunge) {
		this.eRunge = eRunge;
	}

	public int getMaxIterProgonka() {
		return maxIterProgonka;
	}

	public void setMaxIterProgonka(int maxIterProgonka) {
		this.maxIterProgonka = maxIterProgonka;
	}

	public int getMaxIterBigDecimal() {
		return maxIterBigDecimal;
	}

	public void setMaxIterBigDecimal(int maxIterBigDecimal) {
		this.maxIterBigDecimal = maxIterBigDecimal;
	}
}
