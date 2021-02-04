/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.awt.Color;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author idan
 */
public class AIR_highlighter{
    private DefaultHighlighter.DefaultHighlightPainter highlighter;
    private DefaultHighlighter.DefaultHighlightPainter highlighter2;
    
    public AIR_highlighter() { 
    }
    
    public AIR_highlighter(Color color) {
        highlighter = new DefaultHighlighter.DefaultHighlightPainter(color);
    }
    
    public AIR_highlighter(Color color1, Color color2) {
        highlighter = new DefaultHighlighter.DefaultHighlightPainter(color1);
        highlighter2 = new DefaultHighlighter.DefaultHighlightPainter(color2);
    }
    
    public void addHighlight(JTextComponent textComp, int start, int end) {
        try {
            Highlighter highlight = textComp.getHighlighter();
        
            highlight.addHighlight(start, end, this.highlighter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeHighlight(JTextComponent textComp) {
        textComp.getHighlighter().removeAllHighlights();
    }

    public DefaultHighlighter.DefaultHighlightPainter getHighlighter() {
        return highlighter;
    }

    public void setHighlighter(DefaultHighlighter.DefaultHighlightPainter highlighter) {
        this.highlighter = highlighter;
    }

    public DefaultHighlighter.DefaultHighlightPainter getHighlighter2() {
        return highlighter2;
    }

    public void setHighlighter2(DefaultHighlighter.DefaultHighlightPainter highlighter2) {
        this.highlighter2 = highlighter2;
    }
    
    
}
