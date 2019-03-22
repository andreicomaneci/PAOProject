package service;

import model.Patient;
import serviceCSV.PatientReadWriteCSVService;

public class PatientService {

    private MedicalOfficeService medicalOfficeService;
    private PatientReadWriteCSVService patientReadWriteCSVService = new PatientReadWriteCSVService(this);

    public PatientService(MedicalOfficeService medicalOfficeService) {
        this.medicalOfficeService = medicalOfficeService;
    }

    public MedicalOfficeService getMedicalOfficeService() {
        return medicalOfficeService;
    }


    public void addPatient(Patient patient) {
        medicalOfficeService.getMedicalOffice().getPatients().put(patient.getCnp(),patient);
    }
}
