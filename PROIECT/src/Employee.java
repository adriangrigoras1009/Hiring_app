import java.util.ArrayList;
import java.util.List;

public class Employee extends Consumer{
    String nume_companie;
    long salariu;
    public int ani_experienta() {
        int numar_ani_experienta = 0;
        for(int i = 0; i < cv.getExperience().size()-1; i++) {
            Experience nou = cv.getExperience().get(i);
            if(nou.sfarsit != null) {
                int adaug = nou.sfarsit.getYear() - nou.inceput.getYear();
                int luna = nou.sfarsit.getMonthValue() - nou.inceput.getMonthValue();
                if (luna >= 3)
                    adaug++;
                else if (luna < -9) {
                    adaug--;
                }
                numar_ani_experienta = numar_ani_experienta + adaug;
            }
        }
        numar_ani_experienta += 2020 - cv.getExperience().get(cv.getExperience().size()-1).inceput.getYear();
        return numar_ani_experienta;
    }
    public Employee() {
        this.cunoscuti = new ArrayList<>();
        this.nume_companie = "";
        this.salariu = 0;
    }
    public Employee(Resume cv, List<Consumer> cunoscuti) {
        this.cv = cv;
        this.cunoscuti = cunoscuti;
    }
    public Employee(String nume_companie, long salariu, Resume cv, List<Consumer> cunoscuti) {
        this(cv,cunoscuti);
        this.nume_companie = nume_companie;
        this.salariu = salariu;
    }
}
