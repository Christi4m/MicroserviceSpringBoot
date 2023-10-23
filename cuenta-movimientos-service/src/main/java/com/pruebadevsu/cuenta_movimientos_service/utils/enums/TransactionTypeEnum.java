package com.pruebadevsu.cuenta_movimientos_service.utils.enums;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.regex.Pattern;

public enum TransactionTypeEnum {
    DEPOSIT("deposito"),
    WITHDRAWAL("retiro");

    private final String displayName;

    TransactionTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public static String getDisplayNames() {
        return Arrays.stream(TransactionTypeEnum.values())
                .map(TransactionTypeEnum::getDisplayName)
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse("");
    }

    public String getDisplayName() {
        return displayName;
    }

    public static boolean equalsIgnoreCase(String input, TransactionTypeEnum type) {
        String normalizedInput = normalize(input);
        String normalizedDisplayName = normalize(type.displayName);
        return normalizedInput != null && type != null && normalizedInput.equalsIgnoreCase(normalizedDisplayName);
    }

    public static boolean matches(String input) {
        for (TransactionTypeEnum type : TransactionTypeEnum.values()) {
            String normalizedInput = normalize(input);
            String normalizedDisplayName = normalize(type.displayName);
            if (normalizedInput != null && normalizedInput.equalsIgnoreCase(normalizedDisplayName)) {
                return true;
            }
        }
        return false;
    }

    private static String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
}
