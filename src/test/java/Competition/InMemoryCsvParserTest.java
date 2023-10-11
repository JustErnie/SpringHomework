package Competition;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class InMemoryCsvParserTest {

    @Test
    public void getCompetitorList() {
        Competitor competitor1 = new Competitor("TestName1", "М", "5 км", "23:45");
        Competitor competitor2 = new Competitor("TestName2", "Ж", "5 км", "22:45");
        Competitor competitor3 = new Competitor("TestName3", "М", "10 км", "21:45");
        Competitor competitor4 = new Competitor("TestName4", "Ж", "10 км", "20:45");
        List<Competitor> competitors = List.of(competitor1, competitor2, competitor3, competitor4);
        InMemoryCsvParser inMemoryCsvParserTest = new InMemoryCsvParser(competitors);

        Assert.assertEquals(competitors, inMemoryCsvParserTest.getCompetitorList());
    }

    @Test
    public void parseCsv() {
        List<Competitor> expectedCompetitors = List.of(
            new Competitor("Иван","М","10 км","54:20"),
            new Competitor("Александр","М","10 км","53:20"),
            new Competitor("Сергей","М","10 км","52:20"),
            new Competitor("Екатерина","Ж","5 км","29:20"),
            new Competitor("Мирослава","Ж","5 км","28:10"),
            new Competitor("Оксана","Ж","5 км","31:31"),
            new Competitor("Кристина","Ж","5 км","32:25"),
            new Competitor("Эрнест","М","5 км","32:25"),
            new Competitor("Владислав","М","5 км","33:47"),
            new Competitor("Роман","М","5 км","32:20"),
            new Competitor("Пётр","М","5 км","33:20"),
            new Competitor("Святослав","М","5 км","29:20"),
            new Competitor("Аркадий","М","5 км","28:10"),
            new Competitor("Владимир","М","5 км","29:35"),
            new Competitor("Ярослав","М","5 км","28:15"),
            new Competitor("Светлана","Ж","10 км","54:20"),
            new Competitor("Наталья","Ж","10 км","53:20"),
            new Competitor("Анна","Ж","10 км","52:20"),
            new Competitor("Мария","Ж","10 км","51:20"),
            new Competitor("Тамара","Ж","10 км","55:10"),
            new Competitor("Михаил","М","10 км","55:40"),
            new Competitor("Роза","Ж","5 км","33:47"),
            new Competitor("Любовь","Ж","5 км","29:35"),
            new Competitor("Надежда","Ж","5 км","28:15"),
            new Competitor("Константин","М","10 км","55:50"),
            new Competitor("Денис","М","10 км","55:55"),
            new Competitor("Дмитрий","М","10 км","51:20"),
            new Competitor("Степан","М","5 км","31:20"),
            new Competitor("Соломон","М","5 км","31:31"),
            new Competitor("Анастасия","Ж","10 км","55:40"),
            new Competitor("Диана","Ж","10 км","55:50"),
            new Competitor("Марина","Ж","10 км","55:55"),
            new Competitor("Александра","Ж","5 км","31:20"),
            new Competitor("Эльмира","Ж","10 км","55:20"),
            new Competitor("Алёна","Ж","10 км","55:30"),
            new Competitor("Алексей","М","10 км","55:10"),
            new Competitor("Григорий","М","10 км","55:20"),
            new Competitor("Георгий","М","10 км","55:30"),
            new Competitor("Евгения","Ж","5 км","32:20"),
            new Competitor("Ксения","Ж","5 км","33:20"));

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ResultProcessorConfig.class);
        CsvParser csvParser = applicationContext.getBean(CsvParser.class);
        csvParser.parseCsv();

        List<Competitor> actualCompetitors = csvParser.getCompetitorList();

        for (int i = 0; i < expectedCompetitors.size(); i++) {
            Assert.assertEquals(expectedCompetitors.get(i).getName(), actualCompetitors.get(i).getName());
            Assert.assertEquals(expectedCompetitors.get(i).getGender(), actualCompetitors.get(i).getGender());
            Assert.assertEquals(expectedCompetitors.get(i).getDistance(), actualCompetitors.get(i).getDistance());
            Assert.assertEquals(expectedCompetitors.get(i).getTime(), actualCompetitors.get(i).getTime());
            Assert.assertEquals(expectedCompetitors.get(i).getTimeInSeconds(), actualCompetitors.get(i).getTimeInSeconds());
        }
    }
}