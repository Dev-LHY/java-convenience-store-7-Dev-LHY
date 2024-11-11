package store.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private static final String DELIMITER_COMMA = ",";
    private static final int NAME_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int GET_INDEX = 2;
    private static final int START_DATE_INDEX = 3;
    private static final int END_DATE_INDEX = 4;

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate start_date;
    private final LocalDate end_date;

    public Promotion(String name, int buy, int get, LocalDate start_date, LocalDate end_date) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public static List<Promotion> createPromotions(List<String> textLines) {
        List<Promotion> promotions = new ArrayList<>();
        for (String textLine : textLines) {
            promotions.add(parsePromotion(textLine));
        }
        return promotions;
    }

    private static Promotion parsePromotion(String textLine) {
        String[] fields = textLine.split(DELIMITER_COMMA);
        String name = fields[NAME_INDEX];
        int buy = Integer.parseInt(fields[BUY_INDEX]);
        int get = Integer.parseInt(fields[GET_INDEX]);
        LocalDate start_date = LocalDate.parse(fields[START_DATE_INDEX]);
        LocalDate end_date = LocalDate.parse(fields[END_DATE_INDEX]);
        return new Promotion(name, buy, get, start_date, end_date);
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", buy=" + buy +
                ", get=" + get +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}
