package domain;

public class RankingRateCount<T extends Rateable> extends MonthlyRankingCriteria<T>
{

    public RankingRateCount(Integer year, Integer month, CriteriaOrder order) {
        super(year, month, order);
    }


    @Override
    public int compare(T o1, T o2)
    {
        /*

        Integer pointsO1 = scoring.calculatePoints(rateable1.ratesInMonthYear(rateable1, this.getMonth(), this.getYear()));
        Integer pointsO2 = scoring.calculatePoints(rateable2.ratesInMonthYear(rateable2, this.getMonth(), this.getYear()));

        return (pointsO1  > pointsO2 ) ? 1 : -1 * getOrder();
        */
        //TODO: Arreglar el algo para que sea la diferencia. y que filtre los rates por mes y anio
        int valor = (o1.getGoodRateCount() > o2.getGoodRateCount()) ? 1 : -1;
        return Math.multiplyExact(valor,getOrder().getValue());
    }
}
