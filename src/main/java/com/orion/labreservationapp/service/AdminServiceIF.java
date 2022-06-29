package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.ServerDO;

public interface AdminServiceIF {
    public void createServer(ServerDO serverDO);
    public void updateServer(ServerDO serverDO);
    public void deleteServer(ServerDO serverDO);

}


