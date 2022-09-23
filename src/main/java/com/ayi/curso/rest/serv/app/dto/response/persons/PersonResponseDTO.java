package com.ayi.curso.rest.serv.app.dto.response.persons;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "PersonResponseDTO",
        description = "Represents the data needed to created Persons"
)
public class PersonResponseDTO implements Serializable {

    private Long idPerson;

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    private String firstName;

    @ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    private String lastName;

    @ApiModelProperty(position = 3, notes = "Non negative value, The type document list is required.")
    private String typeDocument;

    @ApiModelProperty(position = 4, notes = "Non negative value, The number document is required.")
    private Integer numberDocument;

    @ApiModelProperty(position = 5, notes = "Non negative value, The date born is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateBorn;

    @ApiModelProperty(position = 6, notes = "Date Created Person")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;

    @ApiModelProperty(position = 7, notes = "Date Modified Person")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateModified;

    @ApiModelProperty(position = 8, notes = "SoftDelete Person")
    private boolean softDelete;
}
