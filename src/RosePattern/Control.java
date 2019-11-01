package RosePattern;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Control implements ItemListener, ChangeListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (source == Main.autoanimate) {
            if (Main.autoanimate.isSelected()) {
                Main.bautoanimate = true;
            } else {
                Main.bautoanimate = false;
            }
        }

        if (source == Main.blackandwhite) {
            if (Main.blackandwhite.isSelected()) {
                Main.bblackandwhite = true;
            } else {
                Main.bblackandwhite = false;
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}