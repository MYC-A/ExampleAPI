package com.GreenAtomTask.ExampleAPI.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FileDTO {
    @NotBlank
    private String title;
    @NotEmpty
    private byte[] data;
    @NotNull
    private LocalDateTime date;
    @NotBlank
    private String description;

}
