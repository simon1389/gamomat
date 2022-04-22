package com.gamomat.gamomatbe.config;

import com.gamomat.gamomatbe.exceptions.WinLineLengthMismatchException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReelMatrixConfigValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ReelMatrixConfig.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReelMatrixConfig config = (ReelMatrixConfig) target;

        try {
            checkConfig(config);
        } catch (WinLineLengthMismatchException e) {
            errors.rejectValue(
                    "winLines",
                    "winLine.length.mismatch",
                    e.getMessage());
        }
    }

    public static void checkConfig(ReelMatrixConfig config) throws WinLineLengthMismatchException {
        int numberOfReels = config.getReels().size();
        if (config.getWinLines().stream().anyMatch(wl -> wl.size() != numberOfReels)) {
            throw new WinLineLengthMismatchException(numberOfReels);
        }
    }
}
