import java.util.ArrayList;
import java.util.List;

public class User extends Consumer implements Observer {
    List<String> followed_companies;
    ArrayList<Job> aplicari;
    ArrayList<String> notificari = new ArrayList<String>();
    boolean angajat = false;
    public User() {
        cunoscuti = new ArrayList<Consumer>();
        followed_companies = new ArrayList<String>();
        aplicari = new ArrayList<Job>();
    }
    @Override
    public void update(Notification notification) {
        String c =  "Company" + notification.nume_companie + " " + notification.mesaj;
        notificari.add(c);
    }
    public Employee convert() {
        angajat = true;
        Employee nou;
        nou = new Employee(cv, cunoscuti);
        for(User user : Application.getInstance().users) {
            if(user.cv.equals(this.cv)) {
                Application.getInstance().users.remove(user);
                break;
            }
        }
        return nou;
    }
    public Double getTotalScore() {
        Double medie = meanGPA();
        int numar_ani_experienta = ani_experienta();
        double finala =  numar_ani_experienta * 1.5 + medie;
        return finala;
    }
    public int ani_experienta() {
        int numar_ani_experienta = 0;
        for(int i = 0; i < cv.getExperience().size(); i++) {
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
        return numar_ani_experienta;
    }
}
