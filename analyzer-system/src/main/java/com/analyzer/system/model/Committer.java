package com.analyzer.system.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Committer {
    private final String name;
    private final String email;
    private final OffsetDateTime date;


    @JsonCreator
    public Committer(@JsonProperty("name") String name,
                     @JsonProperty("email") String email,
                     @JsonProperty("date") OffsetDateTime date) {
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getDate() {
        return date;
    }

}
