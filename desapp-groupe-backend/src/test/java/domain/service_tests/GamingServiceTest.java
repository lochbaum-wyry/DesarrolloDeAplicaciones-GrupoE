package domain.service_tests;

import domain.services.MonthlyRanking;
import domain.services.System;
import domain.services.gaming_service.GamingService;
import domain.services.ProductsService;
import domain.services.gaming_service.scoring_service.ScoringService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class GamingServiceTest extends AbstractServiceTest
{
    @Autowired
    GamingService gamingService ;

    @Test
    public void test_createRanking_CreateaRanking()
    {
        MonthlyRanking ranking = gamingService.createRanking(4,2016);

        Assert.assertTrue(ranking instanceof MonthlyRanking);
    }


}
