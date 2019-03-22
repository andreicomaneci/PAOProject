package service;

import model.Consult;
import model.Patient;
import serviceCSV.ConsultReadWriteCSVService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ConsultService {

    private ConsultReadWriteCSVService consultReadWriteCSVService = new ConsultReadWriteCSVService(this);
    private MedicalOfficeService medicalOfficeService;

    public ConsultService(MedicalOfficeService medicalOfficeService) {
        this.medicalOfficeService = medicalOfficeService;
    }

    public MedicalOfficeService getMedicalOfficeService() {
        return medicalOfficeService;
    }

    public List<Consult> getConsultsByPatient(Patient patient) {
        String key = patient.getCnp();
        String nextKey = key.substring(0, key.length()-1);
        char c = key.charAt(key.length()-1);
        c++;
        nextKey = nextKey+c;
        Collection<Consult> consultCollection= medicalOfficeService.getMedicalOffice().getConsults().subMap(key,nextKey).values();
        List<Consult> consults = new ArrayList<>(consultCollection);
        consults.sort(Comparator.comparing(Consult::getId));
        return consults;
    }

    public void addConsult(Consult consult) {
        String key = consult.getPatient().getCnp()+consult.getId();
        medicalOfficeService.getMedicalOffice().getConsults().put(key,consult);
    }
}
