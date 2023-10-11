package Competition;

import java.util.List;

public interface ResultsProcessor {
    List<Competitor> getTopCompetitors(int number, Gender gender, Distance distance);

    void printTopCompetitors(int number, Gender gender, Distance distance);

}
