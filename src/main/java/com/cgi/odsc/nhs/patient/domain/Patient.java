package com.cgi.odsc.nhs.patient.domain;

import com.cgi.odsc.nhs.validation.ValidationPattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.cgi.odsc.nhs.validation.ValidationPattern.*;

@Entity
@Table(name="patient")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Patient implements Serializable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = ValidationPattern.NAME_PATTERN, message = "Please enter valid name")
    @Column(name="name")
    @Length(max=50)
    private String name;

}