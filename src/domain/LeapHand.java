/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

/**
 *
 * @author Eric
 */
public class LeapHand {
    // type
    private boolean right;
    
    // hand info
    private Vector palm;
    private Vector arm;
    private Vector wrist;
    private Vector elbow;
    private double pitch;
    private double roll;
    private double yaw;
    
    // finger
    // thumb
    private Vector thumbProximal;
    private Vector thumbMetacarpal;
    private Vector thumbIntermediate;
    private Vector thumbDistal;
    private Vector thumbEnd;
    
    // index
    private Vector indexProximal;
    private Vector indexMetacarpal;
    private Vector indexIntermediate;
    private Vector indexDistal;
    private Vector indexEnd;
    
    // middle
    private Vector middleProximal;
    private Vector middleMetacarpal;
    private Vector middleIntermediate;
    private Vector middleDistal;
    private Vector middleEnd;
    
    // ring
    private Vector ringProximal;
    private Vector ringMetacarpal;
    private Vector ringIntermediate;
    private Vector ringDistal;
    private Vector ringEnd;
    
    // pinky
    private Vector pinkyProximal;
    private Vector pinkyMetacarpal;
    private Vector pinkyIntermediate;
    private Vector pinkyDistal;
    private Vector pinkyEnd;
    
    // gesture
    private int gesture;

    public int getGesture() {
        return gesture;
    }

