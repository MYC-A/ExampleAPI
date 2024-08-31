package com.GreenAtomTask.ExampleAPI.convertor;

import com.GreenAtomTask.ExampleAPI.DTO.FileDTO;
import com.GreenAtomTask.ExampleAPI.entity.File;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class FileConvertor {
    private final ModelMapper modelMapper;
    public FileConvertor(){
        this.modelMapper = new ModelMapper();
    }
    public FileDTO convertToDto(File file){
        return modelMapper.map(file,FileDTO.class);
    }
    public File convertFromDto(FileDTO fileDTO){
        return modelMapper.map(fileDTO,File.class);
    }
}
