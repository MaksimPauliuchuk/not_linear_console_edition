package not_linear.models;

public class ParametrsString
{
    private String tFrom;
    private String tTo;
    private String xFrom;
    private String xTo;
    private String fragmentationT;
    private String fragmentationX;
    private String eSystem;

    public ParametrsString()
    {

    }

    public ParametrsString(String tFrom, String tTo, String xFrom, String xto, String fragmentationT,
            String fragmentationX, String eSystem)
    {
        super();
        this.tFrom = tFrom;
        this.tTo = tTo;
        this.xFrom = xFrom;
        this.xTo = xto;
        this.fragmentationT = fragmentationT;
        this.fragmentationX = fragmentationX;
        this.eSystem = eSystem;
    }

    public String gettFrom()
    {
        return tFrom;
    }

    public void settFrom(String tFrom)
    {
        this.tFrom = tFrom;
    }

    public String gettTo()
    {
        return tTo;
    }

    public void settTo(String tTo)
    {
        this.tTo = tTo;
    }

    public String getxFrom()
    {
        return xFrom;
    }

    public void setxFrom(String xFrom)
    {
        this.xFrom = xFrom;
    }

    public String getxTo()
    {
        return xTo;
    }

    public void setxTo(String xTo)
    {
        this.xTo = xTo;
    }

    public String getFragmentationT()
    {
        return fragmentationT;
    }

    public void setFragmentationT(String fragmentationT)
    {
        this.fragmentationT = fragmentationT;
    }

    public String getFragmentationX()
    {
        return fragmentationX;
    }

    public void setFragmentationX(String fragmentationX)
    {
        this.fragmentationX = fragmentationX;
    }

    public String geteSystem()
    {
        return eSystem;
    }

    public void seteSystem(String eSystem)
    {
        this.eSystem = eSystem;
    }

}
