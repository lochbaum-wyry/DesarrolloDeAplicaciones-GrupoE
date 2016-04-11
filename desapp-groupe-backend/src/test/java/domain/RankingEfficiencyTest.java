package domain;

import domain.gaming_service.ranking.CriteriaOrder;
import domain.gaming_service.ranking.RankingEfficiency;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class RankingEfficiencyTest {

    @Test
    public void test_compare_return1WhenUser1HaveManyEfficiency(){

        Ride ride1 = Mockito.mock(Ride.class);
        Mockito.when(ride1.getEfficiencyPercentage()).thenReturn(70f);
        Ride ride2 = Mockito.mock(Ride.class);
        Mockito.when(ride2.getEfficiencyPercentage()).thenReturn(59f);

        RankingEfficiency rankingEfficiency = new RankingEfficiency(2016,2, CriteriaOrder.ASC);

        List<Ride> listContainRide1 = new ArrayList<Ride>();
        listContainRide1.add(ride1);

        List<Ride> listContainRide2 = new ArrayList<Ride>();
        listContainRide2.add(ride2);

        User user1 = Mockito.mock(User.class);
        Mockito.when(user1.getRidesAsDriver()).thenReturn(listContainRide1);
        Mockito.when(user1.getRidesCount()).thenReturn(1);

        User user2 = Mockito.mock(User.class);
        Mockito.when(user2.getRidesAsDriver()).thenReturn(listContainRide2);
        Mockito.when(user2.getRidesCount()).thenReturn(1);

        Assert.assertEquals(rankingEfficiency.compare(user1,user2),1);
    }

    @Test
    public void test_getEfficiencyAvgForUser_returnThePercentageOfAuserWithaRide(){

        Ride ride1 = Mockito.mock(Ride.class);
        Mockito.when(ride1.getEfficiencyPercentage()).thenReturn(70f);
        Ride ride2 = Mockito.mock(Ride.class);
        Mockito.when(ride2.getEfficiencyPercentage()).thenReturn(59f);

        List<Ride> list = new ArrayList<Ride>();
        list.add(ride1);
        list.add(ride2);

        RankingEfficiency rankingEfficiency = new RankingEfficiency(2016,2, CriteriaOrder.ASC);

        User user = Mockito.mock(User.class);
        Mockito.when(user.getRidesAsDriver()).thenReturn(list);
        Mockito.when(user.getRidesCount()).thenReturn(list.size());

        Assert.assertEquals(rankingEfficiency.getEfficiencyAvgForUser(user).intValue(),64);
    }
}
