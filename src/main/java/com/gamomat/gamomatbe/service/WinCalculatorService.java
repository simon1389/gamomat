package com.gamomat.gamomatbe.service;

import com.gamomat.gamomatbe.config.ReelMatrixConfig;
import com.gamomat.gamomatbe.domain.SlotMachine;
import com.gamomat.gamomatbe.dto.WinInfo;
import com.gamomat.gamomatbe.dto.WinLineAmountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WinCalculatorService {
    @Autowired
    ReelMatrixConfig config;

    public WinInfo calculateWins(SlotMachine machine) {
        List<WinLineAmountInfo> list = new ArrayList<>();

        machine.getWinLines().forEach(wl -> {
            AtomicInteger reelNo = new AtomicInteger();
            List<String> winlineSymbols = wl.getWinLine().stream()
                    .map(pos -> machine.getMatrix().getReels().get(reelNo.getAndIncrement()).getSlots().get(pos))
                    .toList();

            if (winlineSymbols.stream().distinct().count() <= 1) {
                list.add(new WinLineAmountInfo(wl, winlineSymbols.get(0), config.getWinAmount().get(winlineSymbols.get(0))));
            }
        });

        return new WinInfo(list);
    }
}
