package domain.servicesRest;


abstract class Transformer<T> {
    protected String valueStr ;
    public Transformer(String string)
    {
        this.valueStr = string ;
    }
    abstract public T getValue();
}
