/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Moment {
    public static String getDate () { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date d = new Date ();
	return dateFormat.format(d); 
    }
}
