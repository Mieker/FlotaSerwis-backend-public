package com.mieker.FlotaSerwisBackend.service;

import com.mieker.FlotaSerwisBackend.model.Malfunction;

import java.util.List;

public interface MalfunctionService {

    List<Malfunction> getAllMalfunctions();
    //TODO: not sure if it's necessary method

    Malfunction getMalfunctionById(String id);

    Malfunction addMalfunctionToDataBase(Malfunction malfunction);

    Malfunction modifyMalfunction(String malfunctionId, Malfunction malfunctionPatch);

    Malfunction archiveMalfunction(String id);
}
