package model;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;

public class MedicalOffice {

    private String name;
    private Reception reception = new Reception(this);
    private String medicalOfficeStamp;
    private SortedSet<Medic> medics;
    private Map<String, Patient> patients;
    private SortedMap<String,Consult> consults;

    private IdGenerator idGenerator = new IdGenerator();

    public MedicalOffice() {
        this.name = "Cabinet Medical Universitatea din Bucuresti";
        this.medicalOfficeStamp = "Cabinet Medical Universitatea din Bucuresti\n30028395";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }

    public String getMedicalOfficeStamp() {
        return medicalOfficeStamp;
    }

    public void setMedicalOfficeStamp(String medicalOfficeStamp) {
        this.medicalOfficeStamp = medicalOfficeStamp;
    }

    public SortedSet<Medic> getMedics() {
        return medics;
    }

    public void setMedics(SortedSet<Medic> medics) {
        this.medics = medics;
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    public void setPatients(Map<String, Patient> patients) {
        this.patients = patients;
    }

    public SortedMap<String, Consult> getConsults() {
        return consults;
    }

    public void setConsults(SortedMap<String, Consult> consults) {
        this.consults = consults;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalOffice that = (MedicalOffice) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(medicalOfficeStamp, that.medicalOfficeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, medicalOfficeStamp);
    }

    @Override
    public String toString() {
        return "MedicalOffice{" +
                "name='" + name + '\'' +
                ", reception=" + reception +
                ", medicalOfficeStamp='" + medicalOfficeStamp + '\'' +
                ", medics=" + medics +
                ", patients=" + patients +
                ", consults=" + consults +
                ", idGenerator=" + idGenerator +
                '}';
    }
}
