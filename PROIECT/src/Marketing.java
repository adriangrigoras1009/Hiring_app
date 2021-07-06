import java.util.ArrayList;

public class Marketing extends Department {

    public Marketing() {
        angajati = new ArrayList<Employee>();
        joburi_disponibile = new ArrayList<Job>();
    }
    @Override
    public double getTotalSalaryBudget() {
        double salariu_total = 0;
        for(int i = 0; i < angajati.size(); i++) {
            if(angajati.get(i).salariu > 5000) {
                double scadere = 10 * angajati.get(i).salariu/100;
                double salariu = angajati.get(i).salariu - scadere;
                salariu_total += salariu;
            }
            else if(angajati.get(i).salariu < 3000) {
                salariu_total += angajati.get(i).salariu;
            }
            else {
                double scadere = 16 * angajati.get(i).salariu/100;
                double salariu = angajati.get(i).salariu - scadere;
                salariu_total += salariu;
            }
        }
        return salariu_total;
    }
}
