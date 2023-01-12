package ru.krylov.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.protobuf.TextFormat.ParseException;

import ru.krylov.beans.Patient;
import ru.krylov.beans.Polis;
import ru.krylov.dao.DAO;
import ru.krylov.dao.PatientDAO;
import ru.krylov.dao.PolisDAO;

public class ServiceClass {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");

    // private static String user = "root";
    // private static String password = "125785";
    // private static String url = "jdbc:mysql://localhost:3306/rps";

    // public ServiceClass(PatientDAO daoPatient, PolisDAO daoPolis){
    // this.daoPatient = daoPatient;
    // this.daoPolis = daoPolis;
    // }
    DAO<Polis,Integer> daoPolisBean;
    DAO<Patient,Integer> daoPatientBean;
    //PatientDAO daoPatientBean;
    BufferedReader br;

    public ServiceClass(DAO<Patient,Integer>daoPatientBean,DAO<Polis,Integer> daoPolisBean) {
        // daoPolisBean = context.getBean("polisDB", PolisDAO.class);
        // daoPatientBean = context.getBean("patientDB", PatientDAO.class);
        this.daoPatientBean = daoPatientBean;
        this.daoPolisBean = daoPolisBean;
        br = new BufferedReader(new InputStreamReader(System.in));
    }



    public void createPatient() throws IOException {
        Patient patient = new Patient();
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // ServiceClass serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
        System.out.println("Name: ");
        patient.setName(br.readLine());
        System.out.println("Snils Num: ");
        patient.setSnilsNum(Integer.parseInt(br.readLine()));
        System.out.println("Polis Num");
        patient.setPolisId(Integer.parseInt(br.readLine()));
        System.out.println("Generated ID:");
        // System.out.println(serviceClass.daoPatient.create(patient));
        System.out.println(daoPatientBean.create(patient));
    }

