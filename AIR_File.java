/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author idan
 */
public class AIR_File {
    private String fileName;
    private String directory;
    private boolean changed = false;
    
    public AIR_File(){
    }
    
    public File FileSelection(AIR air) {
        File f = null;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showOpenDialog(air);
        fc.setSize(500, 300);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Document Files", "doc", "docx", "txt"));
        fc.setVisible(true);
        if(result == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            this.directory = fc.getCurrentDirectory().getAbsolutePath();
        }
        return f;
    }

    public File FileSave(AIR air) {
        File f = null;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showSaveDialog(air);
        fc.setSize(500, 300);
        fc.setVisible(true);
        if(result == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            if (!f.getPath().toLowerCase().endsWith(".docx")) {
                f = new File(f.getPath() + ".docx");
            }
            this.fileName = fc.getSelectedFile().getName();
            this.directory = fc.getCurrentDirectory().getAbsolutePath();
        }
        
        return f;
    }
    
    public void saveStyles(Document doc, EditorKit kit, File f) {
        if (fileName != null) {
            try {
                
                FileOutputStream fis = new FileOutputStream(f);
                kit.write(fis, doc, 0, doc.getLength());
                fis.close();
            } catch (IOException e) {
                System.out.println(e);
            } catch (BadLocationException ex) {
                Logger.getLogger(AIR_File.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void newFile(AIR air) {
        try {
            Document doc = (StyledDocument)air.getTextPane().getDocument();
            if (doc.getLength() > 0) {
                int val = JOptionPane.showConfirmDialog(air, "Do you want to save this document first?");
                if (val == JOptionPane.YES_OPTION) {
                    saveAs(air);
                    doc.remove(0, doc.getLength());
                } else if (val == JOptionPane.NO_OPTION){
                    try {
                        doc.remove(0, doc.getLength());
                    } catch (BadLocationException ex) {
                        JOptionPane.showMessageDialog(air, "Error! Could not create new file!", "Error", JOptionPane.WARNING_MESSAGE);
                        Logger.getLogger(AIR_File.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (doc.getText(0, doc.getLength()).strip().length() == 0){
                doc.remove(0, doc.getLength());
            }
            SimpleAttributeSet a = new SimpleAttributeSet();
            StyleConstants.setFontFamily(a, "Arial");
            StyleConstants.setFontSize(a, 13);
            StyleConstants.setAlignment(a, StyleConstants.ALIGN_LEFT);
            a.addAttributes(a);
            air.getTextPane().setCharacterAttributes(a, true);
            air.getTextPane().setCaretPosition(0);
            air.getTextPane().setParagraphAttributes(a, false);
        } catch (BadLocationException ex) {
            JOptionPane.showMessageDialog(air, "Error! Could not create new file!", "Error", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(AIR_File.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void open(AIR air) {
        if (this.changed == true) {
            this.changed = false;
            int val = JOptionPane.showConfirmDialog(air, "Do you want to save the changes?");
                
            if (val == JOptionPane.YES_OPTION)
                this.save(air);
        }
        
        File f = FileSelection(air);
        try(FileInputStream fis = new FileInputStream(f)) {  
            Document doc = (StyledDocument) air.getTextPane().getDocument();
            doc.remove(0, doc.getLength());
            air.getTextPane().getEditorKit().read(fis, doc, 0);
            this.fileName = f.getName(); 
        }catch(Exception e) {
            JOptionPane.showMessageDialog(air, "Error! Could not open file!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void save(AIR air){
        
        if (fileName != null) {
            File f = new File(directory+"/"+fileName);
            Document doc = air.getTextPane().getDocument();
            this.saveStyles(doc, air.getTextPane().getEditorKit(), f);
           
        } else {
            saveAs(air);
        }
            
    }
    
    public void saveAs(AIR air){
        File f = FileSave(air);
        try {  
            this.saveStyles(air.getTextPane().getDocument(), air.getTextPane().getEditorKit(), f);
        }catch(Exception e) {  
            JOptionPane.showMessageDialog(air, "Error! Could not save file!", "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println(e);  
        }
       
    }
    
    public void exit(AIR air){
        Document doc = (StyledDocument)air.getTextPane().getDocument();
        
        try {
            if (doc.getText(0, doc.getLength()).strip().length() > 0 || this.changed == true) {
                int val = JOptionPane.showConfirmDialog(air, "Do you want to save the changes?");
                
                if (val == JOptionPane.YES_OPTION)
                    this.save(air);
            }
            this.changed = false;    
            System.exit(0);
        } catch (BadLocationException ex) {
            Logger.getLogger(AIR_File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
    
}
