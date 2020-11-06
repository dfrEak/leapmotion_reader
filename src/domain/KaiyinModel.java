/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.leapmotion.leap.Vector;

/**
 *
 * @author Eric
 */
public class KaiyinModel {
    // 1. the relative orientations of each distal phalangs to the palm
//    private KaiyinModelOrientation orientation;
    private Vector thumbOrientation;
    private Vector indexOrientation;
    private Vector middleOrientation;
    private Vector ringOrientation;
    private Vector pinkyOrientation;
    
    // 2. the top to palm ration RTP
    private float RTPThumb;
    private float RTPIndex;
    private float RTPMiddle;
    private float RTPRing;
    private float RTPPinky;
    
    // 3. the tip to top ration RTT
    private float RTTThumbIndex;
    private float RTTThumbMiddle;
    private float RTTThumbRing;
    private float RTTThumbPinky;
    private float RTTIndexMiddle;
    private float RTTIndexRing;
    private float RTTIndexPinky;
    private float RTTMiddleRing;
    private float RTTMiddlePinky;
    private float RTTRingPinky;
    
    // 4. the tip to joint ration RTJ
    private float RTJThumb;
    private float RTJIndex;
    private float RTJMiddle;
    private float RTJRing;
    private float RTJPinky;
    
    private int gesture;

    public Vector getThumbOrientation() {
        return thumbOrientation;
    }

    public int getGesture() {
        return gesture;
    }

    public void setGesture(int gesture) {
        this.gesture = gesture;
    }

    public void setThumbOrientation(Vector thumbOrientation) {
        this.thumbOrientation = thumbOrientation;
    }

    public Vector getIndexOrientation() {
        return indexOrientation;
    }

    public void setIndexOrientation(Vector indexOrientation) {
        this.indexOrientation = indexOrientation;
    }

    public Vector getMiddleOrientation() {
        return middleOrientation;
    }

    public void setMiddleOrientation(Vector middleOrientation) {
        this.middleOrientation = middleOrientation;
    }

    public Vector getRingOrientation() {
        return ringOrientation;
    }

    public void setRingOrientation(Vector ringOrientation) {
        this.ringOrientation = ringOrientation;
    }

    public Vector getPinkyOrientation() {
        return pinkyOrientation;
    }

    public void setPinkyOrientation(Vector pinkyOrientation) {
        this.pinkyOrientation = pinkyOrientation;
    }

    public float getRTPThumb() {
        return RTPThumb;
    }

    public void setRTPThumb(float RTPThumb) {
        this.RTPThumb = RTPThumb;
    }

    public float getRTPIndex() {
        return RTPIndex;
    }

    public void setRTPIndex(float RTPIndex) {
        this.RTPIndex = RTPIndex;
    }

    public float getRTPMiddle() {
        return RTPMiddle;
    }

    public void setRTPMiddle(float RTPMiddle) {
        this.RTPMiddle = RTPMiddle;
    }

    public float getRTPRing() {
        return RTPRing;
    }

    public void setRTPRing(float RTPRing) {
        this.RTPRing = RTPRing;
    }

    public float getRTPPinky() {
        return RTPPinky;
    }

    public void setRTPPinky(float RTPPinky) {
        this.RTPPinky = RTPPinky;
    }

    public float getRTTThumbIndex() {
        return RTTThumbIndex;
    }

    public void setRTTThumbIndex(float RTTThumbIndex) {
        this.RTTThumbIndex = RTTThumbIndex;
    }

    public float getRTTThumbMiddle() {
        return RTTThumbMiddle;
    }

    public void setRTTThumbMiddle(float RTTThumbMiddle) {
        this.RTTThumbMiddle = RTTThumbMiddle;
    }

    public float getRTTThumbRing() {
        return RTTThumbRing;
    }

    public void setRTTThumbRing(float RTTThumbRing) {
        this.RTTThumbRing = RTTThumbRing;
    }

    public float getRTTThumbPinky() {
        return RTTThumbPinky;
    }

    public void setRTTThumbPinky(float RTTThumbPinky) {
        this.RTTThumbPinky = RTTThumbPinky;
    }

    public float getRTTIndexMiddle() {
        return RTTIndexMiddle;
    }

    public void setRTTIndexMiddle(float RTTIndexMiddle) {
        this.RTTIndexMiddle = RTTIndexMiddle;
    }

    public float getRTTIndexRing() {
        return RTTIndexRing;
    }

    public void setRTTIndexRing(float RTTIndexRing) {
        this.RTTIndexRing = RTTIndexRing;
    }

    public float getRTTIndexPinky() {
        return RTTIndexPinky;
    }

    public void setRTTIndexPinky(float RTTIndexPinky) {
        this.RTTIndexPinky = RTTIndexPinky;
    }

    public float getRTTMiddleRing() {
        return RTTMiddleRing;
    }

