package service;

import serviceCSV.AppointmentReadWriteCSVService;

public class AppointmentService {

    private AppointmentReadWriteCSVService appointmentReadWriteCSVService = new AppointmentReadWriteCSVService(this);
    private ReceptionService receptionService;

    public AppointmentService(ReceptionService receptionService) {
        this.receptionService = receptionService;
    }

    public ReceptionService getReceptionService() {
        return receptionService;
    }
}
