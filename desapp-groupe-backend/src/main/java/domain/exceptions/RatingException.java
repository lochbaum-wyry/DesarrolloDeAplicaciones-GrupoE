package domain.exceptions;

public class RatingException extends SubiQueTeLlevoException{
    public RatingException() {
    }

    public RatingException(String var1) {
        super(var1);
    }

    public RatingException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public RatingException(Throwable var1) {
        super(var1);
    }

    protected RatingException(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4);
    }
}
