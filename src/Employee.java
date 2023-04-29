import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Employee {
    private String id;
    private List<Project> employeeProjects;

    public Employee(String id) {
        this.id = id;
        this.employeeProjects = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public List<Project> getEmployeeProjects() {
        return employeeProjects;
    }

    public void addProject(Project project){
        this.employeeProjects.add(project);
        this.employeeProjects.sort(Comparator.comparing(Project::getId));

    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee: ").append(getId()).append(System.lineSeparator());

        for (Project p: employeeProjects) {
            sb.append(p.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
