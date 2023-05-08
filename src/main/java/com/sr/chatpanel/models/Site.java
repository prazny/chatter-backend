package com.sr.chatpanel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
    private Long id;
    @Column(name = "uri")
    private String uri;

}
