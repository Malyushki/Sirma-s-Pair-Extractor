import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatesParser {
    private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/MMM/yyyy"),
            DateTimeFormatter.ofPattern("d/MMMM/yyyy"),

            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/MMM/yyyy"),
            DateTimeFormatter.ofPattern("dd/MMMM/yyyy"),

            DateTimeFormatter.ofPattern("d/M/yy"),
            DateTimeFormatter.ofPattern("d/MM/yy"),
            DateTimeFormatter.ofPattern("d/MMM/yy"),
            DateTimeFormatter.ofPattern("d/MMMM/yy"),

            DateTimeFormatter.ofPattern("dd/MM/yy"),
            DateTimeFormatter.ofPattern("dd/MMM/yy"),
            DateTimeFormatter.ofPattern("dd/MMMM/yy"),
            DateTimeFormatter.ofPattern("dd/M/yy"),


            DateTimeFormatter.ofPattern("yyyy/MMMM/d"),
            DateTimeFormatter.ofPattern("yyyy/MMM/d"),
            DateTimeFormatter.ofPattern("yyyy/MM/d"),
            DateTimeFormatter.ofPattern("yyyy/M/d"),


            DateTimeFormatter.ofPattern("yyyy/MMMM/dd"),
            DateTimeFormatter.ofPattern("yyyy/MMM/dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy/M/dd"),

            DateTimeFormatter.ofPattern("yy/MMMM/d"),
            DateTimeFormatter.ofPattern("yy/MMM/d"),
            DateTimeFormatter.ofPattern("yy/MM/d"),
            DateTimeFormatter.ofPattern("yy/M/d"),

            DateTimeFormatter.ofPattern("yy/MMMM/dd"),
            DateTimeFormatter.ofPattern("yy/MMM/dd"),
            DateTimeFormatter.ofPattern("yy/MM/dd"),
            DateTimeFormatter.ofPattern("yy/M/dd"),
    };

    public static LocalDate parseDate(String dateString) {
       dateString = dateString.trim();
        if (dateString.equals("NULL")){
            return LocalDate.now();
        }
        dateString = replaceDateSplitterSymbols(dateString);

        for (DateTimeFormatter formatter : SUPPORTED_FORMATS) {
            try {
                LocalDate date = LocalDate.parse(dateString, formatter);
                int year = date.getYear();
                int currentYear = LocalDate.now().getYear();
                if (year > currentYear) {
                    date = date.withYear(year-100); //съгласно условията годината няма как да е в бъдещето и
                    // понеже можем да получим формат на дата d.M.yy например 1.1.98 за да не я отчете годината
                    // като 2098, ако годината от стринг-а е по голяма от сегашната година я намаляме с 100
                }
                return date;
            } catch (Exception ignored) {

            }
        }
        throw new IllegalArgumentException("Invalid date format: " + dateString);
    }

    private static String replaceDateSplitterSymbols(String dateString) {
        return dateString.replaceAll("[.-]", "/");
    }


}
