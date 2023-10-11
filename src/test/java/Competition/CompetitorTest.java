package Competition;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompetitorTest {

    @Test
    public void parseTime() {
        Competitor competitor = new Competitor("Вася", "М", "5 км", "51:20");
        assertEquals(3080, competitor.getTimeInSeconds());
    }

    @Test
    public void parseDistance() {
        Competitor competitor = new Competitor("Вася", "М", "5 км", "51:20");
        assertEquals(Distance.FiveKm, competitor.getDistance());
    }

    @Test
    public void parseGender() {
        Competitor competitor = new Competitor("Вася", "М", "5 км", "51:20");
        assertEquals(Gender.MALE, competitor.getGender());
    }
}