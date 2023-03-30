package com.example.school_app;


import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class JavaSyntaxHighlighter {
    private static final String[] KEYWORDS = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "if", "goto", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};
    private static final String[] TYPES = {"boolean", "byte", "char", "double", "float", "int", "long", "short", "void"};
    private static final String[] MODIFIERS = {"abstract", "final", "native", "private", "protected", "public", "static", "synchronized", "transient", "volatile"};

    public static void applySyntaxHighlighting(SpannableStringBuilder builder) {
        applyColor(builder, KEYWORDS, 0xFF8800);    // Orange
        applyColor(builder, TYPES, 0x008800);       // Green
        applyColor(builder, MODIFIERS, 0x880088);   // Purple
    }

    private static void applyColor(SpannableStringBuilder builder, String[] words, int color) {
        for (String word : words) {
            int start = 0;
            while (true) {
                start = builder.toString().indexOf(word, start);
                if (start < 0) {
                    break;
                }
                int end = start + word.length();
                builder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                start = end;
            }
        }
    }
}
