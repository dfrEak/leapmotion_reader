package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
public class RajeshModel {
    private float d1;
    private float d2;
    private float d3;
    private float d4;
    private float d5;
    private float d6;
    private float d7;
    private float d8;
    private int gesture;

    public int getGesture() {
        return gesture;
    }

    public void setGesture(int gesture) {
        this.gesture = gesture;
    }

    public float getD1() {
        return d1;
    }

    public void setD1(float d1) {
        this.d1 = d1;
    }

    public float getD2() {
        return d2;
    }

    public void setD2(float d2) {
        this.d2 = d2;
    }

    public float getD3() {
        return d3;
    }

    public void setD3(float d3) {
        this.d3 = d3;
    }

    public float getD4() {
        return d4;
    }

    public void setD4(float d4) {
        this.d4 = d4;
    }

    public float getD5() {
        return d5;
    }

    public void setD5(float d5) {
        this.d5 = d5;
    }

    public float getD6() {
        return d6;
    }

    public void setD6(float d6) {
        this.d6 = d6;
    }

    public float getD7() {
        return d7;
    }

    public void setD7(float d7) {
        this.d7 = d7;
    }

    public float getD8() {
        return d8;
    }

    public void setD8(float d8) {
        this.d8 = d8;
    }
    
    public static RajeshModel preProcess(String str)
    {
        return preProcess(LeapHand.parseString(str));
    }
    
    public static RajeshModel preProcess(LeapHand leaphand)
    {
        RajeshModel retval=new RajeshModel();
        // distance to palm
        retval.setD1(leaphand.getThumbEnd().distanceTo(leaphand.getPalm()));
        retval.setD2(leaphand.getIndexEnd().distanceTo(leaphand.getPalm()));
        retval.setD3(leaphand.getMiddleEnd().distanceTo(leaphand.getPalm()));
        retval.setD4(leaphand.getRingEnd().distanceTo(leaphand.getPalm()));
        retval.setD5(leaphand.getPinkyEnd().distanceTo(leaphand.getPalm()));
        // distance between fingertip
        retval.setD6(leaphand.getIndexEnd().distanceTo(leaphand.getMiddleEnd()));
        retval.setD7(leaphand.getIndexEnd().distanceTo(leaphand.getRingEnd()));
        retval.setD8(leaphand.getIndexEnd().distanceTo(leaphand.getPinkyEnd()));
        // gesture
        retval.setGesture(leaphand.getGesture());
        return retval;
    }
    
    public static String print(String str)
    {
        return preProcess(str).print();
    }
    
    public String print()
    {
        StringBuilder retval=new StringBuilder();
        retval.append(d1).append(",");
        retval.append(d2).append(",");
        retval.append(d3).append(",");
        retval.append(d4).append(",");
        retval.append(d5).append(",");
        retval.append(d6).append(",");
        retval.append(d7).append(",");
        retval.append(d8).append(",");
        retval.append(gesture);
        return retval.toString();
    }
}
