/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Eric
 */
public class KaiyinModelDistance {    
    private float thumbProximal;
    private float thumbIntermediate;
    private float thumbDistal;

    private float indexMetacarpal;
    private float indexProximal;
    private float indexIntermediate;
    private float indexDistal;

    private float middleMetacarpal;
    private float middleProximal;
    private float middleIntermediate;
    private float middleDistal;

    private float ringMetacarpal;
    private float ringProximal;
    private float ringIntermediate;
    private float ringDistal;

    private float pinkyMetacarpal;
    private float pinkyProximal;
    private float pinkyIntermediate;
    private float pinkyDistal;

    public float getThumbProximal() {
        return thumbProximal;
    }

    public void setThumbProximal(float thumbProximal) {
        this.thumbProximal = thumbProximal;
    }

    public float getThumbIntermediate() {
        return thumbIntermediate;
    }

    public void setThumbIntermediate(float thumbIntermediate) {
        this.thumbIntermediate = thumbIntermediate;
    }

    public float getThumbDistal() {
        return thumbDistal;
    }

    public void setThumbDistal(float thumbDistal) {
        this.thumbDistal = thumbDistal;
    }

    public float getIndexMetacarpal() {
        return indexMetacarpal;
    }

    public void setIndexMetacarpal(float indexMetacarpal) {
        this.indexMetacarpal = indexMetacarpal;
    }

    public float getIndexProximal() {
        return indexProximal;
    }

    public void setIndexProximal(float indexProximal) {
        this.indexProximal = indexProximal;
    }

    public float getIndexIntermediate() {
        return indexIntermediate;
    }

    public void setIndexIntermediate(float indexIntermediate) {
        this.indexIntermediate = indexIntermediate;
    }

    public float getIndexDistal() {
        return indexDistal;
    }

    public void setIndexDistal(float indexDistal) {
        this.indexDistal = indexDistal;
    }

    public float getMiddleMetacarpal() {
        return middleMetacarpal;
    }

    public void setMiddleMetacarpal(float middleMetacarpal) {
        this.middleMetacarpal = middleMetacarpal;
    }

    public float getMiddleProximal() {
        return middleProximal;
    }

    public void setMiddleProximal(float middleProximal) {
        this.middleProximal = middleProximal;
    }

    public float getMiddleIntermediate() {
        return middleIntermediate;
    }

    public void setMiddleIntermediate(float middleIntermediate) {
        this.middleIntermediate = middleIntermediate;
    }

    public float getMiddleDistal() {
        return middleDistal;
    }

    public void setMiddleDistal(float middleDistal) {
        this.middleDistal = middleDistal;
    }

    public float getRingMetacarpal() {
        return ringMetacarpal;
    }

    public void setRingMetacarpal(float ringMetacarpal) {
        this.ringMetacarpal = ringMetacarpal;
    }

    public float getRingProximal() {
        return ringProximal;
    }

    public void setRingProximal(float ringProximal) {
        this.ringProximal = ringProximal;
    }

    public float getRingIntermediate() {
        return ringIntermediate;
    }

    public void setRingIntermediate(float ringIntermediate) {
        this.ringIntermediate = ringIntermediate;
    }

    public float getRingDistal() {
        return ringDistal;
    }

    public void setRingDistal(float ringDistal) {
        this.ringDistal = ringDistal;
    }

    public float getPinkyMetacarpal() {
        return pinkyMetacarpal;
    }

    public void setPinkyMetacarpal(float pinkyMetacarpal) {
        this.pinkyMetacarpal = pinkyMetacarpal;
    }

    public float getPinkyProximal() {
        return pinkyProximal;
    }

    public void setPinkyProximal(float pinkyProximal) {
        this.pinkyProximal = pinkyProximal;
    }

    public float getPinkyIntermediate() {
        return pinkyIntermediate;
    }

    public void setPinkyIntermediate(float pinkyIntermediate) {
        this.pinkyIntermediate = pinkyIntermediate;
    }

    public float getPinkyDistal() {
        return pinkyDistal;
    }

    public void setPinkyDistal(float pinkyDistal) {
        this.pinkyDistal = pinkyDistal;
    }

    public static KaiyinModelDistance generate(LeapHand leaphand)
    {
        KaiyinModelDistance retval=new KaiyinModelDistance();
        
        retval.setThumbProximal(leaphand.getThumbProximal().distanceTo(leaphand.getThumbIntermediate()));
        retval.setThumbIntermediate(leaphand.getThumbIntermediate().distanceTo(leaphand.getThumbDistal()));
        retval.setThumbDistal(leaphand.getThumbDistal().distanceTo(leaphand.getThumbEnd()));
        
        retval.setIndexMetacarpal(leaphand.getIndexMetacarpal().distanceTo(leaphand.getIndexProximal()));
        retval.setIndexProximal(leaphand.getIndexProximal().distanceTo(leaphand.getIndexIntermediate()));
        retval.setIndexIntermediate(leaphand.getIndexIntermediate().distanceTo(leaphand.getIndexDistal()));
        retval.setIndexDistal(leaphand.getIndexDistal().distanceTo(leaphand.getIndexEnd()));
        
        retval.setMiddleMetacarpal(leaphand.getMiddleMetacarpal().distanceTo(leaphand.getMiddleProximal()));
        retval.setMiddleProximal(leaphand.getMiddleProximal().distanceTo(leaphand.getMiddleIntermediate()));
        retval.setMiddleIntermediate(leaphand.getMiddleIntermediate().distanceTo(leaphand.getMiddleDistal()));
        retval.setMiddleDistal(leaphand.getMiddleDistal().distanceTo(leaphand.getMiddleEnd()));
        
        retval.setRingMetacarpal(leaphand.getRingMetacarpal().distanceTo(leaphand.getRingProximal()));
        retval.setRingProximal(leaphand.getRingProximal().distanceTo(leaphand.getRingIntermediate()));
        retval.setRingIntermediate(leaphand.getRingIntermediate().distanceTo(leaphand.getRingDistal()));
        retval.setRingDistal(leaphand.getRingDistal().distanceTo(leaphand.getRingEnd()));
        
        retval.setPinkyMetacarpal(leaphand.getPinkyMetacarpal().distanceTo(leaphand.getPinkyProximal()));
        retval.setPinkyProximal(leaphand.getPinkyProximal().distanceTo(leaphand.getPinkyIntermediate()));
        retval.setPinkyIntermediate(leaphand.getPinkyIntermediate().distanceTo(leaphand.getPinkyDistal()));
        retval.setPinkyDistal(leaphand.getPinkyDistal().distanceTo(leaphand.getPinkyEnd()));
        
        return retval;
    }
}
