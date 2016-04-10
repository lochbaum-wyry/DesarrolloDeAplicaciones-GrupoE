package domain;

import domain.Rate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Rateable
{
    // TODO: java 8 cannot assign a value to final variable  (Integer totalRateCount)
    List<Rate> rates = new ArrayList<Rate>();

    default void addRate(Rate rate)
    {
        this.rates.add(rate);
    }

    default List<Rate> getRates(){
        return rates;
    }

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
        return rate.getMonth() == month && rate.getYear() == year;
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
