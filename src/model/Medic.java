package model;

import java.util.Objects;
import java.util.SortedSet;

public class Medic extends Person{

    private Long id;
    private String specializations;
    private SortedSet<String> appointments;

    public Medic() {
    }

    public Medic(Long id, String specializations, SortedSet<String> appointments) {
        this.id = id;
        this.specializations = specializations;
        this.appointments = appointments;
    }

    public Medic(Long id, String specializations, String name, String cnp, String birthDate, SortedSet<String> appointments) {
        super(name, cnp, birthDate);
        this.id = id;
        this.specializations = specializations;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public SortedSet<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(SortedSet<String> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medic medic = (Medic) o;
        return Objects.equals(id, medic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Medic{" +
                "id=" + id +
                ", specializations='" + specializations + '\'' +
                ", appointments=" + appointments +
                ", name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
