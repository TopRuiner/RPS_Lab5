// package ru.krylov.jdbc;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;

// import ru.krylov.beans.Patient;
// import ru.krylov.beans.Polis;
// import ru.krylov.dao.DAO;
// import ru.krylov.dao.PatientDAO;
// import ru.krylov.dao.PolisDAO;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.sql.*;

// import static org.assertj.core.api.Assertions.*;

// public class AllDAOTest {
//     private DAO<Patient,Integer> daoPatient;
//     private DAO<Polis,Integer> daoPolis;
//     private Connection connection;
//     @Before
//     public void before(){
//         try {
//             String user = "root";
//             String password = "125785";
//             String url = "jdbc:mysql://localhost:3306/rps";
//             connection = DriverManager.getConnection(url, user, password);
//             daoPatient = new PatientDAO(connection);
//             daoPolis = new PolisDAO(connection);
//         }
//         catch (SQLException e){
//             e.printStackTrace();
//         }
//     }
//     @After
//     public void after(){
//         try{
//             connection.close();
//         }
//         catch (SQLException e){
//             e.printStackTrace();
//         }
//     }
//     @Test
//     public void getPatientThatExists(){
//         final Patient actual = daoPatient.read(3);
//         final Patient expected = new Patient();
//         expected.setId(3);
//         expected.setSnilsNum(222);
//         expected.setName("Alex");
//         expected.setPolisId(10203);
//         assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
//     }
//     @Test
//     public void getPolisThatExists(){
//         final Polis actual = daoPolis.read(10203);
//         final Polis expected = new Polis();
//         java.util.Date date1;
//         java.sql.Date sqlDate;
//         try {
//             String sDate1 = "20/12/2022";
//             date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//             sqlDate = new java.sql.Date(date1.getTime());
//         } catch (ParseException e) {
//             throw new RuntimeException(e);
//         }

//         expected.setId(10203);
//         expected.setCompany("TestComp3");
//         expected.setEndDate(sqlDate);
//         assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
//     }
//     @Test
//     public void patientDoesNotExist(){
//         final Patient patient = daoPatient.read(99999);
//         assertThat(patient.getId()).isEqualTo(-1);
//     }
//     @Test
//     public void polisDoesNotExist(){
//         final Polis polis = daoPolis.read(9999);
//         assertThat(polis.getId()).isEqualTo(-1);
//     }
//     @Test
//     public void polisThenPatientCreate() {
//         java.util.Date date1;
//         java.sql.Date sqlDate;
//         try {
//             String sDate1 = "31/12/2024";
//             date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//             sqlDate = new java.sql.Date(date1.getTime());
//         } catch (ParseException e) {
//             throw new RuntimeException(e);
//         }
//         Patient patient = new Patient();
//         Polis polis = new Polis();
//         patient.setName("George");
//         patient.setSnilsNum(1234);
//         polis.setId(124);
//         polis.setCompany("Sample Company");
//         polis.setEndDate(sqlDate);
//         patient.setPolisId(124);
//         Integer idPolis = daoPolis.create(polis);
//         Integer idPatient = daoPatient.create(patient);
//         Patient actualPatient = new Patient();
//         actualPatient = daoPatient.read(idPatient);
//         Polis actualPolis = new Polis();
//         actualPolis = daoPolis.read(idPolis);
//         assertThat(actualPatient).usingRecursiveComparison().isEqualTo(patient);
//         assertThat(actualPolis).usingRecursiveComparison().isEqualTo(polis);
//         daoPatient.delete(idPatient);
//         daoPolis.delete(idPolis);
//     }
//     @Test
//     public void deletePolisPatient(){
//         java.util.Date date1;
//         java.sql.Date sqlDate;
//         try {
//             String sDate1 = "31/12/2024";
//             date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//             sqlDate = new java.sql.Date(date1.getTime());
//         } catch (ParseException e) {
//             throw new RuntimeException(e);
//         }
//         Patient patient = new Patient();
//         Polis polis = new Polis();
//         patient.setName("George");
//         patient.setSnilsNum(1234);
//         polis.setId(124);
//         polis.setCompany("Sample Company");
//         polis.setEndDate(sqlDate);
//         patient.setPolisId(124);
//         Integer idPolis = daoPolis.create(polis);
//         Integer idPatient = daoPatient.create(patient);
//         Patient actualPatient = new Patient();
//         actualPatient = daoPatient.read(idPatient);
//         Polis actualPolis = new Polis();
//         actualPolis = daoPolis.read(idPolis);
//         daoPatient.delete(idPatient);
//         daoPolis.delete(idPolis);
//         assertThat(daoPatient.read(patient.getId()).getId()).isEqualTo(-1);
//         assertThat(daoPolis.read((polis.getId())).getId()).isEqualTo(-1);
//     }
// }
