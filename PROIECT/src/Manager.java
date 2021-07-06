import java.util.ArrayList;
import java.util.Comparator;

public class Manager extends Employee {
    ArrayList<Request<Job, Consumer>> cereri;

    public void process(Job job) {
        ArrayList<Request<Job, Consumer>> cereri2 = new ArrayList<Request<Job, Consumer>>();
        for(int i = 0; i < cereri.size(); i++) {
            if(cereri.get(i).getKey().equals(job) && job.meetsRequirements((User)cereri.get(i).getValue1()))
                cereri2.add(cereri.get(i));
        }
        Comparator<Request<Job, Consumer>> comp;
        cereri2.sort(comp = new Comparator<Request<Job, Consumer>>() {
            @Override
            public int compare(Request<Job, Consumer> o1, Request<Job, Consumer> o2) {
                if (o1.getScore() > o2.getScore())
                    return 1;
                else if (o1.getScore() < o2.getScore())
                    return -1;
                else
                    return 0;
            }
        });
        int contor = 0;
        for (int i = 0; i < cereri2.size(); i++) {
            Request<Job, Consumer> nou = cereri2.get(i);
            if (nou.getKey().equals(job)) {
                if (Application.getInstance().users.contains(nou.getValue1()) && contor < job.nr_candidati) {
                    contor++;
                    User ang = (User)nou.getValue1();
                    Application.getInstance().users.remove(ang);
                    Employee angajat = ang.convert();
                    angajat.salariu = (long)job.salariu;
                    angajat.nume_companie = job.nume_companie;
                    for(Company comp1 : Application.getInstance().companies) {
                        if(job.nume_companie.equals(comp1.nume_companie)) {
                            for(Department dep : comp1.departamente) {
                                if(dep.getClass().getName().equals(job.nume_departament)) {
                                    comp1.add(angajat,dep);
                                }
                            }
                        }
                    }
                }
                else {
                    cereri.remove(nou);
                    for(int j = 0; j < Application.getInstance().companies.size(); j++) {
                        if(Application.getInstance().companies.get(j).manager.equals(this)) {
                            Application.getInstance().companies.get(j).notifyAllObservers3();
                            break;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < cereri.size(); i++) {
            if(cereri.get(i).getKey().nume_job.equals(job.nume_job))
                cereri.remove(cereri.get(i));
        }
        job.valabil = false;
        for(int i = 0; i < Application.getInstance().companies.size(); i++) {
            if(Application.getInstance().companies.get(i).manager.equals(this)) {
                Application.getInstance().companies.get(i).notifyAllObservers2();
                break;
            }
        }
    }
}
