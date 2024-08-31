package com.GreenAtomTask.ExampleAPI.controller;

import com.GreenAtomTask.ExampleAPI.DTO.FileDTO;
import com.GreenAtomTask.ExampleAPI.entity.File;
import com.GreenAtomTask.ExampleAPI.services.FileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final FileService fileService;

    @PostMapping("api/put")
    public void putFile(@Valid @RequestBody FileDTO fileDTO){

        fileService.saveFile(File.builder()
                .title(fileDTO.getTitle())
                .data(fileDTO.getData())
                .date(fileDTO.getDate())
                .description(fileDTO.getDescription()).
                build());
    }

    @GetMapping("/api/storage")
    public List<File> getSortAllFiles(){
        return fileService.getAll();
    }

    @GetMapping("/api/get")
    public File getOneFile(@RequestParam int id){
        return fileService.getFile(id);
    }
    @GetMapping("/api/all")
    public String show(){
        return "Hello World";
    }
}
