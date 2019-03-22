package model;

import java.util.Objects;

public class MedicalLeave {
    private String date;
    private String availabilityDate;
    private String personName;
    private String reason;
    private String medicalOfficeStamp;

    public MedicalLeave() {
    }

    public MedicalLeave(String date, String availabilityDate, String personName, String reason, String medicalOfficeStamp) {
        this.date = date;
        this.availabilityDate = availabilityDate;
        this.personName = personName;
        this.reason = reason;
        this.medicalOfficeStamp = medicalOfficeStamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(String availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMedicalOfficeStamp() {
        return medicalOfficeStamp;
    }

    public void setMedicalOfficeStamp(String medicalOfficeStamp) {
        this.medicalOfficeStamp = medicalOfficeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalLeave that = (MedicalLeave) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(availabilityDate, that.availabilityDate) &&
                Objects.equals(personName, that.personName) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(medicalOfficeStamp, that.medicalOfficeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, availabilityDate, personName, reason, medicalOfficeStamp);
    }

    @Override
    public String toString() {
        return "MedicalLeave{" +
                "date='" + date + '\'' +
                ", availabilityDate='" + availabilityDate + '\'' +
                ", personName='" + personName + '\'' +
                ", reason='" + reason + '\'' +
                ", medicalOfficeStamp='" + medicalOfficeStamp + '\'' +
                '}';
    }
}
