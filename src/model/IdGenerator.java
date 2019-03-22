package model;

import java.util.Objects;

import static java.lang.Long.valueOf;

public class IdGenerator {

    private Long patientId;
    private Long medicId;
    private Long consultId;
    private Long appointmentId;

    public IdGenerator() {
        patientId = valueOf(1);
        medicId = valueOf(1);
        consultId = valueOf(1);
        appointmentId = valueOf(1);
    }

    public Long getPatientId() {
        return patientId++;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId+1;
    }

    public Long getMedicId() {
        return medicId++;
    }

    public void setMedicId(Long medicId) {
        this.medicId = medicId+1;
    }

    public Long getConsultId() {
        return consultId++;
    }

    public void setConsultId(Long consultId) {
        this.consultId = consultId+1;
    }

    public Long getAppointmentId() {
        return appointmentId++;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId+1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdGenerator that = (IdGenerator) o;
        return Objects.equals(patientId, that.patientId) &&
                Objects.equals(medicId, that.medicId) &&
                Objects.equals(consultId, that.consultId) &&
                Objects.equals(appointmentId, that.appointmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, medicId, consultId, appointmentId);
    }

    @Override
    public String toString() {
        return "IdGenerator{" +
                "patientId=" + patientId +
                ", medicId=" + medicId +
                ", consultId=" + consultId +
                ", appointmentId=" + appointmentId +
                '}';
    }
}
