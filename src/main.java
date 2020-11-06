
import leapmotion.demo;
import leapmotion.motiondetection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Eric
 */
public class main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // leap motion frame rate ~30 if idle, ~110 if not idle
        // 1/110 = 9 milisecond = 9000 microsecond
        // timestamp --> microsecond
        motiondetection tes=new motiondetection();
        tes.setVisible(true);
//        System.out.println(System.getProperty("java.library.path"));
//        System.setProperty("java.library.path", "./lib");
//        demo tes2=new demo();
//        tes2.setVisible(true);
//        TestData tes=new TestData();
    }
}
