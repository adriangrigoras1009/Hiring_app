import java.util.ArrayList;

public class Job {
    String nume_job;
    String nume_companie;
    String nume_departament;
    boolean valabil;
    Constraint anul_absolvirii;
    Constraint ani_experienta;
    Constraint media_academica;
    ArrayList<User> candidates = new ArrayList<User>();
    int nr_candidati;
    double salariu;
    public Job() {
        this.nume_companie = null;
        this.nume_departament = null;
    }
    public Job(String companie, String departament) {
        this.nume_companie = companie;
        this.nume_departament = departament;
    }
    public void apply(User user) {
        if(valabil == true) {
            Recruiter rec;
            for(Company comp : Application.getInstance().companies)
                if(comp.nume_companie.equals(nume_companie)) {
                    rec = comp.getRecruiter(user);
                    rec.evaluate(this, user);
                    candidates.add(user);
                    if(user.aplicari == null)
                        user.aplicari = new ArrayList<Job>();
                    user.aplicari.add(this);
                    break;
                }
        }
    }
    public boolean meetsRequirements(User user) {
        int ani = user.ani_experienta();
        int absolv = user.getGraduationYear();
        double media = user.meanGPA();
        if((ani > ani_experienta.limita_sup && ani_experienta.limita_sup > 0 ) || ani < ani_experienta.limita_inf)
            return false;
        if((absolv > anul_absolvirii.limita_sup && anul_absolvirii.limita_sup > 0) || absolv < anul_absolvirii.limita_inf)
            return false;
        if((media > media_academica.limita_sup && media_academica.limita_sup > 0) || media < media_academica.limita_inf)
            return false;
        return true;
    }
    public String toString() {
        return nume_job;
    }
}
