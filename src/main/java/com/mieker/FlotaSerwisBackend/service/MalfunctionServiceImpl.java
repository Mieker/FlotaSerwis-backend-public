package com.mieker.FlotaSerwisBackend.service;

import com.mieker.FlotaSerwisBackend.model.Malfunction;
import com.mieker.FlotaSerwisBackend.repository.MalfunctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MalfunctionServiceImpl implements MalfunctionService {

    private final MalfunctionRepository malfunctionRepository;

    public MalfunctionServiceImpl(MalfunctionRepository malfunctionRepository) {
        this.malfunctionRepository = malfunctionRepository;
    }

    @Override
    public List<Malfunction> getAllMalfunctions() {
        return malfunctionRepository.findAll();
    }

    @Override
    public Malfunction getMalfunctionById(String id) {
        return malfunctionRepository.findById(id);
    }

    @Override
    public Malfunction addMalfunctionToDataBase(Malfunction malfunction) {
        return malfunctionRepository.save(malfunction);
    }

    @Override
    public Malfunction modifyMalfunction(String malfunctionId, Malfunction malfunctionPatch) {
        Malfunction existingMalfunction = malfunctionRepository.findById(malfunctionId);
        if (malfunctionPatch.getTitle() != null && !malfunctionPatch.getTitle().isEmpty()) {
            existingMalfunction.setTitle(malfunctionPatch.getTitle());
        }
        if (malfunctionPatch.getDescription() != null && !malfunctionPatch.getDescription().isEmpty()) {
            existingMalfunction.setDescription(malfunctionPatch.getDescription());
        }
        if (malfunctionPatch.getDateOfFailure() != null) {
            existingMalfunction.setDateOfFailure(malfunctionPatch.getDateOfFailure());
        }
        if (malfunctionPatch.getActionTaken() != null && !malfunctionPatch.getActionTaken().isEmpty()) {
            existingMalfunction.setActionTaken(malfunctionPatch.getActionTaken());
        }
        if (malfunctionPatch.getPlaceOfRepair() != null && !malfunctionPatch.getPlaceOfRepair().isEmpty()) {
            existingMalfunction.setPlaceOfRepair(malfunctionPatch.getPlaceOfRepair());
        }
        if (malfunctionPatch.getExpectedDateOfCompletion() != null) {
            existingMalfunction.setExpectedDateOfCompletion(malfunctionPatch.getExpectedDateOfCompletion());
        }
        return malfunctionRepository.save(existingMalfunction);
    }

    @Override
    public Malfunction archiveMalfunction(String id) {
        Malfunction malfunctionToArchive = malfunctionRepository.findById(id);
        malfunctionToArchive.setArchived(true);
        return malfunctionRepository.save(malfunctionToArchive);
    }
}
