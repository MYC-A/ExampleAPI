package com.GreenAtomTask.ExampleAPI.services;

import com.GreenAtomTask.ExampleAPI.entity.File;
import com.GreenAtomTask.ExampleAPI.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    public int saveFile(File file){
        fileRepository.save(file);
        return file.getId();
    }

    public List<File> getAll(){
        return fileRepository.findAllByOrderByDateAsc();
    }

    public File getFile(int id){
        return fileRepository.findById(id).orElseThrow();
    }

}
