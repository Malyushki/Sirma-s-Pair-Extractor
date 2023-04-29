import java.time.LocalDate;
import java.util.*;

public class EmployeePairsFinder {


    private List<Employee> employeeList;
    private Map<String, Team> employeePairs;

    public EmployeePairsFinder(List<Employee> employeeList) {
        this.employeeList = employeeList;
        this.employeePairs = new LinkedHashMap<>();
    }


    public Team findTeamWithMostDaysWorkedTogether(boolean projectsIdAreIntegerOnly) {
        System.out.println(employeeList.toString());
        for (int i = 0; i < employeeList.size() - 1; i++) {
            Employee employee1 = employeeList.get(i);
            for (int j = i + 1; j < employeeList.size(); j++) {
                Employee employee2 = employeeList.get(j);
                if (projectsIdAreIntegerOnly) {
                    findCommonProjectsInteger(employee1, employee2);
                } else {
                    for (Project project1 : employee1.getEmployeeProjects()) {
                        for (Project project2 : employee2.getEmployeeProjects()) {
                            if (project1.getId().equals(project2.getId())) {
                                findCommonDatesOnProjects(employee1, employee2, project1, project2);
                            }
                        }
                    }
                }
            }
        }
        return employeePairs.values().stream()
                .max(Comparator.comparing(Team::daysWorkedTogether))
                .orElse(null);
    }

    private void findCommonProjectsInteger(Employee employee1, Employee employee2) {
        int i = 0;
        int j = 0;

        while (i < employee1.getEmployeeProjects().size() && j < employee2.getEmployeeProjects().size()) {

            Project currentProject1 = employee1.getEmployeeProjects().get(i);
            Project currentProject2 = employee2.getEmployeeProjects().get(j);

            long num1 = Long.parseLong(currentProject1.getId());
            long num2 = Long.parseLong(currentProject2.getId());


            if (num1 == num2) {
                findCommonDatesOnProjects(employee1, employee2, currentProject1, currentProject2);
                i++;
                j++;
            } else if (num1 < num2) {
                i++;
            } else {
                j++;
            }
        }

    }

    private void findCommonDatesOnProjects(Employee employee1, Employee employee2, Project project1, Project project2) {

        if (project1.getStartDate().isBefore(project2.getEndDate()) && project2.getStartDate().isBefore(project1.getEndDate())) {


            LocalDate startGroupDate = getStartLocalDate(project1, project2);
            LocalDate endGroupDate = getEndLocalDate(project1, project2);

            Team team = new Team(employee1.getId(), employee2.getId());
            Project project = new Project(project2.getId(), startGroupDate, endGroupDate);
            String key = employee1.getId() + "," + employee2.getId();

            if (!employeePairs.containsKey(key)) {
                employeePairs.put(key, team);
            }

            employeePairs.get(key).addProject(project);
        }
    }


    private LocalDate getEndLocalDate(Project project1, Project project2) {
        if (project1.getEndDate().isBefore(project2.getEndDate())) {
            return project1.getEndDate();
        } else {
            return project2.getEndDate();
        }

    }

    private LocalDate getStartLocalDate(Project project1, Project project2) {

        if (project1.getStartDate().isAfter(project2.getStartDate())) {
            return project1.getStartDate();
        } else {
            return project2.getStartDate();
        }

    }
}