    public void createPolis() throws java.text.ParseException, IOException {
        try {
            Polis polis = new Polis();
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // ServiceClass serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
            System.out.println("Polis id:");
            polis.setId(Integer.parseInt(br.readLine()));
            System.out.println("Polis company: ");
            polis.setCompany(br.readLine());
            java.util.Date date1 = null;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("End date (dd/MM/yyyy):");
            date1 = (java.util.Date) format.parse(br.readLine());
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
            polis.setEndDate(sqlDate);
            System.out.println("Generated ID:");
            // System.out.println(serviceClass.daoPolis.create(polis));
            System.out.println(daoPolisBean.create(polis));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient() throws IOException {
        Patient patient = new Patient();

        // ServiceClass serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
        System.out.println("Id: ");
        patient.setId(Integer.parseInt(br.readLine()));
        System.out.println("Name: ");
        patient.setName(br.readLine());
        System.out.println("Snils num: ");
        patient.setSnilsNum(Integer.parseInt(br.readLine()));
        System.out.println("Polis id: ");

        patient.setPolisId(Integer.parseInt(br.readLine()));
        // serviceClass.daoPatient.update(patient);
        daoPatientBean.update(patient);
    }

    public void updatePolis() throws NumberFormatException, IOException, java.text.ParseException {
        Polis polis = new Polis();
        // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);

        System.out.println("Id: ");
        polis.setId(Integer.parseInt(br.readLine()));
        System.out.println("Company: ");
        polis.setCompany(br.readLine());
        java.sql.Date sqlDate;
        java.util.Date date1 = null;
        System.out.println("End date (dd/MM/yyyy):");
        try {
            String sDate1 = br.readLine();
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            sqlDate = new Date(date1.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        polis.setEndDate(sqlDate);

        // serviceClass.daoPolis.update(polis);
        daoPolisBean.update(polis);
    }

    public void deletePatient() throws NumberFormatException, IOException {
        System.out.println("Id: ");

        // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
        // serviceClass.daoPolis.delete(Integer.parseInt(br.readLine()));
        daoPatientBean.delete(Integer.parseInt(br.readLine()));
    }

    public void deletePolis() throws NumberFormatException, IOException {
        System.out.println("Id: ");
        daoPolisBean.delete(Integer.parseInt(br.readLine()));
    }

    public void findPatient() throws NumberFormatException, IOException {
        // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
        Patient patientToRead = new Patient();
        System.out.println("Id: ");
        // patientToRead =
        // serviceClass.daoPatient.read(Integer.parseInt(br.readLine()));
        patientToRead = daoPatientBean.read(Integer.parseInt(br.readLine()));
        System.out.println(patientToRead.toString());
    }

    public void findPolis() throws NumberFormatException, IOException {
        // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
        Polis polisToRead = new Polis();
        System.out.println("Id: ");
        // polisToRead = serviceClass.daoPolis.read(Integer.parseInt(br.readLine()));
        polisToRead = daoPolisBean.read(Integer.parseInt(br.readLine()));
        System.out.println(polisToRead.toString());
    }

    public void getAllPolis() {
        List<Polis> polisList = null;
        // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
        // polisList = serviceClass.daoPolis.getAll();
        polisList = daoPolisBean.getAll();
        System.out.println("All polises:");
        if (polisList == null) {
            System.out.println("No polises found ");
        } else {
            polisList.forEach(System.out::println);
            System.out.println("");
        }
    }

    public void getAllPatients() {
        List<Patient> patientList = null;
        patientList = daoPatientBean.getAll();
        System.out.println("All patients");
        if (patientList == null) {
            System.out.println("No patients found ");
        } else {
            patientList.forEach(System.out::println);
            System.out.println("");
        }
    }
    public void getAllPatientsByPolisCompany() throws IOException{
        List<Patient> patientList = null;
        List<Polis> polisList = null;
        System.out.println("Type company name");
        String company = br.readLine();
        polisList = daoPolisBean.getAllFiltered("company",company);
        polisList.forEach(System.out::println);

    }
    public void getAllPolisesByCompany() throws IOException{
        List<Polis> polisList = null;
        System.out.println("Type company");
        String company = br.readLine();
        polisList = daoPolisBean.getAllFiltered("company",company);
        polisList.forEach(System.out::println);
    }
    public List<String> getAllPolisCompanies() throws IOException{
        String field = "company";
        System.out.println("Companies:\n");
        List<String> companiesList = new ArrayList<>();
        companiesList = daoPolisBean.getAllGroupedBy(field);
        for(int i=0;i<companiesList.size();i++){
            System.out.println(i+") "+companiesList.get(i));
        }
        System.out.println("\n");
        return companiesList;
        //Integer companyIndex = Integer.parseInt(br.readLine());
    }
    public void getAllPatientsWithPolisCompany() throws IOException{
        List<String> companiesList = getAllPolisCompanies();
        String field = "company";
        Integer companyIndex = Integer.parseInt(br.readLine());
        List<Patient> patientList = new ArrayList<>();
        patientList = daoPatientBean.getAllWhere(field, companiesList.get(companyIndex));
        patientList.forEach(System.out::println);
    }
        /* 
    public static void InitService() throws IOException, java.text.ParseException {
         * try {
         * connection = DriverManager.getConnection(url, user, password);
         * daoPatient = new PatientDAO(connection);
         * daoPolis = new PolisDAO(connection);
         * } catch (SQLException e) {
         * e.printStackTrace();
         * }

        do {
            PolisDAO daoPolisBean = context.getBean("polisDB", PolisDAO.class);
            PatientDAO daoPatientBean = context.getBean("patientDB", PatientDAO.class);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(
                    "0 - exit \n1 - create patient \n2 - create polis \n3 - update patient \n4 - update polis \n5 - delete patient \n6 - delete polis \n7 - read patient \n8 - read polis \n9 - get all polis \n10 - get all patients\n");
            String str = br.readLine();
            switch (str) {
                case "0":
                    System.exit(0);
                    break;
                case "1": {
                    Patient patient = new Patient();
                    // ServiceClass serviceClass = context.getBean("ServiceDAO",ServiceClass.class);

                    System.out.println("Name: ");
                    patient.setName(br.readLine());
                    System.out.println("Snils Num: ");
                    patient.setSnilsNum(Integer.parseInt(br.readLine()));
                    System.out.println("Polis Num");
                    patient.setPolisId(Integer.parseInt(br.readLine()));
                    System.out.println("Generated ID:");
                    // System.out.println(serviceClass.daoPatient.create(patient));
                    System.out.println(daoPatientBean.create(patient));
                }
                    continue;
                case "2":
                    try {
                        Polis polis = new Polis();
                        // ServiceClass serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
                        System.out.println("Polis id:");
                        polis.setId(Integer.parseInt(br.readLine()));
                        System.out.println("Polis company: ");
                        polis.setCompany(br.readLine());
                        java.util.Date date1 = null;
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("End date (dd/MM/yyyy):");
                        date1 = (java.util.Date) format.parse(br.readLine());
                        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
                        polis.setEndDate(sqlDate);
                        System.out.println("Generated ID:");
                        // System.out.println(serviceClass.daoPolis.create(polis));
                        System.out.println(daoPolisBean.create(polis));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    continue;
                case "3":
                    Patient patient = new Patient();
                    // ServiceClass serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
                    System.out.println("Id: ");
                    patient.setId(Integer.parseInt(br.readLine()));
                    System.out.println("Name: ");
                    patient.setName(br.readLine());
                    System.out.println("Snils num: ");
                    patient.setSnilsNum(Integer.parseInt(br.readLine()));
                    System.out.println("Polis id: ");

                    patient.setPolisId(Integer.parseInt(br.readLine()));
                    // serviceClass.daoPatient.update(patient);
                    daoPatientBean.update(patient);
                    continue;
                case "4":
                    Polis polis = new Polis();
                    // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);

                    System.out.println("Id: ");
                    polis.setId(Integer.parseInt(br.readLine()));
                    System.out.println("Company: ");
                    polis.setCompany(br.readLine());
                    java.sql.Date sqlDate;
                    java.util.Date date1 = null;
                    System.out.println("End date (dd/MM/yyyy):");
                    try {
                        String sDate1 = br.readLine();
                        date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                        sqlDate = new Date(date1.getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    polis.setEndDate(sqlDate);

                    // serviceClass.daoPolis.update(polis);
                    daoPolisBean.update(polis);
                    continue;
                case "5":
                    System.out.println("Id: ");

                    // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
                    // serviceClass.daoPolis.delete(Integer.parseInt(br.readLine()));
                    daoPatientBean.delete(Integer.parseInt(br.readLine()));
                    continue;
                case "6":
                    System.out.println("Id: ");
                    daoPolisBean.delete(Integer.parseInt(br.readLine()));
                    continue;
                case "7":
                    // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
                    Patient patientToRead = new Patient();
                    System.out.println("Id: ");
                    // patientToRead =
                    // serviceClass.daoPatient.read(Integer.parseInt(br.readLine()));
                    patientToRead = daoPatientBean.read(Integer.parseInt(br.readLine()));
                    System.out.println(patientToRead.toString());
                    continue;
                case "8":
                    // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
                    Polis polisToRead = new Polis();
                    System.out.println("Id: ");
                    // polisToRead = serviceClass.daoPolis.read(Integer.parseInt(br.readLine()));
                    polisToRead = daoPolisBean.read(Integer.parseInt(br.readLine()));
                    System.out.println(polisToRead.toString());
                    continue;
                case "9":

                    List<Polis> polisList = null;
                    // serviceClass = context.getBean("ServiceDAO",ServiceClass.class);
                    // polisList = serviceClass.daoPolis.getAll();
                    polisList = daoPolisBean.getAll();
                    System.out.println("All polises:");
                    if (polisList == null) {
                        System.out.println("No polises found ");
                    } else {
                        polisList.forEach(System.out::println);
                        System.out.println("");
                    }
                    continue;
                case "10":
                    List<Patient> patientList = null;
                    patientList = daoPatientBean.getAll();
                    System.out.println("All patients");
                    if (patientList == null) {
                        System.out.println("No patients found ");
                    } else {
                        patientList.forEach(System.out::println);
                        System.out.println("");
                    }
                    continue;
                default:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
    */
}
