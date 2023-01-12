package ru.krylov.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.krylov.beans.Patient;
import ru.krylov.beans.Polis;
import ru.krylov.dao.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ServletServiceClass {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml");
    DAO<Polis,Integer> daoPolisBean;
    DAO<Patient,Integer> daoPatientBean;

    public ServletServiceClass(DAO<Patient,Integer>daoPatientBean,DAO<Polis,Integer> daoPolisBean) {
        this.daoPatientBean = daoPatientBean;
        this.daoPolisBean = daoPolisBean;
    }
    public Integer createPatient(Patient patient) throws IOException {
        return daoPatientBean.create(patient);
    }
    public Patient findPatient(Integer id) throws NumberFormatException, IOException {
        Patient patientToRead = new Patient();
        patientToRead = daoPatientBean.read(id);
        return patientToRead;
    }
    public void deletePatient(Integer id) throws NumberFormatException, IOException {
        daoPatientBean.delete(id);
    }
    public List<Patient> getAllPatients(){
        List<Patient> patientList = null;
        patientList = daoPatientBean.getAll();
        return patientList;
    }
}
