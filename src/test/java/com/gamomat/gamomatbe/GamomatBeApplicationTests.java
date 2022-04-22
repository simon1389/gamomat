package com.gamomat.gamomatbe;

import com.gamomat.gamomatbe.config.ReelMatrixConfig;
import com.gamomat.gamomatbe.domain.SlotMachine;
import com.gamomat.gamomatbe.exceptions.WinLineLengthMismatchException;
import com.gamomat.gamomatbe.service.WinCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class GamomatBeApplicationTests {

    @Autowired
    private ReelMatrixConfig config;

    @Autowired
    private WinCalculatorService winCalculatorService;

    @Test
    void testInvalidConfig() {
        this.config.setWinLines(List.of(List.of(0,0,0,0)));
        assertThrows(WinLineLengthMismatchException.class,() -> SlotMachine.createFromConfig(this.config));
    }

    @Test
    void testWinCalculation() throws WinLineLengthMismatchException {
        this.config.setReels(List.of(
                List.of("A", "B", "C"),
                List.of("A", "B", "C"),
                List.of("A", "B", "C")
        ));

        this.config.setWinLines(List.of(List.of(0,0,0)));
        SlotMachine m = SlotMachine.createFromConfig(this.config);
        Assertions.assertEquals(10, winCalculatorService.calculateWins(m).getTotalWinAmount());

        this.config.setWinLines(List.of(
                List.of(0,0,0),
                List.of(1,1,1))
        );
        SlotMachine m2 = SlotMachine.createFromConfig(this.config);
        Assertions.assertEquals(25, winCalculatorService.calculateWins(m2).getTotalWinAmount());
    }
}
