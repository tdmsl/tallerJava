/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.utilidades;

import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;

/**
 *
 * @author Manu
 */
public class SingletonBrowser {
   private static SingletonBrowser INSTANCE = null;
    private BrowserLauncher launcher;
 
    // El constructor privado no permite que se genere un constructor por defecto.
    // (con mismo modificador de acceso que la definición de la clase) 
    private SingletonBrowser() throws BrowserLaunchingInitializingException, UnsupportedOperatingSystemException {
        launcher = new BrowserLauncher();
    }
 
    public static SingletonBrowser getInstance() throws BrowserLaunchingInitializingException, UnsupportedOperatingSystemException {
       if (INSTANCE == null){
             INSTANCE = new SingletonBrowser();
        }
        return INSTANCE;
    } 

    public BrowserLauncher getLauncher() {
        return launcher;
    }
    
    
}
