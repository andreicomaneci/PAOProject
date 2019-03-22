package service;

import model.*;

import java.util.*;

public class ReceptionService {

    private AppointmentService appointmentService = new AppointmentService(this);
    private MedicalOfficeService medicalOfficeService;
    private Reception reception;

    public ReceptionService(MedicalOfficeService medicalOfficeService) {
        this.medicalOfficeService = medicalOfficeService;
        reception = medicalOfficeService.getMedicalOffice().getReception();
    }

    public MedicalOfficeService getMedicalOfficeService() {
        return medicalOfficeService;
    }

    public Reception getReception() {
        return reception;
    }

    //face programare din alta sursa (telefon, site, etc)
    public Appointment makeAppointment(String date, Long medicId, String personName) {
        Medic medic = medicalOfficeService.getMedicService().getMedicById(medicId);
        if(medic==null) throw new RuntimeException("The medic "+medicId+" does not exist!");

        if(medic.getAppointments().contains(date)) throw new RuntimeException("The medic "+medic.getName()+" is busy at "+date+" !");

        Person person = new Person();
        person.setName(personName);

        Long id = medicalOfficeService.getMedicalOffice().getIdGenerator().getAppointmentId();
        String key = personName + id.toString();
        Appointment appointment = new Appointment(id,date,person,medic);
        reception.getAppointments().put(key,appointment);
        return appointment;
    }

    //face programare la receptie (in persoana)
    public Appointment makeAppointmentForPatient(String date, Long medicId, String personCnp) {
        Medic medic = medicalOfficeService.getMedicService().getMedicById(medicId);
        if(medic==null) throw new RuntimeException("The medic "+medicId+" does not exist!");

        if(medic.getAppointments().contains(date)) throw new RuntimeException("The medic "+medic.getName()+" is busy at "+date+" !");

        Boolean isCnp = true;
        for(char c : personCnp.toCharArray()) {
            if(!Character.isDigit(c)) isCnp = false;
        }
        if(!isCnp) throw new RuntimeException("Cnp is wrong!");
        Person person = medicalOfficeService.getMedicalOffice().getPatients().get(personCnp);
        if(person==null) throw new RuntimeException("The person is not found in database!");

        Long id = medicalOfficeService.getMedicalOffice().getIdGenerator().getAppointmentId();
        String key = personCnp + id.toString();
        Appointment appointment = new Appointment(id,date,person,medic);
        reception.getAppointments().put(key,appointment);
        return appointment;
    }


    public void removeAppointment(String key) {
        reception.getAppointments().remove(key);
    }

    public void removeAppointment(Appointment appointment) {
        String key;
        String personId = appointment.getPerson().getCnp();
        if(personId == null) {
            personId = appointment.getPerson().getName();
            key = personId + appointment.getId();
            reception.getAppointments().remove(key);
        }
        else {
            key = personId + appointment.getId();
            reception.getAppointments().remove(key);
            personId = appointment.getPerson().getName();
            key = personId + appointment.getId();
            reception.getAppointments().remove(key);
        }
    }

    public List<Appointment> getAppointmentsByPerson(String personId) {
        String nextKey = personId.substring(0, personId.length()-1);
        char c = personId.charAt(personId.length()-1);
        c++;
        nextKey = nextKey+c;

        Collection<Appointment> appointments = reception.getAppointments().subMap(personId,nextKey).values();

        Boolean isCnp = true;
        for(char cc : personId.toCharArray()) {
            if(!Character.isDigit(cc)) isCnp = false;
        }
        if(isCnp) {
            Person person = medicalOfficeService.getMedicalOffice().getPatients().get(personId);
            if(person!=null) {
                String personName = person.getName();
                nextKey = personId.substring(0, personId.length()-1);
                c = personId.charAt(personId.length()-1);
                c++;
                nextKey = nextKey+c;
                appointments.addAll(reception.getAppointments().subMap(personName,nextKey).values());
            }
        }
        return new ArrayList<>(appointments);
    }

    //incepe consult in urma programarii
    public Papers beginConsult(String appointmentKey, String personName, String personCnp, String personBirthDate) {
        Appointment appointment = reception.getAppointments().get(appointmentKey);

        Patient patient = getMedicalOfficeService().getMedicalOffice().getPatients().get(personCnp);
        if(patient == null) {
            patient = new Patient();
            patient.setName(personName);
            patient.setCnp(personCnp);
            patient.setBirthDate(personBirthDate);
            Long id = reception.getMedicalOffice().getIdGenerator().getPatientId();
            patient.setId(id);
            medicalOfficeService.getPatientService().addPatient(patient);
        }
        Long id = reception.getMedicalOffice().getIdGenerator().getConsultId();
        Consult consult = new Consult();
        consult.setId(id);
        consult.setDate(appointment.getDate());
        consult.setMedic(appointment.getMedic());
        consult.setPatient(patient);
        removeAppointment(appointmentKey);
        //de la medic il scoatem la sfarsitul consultului

        return medicalOfficeService.getMedicService().doConsult(consult);
    }

    //incepe consult fara programare
    public Papers beginConsult(String date, Long medicId, String personName, String personCnp, String personBirthDate) {
        Medic medic = medicalOfficeService.getMedicService().getMedicById(medicId);
        if(medic==null) throw new RuntimeException("The medic "+medicId+" does not exist!");

        if(medic.getAppointments().contains(date)) throw new RuntimeException("The medic "+medic.getName()+" is busy at "+date+" !");

        Patient patient = getMedicalOfficeService().getMedicalOffice().getPatients().get(personCnp);
        if(patient == null) {
            patient = new Patient();
            patient.setName(personName);
            patient.setCnp(personCnp);
            patient.setBirthDate(personBirthDate);
            Long id = reception.getMedicalOffice().getIdGenerator().getPatientId();
            patient.setId(id);
            medicalOfficeService.getPatientService().addPatient(patient);
        }
        Long id = reception.getMedicalOffice().getIdGenerator().getConsultId();
        Consult consult = new Consult();
        consult.setId(id);
        consult.setDate(date);
        consult.setMedic(medic);
        consult.setPatient(patient);

        return medicalOfficeService.getMedicService().doConsult(consult);
    }
}
