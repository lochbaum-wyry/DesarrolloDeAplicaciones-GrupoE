package domain;

public enum RateValue {
    GOOD(1), BAD(-1);

    private Integer value;

    RateValue(Integer value)
    {
        this.value = value;
    }
}
