package domain.gaming_service.ranking;

import domain.Rateable;

public class RankingRateCount<T extends Rateable> extends MonthlyRankingCriteria<T>
{

    public RankingRateCount(Integer year, Integer month, CriteriaOrder order) {
        super(year, month, order);
    }


    @Override
    public int compare(T o1, T o2)
    {
        //TODO: Arreglar el algo para que sea la diferencia. y que filtre los rates por mes y anio
        int valor = (o1.getGoodRateCount() > o2.getGoodRateCount()) ? -1 : 1;
        return Math.multiplyExact(valor,getOrder().getValue());
    }
}
