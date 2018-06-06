/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.exceptions;

import javax.swing.JOptionPane;

/**
 *
 * @author Manu
 */
public class ClienteException extends Exception {

    public ClienteException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message , "Error", 0);

    }
}
