import java.util.ArrayList;
import java.util.List;

public class Information {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private String data_nastere;
    private String sex;
    private ArrayList<ArrayList<String>> limbi = new ArrayList<ArrayList<String>>(3);
    String getNume() {
        return nume;
    }
    void setNume(String nume) {
        this.nume = nume;
    }
    String getPrenume() {
        return prenume;
    }
    void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    String getEmail() {
        return email;
    }
   void setEmail(String email) {
        this.email = email;
    }
    String getTelefon() {
        return telefon;
    }
    void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    String getData_nastere() {
        return data_nastere;
    }
    void setData_nastere(String data_nastere) {
        this.data_nastere = data_nastere;
    }
    String getSex() {
        return sex;
    }
    void setSex(String sex) {
        this.sex = sex;
    }
    ArrayList<ArrayList<String>> getLimbi() {
        return limbi;
    }
    void setLimbi(ArrayList<String> Beginner, ArrayList<String> Advanced, ArrayList<String> Experienced) {
        this.limbi = new ArrayList<ArrayList<String>>(3);
        this.limbi.add(Beginner);
        this.limbi.add(Advanced);
        this.limbi.add(Experienced);
    }
    String getNumeComplet() {
        return prenume + " " + nume;
    }
    public String afisare_limbi(int i) {
        String nou = "";
        if(i == 0) {
            for (int j = 0; j < limbi.get(0).size(); j++)
                nou = nou + limbi.get(0).get(j) + ", ";
        }
        else if(i == 1) {
            for (int j = 0; j < limbi.get(1).size(); j++)
                nou = nou + limbi.get(1).get(j) + ", ";
        }
        else if(i == 2) {
            for (int j = 0; j < limbi.get(2).size(); j++)
                nou = nou + limbi.get(2).get(j) + ", ";
        }
        return nou;
    }
}
