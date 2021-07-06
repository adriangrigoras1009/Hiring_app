import java.util.ArrayList;
import java.util.List;

public class Company implements Subject {
    String nume_companie;
    Manager manager;
    List<Department> departamente;
    List<Recruiter> recruiters;
    ArrayList<User> observers = new ArrayList<User>();
    public Company(String nume_companie) {
        this.nume_companie = nume_companie;
    }
    public void addObserver(User user) {
        for(int i = 0; i < user.followed_companies.size(); i++) {
            if(user.followed_companies.get(i).equals(nume_companie)) {
                observers.add(user);
                break;
            }
        }
    }
    public void removeObserver(User c) {
        if(c.angajat == true) {
            Application ceva = Application.getInstance();
            for (int i = 0; i < ceva.companies.size(); i++) {
                for(int j = 0; j < c.followed_companies.size(); j++) {
                    if(ceva.companies.get(i).nume_companie.equals(c.followed_companies.get(j)))
                        ceva.companies.get(i).observers.remove(c);
                }
            }
        }
    }
    public void notifyAllObservers() {
        Notification notification = new Notification();
        notification.nume_companie = nume_companie;
        notification.mesaj = "a adaugat un job";
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(notification);
    }
    public void notifyAllObservers2() {
        Notification notification = new Notification();
        notification.nume_companie = nume_companie;
        notification.mesaj = "a inchis un post";
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(notification);
    }
    public void notifyAllObservers3() {
        Notification notification = new Notification();
        notification.nume_companie = nume_companie;
        notification.mesaj = "te-a respins";
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(notification);
    }
    public void add(Department departament) {
        if(departamente == null)
            departamente = new ArrayList<Department>();
        departament.compania = nume_companie;
        departamente.add(departament);
    }
    public void add(Recruiter recruiter) {
        if(recruiters == null)
            recruiters = new ArrayList<Recruiter>();
        recruiters.add(recruiter);
    }
    public void add(Employee employee, Department department) {
        for(int i = 0; i < departamente.size(); i++)
            if(departamente.get(i).equals(department))
                departamente.get(i).add(employee);
    }
    public void remove(Employee employee) {
        for(int i = 0; i < departamente.size(); i++) {
            for(int j = 0; j < departamente.get(i).getEmployees().size(); j++) {
                if(departamente.get(i).getEmployees().get(j).equals(employee))
                    departamente.get(i).getEmployees().remove(departamente.get(i).getEmployees().get(j));
            }
        }
    }
    public void remove(Department department) {
        for(int i = 0; i < departamente.size(); i++)
            if(departamente.get(i).equals(department)) {
                departamente.get(i).getEmployees().clear();
                departamente.remove(i);
            }
    }
    public void remove(Recruiter recruiter) {
        for(int i = 0; i < recruiters.size(); i++)
            if(recruiters.get(i).equals(recruiter))
                recruiters.remove(i);
    }
    public void move(Department source, Department destination) {
        ArrayList<Employee> aux = new ArrayList<Employee>();
        int contor;
        for(int i = 0; i < departamente.size(); i++)
            if(departamente.get(i).equals(source)) {
                aux = departamente.get(i).getEmployees();
                departamente.get(i).getEmployees().clear();
                contor = i;
                break;
            }
        for(int i = 0; i < departamente.size(); i++)
            if(departamente.get(i).equals(destination)) {
                for(int j = 0; j < aux.size(); j++)
                    departamente.get(i).add(aux.get(j));
                departamente.add(destination);
                break;
            }

    }
    public void move(Employee employee, Department newDepartment) {
        for(int i = 0; i < departamente.size(); i++) {
            ArrayList<Employee> aux = departamente.get(i).getEmployees();
            for(int j = 0; j < aux.size(); j++)
                if (aux.get(j).equals(employee)) {
                    departamente.get(i).getEmployees().remove(j);
                    break;
                }
        }
        for(int i = 0; i < departamente.size(); i++) {
            if(departamente.get(i).equals(newDepartment)) {
                departamente.get(i).add(employee);
                break;
            }
        }
    }
    public boolean contains(Department department) {
        for(int i = 0; i < departamente.size(); i++) {
            if (departamente.get(i).equals(department))
                return true;
        }
        return false;
    }
    public boolean contains(Employee employee) {
        for(int i = 0; i < departamente.size(); i++) {
            for(int j = 0; j < departamente.get(i).getEmployees().size(); j++) {
                if (departamente.get(i).getEmployees().get(j).equals(employee))
                    return true;
            }
        }
        return false;
    }
    public boolean contains(Recruiter recruiter) {
        for(int i = 0; i < recruiters.size(); i++) {
            if (recruiters.get(i).equals(recruiter))
                return true;
        }
        return false;
    }
    public Recruiter getRecruiter(User user) {
        Recruiter potrivit;
        Recruiter nou = recruiters.get(0);
        int maxim = nou.getDegreeInFriendship(user);
        potrivit = nou;
        double rating_maxim = 5;
        for(int i = 1; i < recruiters.size(); i++) {
            nou = recruiters.get(i);
            if(nou.getDegreeInFriendship(user) > maxim) {
                maxim = nou.getDegreeInFriendship(user);
                potrivit = nou;
            }
            else if(nou.getDegreeInFriendship(user) == maxim) {
                if(nou.rating > rating_maxim)
                    potrivit = nou;
            }
        }
        return potrivit;
    }
    public ArrayList<Job> getJobs() {
        ArrayList<Job> joburi_disp = new ArrayList<Job>();
        for(int i = 0; i < departamente.size(); i++) {
            Department nou = departamente.get(i);
            for(int j = 0; j < nou.joburi_disponibile.size(); j++)
                joburi_disp.add(nou.joburi_disponibile.get(j));
        }
        return joburi_disp;
    }
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        for(Department dep : departamente) {
            employees.addAll(dep.angajati);
        }
        return employees;
    }
}
