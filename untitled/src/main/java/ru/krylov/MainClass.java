package ru.krylov;

import ru.krylov.beans.Patient;
import ru.krylov.beans.Polis;
import ru.krylov.dao.DAO;
import ru.krylov.dao.PatientDAO;
import ru.krylov.dao.PolisDAO;
import ru.krylov.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
    // private static DAO<Patient, Integer> daoPatient;
    // private static DAO<Polis, Integer> daoPolis;
    // private static Connection connection;
    // private static String user = "root";
    // private static String password = "125785";
    // private static String url = "jdbc:mysql://localhost:3306/rps";
    private static ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");

    public static void main(String[] args) throws IOException, ParseException {

        // ServiceClass.InitService();
        // ServiceClass serviceClass = new ServiceClass();

        ServiceClass serviceClass = context.getBean("ServiceDAO", ServiceClass.class);
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(
                    "0 - exit \n1 - create patient \n2 - create polis \n3 - update patient \n4 - update polis \n5 - delete patient \n6 - delete polis \n7 - read patient \n8 - read polis \n9 - get all polis \n10 - get all patients\n11 - get all polises with company\n12 - get list of companies\n13 - get list of Patients who has same Polis company\n");
            String str = br.readLine();
            switch (str) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    serviceClass.createPatient();
                    break;
                case "2":
                    serviceClass.createPolis();
                    break;
                case "3":
                    serviceClass.updatePatient();
                    break;
                case "4":
                    serviceClass.updatePolis();
                    break;
                case "5":
                    serviceClass.deletePatient();
                    break;
                case "6":
                    serviceClass.deletePolis();
                    break;
                case "7":
                    serviceClass.findPatient();
                    break;
                case "8":
                    serviceClass.findPolis();
                    break;
                case "9":
                    serviceClass.getAllPolis();
                    break;
                case "10":
                    serviceClass.getAllPatients();
                    break;
                case "11":
                    serviceClass.getAllPolisesByCompany();
                    break;
                case "12":
                    serviceClass.getAllPolisCompanies();
                    break;
                case "13":
                    serviceClass.getAllPatientsWithPolisCompany();
                default:
                    System.exit(0);
                    break;

            }
        } while (true);
    }
}
