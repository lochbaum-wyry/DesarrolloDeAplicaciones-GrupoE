package domain;

import java.lang.*;
import java.util.ArrayList;
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
        Long countTotalRate = getTotalRateCount();
        Long countGoodRate = getGoodRateCount();

        return ((float)countGoodRate* 100) / countTotalRate;
    }

    default Float getBadRatesPercentage()
    {
        Long countTotalRate = getTotalRateCount();
        Long countBadRate = getBadRateCount();

        return ((float)countBadRate * 100) / countTotalRate;
    }

    default long getGoodRateCount() {
        return this.getGoodRates().stream().count();
    }

    default long getBadRateCount()
    {
        return this.getBadRates().stream().count();
    }

    default long getTotalRateCount()
    {
        return this.getRates().stream().count();
    }

}
