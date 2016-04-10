package domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class GamingSystemTest {

    @Test
    public void test_createRanking_CreateaRanking(){
        System system = Mockito.mock(System.class);
        Mockito.when(system.getUsers()).thenReturn(new ArrayList<User>());

        GamingSystem gamingSystem = new GamingSystem(system);

        Ranking ranking = gamingSystem.createRanking(4,2016);

        Assert.assertTrue(ranking instanceof Ranking);
    }
}
