package serviceCSV;

import model.Consult;
import model.Medic;
import model.Patient;
import service.ConsultService;

import java.util.*;

import static java.lang.Long.valueOf;

public class ConsultReadWriteCSVService {

    private String fileName = "src/dataFiles/Consult.csv";
    private ReadWriteCSVService readWriteCSVService = new ReadWriteCSVService();
    private ConsultService consultService;

    public ConsultReadWriteCSVService(ConsultService consultService) {
        this.consultService = consultService;
    }

    public void getConsults() {
        SortedMap<String, Consult> consults = new TreeMap<>();
        List<List<String>> records = readWriteCSVService.readFrom(fileName);
        Long maxId = valueOf(0);
        //Long id, String date, Medic medic, Patient patient, String diagnosis, String recipe
        for(List<String> record : records) {
            Long id = valueOf(Long.parseLong(record.get(0)));
            if (id > maxId) maxId = id;
            String date = record.get(1);
            Medic medic = consultService.getMedicalOfficeService().getMedicService().getMedicById(valueOf(Long.parseLong(record.get(2))));
            Patient patient = consultService.getMedicalOfficeService().getMedicalOffice().getPatients().get(record.get(3));
            String diagnosis = record.get(4);
            int existsRecipe = Integer.parseInt(record.get(5));
            String recipe = null;
            if (existsRecipe == 1) {
                recipe = record.get(6);
            }
            Consult consult = new Consult(id, date, medic, patient, diagnosis, recipe);
            String key = patient.getCnp() + id.toString();
            consults.put(key, consult);
        }
        consultService.getMedicalOfficeService().getMedicalOffice().setConsults(consults);
        consultService.getMedicalOfficeService().getMedicalOffice().getIdGenerator().setConsultId(maxId);
    }

    public void saveConsults() {
        Collection<Consult> consults = consultService.getMedicalOfficeService().getMedicalOffice().getConsults().values();
        List< List<String> > data = new ArrayList<>();
        for(Consult consult : consults) {
            List<String> record = new ArrayList<>();
            record.add(consult.getId().toString());
            record.add(consult.getDate());
            record.add(consult.getMedic().getId().toString());
            record.add(consult.getPatient().getCnp());
            record.add(consult.getDiagnosis());
            String recipe = consult.getRecipe();
            if(recipe!=null) {
                record.add("1");
                record.add(recipe);
            }
            else {
                record.add("0");
            }
            data.add(record);
        }
        readWriteCSVService.writeTo(fileName,data);
    }
}
