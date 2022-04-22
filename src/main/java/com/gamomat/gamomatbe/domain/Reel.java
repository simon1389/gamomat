package com.gamomat.gamomatbe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Reel {
    private final List<String> slots;
}
