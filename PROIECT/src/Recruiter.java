import java.util.ArrayList;

public class Recruiter extends Employee {
    double rating = 5;
    public Recruiter() {
        this.cunoscuti = new ArrayList<>();
        this.nume_companie = "";
        this.salariu = 0;
    }
    public int evaluate(Job job, User user) {
        rating = rating + 0.1;
        double c = rating * user.getTotalScore();
        int c1 = (int) c;
        Request<Job, Consumer> nou = new Request(job, user, this, c);
        for(Company comp : Application.getInstance().companies) {
            if(comp.nume_companie.equals(job.nume_companie)) {
                if (comp.manager.cereri == null)
                    comp.manager.cereri = new ArrayList<Request<Job, Consumer>>();
                comp.manager.cereri.add(nou);
            }
        }
        return c1;
    }

}
