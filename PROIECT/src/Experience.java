import java.time.LocalDate;

public class Experience implements Comparable {
    LocalDate inceput;
    LocalDate sfarsit;

    String nume_companie;
    String pozitia;

    public Experience(String pozitia, LocalDate inceput, LocalDate sfarsit, String nume_companie) {
        this.pozitia = pozitia;
        this.inceput = inceput;
        this.sfarsit = sfarsit;
        this.nume_companie = nume_companie;
    }

    @Override
    public int compareTo(Object o) {
        Experience t = (Experience) o;
        if(sfarsit != null && t.sfarsit != null) {
            if (sfarsit.compareTo(t.sfarsit) > 0) {
                return 1;
            }
            else if (sfarsit.compareTo(t.sfarsit) < 0) {
                return -1;
            }
        }
        else {
            if(nume_companie.compareTo(t.nume_companie) > 0)
                return -1;
            else if(nume_companie.compareTo(t.nume_companie) < 0)
                return 1;
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
        return "nume_companie: " + nume_companie + "\n\tpozitia: " + pozitia + "\n\tdata_inceput: " + inceput + "\n\tdata_sfarsit: " + sfarsit;
    }
}
