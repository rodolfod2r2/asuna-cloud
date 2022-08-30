package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.util;

public class CustomText {

    public static boolean isBlankOrNull(String text) {
        return (text == null || text.equals("") || text.trim().equals(""));
    }

    public static String fullLeftPositions(String text, char character, int totalSize) {
        return fullPositions(text, character, totalSize, Alignment.LEFT);
    }

    public static String fullRightPositions(String text, char character, int totalSize) {
        return fullPositions(text, character, totalSize, Alignment.RIGHT);
    }

    private static String fullPositions(String text, char character, int totalSize, Alignment tipo) {
        if (text == null || totalSize < 1) {
            return text;
        }
        StringBuilder newText = new StringBuilder(text);
        while (newText.length() < totalSize) {
            if (tipo == Alignment.LEFT) {
                newText.insert(0, character);
                continue;
            }
            if (tipo == Alignment.RIGHT) {
                newText.append(character);
            }
        }
        return newText.toString();
    }


    private enum Alignment {
        RIGHT, LEFT
    }


}
