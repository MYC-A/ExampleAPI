package com.GreenAtomTask.ExampleAPI.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileDTO {
    @NotBlank(message = "Необходим указать названия")
    private String title;
    @NotEmpty(message = "Необходим добавить файл")
    private byte[] data;
    @NotNull(message = "Необходим указать дату отправки файла")
    private LocalDateTime date;
    @NotBlank(message = "Необходим добавить описание файла")
    private String description;

}
