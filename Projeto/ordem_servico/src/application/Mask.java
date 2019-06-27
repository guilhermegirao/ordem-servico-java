/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class Mask {
    public static void create (JFormattedTextField txt, String mask) {
        try {
            MaskFormatter m = new MaskFormatter(mask);
            m.install(txt);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar o campo!");
        }
    }
}
