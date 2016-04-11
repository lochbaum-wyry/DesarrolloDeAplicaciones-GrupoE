package domain;

import java.lang.*;
import java.util.List;
import java.util.stream.Collectors;

public interface Rateable
{
    // TODO: java 8 cannot assign a value to final variable  (Integer totalRateCount)

    default void addRate(Rate rate)
    {
        getRates().add(rate);
    }

    public List<Rate> getRates();

    default List<Rate> getBadRates()
    {
        return getRates().stream().filter(rate -> rate.getValue().equals(RateValue.BAD)).collect(Collectors.toList());
    }

    default List<Rate>  getGoodRates()
    {
        return getRates().stream().filter(rate -> rate.getValue().equals(RateValue.GOOD)).collect(Collectors.toList());
    }

    default Boolean rateIsOfMonthYear(Rate rate, Integer month, Integer year)
    {
        return (rate.getMonth().equals(month)) && (rate.getYear().equals(year));
    }

    default List<Rate> ratesInMonthYear(Rateable user, Integer month, Integer year)
    {
        return user.getRates().stream().filter(rate -> this.rateIsOfMonthYear(rate, month, year)).collect(Collectors.toList());
    }

    default Float getGoodRatesPercentage()
    {
        Integer countTotalRate = getTotalRateCount();
        Integer countGoodRate = getGoodRateCount();

        return ((float)(countGoodRate * 100)) / countTotalRate;
    }

    default Float getBadRatesPercentage()
    {
        Integer countTotalRate = getTotalRateCount();
        Integer countBadRate = getBadRateCount();

        return ((float)countBadRate * 100) / countTotalRate;
    }

    default Integer getGoodRateCount() {
        return (Integer)(int)this.getGoodRates().stream().count();
    }

    default Integer getBadRateCount()
    {
        return (Integer)(int)this.getBadRates().stream().count();
    }

    default Integer getTotalRateCount()
    {
        return (Integer)(int)this.getRates().stream().count();
    }

}
