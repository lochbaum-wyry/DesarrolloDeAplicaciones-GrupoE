package domain.servicesRest;


abstract class Transformer<T> {
    public String getValueStr() {
        return valueStr;
    }

    protected String valueStr ;
    public Transformer(String string)
    {
        this.valueStr = string ;
    }
    abstract public T getValue();
}
