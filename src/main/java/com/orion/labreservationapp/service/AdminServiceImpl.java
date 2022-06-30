package com.orion.labreservationapp.service;

import com.orion.labreservationapp.DBC.TransactionManager;
import com.orion.labreservationapp.model.ServerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceIF {

    public int getServerCount() throws SQLException {
        Connection connection = TransactionManager.getConnection();
        int count = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(*) from servers;");
        while (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        resultSet.close();
        statement.close();
        connection.close();
        return count;
    }

    /*
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    //method isimleri
    public int save(ServerDO serverDO) {
        return jdbcTemplate.update("INSERT INTO servers (server_name, server_ip, server_location, serial_number, server_type, is_host) VALUES (?, ?, ?, ?, ?, ?)",
               new Object[]{
                       serverDO.getServer_name(),
                       serverDO.getServer_ip(),
                       serverDO.getServer_location(),
                       serverDO.getSerial_number(),
                       serverDO.getServer_type(),
                       serverDO.getIs_host()});
    }

    public List<ServerDO> findAll() {
        return jdbcTemplate.query("SELECT * FROM servers", BeanPropertyRowMapper.newInstance(ServerDO.class));
    }

    public int update(ServerDO serverDO) {
        return jdbcTemplate.update("UPDATE servers SET server_name = ?, server_ip = ?, server_location = ?, serial_number = ?, server_type = ?, is_host = ? WHERE id = ?",
                new Object[]{
                        serverDO.getServer_name(),
                        serverDO.getServer_ip(),
                        serverDO.getServer_location(),
                        serverDO.getSerial_number(),
                        serverDO.getServer_type(),
                        serverDO.getIs_host(),
                        serverDO.getId()});
    }

    public List<ServerDO> findByServerName(String serverName) {
        //kontrol et
        String q = "SELECT * from servers WHERE server_name ILIKE '%" + serverName + "%'";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(ServerDO.class));
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM servers WHERE id = ?", id);
    }


    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM servers");
    }

    public ServerDO findById(int id) {
        try {
            ServerDO serverDO = jdbcTemplate.queryForObject("SELECT * FROM servers WHERE id = ?",
                    BeanPropertyRowMapper.newInstance(ServerDO.class), id);
            return serverDO;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    */

}
