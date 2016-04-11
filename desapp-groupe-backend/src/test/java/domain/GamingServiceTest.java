package domain;

import domain.gaming_service.GamingService;
import domain.gaming_service.product_service.ProductsService;
import domain.gaming_service.ranking.Ranking;
import domain.gaming_service.scoring_service.ScoringService;
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

    @Test
    public void test_getProductsService(){
        System system = Mockito.mock(System.class);

        GamingService gamingService = new GamingService(system);

        Assert.assertTrue(gamingService.getProductsService() instanceof ProductsService);
    }

    @Test
    public void test_geScoringService(){
        System system = Mockito.mock(System.class);

        GamingService gamingService = new GamingService(system);

        Assert.assertTrue(gamingService.getScoringService() instanceof ScoringService);
    }
}
