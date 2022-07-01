package com.orion.labreservationapp.service;

import com.orion.labreservationapp.DBC.TransactionManager;
import com.orion.labreservationapp.log.Log;
import com.orion.labreservationapp.model.ServerDO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceIF {
    private final String SQL_GET_SERVERS = "SELECT * FROM servers";
    private final String SQL_CREATE_SERVERS = "INSERT INTO servers (server_name, server_ip, server_location, serial_number, server_type, is_host) VALUES (?,?,?,?,?,?)";
    private final String SQL_DELETE_SERVERS_BY_ID = "DELETE FROM servers WHERE server_id = ?";
    private final String SQL_DELETE_SERVERS = "DELETE FROM servers";
    private final String SQL_UPDATE_SERVERS = "UPDATE servers SET server_name = ?, server_ip = ?, server_location = ?, serial_number = ?, server_type = ?, is_host = ? WHERE server_id = ?";

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


    public List<ServerDO> getServer() throws SQLException {
        ArrayList<ServerDO> servers = new ArrayList<>();
        Connection connection = TransactionManager.getConnection();
        Log.logger(Log.LogConstant.TAG_INFO, "connected to database");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_GET_SERVERS);
        while (resultSet.next()) {
            ServerDO server = new ServerDO();
            server.setServer_name(resultSet.getString("server_name"));
            server.setServer_ip(resultSet.getString("server_ip"));
            server.setServer_location(resultSet.getString("server_location"));
            server.setSerial_number(resultSet.getString("serial_number"));
            server.setServer_type(resultSet.getString("server_type"));
            server.setIs_host(resultSet.getBoolean("is_host"));
            server.setId(resultSet.getInt("id"));
            servers.add(server);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return servers;
    }

    @Override
    public void createServer() throws SQLException {
        try {
        Connection connection = TransactionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_SERVERS);
        preparedStatement.setString(1, "server_name");
        preparedStatement.setString(2, "server_ip");
        preparedStatement.setString(3, "server_location");
        preparedStatement.setString(4, "serial_number");
        preparedStatement.setString(5, "server_type");
        preparedStatement.setBoolean(6, true);
        int row = preparedStatement.executeUpdate();
        connection.commit();
        connection.close();
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
        }

    }


    public int updateServer(ServerDO server) throws SQLException {
        Connection connection = TransactionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SERVERS);
        statement.setString(1, server.getServer_name());
        statement.setString(2, server.getServer_ip());
        statement.setString(3, server.getServer_location());
        statement.setString(4, server.getSerial_number());
        statement.setString(5, server.getServer_type());
        statement.setBoolean(6, server.getIs_host());
        statement.setInt(7, server.getId());
        int result = statement.executeUpdate();
        statement.close();
        connection.close();
        return result;
    }

    public int deleteServerByID(ServerDO server) throws SQLException {
        Connection connection = TransactionManager.getConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(SQL_DELETE_SERVERS_BY_ID);
        preparedStatement.setInt(1, server.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
        return server.getId();
    }

    public List<ServerDO> deleteServer() throws SQLException {
        Connection connection = TransactionManager.getConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(SQL_DELETE_SERVERS);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
        return getServer();
    }

}
