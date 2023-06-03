package com.sr.chatpanel.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "site")
@Getter
@Setter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    @Size(min = 5, max = 30, message = "Must be between 5 and 100.")
    @NotBlank
    private String name;

    @Column(name = "uri")
    @Size(min = 5, max = 30, message = "Must be between 5 and 100.")

    @NotBlank
    @URL
    private String uri;

    @Size(max = 40)
    private String chatName;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private User user;

}
