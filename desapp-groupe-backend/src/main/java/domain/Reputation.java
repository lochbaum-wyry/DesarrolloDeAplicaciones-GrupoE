package domain;

public class Reputation {
    private Double percentageofBadRate;
    private Double percentageofGoodRate;

    public Reputation(Long countTotalRate, Long countBadRate, Long countGoodRate) {
        percentageofBadRate = (countBadRate.doubleValue() * 100) / countTotalRate;
        percentageofGoodRate = (countGoodRate.doubleValue() * 100) / countTotalRate;
    }

    public Double getPercentageofBadRate() {
        return percentageofBadRate;
    }

    public Double getPercentageofGoodRate() {
        return percentageofGoodRate;
    }

}
