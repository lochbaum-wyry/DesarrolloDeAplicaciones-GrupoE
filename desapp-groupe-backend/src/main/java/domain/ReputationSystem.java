package domain;

public class ReputationSystem {

    public Reputation calculateReputation(User user){
        Long countTotalRate = user.getRates().stream().count();
        Long countBadRate = user.getBadRates().stream().count();
        Long countGoodRate = user.getGoodRates().stream().count();

        return new Reputation(countTotalRate,countBadRate,countGoodRate);
    }
}
