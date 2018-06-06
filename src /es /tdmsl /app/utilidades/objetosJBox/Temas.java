/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.utilidades.objetosJBox;

/**
 *
 * @author Manu
 */
public class Temas {
   private String nemo;
   private String path;

    public Temas(String nemo, String path) {
        this.nemo = nemo;
        this.path = path;
    }

    public String getNemo() {
        return nemo;
    }

    public void setNemo(String nemo) {
        this.nemo = nemo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return nemo;
    }
   
}
