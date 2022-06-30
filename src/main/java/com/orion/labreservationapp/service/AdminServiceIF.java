package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.ServerDO;

import java.sql.SQLException;
import java.util.List;

public interface AdminServiceIF {

    public int getServerCount() throws SQLException;
}



