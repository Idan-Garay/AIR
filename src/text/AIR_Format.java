/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author idan
 */
public class AIR_Format {
    
    public AIR_Format() {
        
    }
    
    public AIR_Format(JTextPane tp) {
        StyledDocument doc = (StyledDocument) tp.getDocument();
        //font family
        Style style = doc.addStyle("Arial", null);
        StyleConstants.setFontFamily(style, "Arial");
        
        style = doc.addStyle("Times New Roman", null);
        StyleConstants.setFontFamily(style, "Times New Roman");
        
        style = doc.addStyle("Century Gothic", null);
        StyleConstants.setFontFamily(style, "Century Gothic");
        
        //fontsize
        style = doc.addStyle("small", null);
        StyleConstants.setFontSize(style, 13);
        
        style = doc.addStyle("medium", null);
        StyleConstants.setFontSize(style, 26);
        
        style = doc.addStyle("large", null);
        StyleConstants.setFontSize(style, 39);
        
        //alignmnet
        style = doc.addStyle("left", null);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_LEFT);
        
        style = doc.addStyle("center", null);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        
        style = doc.addStyle("right", null);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
        
        style = doc.addStyle("justify", null);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
        
        //color
        style = doc.addStyle("red", null);
        StyleConstants.setForeground(style, Color.red);
        
        style = doc.addStyle("black", null);
        StyleConstants.setForeground(style, Color.black);
        
        style = doc.addStyle("blue", null);
        StyleConstants.setForeground(style, Color.blue);
        
    }
        
    public void setFormat(String format, JTextPane tp, int type) {
        StyledDocument doc = (StyledDocument) tp.getDocument();
        Style style = doc.getStyle(format);
        MutableAttributeSet set = new SimpleAttributeSet();
        set.addAttributes(tp.getCharacterAttributes());
        
        switch (format) {
            case "bold": StyleConstants.setBold(set, !StyleConstants.isBold(set));
                break;
            case "italicize": StyleConstants.setItalic(set, !StyleConstants.isItalic(set));
                break;
            case "underline":StyleConstants.setUnderline(set, !StyleConstants.isUnderline(set));
                break;
            default:set.addAttributes(style);
                break;
        }
        setAttribute(tp, type, set);
    }
    
    public void setFormat(int size, JTextPane tp) {
        StyledDocument doc = (StyledDocument) tp.getDocument();
        
        MutableAttributeSet set = new SimpleAttributeSet();
        set.addAttributes(tp.getCharacterAttributes());
        StyleConstants.setFontSize(set, size);
        
        int start = tp.getSelectionStart();
        int end = tp.getSelectionEnd();

        doc.setCharacterAttributes(start, end-start, set, false);
    }
    
    public void setFormat(Color color, JTextPane tp) {
        
        StyledDocument doc = (StyledDocument) tp.getDocument();
        
        MutableAttributeSet m = new SimpleAttributeSet();
        
        m.addAttributes(tp.getCharacterAttributes());

        StyleConstants.setForeground(m, color);
        int start = tp.getSelectionStart();
        int end = tp.getSelectionEnd();

        tp.setCharacterAttributes(m, false);
        doc.setCharacterAttributes(start, end-start, m, false);
    }
    
    public void setAttribute(JTextPane tp, int type, MutableAttributeSet set) {
        StyledDocument doc = (StyledDocument) tp.getDocument();
        int start = tp.getSelectionStart();
        int end = tp.getSelectionEnd();
        
        switch (type) {
            case 0: doc.setParagraphAttributes(start, end-start, set, false);
                break;
            case 1: doc.setCharacterAttributes(start, end-start, set, false);
                break;
        }
    }
}
