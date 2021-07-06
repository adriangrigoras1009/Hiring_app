import java.util.ArrayList;

public class Finance extends Department {

    public Finance() {
        angajati = new ArrayList<Employee>();
        joburi_disponibile = new ArrayList<Job>();
    }
    @Override
    public double getTotalSalaryBudget() {
        double salariu_total = 0;
        for(int i = 0; i < angajati.size(); i++) {
            int size = angajati.get(i).cv.getExperience().size();
            int ani_vechime = angajati.get(i).ani_experienta();
            if(ani_vechime < 1) {
                double scadere = 10 * angajati.get(i).salariu / 100;
                double salariu = angajati.get(i).salariu - scadere;
                salariu_total += salariu;
            }
            else {
                double scadere = (double)16 * angajati.get(i).salariu / 100;
                double salariu = (double)angajati.get(i).salariu - (double)scadere;
                salariu_total += salariu;
            }
        }
        return salariu_total;
    }
}
