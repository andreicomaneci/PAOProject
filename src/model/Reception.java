package model;

import java.util.Objects;
import java.util.SortedMap;

public class Reception {

    private MedicalOffice medicalOffice;
    private SortedMap<String,Appointment> appointments;

    public Reception(MedicalOffice medicalOffice) {
        this.medicalOffice = medicalOffice;
    }

    public MedicalOffice getMedicalOffice() {
        return medicalOffice;
    }

    public void setMedicalOffice(MedicalOffice medicalOffice) {
        this.medicalOffice = medicalOffice;
    }

    public SortedMap<String, Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(SortedMap<String, Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reception reception = (Reception) o;
        return Objects.equals(medicalOffice, reception.medicalOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicalOffice);
    }

    @Override
    public String toString() {
        return "Reception{" +
                "medicalOffice=" + medicalOffice +
                ", appointments=" + appointments +
                '}';
    }
}
