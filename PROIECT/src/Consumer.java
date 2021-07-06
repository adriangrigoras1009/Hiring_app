import java.util.*;

public abstract class Consumer {
    List<Consumer> cunoscuti;
    Resume cv;
    public void add(Education education) {
        cv.getEducation().add(education);
        Collections.sort(cv.getEducation());
    }
    public void add(Experience experience) {
        cv.getExperience().add(experience);
        Collections.sort(cv.getExperience());
    }
    public void add(Consumer consumer) {
        if(cunoscuti == null)
            cunoscuti = new ArrayList<Consumer>();
        cunoscuti.add(consumer);
    }
    public int getDegreeInFriendship(Consumer consumer) {
        int grad = 1;
        int contor = 0;
        Queue<Consumer> queue = new ArrayDeque<Consumer>();
        Consumer nou1 = this;
        queue.add(nou1);
        List<Consumer> vizitat = new ArrayList<Consumer>();
        int contor2 = 1;
        do {
            int contor3 = contor2;
            contor2 = 0;
            while(contor3 != 0) {
                Consumer nou = queue.poll();
                vizitat.add(nou);
                for (int i = 0; i < nou.cunoscuti.size(); i++) {
                    if (nou.cunoscuti.get(i).equals(consumer)) {
                        contor = 1;
                        break;
                    }
                    if (!vizitat.contains(nou.cunoscuti.get(i))) {
                        queue.add(nou.cunoscuti.get(i));
                        contor2++;
                    }
                }
                contor3--;
                if (contor == 1)
                    break;
            }
            if(contor == 1)
                break;
            grad++;
        }while(!queue.isEmpty());
        return grad;
    }
    public void remove(Consumer consumer) {
        cunoscuti.remove(consumer);
    }
    public Integer getGraduationYear() {
        Integer ceva;
        Education nou = cv.getEducation().get(cv.getEducation().size()-1);
        if(nou.sfarsit != null)
             ceva = (Integer) nou.sfarsit.getYear();
        else
            ceva = 0;
        return ceva;
    }
    public Double meanGPA() {
        double suma = 0;
        for(int i = 0; i < cv.getEducation().size(); i++)
            suma = suma + cv.getEducation().get(i).media_de_finalizare;
        suma = suma / cv.getEducation().size();
        return suma;
    }
    public String toString() {
        return cv.getPersonal_datas().getPrenume() + " " + cv.getPersonal_datas().getNume();
    }
}
