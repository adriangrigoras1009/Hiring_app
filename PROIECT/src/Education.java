import java.time.LocalDate;

public class Education implements Comparable {
    LocalDate inceput;
    LocalDate sfarsit;

    String nume;
    String nivel;
    double media_de_finalizare;

    public Education() {
        this.nume = "";
        this.nivel = "";
    }
    public Education(String nume, String nivel) {
        this.nume = nume;
        this.nivel = nivel;
    }
    public Education(String nume, String nivel, LocalDate inceput, LocalDate sfarsit, double media_de_finalizare) {
        this.nume = nume;
        this.nivel = nivel;
        this.inceput = inceput;
        this.sfarsit = sfarsit;
        this.media_de_finalizare = media_de_finalizare;
    }
    public int compareTo(Object o) {
        Education t = (Education) o;
        if(sfarsit != null && t.sfarsit != null) {
            if (sfarsit.compareTo(t.sfarsit) > 0)
                return 1;
            else if (sfarsit.compareTo(t.sfarsit) < 0)
                return -1;
            else if (sfarsit.compareTo(t.sfarsit) == 0) {
                if (media_de_finalizare > t.media_de_finalizare)
                    return 1;
                else if (media_de_finalizare < t.media_de_finalizare)
                    return -1;
            }
        }
        else {
            //if(inceput != null && t.inceput != null) {
                if (inceput.compareTo(t.inceput) > 0)
                    return 1;
                else if (inceput.compareTo(t.inceput) < 0)
                    return -1;
                else if (inceput.compareTo(t.inceput) == 0) {
                    if (media_de_finalizare > t.media_de_finalizare)
                        return 1;
                    else if (media_de_finalizare < t.media_de_finalizare)
                        return -1;
                }
          //  }
        }
        return 0;
    }
    public void validare_date() throws InvalidDatesException {
        if(inceput.getYear() > sfarsit.getYear())
            throw new InvalidDatesException("date invalide!");
        else if(inceput.getYear() == sfarsit.getYear()) {
            if(inceput.getMonthValue() > sfarsit.getMonthValue())
                throw new InvalidDatesException("date invalide!");
            else if(inceput.getMonthValue() == sfarsit.getMonthValue())
                if(inceput.getDayOfMonth() > sfarsit.getDayOfMonth())
                    throw new InvalidDatesException("date invalide!");
        }
    }
    public String toString() {
        return "nume: " + nume + "\n\tnivel: " + nivel + "\n\tmedia: " + media_de_finalizare + "\n\tdata_inceput: " + inceput + "\n\tdata_sfarsit: " + sfarsit;
    }
}
