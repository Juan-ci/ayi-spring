package com.ayi.curso.rest.serv.app.dto.response.persons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(
        value = "PersonResponseDTOFull",
        description = "Represents the data needed to get Persons in pages"
)
public class PersonResponseDTOFull {

    private List<PersonResponseDTO> personResponseDTOList;

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    private List<PersonResponseDTO> personResponseDTO;

    @ApiModelProperty(position = 2, notes = "Total pages")
    private Integer totalPages;

    @ApiModelProperty(position = 3, notes = "Current pages")
    private Integer currentPage;

    @ApiModelProperty(position = 4, notes = "Size")
    private Integer size;

    @ApiModelProperty(position = 5, notes = "Total de Elementos de la pagina")
    private Integer totalElements;
}
