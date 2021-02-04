/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.awt.Color;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;


/**
 *
 * @author idan
 */
public class AIR_Edit implements EditInterface{
    private UndoManager o;
    private AIR_Search as;
    
    public AIR_Edit() {
        this.o = new UndoManager();
    }
    
    public AIR_Edit(AIR gui) {
        this.o = new UndoManager();
        gui.getTextPane().getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        o.addEdit(e.getEdit());
                    }
                });
        
        
        this.as = new AIR_Search(Color.yellow);
    }
    
    @Override
    public void cut(AIR gui) {
        gui.getTextPane().cut();
    }
    @Override
    public void copy(AIR gui) {
        gui.getTextPane().copy();
    }
    @Override
    public void paste(AIR gui) {
        gui.getTextPane().paste();
    }
    @Override
    public void redo(AIR gui) {
        if (o.canRedo()) {
            this.o.redo();
        }
    }
    @Override
    public void undo(AIR gui) {
        if (o.canUndo()) {
            this.o.undo();
        }
    }
    
}
