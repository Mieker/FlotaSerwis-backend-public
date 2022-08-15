package com.mieker.FlotaSerwisBackend.model;

import com.mieker.FlotaSerwisBackend.validation.PatchVehicleValidation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class Vehicle {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;
    @NotBlank
    @Size(min = 1, max = 25)
    @Size(min = 1, max = 25, groups = {PatchVehicleValidation.class})
    private String modelName;
    @NotBlank
    @Size(min = 5, max = 9)
    @Size(min = 5, max = 9, groups = {PatchVehicleValidation.class})
    private String registrationNumber;
    @NotBlank
    @Size(min = 17, max = 17)
    @Size(min = 17, max = 17, groups = {PatchVehicleValidation.class})
    private String vin;
    @NotNull
    @Min(1900)
    @Min(value = 1900, groups = {PatchVehicleValidation.class})
    @Max(2100)
    @Max(value = 2100, groups = {PatchVehicleValidation.class})
    private Integer yearOfProduction;
    @NotBlank
    @Size(min = 1, max = 25)
    @Size(min = 1, max = 25, groups = {PatchVehicleValidation.class})
    private String user;
    private String usersEmail;
    @NotNull
    private Boolean isTechnicallyEfficient;
    @DBRef
    private List<Malfunction> malfunctions = new ArrayList<>();
    private boolean isArchived;
    private String photoUrl;

    public void addMalfunction(Malfunction malfunction) {
        this.malfunctions.add(malfunction);
    }

}
