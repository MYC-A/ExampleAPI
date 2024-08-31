package com.GreenAtomTask.ExampleAPI.controller;

import com.GreenAtomTask.ExampleAPI.DTO.FileDTO;
import com.GreenAtomTask.ExampleAPI.convertor.FileConvertor;
import com.GreenAtomTask.ExampleAPI.services.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final FileService fileService;
    private final FileConvertor fileConvertor;

    @PostMapping("api/put")
    public int putFile(@Valid @RequestBody FileDTO fileDTO){
        return fileService.saveFile(fileConvertor.convertFromDto(fileDTO));
    }

    @GetMapping("/api/storage")
    public List<FileDTO> getSortAllFiles(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        return fileService.FindAll(pageable).getContent()
                .stream().map(fileConvertor::convertToDto)
                .collect(Collectors.toList());

    }


    @GetMapping("/api/get")
    public FileDTO getOneFile(@RequestParam int id){
        return fileConvertor.convertToDto(fileService.FindFile(id));
    }
    @GetMapping("/api/all")
    public String show(){
        return "Hello World";
    }
}
