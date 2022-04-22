package com.gamomat.gamomatbe.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gamomat.gamomatbe.domain.WinLine;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WinLineAmountInfo {
    @JsonUnwrapped
    public WinLine winLine;
    public String symbol;
    public Integer amount;
}
