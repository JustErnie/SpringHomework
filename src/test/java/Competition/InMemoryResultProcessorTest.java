package Competition;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class InMemoryResultProcessorTest {

    @Test
    public void getTopCompetitors() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResultProcessorConfig.class);
        CsvParser csvParser = context.getBean(CsvParser.class);
        csvParser.parseCsv();
        ResultsProcessor resultsProcessor = context.getBean(ResultsProcessor.class);

        List<Competitor> actualList = resultsProcessor.getTopCompetitors(5, Gender.FEMALE, Distance.TenKm);

        Competitor competitor1 = new Competitor("Мария","Ж","10 км","51:20");
        Competitor competitor2 = new Competitor("Анна","Ж","10 км","52:20");
        Competitor competitor3 = new Competitor("Наталья","Ж","10 км","53:20");
        Competitor competitor4 = new Competitor("Светлана","Ж","10 км","54:20");
        Competitor competitor5 = new Competitor("Тамара","Ж","10 км","55:10");
        List<Competitor> expectedList = List.of(competitor1, competitor2, competitor3, competitor4, competitor5);

        for (int i = 0; i < actualList.size(); i++) {
            assertEquals(expectedList.get(i).getName(), actualList.get(i).getName());
            assertEquals(expectedList.get(i).getGender(), actualList.get(i).getGender());
            assertEquals(expectedList.get(i).getDistance(), actualList.get(i).getDistance());
            assertEquals(expectedList.get(i).getTime(), actualList.get(i).getTime());
            assertEquals(expectedList.get(i).getTimeInSeconds(), actualList.get(i).getTimeInSeconds());
        }
    }

    @Test
    public void printTopCompetitors() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResultProcessorConfig.class);
        CsvParser csvParser = context.getBean(CsvParser.class);
        csvParser.parseCsv();
        ResultsProcessor resultsProcessor = context.getBean(ResultsProcessor.class);

        PrintStream defaultOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        resultsProcessor.printTopCompetitors(10, Gender.FEMALE, Distance.TenKm);
        String actual = outputStreamCaptor.toString().trim();

        String expected = "Top 1 competitor is Мария with finish time: 51:20 \n" +
                          "Top 2 competitor is Анна with finish time: 52:20 \n" +
                          "Top 3 competitor is Наталья with finish time: 53:20 \n" +
                          "Top 4 competitor is Светлана with finish time: 54:20 \n" +
                          "Top 5 competitor is Тамара with finish time: 55:10 \n" +
                          "Top 6 competitor is Эльмира with finish time: 55:20 \n" +
                          "Top 7 competitor is Алёна with finish time: 55:30 \n" +
                          "Top 8 competitor is Анастасия with finish time: 55:40 \n" +
                          "Top 9 competitor is Диана with finish time: 55:50 \n" +
                          "Top 10 competitor is Марина with finish time: 55:55 \n".trim();
        assertEquals(expected, actual);
        System.setOut(defaultOut);
    }
}