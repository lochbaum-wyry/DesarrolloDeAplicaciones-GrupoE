package domain;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankingRateCountTest
{
    @Test
    public void test_compare_returnsPositiveWhenAIsBeforeB()
    {
        RankingRateCount<User> rankCrit = new RankingRateCount(2015, 12, CriteriaOrder.ASC);

        User userA = mock(User.class);
        when(userA.getGoodRateCount()).thenReturn(3l);

        User userB = mock(User.class);
        when(userB.getGoodRateCount()).thenReturn(1l);

        Assert.assertEquals(1,rankCrit.compare(userA,userB));
    }

    @Test
    public void test_compare_returnsNegativeWhenAIsBeforeB()
    {
        RankingRateCount<User> rankCrit = new RankingRateCount(2015, 12, CriteriaOrder.DESC);

        User userA = mock(User.class);
        when(userA.getGoodRateCount()).thenReturn(3l);

        User userB = mock(User.class);
        when(userB.getGoodRateCount()).thenReturn(1l);

        Assert.assertEquals(-1,rankCrit.compare(userA,userB));
    }
}
