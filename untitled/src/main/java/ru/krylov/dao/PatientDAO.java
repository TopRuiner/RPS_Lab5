package ru.krylov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.krylov.beans.Patient;
import ru.krylov.bl.Util;

public class PatientDAO implements DAO<Patient, Integer> {

    public PatientDAO() {
    }

    Util util = new Util();
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(util.getDataSource());

    @Override
    public Integer create(Patient patient) {
        Integer result = -1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.INSERT.QUERY)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getSnilsNum());
            statement.setInt(3, patient.getPolisId());
            statement.execute();
            PreparedStatement statement1 = connection.prepareStatement("select LAST_INSERT_ID() as \"Id\";");
            ResultSet rs = statement1.executeQuery();
            if (rs.next()) {
                result = rs.getInt("id");
                patient.setId(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Patient read(Integer id) {
        final Patient result = new Patient();
        result.setId(-1);
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement((SQLPatient.GET.QUERY))) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(Integer.parseInt(rs.getString("id")));
                result.setName(rs.getString("name"));
                result.setSnilsNum(Integer.parseInt(rs.getString("snilsNum")));
                result.setPolisId(Integer.parseInt(rs.getString("polisId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Patient patient) {
        boolean result = false;
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.UPDATE.QUERY)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getSnilsNum());
            statement.setInt(3, patient.getPolisId());
            statement.setInt(4, patient.getId());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result = false;
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.DELETE.QUERY)) {
            statement.setInt(1, id);
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patientList = new ArrayList<>();
        Patient patient;
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement((SQLPatient.GETALL.QUERY))) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setPolisId(rs.getInt("polisId"));
                patient.setSnilsNum(rs.getInt("snilsNum"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientList;
    }




    enum SQLPatient {
        INSERT("insert into Patient (name,snilsNum,polisId) values (?,?,?);"),
        DELETE("delete from Patient where id = ?;"),
        UPDATE("update Patient set name = ?,snilsNum = ?,polisId = ? where id = ?;"),
        GETALL("select * from Patient"),
        GET("select * from Patient where id = ?;");

        String QUERY;

        SQLPatient(String QUERY) {
            this.QUERY = QUERY;
        }
    }


    @Override
    public List<Patient> getAllFiltered(String field, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getAllGroupedBy(String field) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Patient> getAllWhere(String field, String value) {
        List<Patient> patientList = new ArrayList<>();
        Patient patient;
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("select * from Patient inner join polis on polisId = polis.id where " + field + "="+"\"" +value+"\"" +";")) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setPolisId(rs.getInt("polisId"));
                patient.setSnilsNum(rs.getInt("snilsNum"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientList;
    }
}
