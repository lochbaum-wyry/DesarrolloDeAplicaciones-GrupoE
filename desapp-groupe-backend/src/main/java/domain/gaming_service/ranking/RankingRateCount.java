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
        int totalO1 = o1.getGoodRateCount() - o1.getBadRateCount();
        int totalO2 = o2.getGoodRateCount() - o2.getBadRateCount();
        int valor = totalO1 > totalO2 ? -1 : 1;
        return Math.multiplyExact(valor,getOrder().getValue());
    }
}
