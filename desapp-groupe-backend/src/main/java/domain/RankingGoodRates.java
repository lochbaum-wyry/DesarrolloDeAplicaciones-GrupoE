package domain;

public class RankingGoodRates extends MonthlyRankingCriteria{

    public RankingGoodRates(Integer year, Integer month) {
        super(year, month);
    }

    @Override
    public int compare(Object obj1, Object obj2) {
        /*
        Rateable rateable1 = ((Rateable) obj1);
        Rateable rateable2 = ((Rateable) obj2);

        Integer pointsO1 = scoring.calculatePoints(rateable1.ratesInMonthYear(rateable1, this.getMonth(), this.getYear()));
        Integer pointsO2 = scoring.calculatePoints(rateable2.ratesInMonthYear(rateable2, this.getMonth(), this.getYear()));

        return (pointsO1  > pointsO2 ) ? 1 : -1;
        */
        return 0;
    }

}