    public void setGesture(int gesture) {
        this.gesture = gesture;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
    
    public Vector getPalm() {
        return palm;
    }

    public void setPalm(Vector palm) {
        this.palm = palm;
    }

    public Vector getArm() {
        return arm;
    }

    public void setArm(Vector arm) {
        this.arm = arm;
    }

    public Vector getWrist() {
        return wrist;
    }

    public void setWrist(Vector wrist) {
        this.wrist = wrist;
    }

    public Vector getElbow() {
        return elbow;
    }

    public void setElbow(Vector elbow) {
        this.elbow = elbow;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public Vector getThumbProximal() {
        return thumbProximal;
    }

    public void setThumbProximal(Vector thumbProximal) {
        this.thumbProximal = thumbProximal;
    }

    public Vector getThumbMetacarpal() {
        return thumbMetacarpal;
    }

    public void setThumbMetacarpal(Vector thumbMetacarpal) {
        this.thumbMetacarpal = thumbMetacarpal;
    }

    public Vector getThumbIntermediate() {
        return thumbIntermediate;
    }

    public void setThumbIntermediate(Vector thumbIntermediate) {
        this.thumbIntermediate = thumbIntermediate;
    }

    public Vector getThumbDistal() {
        return thumbDistal;
    }

    public void setThumbDistal(Vector thumbDistal) {
        this.thumbDistal = thumbDistal;
    }

    public Vector getThumbEnd() {
        return thumbEnd;
    }

    public void setThumbEnd(Vector thumbEnd) {
        this.thumbEnd = thumbEnd;
    }

    public Vector getIndexProximal() {
        return indexProximal;
    }

    public void setIndexProximal(Vector indexProximal) {
        this.indexProximal = indexProximal;
    }

    public Vector getIndexMetacarpal() {
        return indexMetacarpal;
    }

    public void setIndexMetacarpal(Vector indexMetacarpal) {
        this.indexMetacarpal = indexMetacarpal;
    }

    public Vector getIndexIntermediate() {
        return indexIntermediate;
    }

    public void setIndexIntermediate(Vector indexIntermediate) {
        this.indexIntermediate = indexIntermediate;
    }

    public Vector getIndexDistal() {
        return indexDistal;
    }

    public void setIndexDistal(Vector indexDistal) {
        this.indexDistal = indexDistal;
    }

    public Vector getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(Vector indexEnd) {
        this.indexEnd = indexEnd;
    }

    public Vector getMiddleProximal() {
        return middleProximal;
    }

    public void setMiddleProximal(Vector middleProximal) {
        this.middleProximal = middleProximal;
    }

    public Vector getMiddleMetacarpal() {
        return middleMetacarpal;
    }

    public void setMiddleMetacarpal(Vector middleMetacarpal) {
        this.middleMetacarpal = middleMetacarpal;
    }

    public Vector getMiddleIntermediate() {
        return middleIntermediate;
    }

    public void setMiddleIntermediate(Vector middleIntermediate) {
        this.middleIntermediate = middleIntermediate;
    }

    public Vector getMiddleDistal() {
        return middleDistal;
    }

    public void setMiddleDistal(Vector middleDistal) {
        this.middleDistal = middleDistal;
    }

    public Vector getMiddleEnd() {
        return middleEnd;
    }

    public void setMiddleEnd(Vector middleEnd) {
        this.middleEnd = middleEnd;
    }

    public Vector getRingProximal() {
        return ringProximal;
    }

    public void setRingProximal(Vector ringProximal) {
        this.ringProximal = ringProximal;
    }

    public Vector getRingMetacarpal() {
        return ringMetacarpal;
    }

    public void setRingMetacarpal(Vector ringMetacarpal) {
        this.ringMetacarpal = ringMetacarpal;
    }

    public Vector getRingIntermediate() {
        return ringIntermediate;
    }

    public void setRingIntermediate(Vector ringIntermediate) {
        this.ringIntermediate = ringIntermediate;
    }

    public Vector getRingDistal() {
        return ringDistal;
    }

    public void setRingDistal(Vector ringDistal) {
        this.ringDistal = ringDistal;
    }

    public Vector getRingEnd() {
        return ringEnd;
    }

    public void setRingEnd(Vector ringEnd) {
        this.ringEnd = ringEnd;
    }

    public Vector getPinkyProximal() {
        return pinkyProximal;
    }

    public void setPinkyProximal(Vector pinkyProximal) {
        this.pinkyProximal = pinkyProximal;
    }

    public Vector getPinkyMetacarpal() {
        return pinkyMetacarpal;
    }

    public void setPinkyMetacarpal(Vector pinkyMetacarpal) {
        this.pinkyMetacarpal = pinkyMetacarpal;
    }

    public Vector getPinkyIntermediate() {
        return pinkyIntermediate;
    }

    public void setPinkyIntermediate(Vector pinkyIntermediate) {
        this.pinkyIntermediate = pinkyIntermediate;
    }

    public Vector getPinkyDistal() {
        return pinkyDistal;
    }

    public void setPinkyDistal(Vector pinkyDistal) {
        this.pinkyDistal = pinkyDistal;
    }

    public Vector getPinkyEnd() {
        return pinkyEnd;
    }

    public void setPinkyEnd(Vector pinkyEnd) {
        this.pinkyEnd = pinkyEnd;
    }

    public static LeapHand parseString(String str)
    {
        LeapHand retval=new LeapHand();
        String[] strPerData=str.split("\t");
        int i=0;
        
        //A
        // right
        if(strPerData[i].equalsIgnoreCase("FALSE")) retval.setRight(false); else retval.setRight(true);
        
        //B-D
        // palm
        retval.setPalm(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        
        //E-G
        // arm
//        retval.setArm(new Vector(Float.parseFloat(strPerData[++i]), 
//                        Float.parseFloat(strPerData[++i]), 
//                        Float.parseFloat(strPerData[++i])));
        
        //H-J
        // wrist
        retval.setWrist(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        
        //K-M
        // elbow
//        retval.setElbow(new Vector(Float.parseFloat(strPerData[++i]), 
//                        Float.parseFloat(strPerData[++i]), 
//                        Float.parseFloat(strPerData[++i])));
        
        // pitch,roll,yaw
//        retval.setPitch(Float.parseFloat(strPerData[++i]));
//        retval.setRoll(Float.parseFloat(strPerData[++i]));
//        retval.setYaw(Float.parseFloat(strPerData[++i]));
        
        // thumb
        retval.setThumbMetacarpal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setThumbProximal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setThumbIntermediate(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setThumbDistal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setThumbEnd(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        
        // index
        retval.setIndexMetacarpal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setIndexProximal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setIndexIntermediate(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setIndexDistal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setIndexEnd(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        
        // middle
        retval.setMiddleMetacarpal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setMiddleProximal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setMiddleIntermediate(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setMiddleDistal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setMiddleEnd(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        
        // ring
        retval.setRingMetacarpal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setRingProximal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setRingIntermediate(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setRingDistal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setRingEnd(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        
        // pinky
        retval.setPinkyMetacarpal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setPinkyProximal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setPinkyIntermediate(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setPinkyDistal(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        retval.setPinkyEnd(new Vector(Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i]), 
                        Float.parseFloat(strPerData[++i])));
        
        // gesture
        //i++;
        retval.setGesture(Integer.parseInt(strPerData[++i]));
        
        return retval;
    }

    public static LeapHand parseHand(Hand hand)
    {
        LeapHand retval=new LeapHand();
        
        //A
        // right
        retval.setRight(hand.isRight());
        
        //B-D
        // palm
        retval.setPalm(hand.palmPosition());
        
        //E-G
        // arm
        retval.setArm(hand.arm().direction());
        
        //H-J
        // wrist
        retval.setWrist(hand.wristPosition());
        
        //K-M
        // elbow
        retval.setElbow(hand.arm().elbowPosition());
        
        // pitch,roll,yaw
        retval.setPitch(hand.palmPosition().pitch());
        retval.setRoll(hand.palmPosition().roll());
        retval.setYaw(hand.palmPosition().yaw());
        
        // thumb
        retval.setThumbMetacarpal(hand.fingers().get(0).bone(Bone.Type.TYPE_METACARPAL).prevJoint());
        retval.setThumbProximal(hand.fingers().get(0).bone(Bone.Type.TYPE_PROXIMAL).prevJoint());
        retval.setThumbIntermediate(hand.fingers().get(0).bone(Bone.Type.TYPE_INTERMEDIATE).prevJoint());
        retval.setThumbDistal(hand.fingers().get(0).bone(Bone.Type.TYPE_DISTAL).prevJoint());
        retval.setThumbEnd(hand.fingers().get(0).bone(Bone.Type.TYPE_DISTAL).nextJoint());
        
        // index
        retval.setIndexMetacarpal(hand.fingers().get(1).bone(Bone.Type.TYPE_METACARPAL).prevJoint());
        retval.setIndexProximal(hand.fingers().get(1).bone(Bone.Type.TYPE_PROXIMAL).prevJoint());
        retval.setIndexIntermediate(hand.fingers().get(1).bone(Bone.Type.TYPE_INTERMEDIATE).prevJoint());
        retval.setIndexDistal(hand.fingers().get(1).bone(Bone.Type.TYPE_DISTAL).prevJoint());
        retval.setIndexEnd(hand.fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint());
        
        // middle
        retval.setMiddleMetacarpal(hand.fingers().get(2).bone(Bone.Type.TYPE_METACARPAL).prevJoint());
        retval.setMiddleProximal(hand.fingers().get(2).bone(Bone.Type.TYPE_PROXIMAL).prevJoint());
        retval.setMiddleIntermediate(hand.fingers().get(2).bone(Bone.Type.TYPE_INTERMEDIATE).prevJoint());
        retval.setMiddleDistal(hand.fingers().get(2).bone(Bone.Type.TYPE_DISTAL).prevJoint());
        retval.setMiddleEnd(hand.fingers().get(2).bone(Bone.Type.TYPE_DISTAL).nextJoint());
        
        // ring
        retval.setRingMetacarpal(hand.fingers().get(3).bone(Bone.Type.TYPE_METACARPAL).prevJoint());
        retval.setRingProximal(hand.fingers().get(3).bone(Bone.Type.TYPE_PROXIMAL).prevJoint());
        retval.setRingIntermediate(hand.fingers().get(3).bone(Bone.Type.TYPE_INTERMEDIATE).prevJoint());
        retval.setRingDistal(hand.fingers().get(3).bone(Bone.Type.TYPE_DISTAL).prevJoint());
        retval.setRingEnd(hand.fingers().get(3).bone(Bone.Type.TYPE_DISTAL).nextJoint());
        
        // pinky
        retval.setPinkyMetacarpal(hand.fingers().get(4).bone(Bone.Type.TYPE_METACARPAL).prevJoint());
        retval.setPinkyProximal(hand.fingers().get(4).bone(Bone.Type.TYPE_PROXIMAL).prevJoint());
        retval.setPinkyIntermediate(hand.fingers().get(4).bone(Bone.Type.TYPE_INTERMEDIATE).prevJoint());
        retval.setPinkyDistal(hand.fingers().get(4).bone(Bone.Type.TYPE_DISTAL).prevJoint());
        retval.setPinkyEnd(hand.fingers().get(4).bone(Bone.Type.TYPE_DISTAL).nextJoint());
        
        // gesture
        //i++;
        retval.setGesture(-1);
        
        return retval;
    }
    
    public static AngleData preProcess(String str)
    {
        return preProcess(parseString(str));
    }
    
    public static AngleData preProcess(Hand hand)
    {
        return preProcess(parseHand(hand));
    }
    
    public static AngleData preProcess(LeapHand leaphand)
    {
        AngleData retval=new AngleData();
        Vector vector1;
        Vector vector2;
        
        // thumb
        // using cross matrix so the vector will be different between left and right hand
        vector1=leaphand.getIndexProximal().minus(leaphand.getIndexMetacarpal())
                .cross(leaphand.getMiddleProximal().minus(leaphand.getMiddleMetacarpal()));
//        
//        vector1=leaphand.getIndexMetacarpal().minus(leaphand.getIndexProximal())
//                .cross(leaphand.getMiddleMetacarpal().minus(leaphand.getMiddleProximal()));
//        Vector temp1=leaphand.getIndexMetacarpal().minus(leaphand.getIndexProximal());
//        Vector temp2=leaphand.getMiddleMetacarpal().minus(leaphand.getMiddleProximal());
//        System.out.println("--------------------------------");
//        System.out.println("metacarpal1: "+temp1+" - "+temp1.normalized());
//        System.out.println("metacarpal2: "+temp2+" - "+temp2.normalized());
//        System.out.println("vector1 "+vector1+" - "+ vector1.normalized());
        
        
        // opposite direction
        if(!leaphand.isRight())
            vector1=vector1.opposite();
        Vector palmNormal=vector1;
        // thumb proximal direction
        vector2=leaphand.getThumbIntermediate().minus(leaphand.getThumbProximal());
//        retval.setThumbAngleA(vector1.angleTo(vector2));
        retval.setThumbAngleA(Math.PI/2-palmNormal.angleTo(vector2));
        
        vector1=vector2;
        // thumb intermediate direction
        vector2=leaphand.getThumbDistal().minus(leaphand.getThumbIntermediate());
        retval.setThumbAngleB(vector1.angleTo(vector2));
        
        vector1=vector2;
        // thumb distal direction
        vector2=leaphand.getThumbEnd().minus(leaphand.getThumbDistal());
        retval.setThumbAngleC(vector1.angleTo(vector2));
        
        // index
        // index metacarpal direction
        vector1=leaphand.getIndexProximal().minus(leaphand.getIndexMetacarpal());
        // index proximal direction
        vector2=leaphand.getIndexIntermediate().minus(leaphand.getIndexProximal());
//        retval.setIndexAngleA(vector1.angleTo(vector2));
        retval.setIndexAngleA(Math.PI/2-palmNormal.angleTo(vector2));
        
        vector1=vector2;
        // index intermediate direction
        vector2=leaphand.getIndexDistal().minus(leaphand.getIndexIntermediate());
        retval.setIndexAngleB(vector1.angleTo(vector2));
        
        vector1=vector2;
        // index distal direction
        vector2=leaphand.getIndexEnd().minus(leaphand.getIndexDistal());
        retval.setIndexAngleC(vector1.angleTo(vector2));
        
        // middle
        // middle metacarpal direction
        vector1=leaphand.getMiddleProximal().minus(leaphand.getMiddleMetacarpal());
        // middle proximal direction
        vector2=leaphand.getMiddleIntermediate().minus(leaphand.getMiddleProximal());
//        retval.setMiddleAngleA(vector1.angleTo(vector2));
        retval.setMiddleAngleA(Math.PI/2-palmNormal.angleTo(vector2));
        
        vector1=vector2;
        // middle intermediate direction
        vector2=leaphand.getMiddleDistal().minus(leaphand.getMiddleIntermediate());
        retval.setMiddleAngleB(vector1.angleTo(vector2));
        
        vector1=vector2;
        // middle distal direction
        vector2=leaphand.getMiddleEnd().minus(leaphand.getMiddleDistal());
        retval.setMiddleAngleC(vector1.angleTo(vector2));
        
        // ring
        // ring metacarpal direction
        vector1=leaphand.getRingProximal().minus(leaphand.getRingMetacarpal());
        // ring proximal direction
        vector2=leaphand.getRingIntermediate().minus(leaphand.getRingProximal());
//        retval.setRingAngleA(vector1.angleTo(vector2));
        retval.setRingAngleA(Math.PI/2-palmNormal.angleTo(vector2));
        
        vector1=vector2;
        // ring intermediate direction
        vector2=leaphand.getRingDistal().minus(leaphand.getRingIntermediate());
        retval.setRingAngleB(vector1.angleTo(vector2));
        
        vector1=vector2;
        // ring distal direction
        vector2=leaphand.getRingEnd().minus(leaphand.getRingDistal());
        retval.setRingAngleC(vector1.angleTo(vector2));
        
        // pinky
        // pinky metacarpal direction
        vector1=leaphand.getPinkyProximal().minus(leaphand.getPinkyMetacarpal());
        // pinky proximal direction
        vector2=leaphand.getPinkyIntermediate().minus(leaphand.getPinkyProximal());
//        retval.setPinkyAngleA(vector1.angleTo(vector2));
        retval.setPinkyAngleA(Math.PI/2-palmNormal.angleTo(vector2));
        
        vector1=vector2;
        // pinky intermediate direction
        vector2=leaphand.getPinkyDistal().minus(leaphand.getPinkyIntermediate());
        retval.setPinkyAngleB(vector1.angleTo(vector2));
        
        vector1=vector2;
        // pinky distal direction
        vector2=leaphand.getPinkyEnd().minus(leaphand.getPinkyDistal());
        retval.setPinkyAngleC(vector1.angleTo(vector2));
        
        // 4 angles
        // step: vectorF = proj vectorA to plane(vectorB and vectorC)
        // 1.define vectorD = vectorB x vectorC (cross product)
        // 2.vectorE = projD(vectorA)
        // 3.vectorF = vectorA - vectorE
        
        // step 1
        // index metacarpal direction
        vector1=leaphand.getIndexProximal().minus(leaphand.getIndexMetacarpal());
        // middle metacarpal direction
        vector2=leaphand.getMiddleProximal().minus(leaphand.getMiddleMetacarpal());
        Vector vectorD=vector1.cross(vector2);
        
        // step 2 & 3
        Vector projectionThumb, projectionIndex, projectionMiddle, projectionRing, projectionPinky;
        
        // angleA
        // proj thumb
        projectionThumb=vectorD.times((float) (leaphand.getThumbIntermediate().minus(leaphand.getThumbProximal()).dot(vectorD)
                /Math.pow(vectorD.magnitude(),2)));
        projectionThumb=leaphand.getThumbIntermediate().minus(leaphand.getThumbProximal()).minus(projectionThumb);
        // proj index
        projectionIndex=vectorD.times((float) (leaphand.getIndexIntermediate().minus(leaphand.getIndexProximal()).dot(vectorD)
                /Math.pow(vectorD.magnitude(),2)));
        projectionIndex=leaphand.getIndexIntermediate().minus(leaphand.getIndexProximal()).minus(projectionIndex);
        retval.setSpaceAngleA(projectionThumb.angleTo(projectionIndex));
//        System.out.println(projectionThumb.getX()+" "+projectionThumb.getY()+" "+projectionThumb.getZ()+" -> "+projectionIndex.getX()+" "+projectionIndex.getY()+" "+projectionIndex.getZ()+" ");
        // orientation checking
        Vector temp=leaphand.middleProximal.minus(leaphand.indexProximal);
        // check if the angle smaller than 90 degree or not
        // System.out.println(temp+" - "+projectionThumb.angleTo(temp));
        if(projectionThumb.angleTo(temp)<Math.PI/2)
        {
            retval.setSpaceAngleA(-retval.getSpaceAngleA());
            // System.out.println(retval.getSpaceAngleA());
        }
        
        // angleB
        // proj index
        // proj middle
        projectionMiddle=vectorD.times((float) (leaphand.getMiddleIntermediate().minus(leaphand.getMiddleProximal()).dot(vectorD)
                /Math.pow(vectorD.magnitude(),2)));
        projectionMiddle=leaphand.getMiddleIntermediate().minus(leaphand.getMiddleProximal()).minus(projectionMiddle);
        retval.setSpaceAngleB(projectionIndex.angleTo(projectionMiddle));
//        System.out.println(projectionIndex.getX()+" "+projectionIndex.getY()+" "+projectionIndex.getZ()+" -> "+projectionMiddle.getX()+" "+projectionMiddle.getY()+" "+projectionMiddle.getZ()+" ");
        
        // angleC
        // proj middle
        // proj ring
        projectionRing=vectorD.times((float) (leaphand.getRingIntermediate().minus(leaphand.getRingProximal()).dot(vectorD)
                /Math.pow(vectorD.magnitude(),2)));
        projectionRing=leaphand.getRingIntermediate().minus(leaphand.getRingProximal()).minus(projectionRing);
        retval.setSpaceAngleC(projectionMiddle.angleTo(projectionRing));
//        System.out.println(projectionMiddle.getX()+" "+projectionMiddle.getY()+" "+projectionMiddle.getZ()+" -> "+projectionRing.getX()+" "+projectionRing.getY()+" "+projectionRing.getZ()+" ");
        
        // angleD
        // proj ring
        // proj pinky
        projectionPinky=vectorD.times((float) (leaphand.getPinkyIntermediate().minus(leaphand.getPinkyProximal()).dot(vectorD)
                /Math.pow(vectorD.magnitude(),2)));
        projectionPinky=leaphand.getPinkyIntermediate().minus(leaphand.getPinkyProximal()).minus(projectionPinky);
        retval.setSpaceAngleD(projectionRing.angleTo(projectionPinky));
//        System.out.println(projectionRing.getX()+" "+projectionRing.getY()+" "+projectionRing.getZ()+" -> "+projectionPinky.getX()+" "+projectionPinky.getY()+" "+projectionPinky.getZ()+" ");
        
        retval.setGesture(leaphand.getGesture());
        
        return retval;
    }
    
    public static String print(String str)
    {
        AngleData retval=preProcess(str);
        return retval.printGesture();
    }
    
}
