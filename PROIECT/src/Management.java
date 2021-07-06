import java.util.ArrayList;

public class Management extends Department {

    public Management() {
        angajati = new ArrayList<Employee>();
        joburi_disponibile = new ArrayList<Job>();
    }
    @Override
    public double getTotalSalaryBudget() {
        double salariu_total = 0;
        for(int i = 0; i < angajati.size(); i++) {
            double scadere = 16 * angajati.get(i).salariu/100;
            double salariu = angajati.get(i).salariu - scadere;
            salariu_total += salariu;
        }
        return salariu_total;
    }
}
