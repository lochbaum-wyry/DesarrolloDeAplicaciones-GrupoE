package domain;

public class RankingGoodRates extends MonthlyRankingCriteria{

    public RankingGoodRates(Integer year, Integer month) {
        super(year, month);

    }

    @Override
    public int compare(Object rateable1, Object rateable2) {
        Integer pointsO1 = scoring.calculatePoints(((Rateable) rateable1).ratesInMonthYear(((Rateable) rateable1), this.getMonth(), this.getYear()));
        Integer pointsO2 = scoring.calculatePoints(((Rateable) rateable2).ratesInMonthYear(((Rateable) rateable2), this.getMonth(), this.getYear()));

        return (pointsO1  > pointsO2 ) ? 1 : -1;
    }

}
