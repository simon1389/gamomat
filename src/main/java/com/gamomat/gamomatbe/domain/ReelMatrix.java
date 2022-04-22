package com.gamomat.gamomatbe.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReelMatrix {
    private final List<Reel> reels;

    public static Reel createReel(List<String> slots) {
        return new Reel(slots);
    }
}
