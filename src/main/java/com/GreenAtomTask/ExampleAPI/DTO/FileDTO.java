package com.GreenAtomTask.ExampleAPI.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileDTO {
    //Класс для скрытия внутриней информации от пользователей
    @NotBlank(message = "Необходимо указать название")
    private String title;
    @NotEmpty(message = "Необходимо добавить файл")
    private byte[] data;
    @JsonProperty("creation_date")
    @NotNull(message = "Необходимо указать дату отправки файла")
    private LocalDateTime date;
    @NotBlank(message = "Необходимо добавить описание файла")
    private String description;

}
