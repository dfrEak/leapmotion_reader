/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

/**
 *
 * @author Eric
 */
public class AngleData {
    private double thumbAngleA;
    private double thumbAngleB;
    private double thumbAngleC;
    
    private double indexAngleA;
    private double indexAngleB;
    private double indexAngleC;
    
    private double middleAngleA;
    private double middleAngleB;
    private double middleAngleC;
    
    private double ringAngleA;
    private double ringAngleB;
    private double ringAngleC;
    
    private double pinkyAngleA;
    private double pinkyAngleB;
    private double pinkyAngleC;
    
    private double spaceAngleA; // between thumb and index
    private double spaceAngleB; // between index and middle
    private double spaceAngleC; // between middle and ring
    private double spaceAngleD; // between ring and pinky
    
    private int gesture;

    public int getGesture() {
        return gesture;
    }

    public void setGesture(int gesture) {
        this.gesture = gesture;
    }

    public double getThumbAngleA() {
        return thumbAngleA;
    }

    public void setThumbAngleA(double thumbAngleA) {
        this.thumbAngleA = thumbAngleA;
    }

    public double getThumbAngleB() {
        return thumbAngleB;
    }

    public void setThumbAngleB(double thumbAngleB) {
        this.thumbAngleB = thumbAngleB;
    }

    public double getThumbAngleC() {
        return thumbAngleC;
    }

    public void setThumbAngleC(double thumbAngleC) {
        this.thumbAngleC = thumbAngleC;
    }

    public double getIndexAngleA() {
        return indexAngleA;
    }

    public void setIndexAngleA(double indexAngleA) {
        this.indexAngleA = indexAngleA;
    }

    public double getIndexAngleB() {
        return indexAngleB;
    }

    public void setIndexAngleB(double indexAngleB) {
        this.indexAngleB = indexAngleB;
    }

    public double getIndexAngleC() {
        return indexAngleC;
    }

    public void setIndexAngleC(double indexAngleC) {
        this.indexAngleC = indexAngleC;
    }

    public double getMiddleAngleA() {
        return middleAngleA;
    }

    public void setMiddleAngleA(double middleAngleA) {
        this.middleAngleA = middleAngleA;
    }

    public double getMiddleAngleB() {
        return middleAngleB;
    }

    public void setMiddleAngleB(double middleAngleB) {
        this.middleAngleB = middleAngleB;
    }

    public double getMiddleAngleC() {
        return middleAngleC;
    }

    public void setMiddleAngleC(double middleAngleC) {
        this.middleAngleC = middleAngleC;
    }

    public double getRingAngleA() {
        return ringAngleA;
    }

    public void setRingAngleA(double ringAngleA) {
        this.ringAngleA = ringAngleA;
    }

    public double getRingAngleB() {
        return ringAngleB;
    }

    public void setRingAngleB(double ringAngleB) {
        this.ringAngleB = ringAngleB;
    }

    public double getRingAngleC() {
        return ringAngleC;
    }

    public void setRingAngleC(double ringAngleC) {
        this.ringAngleC = ringAngleC;
    }

    public double getPinkyAngleA() {
        return pinkyAngleA;
    }

    public void setPinkyAngleA(double pinkyAngleA) {
        this.pinkyAngleA = pinkyAngleA;
    }

    public double getPinkyAngleB() {
        return pinkyAngleB;
    }

    public void setPinkyAngleB(double pinkyAngleB) {
        this.pinkyAngleB = pinkyAngleB;
    }

    public double getPinkyAngleC() {
        return pinkyAngleC;
    }

    public void setPinkyAngleC(double pinkyAngleC) {
        this.pinkyAngleC = pinkyAngleC;
    }

    public double getSpaceAngleA() {
        return spaceAngleA;
    }

    public void setSpaceAngleA(double spaceAngleA) {
        this.spaceAngleA = spaceAngleA;
    }

    public double getSpaceAngleB() {
        return spaceAngleB;
    }

    public void setSpaceAngleB(double spaceAngleB) {
        this.spaceAngleB = spaceAngleB;
    }

    public double getSpaceAngleC() {
        return spaceAngleC;
    }

    public void setSpaceAngleC(double spaceAngleC) {
        this.spaceAngleC = spaceAngleC;
    }

    public double getSpaceAngleD() {
        return spaceAngleD;
    }

    public void setSpaceAngleD(double spaceAngleD) {
        this.spaceAngleD = spaceAngleD;
    }
    
