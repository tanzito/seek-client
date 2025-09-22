package com.seek.client.context.client.domain;

import com.seek.client.context.shared.auditable.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;


@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "clients")
public class Client extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthdate;

    public Integer getAge() {
        LocalDate now = LocalDate.now();
        return Period.between(this.birthdate, now).getYears();
    }
}
