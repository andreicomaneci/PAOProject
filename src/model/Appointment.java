package model;

import java.util.Objects;

public class Appointment {

    private Long id;
    private String date;
    private Person person;
    private Medic medic;

    public Appointment() {
    }

    public Appointment(Long id, String date, Person person, Medic medic) {
        this.id = id;
        this.date = date;
        this.person = person;
        this.medic = medic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", person=" + person +
                ", medic=" + medic +
                '}';
    }
}
