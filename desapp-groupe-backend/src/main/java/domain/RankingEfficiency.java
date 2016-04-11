package domain;

public class RankingEfficiency extends MonthlyRankingCriteria<User>{

    public RankingEfficiency(Integer year, Integer month, CriteriaOrder criteriaOrder)
    {
        super(year,month, criteriaOrder);
    }


    @Override
    public int compare(User user, User t1)
    {
        Integer valor = (getEfficiencyAvgForUser(user) < getEfficiencyAvgForUser(t1)) ? 1 : -1;
        return valor * this.getOrder().getValue();
    }

    public Float getEfficiencyAvgForUser(User user)
    {
        Float effSumUser = user.getRidesAsDriver().stream()
                .map(ride -> ride.getEfficiencyPercentage())
                .reduce(0f, Float::sum);

        return effSumUser / user.getRidesCount();
    }
}
