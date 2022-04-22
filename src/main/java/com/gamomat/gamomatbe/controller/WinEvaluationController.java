package com.gamomat.gamomatbe.controller;

import com.gamomat.gamomatbe.config.ReelMatrixConfig;
import com.gamomat.gamomatbe.domain.SlotMachine;
import com.gamomat.gamomatbe.dto.WinInfo;
import com.gamomat.gamomatbe.exceptions.WinLineLengthMismatchException;
import com.gamomat.gamomatbe.service.WinCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WinEvaluationController {

    @Autowired
    private ReelMatrixConfig config;

    @Autowired
    private WinCalculatorService winService;

    @GetMapping("/play")
    public WinInfo evaluateWin() throws WinLineLengthMismatchException {
        SlotMachine slotMachine = SlotMachine.createFromConfig(config);
        slotMachine.turnReels();
        return winService.calculateWins(slotMachine);
    }
}
