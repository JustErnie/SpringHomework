package Competition;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class InMemoryResultProcessor implements ResultsProcessor {
    CsvParser csvParser;

    public InMemoryResultProcessor(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    @Override
    public List<Competitor> getTopCompetitors(int number, Gender gender, Distance distance) {
        return csvParser.getCompetitorList().stream()
                .filter(competitor -> competitor.getGender() == gender)
                .filter(competitor -> competitor.getDistance() == distance)
                .sorted(Comparator.comparing(Competitor::getTimeInSeconds))
                .limit(number)
                .toList();
    }

    @Override
    public void printTopCompetitors(int number, Gender gender, Distance distance) {
        List<Competitor> topCompetitors = getTopCompetitors(number, gender, distance);
        for (int i = 0; i < number; i++) {
            if (i >= topCompetitors.size()) break;
            System.out.printf("Top %s competitor is %s with finish time: %s \n",
                    i + 1, topCompetitors.get(i).getName(), topCompetitors.get(i).getTime());
        }
    }
}


