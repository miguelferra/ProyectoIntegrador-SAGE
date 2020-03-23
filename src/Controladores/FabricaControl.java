/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

/**
 *
 * @author ferra
 */
public class FabricaControl {
    
     public static IFachadaControl getFachadaDeControl() {
        return new FachadaControl();
    }
}

