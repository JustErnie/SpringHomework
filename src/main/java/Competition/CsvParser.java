package Competition;

import java.util.List;

public interface CsvParser {

    void parseCsv();

    List<Competitor> getCompetitorList();
}
