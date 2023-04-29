import java.util.ArrayList;
import java.util.List;

public class Team {
    private String employee1Id;
    private String employee2Id;
    List<Project> projectList;

    public Team(String employee1, String employee2) {
        this.employee1Id = employee1;
        this.employee2Id = employee2;
        projectList = new ArrayList<>();
    }

    public String getEmployee1() {
        return employee1Id;
    }

    public String getEmployee2() {
        return employee2Id;
    }

    public void addProject(Project project) {
        projectList.add(project);
    }

    public long daysWorkedTogether() {
        return projectList.stream().mapToLong(Project::getDays).sum();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s, %s, %d", getEmployee1(), getEmployee2(), daysWorkedTogether()))
                .append(System.lineSeparator());
        projectList.forEach(project -> sb.append(String.format("%s, %d", project.getId(), project.getDays())).append(System.lineSeparator()));
       return sb.toString().trim();


    }
}
