package com.GreenAtomTask.ExampleAPI.controller;

import com.GreenAtomTask.ExampleAPI.DTO.FileDTO;
import com.GreenAtomTask.ExampleAPI.convertor.FileConvertor;
import com.GreenAtomTask.ExampleAPI.entity.File;
import com.GreenAtomTask.ExampleAPI.services.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    @Mock
    private FileService fileService;
    @Mock
    private  FileConvertor fileConvertor;
    @InjectMocks
    private MainController controller;

    @Test
    void putFileTest() {
        final FileDTO fileDTO = mock(FileDTO.class);
        final File file = mock(File.class);
        when(fileConvertor.convertFromDto(fileDTO)).thenReturn(file);
        when(fileService.saveFile(file)).thenReturn(1);

        controller.putFile(fileDTO);

        verify(fileConvertor).convertFromDto(refEq(fileDTO));
        verify(fileService).saveFile(refEq(file));


    }

    @Test
    void getSortAllFilesTest() {

        final File firsFile = mock(File.class);
        final File secondFile = mock(File.class);

        List<File> files = new ArrayList<>(List.of(firsFile,secondFile));
        Pageable pageable = PageRequest.of(0, 2);
        PageImpl<File> page = new PageImpl<>(files, pageable, files.size());

        when(fileService.FindAll(pageable)).thenReturn(page);
        when(fileConvertor.convertToDto(any(File.class))).thenReturn(new FileDTO());

        List<FileDTO> allFiles = controller.getSortAllFiles(0, 2);

        assertNotNull(allFiles);
        assertEquals(allFiles, page.getContent()
                .stream().map(fileConvertor::convertToDto)
                .collect(Collectors.toList())); //Проверка совпадают ли значения


        verify(fileService).FindAll(refEq(pageable));

    }

    @Test
    void getOneFileTest(){
        final File file = mock(File.class); // Assuming File is the entity class
        final FileDTO fileDTO = mock(FileDTO.class);
        int id = 1;

        when(fileService.FindFile(id)).thenReturn(file);
        when(fileConvertor.convertToDto(file)).thenReturn(fileDTO);

        final FileDTO getFileDTO = controller.getOneFile(id);
        assertNotNull(getFileDTO);
        assertEquals(getFileDTO,fileDTO);

        verify(fileService).FindFile(id);
        verify(fileConvertor).convertToDto(file);


    }
}