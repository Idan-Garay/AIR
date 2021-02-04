/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

/**
 *
 * @author idan
 */
public interface EditInterface {
    public abstract void cut(AIR gui);
    public abstract void copy(AIR gui);
    public abstract void paste(AIR gui);
    public abstract void redo(AIR gui);
    public abstract void undo(AIR gui);
}
