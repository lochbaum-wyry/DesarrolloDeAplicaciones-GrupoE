package domain;


public enum CriteriaOrder
{
    ASC(1), DESC(-1);

    private int
            value;

    CriteriaOrder(Integer value)
        {
        this.value = value;
        }

    public int getValue()
    {
        return value;
    }
}