    public AngleData create(Hand hand)
    {
        AngleData retval=new AngleData();
        Vector vector1=new Vector();
        Vector vector2=new Vector();
        
        // thumb
//        thumbAngleA=0; // not implemented yet /////////////////////////////////////////////////////
        // using cross matrix so the vector will be different between left and right hand
        vector1=hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).direction().cross(
            hand.fingers().get(2).bone(Bone.Type.TYPE_METACARPAL).direction());
        
//        System.out.println("metacarpal begin "+hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).prevJoint());
//        System.out.println("metacarpal begin "+hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).nextJoint());
//        System.out.println("metacarpal1: "+hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).direction());
//        System.out.println("metacarpal2: "+hand.fingers().get(2).bone(Bone.Type.TYPE_METACARPAL).direction());
//        System.out.println("vector1 "+ vector1);
//        vector1=hand.fingers().get(1).bone(Bone.Type.TYPE_PROXIMAL).prevJoint()
//                .minus(hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).prevJoint())
//                .cross(hand.fingers().get(2).bone(Bone.Type.TYPE_PROXIMAL).prevJoint()
//                .minus(hand.fingers().get(2).bone(Bone.Type.TYPE_METACARPAL).prevJoint()));
        // opposite direction
        if(hand.isRight())
            vector1=vector1.opposite();
//        vector2=hand.fingers().get(0).bone(Bone.Type.TYPE_INTERMEDIATE).prevJoint()
//                .minus(hand.fingers().get(0).bone(Bone.Type.TYPE_PROXIMAL).prevJoint());
        vector2=hand.fingers().get(0).bone(Bone.Type.TYPE_PROXIMAL).direction();
        thumbAngleA=vector1.angleTo(vector2);
        vector1=hand.fingers().get(0).bone(Bone.Type.TYPE_PROXIMAL).direction();
        vector2=hand.fingers().get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
        thumbAngleB=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(0).bone(Bone.Type.TYPE_DISTAL).direction();
        thumbAngleC=vector1.angleTo(vector2);
        
        // index
        vector1=hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).direction();
        vector2=hand.fingers().get(1).bone(Bone.Type.TYPE_PROXIMAL).direction();
        indexAngleA=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(1).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
        indexAngleB=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(1).bone(Bone.Type.TYPE_DISTAL).direction();
        indexAngleC=vector1.angleTo(vector2);
        
        // middle
        vector1=hand.fingers().get(2).bone(Bone.Type.TYPE_METACARPAL).direction();
        vector2=hand.fingers().get(2).bone(Bone.Type.TYPE_PROXIMAL).direction();
        middleAngleA=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(2).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
        middleAngleB=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(2).bone(Bone.Type.TYPE_DISTAL).direction();
        middleAngleC=vector1.angleTo(vector2);
        
        // ring
        vector1=hand.fingers().get(3).bone(Bone.Type.TYPE_METACARPAL).direction();
        vector2=hand.fingers().get(3).bone(Bone.Type.TYPE_PROXIMAL).direction();
        ringAngleA=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(3).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
        ringAngleB=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(3).bone(Bone.Type.TYPE_DISTAL).direction();
        ringAngleC=vector1.angleTo(vector2);
        
        // pinky
        vector1=hand.fingers().get(4).bone(Bone.Type.TYPE_METACARPAL).direction();
        vector2=hand.fingers().get(4).bone(Bone.Type.TYPE_PROXIMAL).direction();
        pinkyAngleA=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(4).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
        pinkyAngleB=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(4).bone(Bone.Type.TYPE_DISTAL).direction();
        pinkyAngleC=vector1.angleTo(vector2);
        
        // space angle
        // thumb special
        vector1=hand.fingers().get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction();
        vector2=hand.fingers().get(1).bone(Bone.Type.TYPE_PROXIMAL).direction();
        Vector vectorTemp=hand.fingers().get(2).bone(Bone.Type.TYPE_METACARPAL).nextJoint().minus(
                hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).nextJoint());
        spaceAngleA=vector1.angleTo(vector2);
        // check if the angle smaller than 90 degree or not
        if(vector1.angleTo(vectorTemp)>Math.PI/2)
            spaceAngleA=-spaceAngleA;
//        System.out.println(spaceAngleA);
        
        vector1=vector2;
        vector2=hand.fingers().get(2).bone(Bone.Type.TYPE_PROXIMAL).direction();
        spaceAngleB=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(3).bone(Bone.Type.TYPE_PROXIMAL).direction();
        spaceAngleC=vector1.angleTo(vector2);
        vector1=vector2;
        vector2=hand.fingers().get(4).bone(Bone.Type.TYPE_PROXIMAL).direction();
        spaceAngleD=vector1.angleTo(vector2);
        
//        System.out.println("debug finger: "
//            +hand.fingers().get(0).type().name()+" "
//            +hand.fingers().get(1).type().name()+" "
//            +hand.fingers().get(2).type().name()+" "
//            +hand.fingers().get(3).type().name()+" "
//            +hand.fingers().get(4).type().name()+" ");
        
        return retval;
    }
    
    public String print()
    {
        StringBuilder retval=new StringBuilder();
        retval.append(thumbAngleA).append("\t").append(thumbAngleB).append("\t").append(thumbAngleC).append("\t");
        retval.append(indexAngleA).append("\t").append(indexAngleB).append("\t").append(indexAngleC).append("\t");
        retval.append(middleAngleA).append("\t").append(middleAngleB).append("\t").append(middleAngleC).append("\t");
        retval.append(ringAngleA).append("\t").append(ringAngleB).append("\t").append(ringAngleC).append("\t");
        retval.append(pinkyAngleA).append("\t").append(pinkyAngleB).append("\t").append(pinkyAngleC).append("\t");
        retval.append(spaceAngleA).append("\t").append(spaceAngleB).append("\t").append(spaceAngleC).append("\t").append(spaceAngleD).append("\t");
//        retval.append("\n");
        return retval.toString();
    }
    
    public String printGesture()
    {
        StringBuilder retval=new StringBuilder();
        retval.append(print());
        retval.append(gesture);
        return retval.toString();
    }
}
