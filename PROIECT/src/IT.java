import java.util.ArrayList;

public class IT extends Department {

    public IT() {
        angajati = new ArrayList<Employee>();
        joburi_disponibile = new ArrayList<Job>();
    }
    @Override
    public double getTotalSalaryBudget() {
        double salariu_total = 0;
        for(int i = 0; i < angajati.size(); i++) {
            salariu_total += angajati.get(i).salariu;
        }
        return salariu_total;
    }
}
