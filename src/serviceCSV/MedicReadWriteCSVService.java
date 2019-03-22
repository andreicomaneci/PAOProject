package serviceCSV;

import model.Medic;
import service.MedicService;

import java.util.*;

import static java.lang.Long.valueOf;

public class MedicReadWriteCSVService {

    private String fileName = "src/dataFiles/Medic.csv";
    private ReadWriteCSVService readWriteCSVService = new ReadWriteCSVService();
    private MedicService medicService;

    public MedicReadWriteCSVService(MedicService medicService) {
        this.medicService = medicService;
    }

    public void getMedics() {
        List<List<String>> records;
        records = readWriteCSVService.readFrom(fileName);
        SortedSet<Medic> medics = new TreeSet<>(Comparator.comparing(Medic::getId));
        //Long id, String specializations, String name, String cnp, String birthDate, SortedSet<String> appointments
        for (List<String> record: records) {
            Long id = valueOf(Long.parseLong(record.get(0)));
            String specializations = record.get(1);
            String name = record.get(2);
            String cnp = record.get(3);
            String birthDate = record.get(4);
            List<String> appointmentsRecords = record.subList(5,record.size());
            SortedSet<String> appointments = new TreeSet<>();
            appointments.addAll(appointmentsRecords);
            Medic medic = new Medic(id,specializations,name,cnp,birthDate,appointments);
            medics.add(medic);
        }
        if(!medics.isEmpty()) medicService.getMedicalOfficeService().getMedicalOffice().getIdGenerator().setMedicId(medics.last().getId());
        else medicService.getMedicalOfficeService().getMedicalOffice().getIdGenerator().setMedicId(valueOf(0));
        medicService.getMedicalOfficeService().getMedicalOffice().setMedics(medics);
    }

    public void saveMedics() {
        SortedSet<Medic> medics = medicService.getMedicalOfficeService().getMedicalOffice().getMedics();
        List< List<String> > data = new ArrayList<>();
        for (Medic medic : medics) {
            List<String> record = new ArrayList<>();
            record.add(medic.getId().toString());
            record.add(medic.getSpecializations());
            record.add(medic.getName());
            record.add(medic.getCnp());
            record.add(medic.getBirthDate());
            record.addAll(medic.getAppointments());
            data.add(record);
        }
        readWriteCSVService.writeTo(fileName,data);
    }
}
