/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text; 

import javax.swing.JTextPane;


/**
 *
 * @author idan
 */
public class AIR_Text {
    private AIR_Edit edit;
    private AIR_Format format;
    private AIR_File file;
    
    public AIR_Text(AIR_Edit edit) {
        this.edit = edit;
    }
    
    public AIR_Text(AIR_Edit edit, AIR_Format format) {
        this.edit = edit;
        this.format = format;
    }

    public AIR_Text(AIR_Edit edit, AIR_Format format, AIR_File file) {
        this.edit = edit;
        this.format = format;
        this.file = file;
    }
    
    public AIR_Edit getEdit() {
        return edit;
    }

    public void setEdit(AIR_Edit edit) {
        this.edit = edit;
    }

    public AIR_Format getFormat() {
        return format;
    }

    public void setFormat(AIR_Format format) {
        this.format = format;
    }

    public AIR_File getFile() {
        return file;
    }

    public void setFile(AIR_File file) {
        this.file = file;
    }
    
}
