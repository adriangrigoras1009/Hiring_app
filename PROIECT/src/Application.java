import java.util.ArrayList;
import java.util.List;

public class Application {
    public ArrayList<Company> companies;
    public ArrayList<User> users;
    private static Application instance;
    private Application() {}
    public static Application getInstance() {
        if(instance == null) {
            instance = new Application();
        }
        return instance;
    }
    public ArrayList<Company> getCompanies() {
        return companies;
    }
    public Company getCompany(String name) {
        for(int i = 0; i < companies.size(); i++) {
            if (companies.get(i).nume_companie.equals(name))
                return companies.get(i);
        }
        return null;
    }
    public void add(Company company) {
        if(companies == null)
            companies = new ArrayList<Company>();
        companies.add(company);
    }
    public void add(User user) {
        if(users == null)
            users = new ArrayList<User>();
        users.add(user);
    }
    public boolean remove(Company company) {
        if(companies.remove(company) == false)
            return false;
        return true;
    }
    public boolean remove(User user) {
        if(users.remove(user) == false)
            return false;
        return true;
    }
    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> jobs = new ArrayList<Job>();
        for(int j = 0; j < companies.size(); j++) {
            for (int i = 0; i < this.companies.size(); i++) {
                if(companies.get(j).equals(this.companies.get(i).nume_companie))
                    for(int z = 0; z < this.companies.get(i).departamente.size(); z++)
                        jobs.addAll(this.companies.get(i).departamente.get(z).joburi_disponibile);
            }
        }
        return jobs;
    }
}
