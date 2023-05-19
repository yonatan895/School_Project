package com.example.classes;


import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class JavaSyntaxHighlighter {
    private static final String[] KEYWORDS = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "if", "goto", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};
    private static final String[] TYPES = {"boolean", "byte", "char", "double", "float", "int", "long", "short", "void"};
    private static final String[] MODIFIERS = {"abstract", "final", "native", "private", "protected", "public", "static", "synchronized", "transient", "volatile"};

    /**
     * The applySyntaxHighlighting function takes a SpannableStringBuilder object and applies
     * syntax highlighting to it. It does this by applying ForegroundColorSpan objects to the
     * builder, which will cause the text in question to be displayed in a different color. The
     * function uses three regular expressions (KEYWORDS, TYPES, and MODIFIERS) that match Java keywords, types (such as int), and modifiers (such as public). Each of these is applied with a different color: orange for keywords, green for types, purple for modifiers. This is done using the applyColor helper function defined below. Note that we
     *
     * @param  builder Apply the color to the text
     *
     */
    public static void applySyntaxHighlighting(SpannableStringBuilder builder) {
        applyColor(builder, KEYWORDS, 0xFF8800);    // Orange
        applyColor(builder, TYPES, 0x008800);       // Green
        applyColor(builder, MODIFIERS, 0x880088);   // Purple
    }

    /**
     * The applyColor function takes a SpannableStringBuilder, an array of words to highlight, and a color.
     * It then iterates through the words in the array and highlights each one by setting its foreground color.

     *
     * @param  builder Apply the color to the text
     * @param  words Pass in the words that should be highlighted
     * @param  color Set the color of the text
     *
     */
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
