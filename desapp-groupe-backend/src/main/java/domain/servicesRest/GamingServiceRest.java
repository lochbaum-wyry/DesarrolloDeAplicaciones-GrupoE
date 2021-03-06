package domain.servicesRest;



import domain.services.MonthlyRanking;
import domain.services.gaming_service.GamingService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("gaming")
@Service
public class GamingServiceRest {

    GamingService gamingService;

    public GamingServiceRest(GamingService gamingService) {
        this.gamingService = gamingService;
    }

    public GamingServiceRest(){}

    @GET
    @Path("ranking")
    @Produces("application/json")
    public MonthlyRanking ranking(){
        return gamingService.createRanking(new DateTime().getMonthOfYear(),new DateTime().getYearOfEra());
    }

    @GET
    @Path("getRankingForDate/{year}/{month}")
    @Produces("application/json")
    public MonthlyRanking getRankingForDate(@PathParam("year") final Integer year,@PathParam("month") final Integer month){
        return gamingService.createRanking(month,year);
    }
}
