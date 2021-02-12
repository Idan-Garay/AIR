/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.awt.Color;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author idan
 */
public class AIR_Search extends AIR_highlighter{
    private int lastMatch = -1;
    
    public AIR_Search() {
        
    }
    
    public AIR_Search(Color color) {
        super(color);
    }
    
    public AIR_Search(Color color1, Color color2) {
        super(color1, color2);
    }
    
    public void searchText(JTextComponent textComp, String textString) {
        try {
            textComp.getHighlighter().removeAllHighlights();
            
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
    
            int pos = 0;
            int len = textString.length();
            while ((pos = text.toUpperCase().indexOf(textString.toUpperCase(), pos)) >= 0 ) {
                this.addHighlight(textComp, pos, pos+len);
                pos += len;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void replaceSearchText(JTextComponent textComp, String findText, String replaceText, Highlighter.HighlightPainter painter) {
        try {
            if (replaceText.length() > 0 && findText.length() > 0) {
                textComp.getHighlighter().removeAllHighlights();
                Document doc = textComp.getDocument();
                String text = doc.getText(0, doc.getLength());

                int findLen = findText.length();
                int replaceLen = replaceText.length();

                int count = 0;
                int offset = 0;

                while ((offset = text.indexOf(findText, offset)) != -1)
                {
                    int replaceOffset = offset + ((replaceLen - findLen) * count);
                    textComp.select(replaceOffset, replaceOffset + findLen);
                    textComp.replaceSelection( replaceText );

                    offset += replaceLen;
                    count++;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void find(JTextComponent textComp, String input, Highlighter.HighlightPainter highlightPainter) {
        textComp.getHighlighter().removeAllHighlights();
        this.searchText(textComp, input);
        Highlighter.Highlight highlights[] = textComp.getHighlighter().getHighlights();
        int hLen = highlights.length;
        
        
        try {
            lastMatch = (lastMatch+1)%hLen;
            int start = highlights[lastMatch].getStartOffset();
            int end = highlights[lastMatch].getEndOffset();
            Object highlightTag = highlights[lastMatch];
            textComp.getHighlighter().removeHighlight(highlightTag);
            Rectangle2D viewRect = textComp.modelToView2D(start);
            textComp.scrollRectToVisible((Rectangle) viewRect);
            textComp.getHighlighter().addHighlight(start, end, highlightPainter);
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void replace(JTextComponent tp, String input, String find) {
        Highlighter.Highlight highlights[] = tp.getHighlighter().getHighlights();
        int hLen = highlights.length;
        int start = highlights[hLen-1].getStartOffset();
        
        if (start >= 0) {
            int end = highlights[hLen-1].getEndOffset();
            tp.select(start, end);
            tp.replaceSelection(input);
            tp.getHighlighter().removeHighlight(highlights[hLen-1]);
            if (this.lastMatch >= 0)
                this.lastMatch--;
        }
        this.find(tp, find, this.getHighlighter2());
    }

    public int getLastMatch() {
        return lastMatch;
    }

    public void setLastMatch(int lastMatch) {
        this.lastMatch = lastMatch;
    }
    
    
}
