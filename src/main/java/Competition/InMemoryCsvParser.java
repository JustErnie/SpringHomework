package Competition;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class InMemoryCsvParser implements CsvParser {

    @Value("classpath:Results.csv")
    private Resource resource;

    private final List<Competitor> competitorList;

    public InMemoryCsvParser(List<Competitor> competitorList) {
        this.competitorList = competitorList;
    }

    public List<Competitor> getCompetitorList() {
        return competitorList;
    }

    public void parseCsv(){
        try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                competitorList.add(new Competitor(split[0], split[1], split[2], split[3]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
