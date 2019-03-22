package service;

import model.*;
import serviceCSV.MedicReadWriteCSVService;

import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;

public class MedicService {

    private MedicalOfficeService medicalOfficeService;
    private MedicReadWriteCSVService medicReadWriteCSVService = new MedicReadWriteCSVService(this);

    public MedicService(MedicalOfficeService medicalOfficeService) {
        this.medicalOfficeService = medicalOfficeService;
    }

    public MedicalOfficeService getMedicalOfficeService() {
        return medicalOfficeService;
    }

    public Medic getMedicById(Long id) {
        Medic medic1 = new Medic();
        medic1.setId(id);
        Medic medic2 = new Medic();
        medic2.setId(++id);
        SortedSet<Medic> medics = medicalOfficeService.getMedicalOffice().getMedics().subSet(medic1,medic2);
        medic1 = medics.first();
        return medic1;
    }

    public void addMedic(Medic medic) {
        medicalOfficeService.getMedicalOffice().getMedics().add(medic);
    }

    public void updatePatientData(Patient patient, Scanner scanner) {
        System.out.println("height: "+patient.getHeight()+"\n");
        System.out.println("weight: "+patient.getWeight()+"\n");
        System.out.println("New data: \n");
        String[] input = scanner.nextLine().split(" ");
        patient.setHeight(Integer.parseInt(input[0]));
        patient.setWeight(Integer.parseInt(input[1]));
    }

    public Papers doConsult(Consult consult) {
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update patient data? yes : no \n");
        input = scanner.nextLine();
        if(input.trim().equals("yes")) {
            updatePatientData(consult.getPatient(),scanner);
        }

        System.out.println("See patient history? yes : no \n");
        input = scanner.nextLine();
        if(input.trim().equals("yes")) {
            List<Consult> consults = medicalOfficeService.getConsultService().getConsultsByPatient(consult.getPatient());
            for (Consult consult1 : consults) {
                System.out.println(consult1.getDate()+"\n"+consult1.getDiagnosis()+"\n"+consult1.getRecipe()+"\n\n");
            }
        }

        System.out.println("Diagnosis: \n");
        input = scanner.nextLine();
        consult.setDiagnosis(input);

        Recipe recipe=null;
        System.out.println("Write recipe? yes : no \n");
        input = scanner.nextLine();
        if(input.trim().equals("yes")) {
            System.out.println("Recipe: \n");
            input = scanner.nextLine();
            recipe = new Recipe(consult.getDate(),input,medicalOfficeService.getMedicalOffice().getMedicalOfficeStamp());
            consult.setRecipe(input);
        }

        MedicalLeave medicalLeave=null;
        System.out.println("Write medical leave? yes : no \n");
        input = scanner.nextLine();
        if(input.trim().equals("yes")) {
            System.out.println("Availability Date: \n");
            input = scanner.nextLine();
            medicalLeave = new MedicalLeave(consult.getDate(),input, consult.getPatient().getName(),consult.getDiagnosis(),medicalOfficeService.getMedicalOffice().getMedicalOfficeStamp());
        }

        consult.getMedic().getAppointments().remove(consult.getDate());
        medicalOfficeService.getConsultService().addConsult(consult);

        return new Papers(recipe,medicalLeave);
    }
}
