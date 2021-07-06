import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ClassCastException {
        Application my_application = Application.getInstance();
        Company Google = new Company("Google");
        Company Amazon = new Company("Amazon");
        my_application.add(Google);
        my_application.add(Amazon);
        DepartmentFactory enumeratie = new DepartmentFactory();
        for(int i = 0; i < my_application.companies.size(); i++) {
            Department Finance = enumeratie.createDepartment(DepartmentFactory.DepartmentType.Finance);
            Department IT = enumeratie.createDepartment(DepartmentFactory.DepartmentType.IT);
            Department Management = enumeratie.createDepartment(DepartmentFactory.DepartmentType.Management);
            Department Marketing = enumeratie.createDepartment(DepartmentFactory.DepartmentType.Marketing);
            my_application.companies.get(i).add(Finance);
            my_application.companies.get(i).add(IT);
            my_application.companies.get(i).add(Marketing);
            my_application.companies.get(i).add(Management);
        }
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("consumers.json");

        //crearea employees
        Object obj = parser.parse(reader);
        JSONObject obj2 = (JSONObject) obj;
        JSONArray vector_employees = (JSONArray)obj2.get("employees");
        JSONArray vector_users = (JSONArray)obj2.get("users");
        JSONArray vector_managers = (JSONArray)obj2.get("managers");
        JSONArray vector_recruiters = (JSONArray)obj2.get("recruiters");

        vector_employees.forEach(emp -> parseEmployee( (JSONObject) emp, my_application));
        vector_recruiters.forEach(rec -> parseRecruiter( (JSONObject) rec, my_application));
        vector_users.forEach(users -> parseUsers( (JSONObject) users, my_application));
        vector_managers.forEach(managers -> parseManagers( (JSONObject) managers, my_application));

        //adaugat observatori
        for(Company comp : my_application.companies) {
            for(User user : my_application.users) {
                comp.addObserver(user);
            }
        }
        //adaugarea joburilor
        Job sde = new Job("Google", "IT");
        sde.valabil = true;
        sde.nr_candidati = 1;
        sde.salariu = (double)10000;
        Constraint medie = new Constraint((double)8,(double)0);
        Constraint an = new Constraint((double)2002,(double)2020);
        Constraint exp = new Constraint((double)2,(double)6);
        sde.media_academica = medie;
        sde.anul_absolvirii = an;
        sde.ani_experienta = exp;
        sde.nume_job = "Software Developer Engineer";

        Job sde1 = new Job("Amazon", "IT");
        sde1.valabil = true;
        sde1.nr_candidati = 1;
        sde1.salariu = (double)12000;
        medie = new Constraint((double)9,(double)0);
        an = new Constraint((double)2014,(double)2020);
        exp = new Constraint((double)1, (double)0);
        sde1.media_academica = medie;
        sde1.anul_absolvirii = an;
        sde1.ani_experienta = exp;
        sde1.nume_job = "Software Developer Engineer";

        Job sdei = new Job("Google", "IT");
        sdei.valabil = true;
        sdei.nr_candidati = 1;
        sdei.salariu = (double)5000;
        medie = new Constraint((double)9,0);
        an = new Constraint((double)0,(double)0);
        exp = new Constraint((double)0, (double)2);
        sdei.media_academica = medie;
        sdei.anul_absolvirii = an;
        sdei.ani_experienta = exp;
        sdei.nume_job = "Software Developer Engineer Intern";

        Job sdei1 = new Job("Amazon", "IT");
        sdei1.valabil = true;
        sdei1.nr_candidati = 1;
        sdei1.salariu = (double)6000;
        medie = new Constraint((double)9.35,(double)0);
        an = new Constraint((double)0,(double)0);
        exp = new Constraint((double)0, (double)2);
        sdei1.media_academica = medie;
        sdei1.anul_absolvirii = an;
        sdei1.ani_experienta = exp;
        sdei1.nume_job = "Software Developer Engineer Intern";

        my_application.companies.get(0).departamente.get(1).add(sde);
        my_application.companies.get(0).departamente.get(1).add(sdei);
        my_application.companies.get(1).departamente.get(1).add(sde1);
        my_application.companies.get(1).departamente.get(1).add(sdei1);
        //aplica la joburile companiilor pe care le urmaresc automat
        for(int i = 0; i < my_application.users.size(); i++) {
            ArrayList<Job> jobs = my_application.getJobs(Application.getInstance().users.get(i).followed_companies);
            for(Job aplic : jobs) {
                aplic.apply(my_application.users.get(i));
            }
        }
        //creez reteaua sociala din graf
        Employee emp2 = my_application.companies.get(1).departamente.get(3).angajati.get(0);
        Employee emp3 = my_application.companies.get(1).departamente.get(2).angajati.get(0);
        Employee emp6 = my_application.companies.get(0).departamente.get(1).angajati.get(0);
        Employee emp7 = my_application.companies.get(0).departamente.get(3).angajati.get(0);
        Employee emp10 = my_application.companies.get(0).departamente.get(2).angajati.get(1);
        my_application.users.get(0).add(my_application.users.get(1));
        my_application.users.get(0).add(emp3);
        my_application.users.get(1).add(my_application.companies.get(0).recruiters.get(0));
        my_application.users.get(1).add(emp7);
        my_application.users.get(1).add(my_application.users.get(0));
        my_application.users.get(2).add(my_application.users.get(3));
        my_application.users.get(2).add(emp3);
        my_application.users.get(3).add(my_application.users.get(2));
        my_application.users.get(3).add(emp10);
        emp2.add(emp10);
        emp2.add(my_application.companies.get(1).recruiters.get(0));
        emp3.add(my_application.users.get(0));
        emp3.add(my_application.companies.get(0).recruiters.get(1));
        emp3.add(my_application.users.get(2));
        emp3.add(emp6);
        emp6.add(emp3);
        emp6.add(my_application.companies.get(1).recruiters.get(1));
        emp10.add(emp2);
        emp10.add(my_application.users.get(3));
        emp7.add(my_application.users.get(1));
        my_application.companies.get(0).recruiters.get(0).add(my_application.users.get(1));
        my_application.companies.get(0).recruiters.get(1).add(emp3);
        my_application.companies.get(1).recruiters.get(0).add(emp2);
        my_application.companies.get(1).recruiters.get(1).add(emp6);
        //pagina de deschidere a aplicatiei
        OpenPage open = new OpenPage(my_application, "open");
        open.setVisible(true);
    }
    public static void parseEmployee(JSONObject employee, Application my_application) {
        //information pentru employees
        Employee nou = new Employee();
        Information noua = new Information();
        String nume_complet = (String) employee.get("name");
        String[] nume = new String[100];
        int contor = 0;
        for(String str: nume_complet.split(" ")) {
            nume[contor] = str;
            contor++;
        }
        noua = new Information();
        noua.setPrenume(nume[0]);
        noua.setNume(nume[1]);
        String email = (String) employee.get("email");
        noua.setEmail(email);
        String telefon = (String) employee.get("phone");
        noua.setTelefon(telefon);
        String data_nastere = (String) employee.get("date_of_birth");
        noua.setData_nastere(data_nastere);
        String genre = (String) employee.get("genre");
        noua.setSex(genre);
        ArrayList<String> limbi = (ArrayList<String>) employee.get("languages");
        ArrayList<String> nivel = (ArrayList<String>) employee.get("languages_level");
        ArrayList<String> Beginner = new ArrayList<String>();
        ArrayList<String> Advanced = new ArrayList<String>();
        ArrayList<String> Experienced = new ArrayList<String>();
        for(int i = 0; i < limbi.size(); i++) {
            if(nivel.get(i).equals("Beginner"))
                Beginner.add(limbi.get(i));
            else if(nivel.get(i).equals("Advanced"))
                Advanced.add(limbi.get(i));
            else if(nivel.get(i).equals("Experienced"))
                Experienced.add(limbi.get(i));
        }
        noua.setLimbi(Beginner,Advanced,Experienced);
        nou.salariu = (long) employee.get("salary");
        JSONArray educations = (JSONArray) employee.get("education");
        Information finalNoua = noua;
        //adaugam studiile
        educations.forEach(edu -> parseEducation((JSONObject) edu, my_application,nou, finalNoua));
        JSONArray jobs = (JSONArray) employee.get("experience");
        //adaugam joburile
        jobs.forEach(exp -> parseJobs((JSONObject) exp, my_application, nou));
        //setam ultimul job ca prezent
        parseExperience((JSONObject)jobs.get(jobs.size()-1),my_application,nou);
    }
    public static void parseRecruiter(JSONObject recruiter, Application my_application) {
        Recruiter nou = new Recruiter();
        String nume_complet = (String) recruiter.get("name");
        String[] nume = new String[100];
        int contor = 0;
        for(String str: nume_complet.split(" ")) {
            nume[contor] = str;
            contor++;
        }
        Information noua = new Information();
        noua = new Information();
        noua.setPrenume(nume[0]);
        noua.setNume(nume[1]);
        String email = (String) recruiter.get("email");
        noua.setEmail(email);
        String telefon = (String) recruiter.get("phone");
        noua.setTelefon(telefon);
        String data_nastere = (String) recruiter.get("date_of_birth");
        noua.setData_nastere(data_nastere);
        String genre = (String) recruiter.get("genre");
        noua.setSex(genre);
        ArrayList<String> limbi = (ArrayList<String>) recruiter.get("languages");
        ArrayList<String> nivel = (ArrayList<String>) recruiter.get("languages_level");
        ArrayList<String> Beginner = new ArrayList<String>();
        ArrayList<String> Advanced = new ArrayList<String>();
        ArrayList<String> Experienced = new ArrayList<String>();
        for(int i = 0; i < limbi.size(); i++) {
            if(nivel.get(i).equals("Beginner"))
                Beginner.add(limbi.get(i));
            else if(nivel.get(i).equals("Advanced"))
                Advanced.add(limbi.get(i));
            else if(nivel.get(i).equals("Experienced"))
                Experienced.add(limbi.get(i));
        }
        noua.setLimbi(Beginner,Advanced,Experienced);
        nou.salariu = (long) recruiter.get("salary");
        JSONArray educations = (JSONArray) recruiter.get("education");
        Information finalNoua = noua;
        educations.forEach(edu -> parseEducation((JSONObject) edu, my_application,nou, finalNoua));
        JSONArray jobs = (JSONArray) recruiter.get("experience");
        jobs.forEach(exp -> parseJobs((JSONObject) exp, my_application, nou));
        parseExperience((JSONObject)jobs.get(jobs.size()-1),my_application,nou);
    }
    public static void parseUsers(JSONObject user, Application my_application) {
        User nou = new User();
        Information noua = new Information();
        String nume_complet = (String) user.get("name");
        String[] nume = new String[100];
        int contor = 0;
        for (String str : nume_complet.split(" ")) {
            nume[contor] = str;
            contor++;
        }
        noua = new Information();
        noua.setPrenume(nume[0]);
        noua.setNume(nume[1]);
        String email = (String) user.get("email");
        noua.setEmail(email);
        String telefon = (String) user.get("phone");
        noua.setTelefon(telefon);
        String data_nastere = (String) user.get("date_of_birth");
        noua.setData_nastere(data_nastere);
        String genre = (String) user.get("genre");
        noua.setSex(genre);
        ArrayList<String> limbi = (ArrayList<String>) user.get("languages");
        ArrayList<String> nivel = (ArrayList<String>) user.get("languages_level");
        ArrayList<String> Beginner = new ArrayList<String>();
        ArrayList<String> Advanced = new ArrayList<String>();
        ArrayList<String> Experienced = new ArrayList<String>();
        for(int i = 0; i < limbi.size(); i++) {
            if(nivel.get(i).equals("Beginner"))
                Beginner.add(limbi.get(i));
            else if(nivel.get(i).equals("Advanced"))
                Advanced.add(limbi.get(i));
            else if(nivel.get(i).equals("Experienced"))
                Experienced.add(limbi.get(i));
        }
        noua.setLimbi(Beginner,Advanced,Experienced);
        ArrayList<String> interested_companies = (ArrayList<String>) user.get("interested_companies");
        nou.followed_companies = interested_companies;
        JSONArray educations = (JSONArray) user.get("education");
        Information finalNoua = noua;
        educations.forEach(edu -> parseEducation((JSONObject) edu, my_application, nou, finalNoua));
        JSONArray jobs = (JSONArray) user.get("experience");
        jobs.forEach(exp -> parseJobs((JSONObject) exp, my_application, nou));
        my_application.add(nou);
    }
    public static void parseManagers(JSONObject manager, Application my_application) {
        Manager nou = new Manager();
        Information noua = new Information();
        String nume_complet = (String) manager.get("name");
        String[] nume = new String[100];
        int contor = 0;
        for (String str : nume_complet.split(" ")) {
            nume[contor] = str;
            contor++;
        }
        noua = new Information();
        noua.setPrenume(nume[0]);
        noua.setNume(nume[1]);
        String email = (String) manager.get("email");
        noua.setEmail(email);
        String telefon = (String) manager.get("phone");
        noua.setTelefon(telefon);
        String data_nastere = (String) manager.get("date_of_birth");
        noua.setData_nastere(data_nastere);
        String genre = (String) manager.get("genre");
        noua.setSex(genre);
        ArrayList<String> limbi = (ArrayList<String>) manager.get("languages");
        ArrayList<String> nivel = (ArrayList<String>) manager.get("languages_level");
        ArrayList<String> Beginner = new ArrayList<String>();
        ArrayList<String> Advanced = new ArrayList<String>();
        ArrayList<String> Experienced = new ArrayList<String>();
        for(int i = 0; i < limbi.size(); i++) {
            if(nivel.get(i).equals("Beginner"))
                Beginner.add(limbi.get(i));
            else if(nivel.get(i).equals("Advanced"))
                Advanced.add(limbi.get(i));
            else if(nivel.get(i).equals("Experienced"))
                Experienced.add(limbi.get(i));
        }
        noua.setLimbi(Beginner,Advanced,Experienced);
        nou.salariu = (long) manager.get("salary");
        JSONArray educations = (JSONArray) manager.get("education");
        Information finalNoua = noua;
        educations.forEach(edu -> parseEducation((JSONObject) edu, my_application, nou, finalNoua));
        JSONArray jobs = (JSONArray) manager.get("experience");
        jobs.forEach(exp -> parseJobs((JSONObject) exp, my_application, nou));
        parseExperience((JSONObject)jobs.get(jobs.size()-1),my_application,nou);
    }
    public static void parseJobs(JSONObject job, Application my_application, Employee employee) {
        String nume_companie = (String)job.get("company");
        String pozitie = (String)job.get("position");
        String inceput_str = (String)job.get("start_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inceput = LocalDate.parse(inceput_str,formatter);
        String sfarsit_str = (String)job.get("end_date");
        LocalDate sfarsit;
        if(sfarsit_str == null)
            sfarsit = null;
        else
            sfarsit = LocalDate.parse(sfarsit_str,formatter);
        Experience experience = new Experience(pozitie,inceput,sfarsit,nume_companie);
        employee.add(experience);
    }
    public static void parseJobs(JSONObject job, Application my_application, Recruiter employee) {
        String nume_companie = (String)job.get("company");
        String pozitie = (String)job.get("position");
        String inceput_str = (String)job.get("start_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inceput = LocalDate.parse(inceput_str,formatter);
        String sfarsit_str = (String)job.get("end_date");
        LocalDate sfarsit;
        if(sfarsit_str == null)
            sfarsit = null;
        else
            sfarsit = LocalDate.parse(sfarsit_str,formatter);
        Experience experience = new Experience(pozitie,inceput,sfarsit,nume_companie);
        employee.add(experience);
    }
    public static void parseJobs(JSONObject job, Application my_application, User employee) {
        String nume_companie = (String)job.get("company");
        String pozitie = (String)job.get("position");
        String inceput_str = (String)job.get("start_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inceput = LocalDate.parse(inceput_str,formatter);
        String sfarsit_str = (String)job.get("end_date");
        LocalDate sfarsit;
        if(sfarsit_str == null)
            sfarsit = null;
        else
            sfarsit = LocalDate.parse(sfarsit_str,formatter);
        Experience experience = new Experience(pozitie,inceput,sfarsit,nume_companie);
        employee.add(experience);
    }
    public static void parseJobs(JSONObject job, Application my_application, Manager employee) {
        String nume_companie = (String)job.get("company");
        String pozitie = (String)job.get("position");
        String inceput_str = (String)job.get("start_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inceput = LocalDate.parse(inceput_str,formatter);
        String sfarsit_str = (String)job.get("end_date");
        LocalDate sfarsit;
        if(sfarsit_str == null)
            sfarsit = null;
        else
            sfarsit = LocalDate.parse(sfarsit_str,formatter);
        Experience experience = new Experience(pozitie,inceput,sfarsit,nume_companie);
        employee.add(experience);
    }
    public static void parseEducation(JSONObject edu, Application my_application, Employee employee, Information noua) {
        String nume = (String)edu.get("name");
        String nivel = (String)edu.get("level");
        Education education = new Education(nume,nivel);
        String inceput_str = (String)edu.get("start_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inceput = LocalDate.parse(inceput_str,formatter);
        String sfarsit_str = (String)edu.get("end_date");
        LocalDate sfarsit;
        if(sfarsit_str == null)
            sfarsit = null;
        else
            sfarsit = LocalDate.parse(sfarsit_str,formatter);
        education.inceput = inceput;
        education.sfarsit = sfarsit;
       Object t = edu.get("grade");
        if(t instanceof Long) {
            long lg = (long) t;
            double var_double = Long.valueOf(lg).doubleValue();
            education.media_de_finalizare = var_double;
        }
        else {
            education.media_de_finalizare = (double) edu.get("grade");
        }
        if(employee.cv == null)
            employee.cv = new Resume.ResumeBuilder(noua,education)
                    .build();

        else {
            employee.add(education);
        }

    }
    public static void parseEducation(JSONObject edu, Application my_application, Recruiter employee, Information noua) {
        String nume = (String)edu.get("name");
        String nivel = (String)edu.get("level");
        Education education = new Education(nume,nivel);
        String inceput_str = (String)edu.get("start_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inceput = LocalDate.parse(inceput_str,formatter);
        String sfarsit_str = (String)edu.get("end_date");
        LocalDate sfarsit;
        if(sfarsit_str == null)
            sfarsit = null;
        else
            sfarsit = LocalDate.parse(sfarsit_str,formatter);
        education.inceput = inceput;
        education.sfarsit = sfarsit;
        Object t = edu.get("grade");
        if(t instanceof Long) {

            long lg = (long) t;
            double var_double = Long.valueOf(lg).doubleValue();
            education.media_de_finalizare = var_double;
        }
        else {
            education.media_de_finalizare = (double) edu.get("grade");
        }
        if(employee.cv == null)
            employee.cv = new Resume.ResumeBuilder(noua,education)
                    .build();
        else
            employee.add(education);
    }
    public static void parseEducation(JSONObject edu, Application my_application, User employee, Information noua) {
        String nume = (String)edu.get("name");
        String nivel = (String)edu.get("level");
        Education education = new Education(nume,nivel);
        String inceput_str = (String)edu.get("start_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inceput = LocalDate.parse(inceput_str,formatter);
        String sfarsit_str = (String)edu.get("end_date");
        LocalDate sfarsit;
        if(sfarsit_str == null)
            sfarsit = null;
        else
            sfarsit = LocalDate.parse(sfarsit_str,formatter);
        education.inceput = inceput;
        education.sfarsit = sfarsit;
        Object t = edu.get("grade");
        if(t instanceof Long) {
            long lg = (long) t;
            double var_double = Long.valueOf(lg).doubleValue();
            education.media_de_finalizare = var_double;
        }
        else {
            education.media_de_finalizare = (double) edu.get("grade");
        }
        if(employee.cv == null)
            employee.cv = new Resume.ResumeBuilder(noua,education)
                    .build();
        else
            employee.add(education);
    }
    public static void parseExperience(JSONObject job, Application my_application,Employee employee) {
        String nume_departament = (String) job.get("department");
        String nume_companie = (String) job.get("company");
        employee.nume_companie = nume_companie;
        for(int i = 0; i < my_application.getCompanies().size(); i++) {
            if(my_application.getCompanies().get(i).nume_companie.equals(nume_companie)) {
                if(nume_departament.equals("Finance"))
                    my_application.getCompanies().get(i).departamente.get(0).add(employee);
                if(nume_departament.equals("IT"))
                    my_application.getCompanies().get(i).departamente.get(1).add(employee);
                if(nume_departament.equals("Marketing"))
                    my_application.getCompanies().get(i).departamente.get(2).add(employee);
                if(nume_departament.equals("Management"))
                    my_application.getCompanies().get(i).departamente.get(3).add(employee);
                break;
            }
        }
    }
    public static void parseExperience(JSONObject job, Application my_application,Recruiter employee) {
        String nume_companie = (String) job.get("company");
        employee.nume_companie = nume_companie;
        for(int i = 0; i < my_application.getCompanies().size(); i++) {
            if(my_application.getCompanies().get(i).nume_companie.equals(nume_companie)) {
                my_application.getCompanies().get(i).add(employee);
                break;
            }
        }
    }
    public static void parseExperience(JSONObject job, Application my_application,Manager employee) {
        String nume_companie = (String) job.get("company");
        employee.nume_companie = nume_companie;
        for(int i = 0; i < my_application.getCompanies().size(); i++) {
            if(my_application.getCompanies().get(i).nume_companie.equals(nume_companie)) {
                my_application.getCompanies().get(i).manager = employee;
                break;
            }
        }
    }
}

