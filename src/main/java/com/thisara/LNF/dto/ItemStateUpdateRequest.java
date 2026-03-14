package com.thisara.LNF.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.thisara.LNF.entity.ItemType;
import lombok.Data;

@Data
public class ItemStateUpdateRequest {

    @JsonAlias("state")
    private ItemType type;
}
