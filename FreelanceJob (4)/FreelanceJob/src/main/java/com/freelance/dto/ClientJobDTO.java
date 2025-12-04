package com.freelance.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientJobDTO {

    private Long id;
    private String title;
    private String description;
    private Double budget;
    private Long clientId;
    private String clientName;
    private String clientEmail;

}
