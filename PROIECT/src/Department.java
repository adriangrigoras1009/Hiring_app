import java.util.ArrayList;
import java.util.List;

public abstract class Department {
    ArrayList<Employee> angajati;
    ArrayList<Job> joburi_disponibile;
    String compania;
    public abstract double getTotalSalaryBudget();
    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobs_deschise = new ArrayList<Job>();
        for(int i = 0; i < joburi_disponibile.size(); i++) {
            if (joburi_disponibile.get(i).valabil == true)
                jobs_deschise.add(joburi_disponibile.get(i));
        }
        return jobs_deschise;
    }
    public void add(Employee employee) {
        if(angajati == null)
            angajati = new ArrayList<Employee>();
        angajati.add(employee);

    }
    public void remove(Employee employee) {
        angajati.remove(employee);
    }
    public void add(Job job) {
        if(joburi_disponibile == null)
            joburi_disponibile = new ArrayList<Job>();
        joburi_disponibile.add(job);

        for(int i = 0; i < Application.getInstance().companies.size(); i++) {
            if(Application.getInstance().companies.get(i).nume_companie.equals(compania)) {
                Application.getInstance().companies.get(i).notifyAllObservers();
                break;
            }
        }
    }
    public ArrayList<Employee> getEmployees() {
        ArrayList employees = (ArrayList) angajati;
        return employees;
    }
}
