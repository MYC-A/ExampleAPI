package com.GreenAtomTask.ExampleAPI.services;

import com.GreenAtomTask.ExampleAPI.entity.File;
import com.GreenAtomTask.ExampleAPI.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {
    private static final int ID = 1;
    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileService fileService;


    @Test
    void saveFileTest() {
        File file = mock(File.class);
        when(file.getId()).thenReturn(1); // Задаем значение для метода getId()


        when(fileRepository.save(file)).thenReturn(file);

        int savedId = fileService.saveFile(file);

        assertEquals(1, savedId);
        verify(fileRepository).save(file);


    }

    @Test
    void FindAllTest() {
        File firsFile = mock(File.class);
        File secondFile = mock(File.class);


        List<File> files = new ArrayList<>(List.of(firsFile,secondFile));
        Pageable pageable = PageRequest.of(0, 2);
        Page<File> page = new PageImpl<>(files, pageable, files.size());


        when(fileRepository.findAllByOrderByDateAsc(pageable)).thenReturn(page);


        Page<File> result = fileService.FindAll(pageable);

        assertEquals(page, result);
        verify(fileRepository, times(1)).findAllByOrderByDateAsc(pageable); //
    }



    @Test
    void FindFileTest() {
        final File file =  mock(File.class);

        when(fileRepository.findById(ID)).thenReturn(Optional.of(file));
        File retrievedFile = fileService.FindFile(ID);

        assertNotNull(retrievedFile);
        assertEquals(file, retrievedFile);
        verify(fileRepository, times(1)).findById(ID);

    }
}