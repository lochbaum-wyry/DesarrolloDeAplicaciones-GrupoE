package domain;

abstract class MonthlyRankingCriteria extends RankingCriteria{
    private Integer year;
    private Integer month;

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public MonthlyRankingCriteria(Integer year,Integer month){
        this.year = year;
        this.month = month;
    }
}
