package com.GreenAtomTask.ExampleAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "Archive")
@Getter
@Setter
public class File {
    public File(String title, byte[] data, LocalDateTime date, String description) {
        this.title = title;
        this.data = data;
        this.date = date;
        this.description = description;
    }
    public File(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    private String title;

    @Column(name="data")
    private byte[] data;

    @Column(name="creationdate")
    private LocalDateTime date;

    @Column(name="description")
    private String description;

}
