package com.example.general.model;

import com.example.general.model.ISO.ConsumerStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,5}", message = "plz insert a real g-mail")
    private String username;//gmail


    @NotBlank
    private String password;

    private String code;

    @Enumerated
    private ConsumerStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "consumer_authority",
            joinColumns = @JoinColumn(name = "consumer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;




    @OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Card card;
}
