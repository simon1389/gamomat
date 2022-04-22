package com.gamomat.gamomatbe.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class WinInfo {
    public List<WinLineAmountInfo> winLineInfos;
    public Integer getTotalWinAmount() {
        return winLineInfos.stream().mapToInt(WinLineAmountInfo::getAmount).sum();
    }
}
