package ru.krylov.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.mysql.cj.util.DnsSrv.SrvRecord;

import ru.krylov.beans.Polis;
import ru.krylov.bl.Util;

public class PolisDAO implements DAO<Polis, Integer> {

    public PolisDAO() {
    }

    Util util = new Util();

    enum SQLPolis {
        INSERT("insert into Polis (id,company,endDate) values (?,?,?);"),
        DELETE("delete from Polis where id = ?;"),
        UPDATE("update Polis set company = ?,endDate = ? where id = ?;"),
        GET("select * from Polis where id = ?;"),
        GETBYCOMPANY("select * from Polis where company = ?;"),
        GETALL("select * from Polis;");

        String QUERY;

        SQLPolis(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public Integer create(Polis polis) {
        Integer result = -1;
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PolisDAO.SQLPolis.INSERT.QUERY)) {
            statement.setInt(1, polis.getId());
            statement.setString(2, polis.getCompany());
            statement.setDate(3, (Date) polis.getEndDate());
            statement.execute();
            result = polis.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Polis read(Integer id) {
        final Polis result = new Polis();
        Connection connection = util.getConnection();
        result.setId(-1);
        try (PreparedStatement statement = connection.prepareStatement((PolisDAO.SQLPolis.GET.QUERY))) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(Integer.parseInt(rs.getString("id")));
                result.setCompany(rs.getString("company"));
                result.setEndDate((rs.getDate("endDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Polis polis) {
        boolean result = false;
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(PolisDAO.SQLPolis.UPDATE.QUERY)) {
            statement.setString(1, polis.getCompany());
            statement.setDate(2, (Date) polis.getEndDate());
            statement.setInt(3, polis.getId());
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
        try (PreparedStatement statement = connection.prepareStatement(PolisDAO.SQLPolis.DELETE.QUERY)) {
            statement.setInt(1, id);
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Polis> getAll() {
        List<Polis> polisList = new ArrayList<>();
        Polis polis;
        Connection connection = util.getConnection();
        try (PreparedStatement statement = connection.prepareStatement((SQLPolis.GETALL.QUERY))) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                polis = new Polis();
                polis.setId(rs.getInt("id"));
                polis.setCompany(rs.getString("company"));
                polis.setEndDate(rs.getDate("endDate"));
                polisList.add(polis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return polisList;
    }

    public List<Polis> getPolisesByCompany(String value) {
        List<Polis> polisList = new ArrayList<>();
        Connection connection = util.getConnection();
        Polis polis;
        try (PreparedStatement statement = connection.prepareStatement(SQLPolis.GETBYCOMPANY.QUERY)) {
            statement.setString(1, value);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                polis = new Polis();
                polis.setId(rs.getInt("id"));
                polis.setCompany(rs.getString("company"));
                polis.setEndDate(rs.getDate("endDate"));
                polisList.add(polis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return polisList;
    }

    @Override
    public List<Polis> getAllFiltered(String field, String value) {
        List<Polis> polisList = new ArrayList<>();
        switch (field) {
            case "company":
                polisList = getPolisesByCompany(value);
                break;
        }
        return polisList;
    }

    @Override
    public List<String> getAllGroupedBy(String field) {
        Connection connection = util.getConnection();
        List<String> fieldList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select "+field+" from Polis group by "+field)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                fieldList.add(rs.getString(field));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fieldList;
    }

    @Override
    public List<Polis> getAllWhere(String field, String value) {
        // TODO Auto-generated method stub
        return null;
    }

}
