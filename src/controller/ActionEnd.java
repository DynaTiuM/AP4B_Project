package controller;

import view.PopupEnd;
import view.View;
import menu.MenuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEnd implements ActionListener {

    private final View viewRef;
    private final PopupEnd popupEnd;
    private final int ID;

    public ActionEnd(View view, PopupEnd popupEnd, int ID) {
        this.viewRef = view;
        this.popupEnd = popupEnd;
        this.ID = ID;
    }

    private void newGame() {
        viewRef.closePopup();
        viewRef.stopGame();
        SwingUtilities.getWindowAncestor(popupEnd).dispose();
        viewRef.dispose();
        new MenuFrame();
    }

    private void quit() {
        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(ID) {
            case 0:
                newGame();
                break;
            case 1:
                quit();
                break;
            default:
                assert false : "Error while clicking on a button : " + ID + " : ActionEnd";
        }
    }
}
