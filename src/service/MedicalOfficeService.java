package service;

import model.MedicalOffice;
import model.Patient;

public class MedicalOfficeService {

    private MedicalOffice medicalOffice;
    private MedicService medicService;
    private PatientService patientService;
    private ConsultService consultService;
    private ReceptionService receptionService;

    public MedicalOfficeService() {
        medicalOffice = new MedicalOffice();
        medicService = new MedicService(this);
        patientService = new PatientService(this);
        consultService = new ConsultService(this);
        receptionService = new ReceptionService(this);
    }

    public MedicalOffice getMedicalOffice() {
        return medicalOffice;
    }

    public MedicService getMedicService() {
        return medicService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public ConsultService getConsultService() {
        return consultService;
    }

    public ReceptionService getReceptionService() {
        return receptionService;
    }
}
