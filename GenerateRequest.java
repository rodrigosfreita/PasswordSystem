package com.example.password.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class GenerateRequest {

    @Min(4)
    @Max(64)
    private int length;
    private boolean useUppercase;
    private boolean useDigits;
    private boolean useSymbols;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isUseUppercase() {
        return useUppercase;
    }

    public void setUseUppercase(boolean useUppercase) {
        this.useUppercase = useUppercase;
    }

    public boolean isUseDigits() {
        return useDigits;
    }

    public void setUseDigits(boolean useDigits) {
        this.useDigits = useDigits;
    }

    public boolean isUseSymbols() {
        return useSymbols;
    }

    public void setUseSymbols(boolean useSymbols) {
        this.useSymbols = useSymbols;
    }
}
