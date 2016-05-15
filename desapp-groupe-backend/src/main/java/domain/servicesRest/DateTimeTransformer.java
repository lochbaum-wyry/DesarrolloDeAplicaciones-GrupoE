package domain.servicesRest;

import org.joda.time.DateTime;


public class DateTimeTransformer extends Transformer<DateTime>
{
    public DateTimeTransformer(String string)
    {
        super(string);
    }

    @Override
    public DateTime getValue()
    {
        return (new DateTime(Long.parseLong(valueStr)));
    }
}
