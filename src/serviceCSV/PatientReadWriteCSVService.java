package serviceCSV;

import model.Patient;
import service.PatientService;

import java.util.*;

import static java.lang.Long.valueOf;

public class PatientReadWriteCSVService {

    private String fileName = "src/dataFiles/Patient.csv";
    private ReadWriteCSVService readWriteCSVService = new ReadWriteCSVService();
    private PatientService patientService;

    public PatientReadWriteCSVService(PatientService patientService) {
        this.patientService = patientService;
    }

    public void getPatients() {
        List<List<String>> records = readWriteCSVService.readFrom(fileName);
        Map<String, Patient> patients = new Hashtable<>();
        Long maxId = valueOf(0);
        //Long id, String name, String cnp, String birthDate, Integer height, Integer weight
        for (List<String> record : records) {
            Long id = Long.parseLong(record.get(0));
            if(maxId<id) {
                maxId=id;
            }
            String name = record.get(1);
            String cnp = record.get(2);
            String birthDate = record.get(3);
            Integer height = Integer.parseInt(record.get(4));
            Integer weight = Integer.parseInt(record.get(5));
            Patient patient = new Patient(id,name,cnp,birthDate,height,weight);
            patients.put(cnp,patient);
        }
        patientService.getMedicalOfficeService().getMedicalOffice().setPatients(patients);
        patientService.getMedicalOfficeService().getMedicalOffice().getIdGenerator().setPatientId(maxId);
    }

    public void savePatients() {
        Collection<Patient> patients = patientService.getMedicalOfficeService().getMedicalOffice().getPatients().values();
        List< List<String> > data = new ArrayList<>();
        for (Patient patient: patients) {
            List<String> record = new ArrayList<>();
            record.add(patient.getId().toString());
            record.add(patient.getName());
            record.add(patient.getCnp());
            record.add(patient.getBirthDate());
            record.add(patient.getHeight().toString());
            record.add(patient.getWeight().toString());
            data.add(record);
        }
        readWriteCSVService.writeTo(fileName,data);
    }
}
