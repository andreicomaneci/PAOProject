package model;

import java.util.Objects;

public class Consult {

    private Long id;
    private String date;
    private Medic medic;
    private Patient patient;
    private String diagnosis;
    private String recipe;

    public Consult() {
    }

    public Consult(Long id, String date, Medic medic, Patient patient, String diagnosis, String recipe) {
        this.id = id;
        this.date = date;
        this.medic = medic;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.recipe = recipe;
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

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consult consult = (Consult) o;
        return Objects.equals(id, consult.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Consult{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", medic=" + medic +
                ", patient=" + patient +
                ", diagnosis='" + diagnosis + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
