import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Project {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String id, String startDate, String endDate) {
        this.id = id;
        this.startDate = DatesParser.parseDate(startDate);
        this.endDate = DatesParser.parseDate(endDate);
    }

    public Project(String id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getDays() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    @Override
    public String toString() {

        return String.format("Project: %s Start date: %s End Date: %s", getId(), getStartDate(), getEndDate());
    }
}
