package com.jobportal.jobportalsystem.model.profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 100)
    private String street_add;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet_add() {
        return street_add;
    }

    public void setStreet_add(String street_add) {
        this.street_add = street_add;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street_add=" + street_add +
                ", city=" + city +
                ", state=" + state +
                '}';
    }
}
