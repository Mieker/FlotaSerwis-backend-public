package com.mieker.FlotaSerwisBackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mieker.FlotaSerwisBackend.validation.PatchMalfunctionValidation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Document
@Getter
@Setter
public class Malfunction {

    @Id
    private String id;
    @NotBlank
    @Size(min = 3, max = 25)
    @Size(min = 3, max = 25, groups = {PatchMalfunctionValidation.class})
    private String title;
    @NotBlank
    @Size(min = 3, max = 250)
    @Size(min = 3, max = 250, groups = {PatchMalfunctionValidation.class})
    private String description;
    @NotNull
    @PastOrPresent
    @PastOrPresent(groups = {PatchMalfunctionValidation.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfFailure;
    @NotBlank
    @Size(min = 3, max = 250)
    @Size(min = 3, max = 250, groups = {PatchMalfunctionValidation.class})
    private String actionTaken;
    @NotBlank
    @Size(min = 3, max = 250)
    @Size(min = 3, max = 250, groups = {PatchMalfunctionValidation.class})
    private String placeOfRepair;
    @NotNull
    @FutureOrPresent
    @FutureOrPresent(groups = {PatchMalfunctionValidation.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedDateOfCompletion;
    private boolean isArchived;
}
