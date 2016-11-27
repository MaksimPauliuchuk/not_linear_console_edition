package not_linear.models;

public class Parametrs
{
    private double lengthT;
    private double lengthX;
    private double tFrom;
    private double tTo;
    private double xFrom;
    private double xTo;
    private int fragmentationT;
    private int fragmentationX;
    private double eSystem;

    public Parametrs()
    {
        super();
    }

    public Parametrs(double lengthT, double lengthX, double tFrom, double tTo, double xFrom, double xTo,
            int fragmentationT, int fragmentationX, double eSystem)
    {
        super();
        this.lengthT = lengthT;
        this.lengthX = lengthX;
        this.tFrom = tFrom;
        this.tTo = tTo;
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.fragmentationT = fragmentationT;
        this.fragmentationX = fragmentationX;
        this.eSystem = eSystem;
    }

    public double getLengthT()
    {
        return lengthT;
    }

    public void setLengthT(double lengthT)
    {
        this.lengthT = lengthT;
    }

    public double getLengthX()
    {
        return lengthX;
    }

    public void setLengthX(double lengthX)
    {
        this.lengthX = lengthX;
    }

    public double gettFrom()
    {
        return tFrom;
    }

    public void settFrom(double tFrom)
    {
        this.tFrom = tFrom;
    }

    public double gettTo()
    {
        return tTo;
    }

    public void settTo(double tTo)
    {
        this.tTo = tTo;
    }

    public double getxFrom()
    {
        return xFrom;
    }

    public void setxFrom(double xFrom)
    {
        this.xFrom = xFrom;
    }

    public double getxTo()
    {
        return xTo;
    }

    public void setxTo(double xTo)
    {
        this.xTo = xTo;
    }

    public int getFragmentationT()
    {
        return fragmentationT;
    }

    public void setFragmentationT(int fragmentationT)
    {
        this.fragmentationT = fragmentationT;
    }

    public int getFragmentationX()
    {
        return fragmentationX;
    }

    public void setFragmentationX(int fragmentationX)
    {
        this.fragmentationX = fragmentationX;
    }

    public double geteSystem()
    {
        return eSystem;
    }

    public void seteSystem(double eSystem)
    {
        this.eSystem = eSystem;
    }

}
