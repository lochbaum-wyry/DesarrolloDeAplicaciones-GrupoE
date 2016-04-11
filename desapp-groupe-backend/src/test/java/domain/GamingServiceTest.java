package domain;

import domain.gaming_service.GamingService;
import domain.gaming_service.ranking.Ranking;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class GamingServiceTest {

    @Test
    public void test_createRanking_CreateaRanking(){
        System system = Mockito.mock(System.class);
        Mockito.when(system.getUsers()).thenReturn(new ArrayList<User>());

        GamingService gamingService = new GamingService(system);

        Ranking ranking = gamingService.createRanking(4,2016);

        Assert.assertTrue(ranking instanceof Ranking);
    }
}
