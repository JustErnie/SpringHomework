package Competition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Competitor {
    private final String name;
    private final Gender gender;
    private final Distance distance;
    private final String time;
    private final int timeInSeconds;

    public Competitor(String name, String gender, String distance, String time) {
        this.name = name;
        this.gender = convertGender(gender);
        this.distance = convertDistance(distance);
        this.time = time;
        this.timeInSeconds = convertTime(time);
    }


    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Distance getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    private Distance convertDistance(String distance) {
        if (Distance.FiveKm.string.equals(distance)) {
            return Distance.FiveKm;
        }
        if (Distance.TenKm.string.equals(distance)) {
            return Distance.TenKm;
        }
        throw new IllegalArgumentException("Wrong distance passed");
    }

    private static int convertTime(String time) {
        // 52:20
        Pattern p = Pattern.compile("(\\d{2}):(\\d{2})");
        Matcher m = p.matcher(time);

        if (m.matches()) {
            String minutesStr = m.group(1);
            String secondsStr = m.group(2);

            int minutes = Integer.parseInt(minutesStr);
            int seconds = Integer.parseInt(secondsStr);
            return minutes * 60 + seconds;
        } else throw new IllegalStateException("Can not match time");
    }

    private Gender convertGender(String gender) {
        //М
        return switch (gender) {
            case "М": yield Gender.MALE;
            case "Ж": yield Gender.FEMALE;
            default : throw new IllegalArgumentException("Wrong gender");
        };
    }

    @Override
    public String toString() {
        return "Competitor{" +
               "name='" + name + '\'' +
               ", gender=" + gender.string +
               ", distance=" + distance.string +
               ", time='" + time + '\'' +
               ", timeInSeconds=" + timeInSeconds +
               '}';
    }
}