    public void setRTTMiddleRing(float RTTMiddleRing) {
        this.RTTMiddleRing = RTTMiddleRing;
    }

    public float getRTTMiddlePinky() {
        return RTTMiddlePinky;
    }

    public void setRTTMiddlePinky(float RTTMiddlePinky) {
        this.RTTMiddlePinky = RTTMiddlePinky;
    }

    public float getRTTRingPinky() {
        return RTTRingPinky;
    }

    public void setRTTRingPinky(float RTTRingPinky) {
        this.RTTRingPinky = RTTRingPinky;
    }

    public float getRTJThumb() {
        return RTJThumb;
    }

    public void setRTJThumb(float RTJThumb) {
        this.RTJThumb = RTJThumb;
    }

    public float getRTJIndex() {
        return RTJIndex;
    }

    public void setRTJIndex(float RTJIndex) {
        this.RTJIndex = RTJIndex;
    }

    public float getRTJMiddle() {
        return RTJMiddle;
    }

    public void setRTJMiddle(float RTJMiddle) {
        this.RTJMiddle = RTJMiddle;
    }

    public float getRTJRing() {
        return RTJRing;
    }

    public void setRTJRing(float RTJRing) {
        this.RTJRing = RTJRing;
    }

    public float getRTJPinky() {
        return RTJPinky;
    }

    public void setRTJPinky(float RTJPinky) {
        this.RTJPinky = RTJPinky;
    }
    
    public static KaiyinModel preProcess(String str)
    {
        return preProcess(LeapHand.parseString(str));
    }
    
