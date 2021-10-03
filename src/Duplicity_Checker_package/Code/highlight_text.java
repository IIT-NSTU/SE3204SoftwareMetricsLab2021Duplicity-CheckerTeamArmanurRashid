package Duplicity_Checker_package.Code;

import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class highlight_text {
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.RED);

    public int highlight(JTextComponent textComp, String pattern) {
        int counter=1;
        try {
            Highlighter hilite = textComp.getHighlighter();
            javax.swing.text.Document doc = textComp.getDocument();
            String texts = doc.getText(0, doc.getLength());
            String[] finalSentence = texts.split("[\\s]*[-.,!?:)\"][\\s]*");

            int pos = 0;

            while ((pos = texts.toUpperCase().indexOf(pattern.toUpperCase(), pos)) >= 0) {
                if((texts.charAt(pos+pattern.length()) != ' ') && texts.charAt(pos+pattern.length()) != ',' && texts.charAt(pos+pattern.length()) != '-' && texts.charAt(pos+pattern.length()) != '.' &&texts.charAt(pos+pattern.length()) != '"') {
                    pos += pattern.length();
                }
                else {
                    hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
                    counter=0;
                    pos += pattern.length();
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return counter;
    }
}