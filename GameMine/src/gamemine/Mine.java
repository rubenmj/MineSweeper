/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemine;

/**
 *
 * @author rmj84
 */
public class Mine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Initialize ini=new Initialize();
        ini.setMineField("*...\n..*.\n....");
        System.out.println(ini.getHintField());
    }
    
}