    public static KaiyinModel preProcess(LeapHand leaphand)
    {
        KaiyinModel retval=new KaiyinModel();
        
        // generate distance model
        KaiyinModelDistance distance=KaiyinModelDistance.generate(leaphand);
        float total;
        
        // 1. the relative orientations of each distal phalangs to the palm
        // palm normal
        Vector vector1=leaphand.getIndexProximal().minus(leaphand.getIndexMetacarpal());
        Vector vector2=leaphand.getMiddleProximal().minus(leaphand.getMiddleMetacarpal());
        // palm
        vector1=vector2.cross(vector1);
        
        // distal
        vector2=leaphand.getThumbEnd().minus(leaphand.getThumbDistal());
        retval.setThumbOrientation(vector2.minus(vector1));
        vector2=leaphand.getIndexEnd().minus(leaphand.getIndexDistal());
        retval.setIndexOrientation(vector2.minus(vector1));
        vector2=leaphand.getMiddleEnd().minus(leaphand.getMiddleDistal());
        retval.setMiddleOrientation(vector2.minus(vector1));
        vector2=leaphand.getRingEnd().minus(leaphand.getRingDistal());
        retval.setRingOrientation(vector2.minus(vector1));
        vector2=leaphand.getPinkyEnd().minus(leaphand.getPinkyDistal());
        retval.setPinkyOrientation(vector2.minus(vector1));
        
        // 2. the top to palm ration RTP
        retval.setRTPThumb(leaphand.getThumbEnd().distanceTo(leaphand.getPalm()));
        retval.setRTPIndex(leaphand.getIndexEnd().distanceTo(leaphand.getPalm()));
        retval.setRTPMiddle(leaphand.getMiddleEnd().distanceTo(leaphand.getPalm()));
        retval.setRTPRing(leaphand.getRingEnd().distanceTo(leaphand.getPalm()));
        retval.setRTPPinky(leaphand.getMiddleEnd().distanceTo(leaphand.getPalm()));
        // calculate total
        total=retval.getRTPThumb()+
                retval.getRTPIndex()+
                retval.getRTPMiddle()+
                retval.getRTPRing()+
                retval.getRTPPinky();
        // normalization
        retval.setRTPThumb(retval.getRTPThumb()/total);
        retval.setRTPIndex(retval.getRTPIndex()/total);
        retval.setRTPMiddle(retval.getRTPMiddle()/total);
        retval.setRTPRing(retval.getRTPRing()/total);
        retval.setRTPPinky(retval.getRTPPinky()/total);

        // 3. the tip to top ration RTT
        retval.setRTTThumbIndex(leaphand.getThumbEnd().distanceTo(leaphand.getIndexEnd()));
        retval.setRTTThumbMiddle(leaphand.getThumbEnd().distanceTo(leaphand.getMiddleEnd()));
        retval.setRTTThumbRing(leaphand.getThumbEnd().distanceTo(leaphand.getRingEnd()));
        retval.setRTTThumbPinky(leaphand.getThumbEnd().distanceTo(leaphand.getPinkyEnd()));
        retval.setRTTIndexMiddle(leaphand.getIndexEnd().distanceTo(leaphand.getMiddleEnd()));
        retval.setRTTIndexRing(leaphand.getIndexEnd().distanceTo(leaphand.getRingEnd()));
        retval.setRTTIndexPinky(leaphand.getIndexEnd().distanceTo(leaphand.getPinkyEnd()));
        retval.setRTTMiddleRing(leaphand.getMiddleEnd().distanceTo(leaphand.getRingEnd()));
        retval.setRTTMiddlePinky(leaphand.getMiddleEnd().distanceTo(leaphand.getPinkyEnd()));
        retval.setRTTRingPinky(leaphand.getRingEnd().distanceTo(leaphand.getPinkyEnd()));
        // calculate total
        total=retval.getRTTThumbIndex()+
                retval.getRTTThumbMiddle()+
                retval.getRTTThumbRing()+
                retval.getRTTThumbPinky()+
                retval.getRTTIndexMiddle()+
                retval.getRTTIndexRing()+
                retval.getRTTIndexPinky()+
                retval.getRTTMiddleRing()+
                retval.getRTTMiddlePinky()+
                retval.getRTTRingPinky();
        // normalization
        retval.setRTTThumbIndex(retval.getRTTThumbIndex()/total);
        retval.setRTTThumbMiddle(retval.getRTTThumbMiddle()/total);
        retval.setRTTThumbRing(retval.getRTTThumbRing()/total);
        retval.setRTTThumbPinky(retval.getRTTThumbPinky()/total);
        retval.setRTTIndexMiddle(retval.getRTTIndexMiddle()/total);
        retval.setRTTIndexRing(retval.getRTTIndexRing()/total);
        retval.setRTTIndexPinky(retval.getRTTIndexPinky()/total);
        retval.setRTTMiddleRing(retval.getRTTMiddleRing()/total);
        retval.setRTTMiddlePinky(retval.getRTTMiddlePinky()/total);
        retval.setRTTRingPinky(retval.getRTTRingPinky()/total);
        
        // 4. the tip to joint ration RTJ
        retval.setRTJThumb((leaphand.getThumbEnd().distanceTo(leaphand.getThumbProximal()))/
                (distance.getThumbDistal()+distance.getThumbIntermediate()+distance.getThumbProximal())
            );
        retval.setRTJIndex((leaphand.getIndexEnd().distanceTo(leaphand.getIndexProximal()))/
                (distance.getIndexDistal()+distance.getIndexIntermediate()+distance.getIndexProximal())
            );
        retval.setRTJMiddle((leaphand.getMiddleEnd().distanceTo(leaphand.getMiddleProximal()))/
                (distance.getMiddleDistal()+distance.getMiddleIntermediate()+distance.getMiddleProximal())
            );
        retval.setRTJRing((leaphand.getRingEnd().distanceTo(leaphand.getRingProximal()))/
                (distance.getRingDistal()+distance.getRingIntermediate()+distance.getRingProximal())
            );
        retval.setRTJPinky((leaphand.getPinkyEnd().distanceTo(leaphand.getPinkyProximal()))/
                (distance.getPinkyDistal()+distance.getPinkyIntermediate()+distance.getPinkyProximal())
            );
        
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
        // 1
        retval.append(thumbOrientation.getX()).append(",").append(thumbOrientation.getY()).append(",").append(thumbOrientation.getZ()).append(",");
        retval.append(indexOrientation.getX()).append(",").append(indexOrientation.getY()).append(",").append(indexOrientation.getZ()).append(",");
        retval.append(middleOrientation.getX()).append(",").append(middleOrientation.getY()).append(",").append(middleOrientation.getZ()).append(",");
        retval.append(ringOrientation.getX()).append(",").append(ringOrientation.getY()).append(",").append(ringOrientation.getZ()).append(",");
        retval.append(pinkyOrientation.getX()).append(",").append(pinkyOrientation.getY()).append(",").append(pinkyOrientation.getZ()).append(",");
        // 2
        retval.append(RTPThumb).append(",");
        retval.append(RTPIndex).append(",");
        retval.append(RTPMiddle).append(",");
        retval.append(RTPRing).append(",");
        retval.append(RTPPinky).append(",");
        // 3
        retval.append(RTTThumbIndex).append(",");
        retval.append(RTTThumbMiddle).append(",");
        retval.append(RTTThumbRing).append(",");
        retval.append(RTTThumbPinky).append(",");
        retval.append(RTTIndexMiddle).append(",");
        retval.append(RTTIndexRing).append(",");
        retval.append(RTTIndexPinky).append(",");
        retval.append(RTTMiddleRing).append(",");
        retval.append(RTTMiddlePinky).append(",");
        retval.append(RTTRingPinky).append(",");
        // 4
        retval.append(RTJThumb).append(",");
        retval.append(RTJIndex).append(",");
        retval.append(RTJMiddle).append(",");
        retval.append(RTJRing).append(",");
        retval.append(RTJPinky).append(",");
        // gesture
        retval.append(gesture);
        return retval.toString();
    }
}
