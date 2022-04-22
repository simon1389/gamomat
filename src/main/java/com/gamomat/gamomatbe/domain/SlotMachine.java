package com.gamomat.gamomatbe.domain;

import com.gamomat.gamomatbe.config.ReelMatrixConfig;
import com.gamomat.gamomatbe.config.ReelMatrixConfigValidator;
import com.gamomat.gamomatbe.exceptions.WinLineLengthMismatchException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class SlotMachine {
    private ReelMatrix matrix;
    private List<WinLine> winLines;

    public void turnReels() {
        Random ran = new Random();
        this.matrix.getReels().forEach(reel -> Collections.rotate(
                reel.getSlots(),
                ran.nextInt(reel.getSlots().size() + 1)
        ));
    }

    public static SlotMachine createFromConfig(ReelMatrixConfig config) throws WinLineLengthMismatchException {
        ReelMatrixConfigValidator.checkConfig(config);

        ReelMatrix matrix = new ReelMatrix(config.getReels().stream().map(ReelMatrix::createReel).collect(Collectors.toList()));
        List<WinLine> winLines = config.getWinLines().stream().map(WinLine::new).toList();

        return new SlotMachine(matrix, winLines);
    }
}
