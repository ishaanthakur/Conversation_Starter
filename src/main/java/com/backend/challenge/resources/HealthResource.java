package com.backend.challenge.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthResource {

    private String health;

    public HealthResource(String health) { this.health = health;
    }
}
