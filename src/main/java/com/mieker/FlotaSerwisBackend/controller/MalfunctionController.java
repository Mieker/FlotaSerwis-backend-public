package com.mieker.FlotaSerwisBackend.controller;

import com.mieker.FlotaSerwisBackend.model.Malfunction;
import com.mieker.FlotaSerwisBackend.service.MalfunctionService;
import com.mieker.FlotaSerwisBackend.validation.PatchMalfunctionValidation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/malfunctions")
public class MalfunctionController {

    private final MalfunctionService malfunctionService;

    public MalfunctionController(MalfunctionService malfunctionService) {
        this.malfunctionService = malfunctionService;
    }

    @GetMapping
    public List<Malfunction> getAllMalfunctions() {
        return malfunctionService.getAllMalfunctions();
    }

    @GetMapping("/{malfunctionId}")
    public Malfunction getMalfunctionById(@PathVariable String malfunctionId) {
        return malfunctionService.getMalfunctionById(malfunctionId);
    }

    @PatchMapping("/{malfunctionId}")
    public Malfunction modifyMalfunction(@PathVariable String malfunctionId, @RequestBody @Validated(PatchMalfunctionValidation.class) Malfunction malfunction) {
        return malfunctionService.modifyMalfunction(malfunctionId, malfunction);
    }

    @PatchMapping("/{malfunctionId}/archive")
    public Malfunction archiveMalfunction(@PathVariable String malfunctionId) {
        return malfunctionService.archiveMalfunction(malfunctionId);
    }

}
