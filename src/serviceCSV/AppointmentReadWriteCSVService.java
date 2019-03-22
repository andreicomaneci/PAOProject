package serviceCSV;


import model.Appointment;
import model.Medic;
import model.Person;
import service.AppointmentService;

import java.util.*;

import static java.lang.Long.valueOf;

public class AppointmentReadWriteCSVService {

    private String fileName = "src/dataFiles/Appointment.csv";
    private ReadWriteCSVService readWriteCSVService = new ReadWriteCSVService();
    private AppointmentService appointmentService;

    public AppointmentReadWriteCSVService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void getAppointments() {
        SortedMap<String, Appointment> appointments = new TreeMap<>();
        List<List<String>> records = readWriteCSVService.readFrom(fileName);
        Long maxId = valueOf(0);
        //Long id, String date, Person person, Medic medic
        for(List<String> record : records) {
            Long id = Long.parseLong(record.get(0));
            if(maxId<id) maxId = id;
            String date = record.get(1);

            String personId = record.get(2);
            Person person;
            Boolean isCnp = true;
            for(char c : personId.toCharArray()) {
                if(!Character.isDigit(c)) isCnp = false;
            }
            if(isCnp) {
                person = appointmentService.getReceptionService().getMedicalOfficeService().getMedicalOffice().getPatients().get(personId);
            }
            else {
                person = new Person();
                person.setName(personId);
            }

            Medic medic = appointmentService.getReceptionService().getMedicalOfficeService().getMedicService().getMedicById(Long.parseLong(record.get(3)));

            Appointment appointment = new Appointment(id,date,person,medic);
            String key = personId + id.toString();
            appointments.put(key,appointment);
        }
        appointmentService.getReceptionService().getMedicalOfficeService().getMedicalOffice().getIdGenerator().setAppointmentId(maxId);
        appointmentService.getReceptionService().getReception().setAppointments(appointments);
    }

    public void saveAppointments() {
        Collection<Appointment> appointments = appointmentService.getReceptionService().getReception().getAppointments().values();
        List< List<String> > data = new ArrayList<>();
        for(Appointment appointment : appointments) {
            List<String> record = new ArrayList<>();
            record.add(appointment.getId().toString());
            record.add(appointment.getDate());
            record.add(appointment.getMedic().getId().toString());
            Person person = appointment.getPerson();
            if(person.getCnp()==null) {
                record.add(person.getName());
            }
            else {
                record.add(person.getCnp());
            }
            data.add(record);
        }
        readWriteCSVService.writeTo(fileName,data);
    }
}
