package domain.gaming_service.ranking;

abstract class MonthlyRankingCriteria<T> extends RankingCriteria<T>{
    private Integer year;
    private Integer month;
    private CriteriaOrder order;

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public CriteriaOrder getOrder() {
        return order;
    }

    public MonthlyRankingCriteria(Integer year,Integer month,CriteriaOrder order){
        this.year = year;
        this.month = month;
        this.order = order;
    }
}
