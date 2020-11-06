/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import org.jblas.DoubleMatrix;

/**
 *
 * @author Eric
 */
public class LeapData {
    // type
    public final static int typeLeft=0;
    public final static int typeRight=1;
    private int handType;
    
    // hand info
    private DoubleMatrix palm=new DoubleMatrix(3);
    private DoubleMatrix arm=new DoubleMatrix(3);
    private DoubleMatrix wrist=new DoubleMatrix(3);
    private DoubleMatrix elbow=new DoubleMatrix(3);
    private String pitch;
    private String roll;
    private String yaw;
    
    // finger
    // thumb
    private double thumbLength;
    private double thumbWidth;
    private DoubleMatrix thumbProximal=new DoubleMatrix(3);
    private DoubleMatrix thumbMetacarpal=new DoubleMatrix(3);
    private DoubleMatrix thumbIntermediate=new DoubleMatrix(3);
    private DoubleMatrix thumbDistal=new DoubleMatrix(3);
    private DoubleMatrix thumbEnd=new DoubleMatrix(3);
    
    // index
    private double indexLength;
    private double indexWidth;
    private DoubleMatrix indexProximal=new DoubleMatrix(3);
    private DoubleMatrix indexMetacarpal=new DoubleMatrix(3);
    private DoubleMatrix indexIntermediate=new DoubleMatrix(3);
    private DoubleMatrix indexDistal=new DoubleMatrix(3);
    private DoubleMatrix indexEnd=new DoubleMatrix(3);
    
    // middle
    private double middleLength;
    private double middleWidth;
    private DoubleMatrix middleProximal=new DoubleMatrix(3);
    private DoubleMatrix middleMetacarpal=new DoubleMatrix(3);
    private DoubleMatrix middleIntermediate=new DoubleMatrix(3);
    private DoubleMatrix middleDistal=new DoubleMatrix(3);
    private DoubleMatrix middleEnd=new DoubleMatrix(3);
    
    // ring
    private double ringLength;
    private double ringWidth;
    private DoubleMatrix ringProximal=new DoubleMatrix(3);
    private DoubleMatrix ringMetacarpal=new DoubleMatrix(3);
    private DoubleMatrix ringIntermediate=new DoubleMatrix(3);
    private DoubleMatrix ringDistal=new DoubleMatrix(3);
    private DoubleMatrix ringEnd=new DoubleMatrix(3);
    
    // pinky
    private double pinkyLength;
    private double pinkyWidth;
    private DoubleMatrix pinkyProximal=new DoubleMatrix(3);
    private DoubleMatrix pinkyMetacarpal=new DoubleMatrix(3);
    private DoubleMatrix pinkyIntermediate=new DoubleMatrix(3);
    private DoubleMatrix pinkyDistal=new DoubleMatrix(3);
    private DoubleMatrix pinkyEnd=new DoubleMatrix(3);

    public int getHandType() {
        return handType;
    }

    public void setHandType(int handType) {
        this.handType = handType;
    }

    public DoubleMatrix getPalm() {
        return palm;
    }

    public void setPalm(DoubleMatrix palm) {
        this.palm = palm;
    }

    public DoubleMatrix getArm() {
        return arm;
    }

    public void setArm(DoubleMatrix arm) {
        this.arm = arm;
    }

    public DoubleMatrix getWrist() {
        return wrist;
    }

    public void setWrist(DoubleMatrix wrist) {
        this.wrist = wrist;
    }

    public DoubleMatrix getElbow() {
        return elbow;
    }

    public void setElbow(DoubleMatrix elbow) {
        this.elbow = elbow;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getYaw() {
        return yaw;
    }

    public void setYaw(String yaw) {
        this.yaw = yaw;
    }

    public double getThumbLength() {
        return thumbLength;
    }

    public void setThumbLength(double thumbLength) {
        this.thumbLength = thumbLength;
    }

    public double getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(double thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public DoubleMatrix getThumbProximal() {
        return thumbProximal;
    }

    public void setThumbProximal(DoubleMatrix thumbProximal) {
        this.thumbProximal = thumbProximal;
    }

    public DoubleMatrix getThumbMetacarpal() {
        return thumbMetacarpal;
    }

    public void setThumbMetacarpal(DoubleMatrix thumbMetacarpal) {
        this.thumbMetacarpal = thumbMetacarpal;
    }

    public DoubleMatrix getThumbIntermediate() {
        return thumbIntermediate;
    }

    public void setThumbIntermediate(DoubleMatrix thumbIntermediate) {
        this.thumbIntermediate = thumbIntermediate;
    }

    public DoubleMatrix getThumbDistal() {
        return thumbDistal;
    }

    public void setThumbDistal(DoubleMatrix thumbDistal) {
        this.thumbDistal = thumbDistal;
    }

    public DoubleMatrix getThumbEnd() {
        return thumbEnd;
    }

    public void setThumbEnd(DoubleMatrix thumbEnd) {
        this.thumbEnd = thumbEnd;
    }

    public double getIndexLength() {
        return indexLength;
    }

    public void setIndexLength(double indexLength) {
        this.indexLength = indexLength;
    }

    public double getIndexWidth() {
        return indexWidth;
    }

    public void setIndexWidth(double indexWidth) {
        this.indexWidth = indexWidth;
    }

    public DoubleMatrix getIndexProximal() {
        return indexProximal;
    }

    public void setIndexProximal(DoubleMatrix indexProximal) {
        this.indexProximal = indexProximal;
    }

    public DoubleMatrix getIndexMetacarpal() {
        return indexMetacarpal;
    }

    public void setIndexMetacarpal(DoubleMatrix indexMetacarpal) {
        this.indexMetacarpal = indexMetacarpal;
    }

    public DoubleMatrix getIndexIntermediate() {
        return indexIntermediate;
    }

    public void setIndexIntermediate(DoubleMatrix indexIntermediate) {
        this.indexIntermediate = indexIntermediate;
    }

    public DoubleMatrix getIndexDistal() {
        return indexDistal;
    }

    public void setIndexDistal(DoubleMatrix indexDistal) {
        this.indexDistal = indexDistal;
    }

    public DoubleMatrix getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(DoubleMatrix indexEnd) {
        this.indexEnd = indexEnd;
    }

    public double getMiddleLength() {
        return middleLength;
    }

    public void setMiddleLength(double middleLength) {
        this.middleLength = middleLength;
    }

    public double getMiddleWidth() {
        return middleWidth;
    }

    public void setMiddleWidth(double middleWidth) {
        this.middleWidth = middleWidth;
    }

    public DoubleMatrix getMiddleProximal() {
        return middleProximal;
    }

    public void setMiddleProximal(DoubleMatrix middleProximal) {
        this.middleProximal = middleProximal;
    }

    public DoubleMatrix getMiddleMetacarpal() {
        return middleMetacarpal;
    }

    public void setMiddleMetacarpal(DoubleMatrix middleMetacarpal) {
        this.middleMetacarpal = middleMetacarpal;
    }

    public DoubleMatrix getMiddleIntermediate() {
        return middleIntermediate;
    }

    public void setMiddleIntermediate(DoubleMatrix middleIntermediate) {
        this.middleIntermediate = middleIntermediate;
    }

    public DoubleMatrix getMiddleDistal() {
        return middleDistal;
    }

    public void setMiddleDistal(DoubleMatrix middleDistal) {
        this.middleDistal = middleDistal;
    }

    public DoubleMatrix getMiddleEnd() {
        return middleEnd;
    }

    public void setMiddleEnd(DoubleMatrix middleEnd) {
        this.middleEnd = middleEnd;
    }

    public double getRingLength() {
        return ringLength;
    }

    public void setRingLength(double ringLength) {
        this.ringLength = ringLength;
    }

    public double getRingWidth() {
        return ringWidth;
    }

    public void setRingWidth(double ringWidth) {
        this.ringWidth = ringWidth;
    }

    public DoubleMatrix getRingProximal() {
        return ringProximal;
    }

    public void setRingProximal(DoubleMatrix ringProximal) {
        this.ringProximal = ringProximal;
    }

    public DoubleMatrix getRingMetacarpal() {
        return ringMetacarpal;
    }

    public void setRingMetacarpal(DoubleMatrix ringMetacarpal) {
        this.ringMetacarpal = ringMetacarpal;
    }

    public DoubleMatrix getRingIntermediate() {
        return ringIntermediate;
    }

    public void setRingIntermediate(DoubleMatrix ringIntermediate) {
        this.ringIntermediate = ringIntermediate;
    }

    public DoubleMatrix getRingDistal() {
        return ringDistal;
    }

    public void setRingDistal(DoubleMatrix ringDistal) {
        this.ringDistal = ringDistal;
    }

    public DoubleMatrix getRingEnd() {
        return ringEnd;
    }

    public void setRingEnd(DoubleMatrix ringEnd) {
        this.ringEnd = ringEnd;
    }

    public double getPinkyLength() {
        return pinkyLength;
    }

    public void setPinkyLength(double pinkyLength) {
        this.pinkyLength = pinkyLength;
    }

    public double getPinkyWidth() {
        return pinkyWidth;
    }

    public void setPinkyWidth(double pinkyWidth) {
        this.pinkyWidth = pinkyWidth;
    }

    public DoubleMatrix getPinkyProximal() {
        return pinkyProximal;
    }

    public void setPinkyProximal(DoubleMatrix pinkyProximal) {
        this.pinkyProximal = pinkyProximal;
    }

    public DoubleMatrix getPinkyMetacarpal() {
        return pinkyMetacarpal;
    }

    public void setPinkyMetacarpal(DoubleMatrix pinkyMetacarpal) {
        this.pinkyMetacarpal = pinkyMetacarpal;
    }

    public DoubleMatrix getPinkyIntermediate() {
        return pinkyIntermediate;
    }

    public void setPinkyIntermediate(DoubleMatrix pinkyIntermediate) {
        this.pinkyIntermediate = pinkyIntermediate;
    }

    public DoubleMatrix getPinkyDistal() {
        return pinkyDistal;
    }

    public void setPinkyDistal(DoubleMatrix pinkyDistal) {
        this.pinkyDistal = pinkyDistal;
    }

    public DoubleMatrix getPinkyEnd() {
        return pinkyEnd;
    }

    public void setPinkyEnd(DoubleMatrix pinkyEnd) {
        this.pinkyEnd = pinkyEnd;
    }
    
    /**
     * Frame id: 133870, timestamp: 22207390523, hands: 1, fingers: 5, tools: 0, gestures 0
        Left hand, id: 35, palm position: (-33.267, 173.094, 28.6862)
        pitch: 28.312047647504343 degrees, roll: 19.810017706421938 degrees, yaw: 42.61270328825247 degrees
        Arm direction: (0.84239, 0.277254, -0.46207), wrist position: (-72.9335, 156.82, 60.2097), elbow position: (-294.185, 84.0003, 181.571)
    TYPE_THUMB, id: 350, length: 50.69714mm, width: 19.698553mm
      TYPE_METACARPAL bone, start: (-52.0577, 148.861, 79.9926), end: (-52.0577, 148.861, 79.9926), direction: (0, 0, 0)
      TYPE_PROXIMAL bone, start: (-52.0577, 148.861, 79.9926), end: (-7.87663, 163.812, 66.4871), direction: (-0.909858, -0.307899, 0.27813)
      TYPE_INTERMEDIATE bone, start: (-7.87663, 163.812, 66.4871), end: (22.7657, 175.641, 61.8822), direction: (-0.923875, -0.356621, 0.138836)
      TYPE_DISTAL bone, start: (22.7657, 175.641, 61.8822), end: (41.7981, 186.001, 68.8625), direction: (-0.835993, -0.455093, -0.306603)
    TYPE_INDEX, id: 351, length: 57.20607mm, width: 18.816057mm
      TYPE_METACARPAL bone, start: (-57.3436, 169.422, 70.9913), end: (-1.5551, 186.605, 29.5896), direction: (-0.779537, -0.240104, 0.578508)
      TYPE_PROXIMAL bone, start: (-1.5551, 186.605, 29.5896), end: (28.8701, 193.084, 1.68016), direction: (-0.728007, -0.15503, 0.667811)
      TYPE_INTERMEDIATE bone, start: (28.8701, 193.084, 1.68016), end: (46.2251, 194.963, -14.071), direction: (-0.738127, -0.079901, 0.669913)
      TYPE_DISTAL bone, start: (46.2251, 194.963, -14.071), end: (58.5752, 195.312, -25.1879), direction: (-0.743076, -0.0209772, 0.668878)
    TYPE_MIDDLE, id: 352, length: 65.181725mm, width: 18.47987mm
      TYPE_METACARPAL bone, start: (-64.2117, 170.648, 61.2548), end: (-17.2324, 186.291, 14.8403), direction: (-0.692214, -0.230498, 0.683894)
      TYPE_PROXIMAL bone, start: (-17.2324, 186.291, 14.8403), end: (9.64497, 193.515, -22.8943), direction: (-0.573228, -0.154053, 0.804784)
      TYPE_INTERMEDIATE bone, start: (9.64497, 193.515, -22.8943), end: (26.0327, 196.07, -45.0324), direction: (-0.592427, -0.0923966, 0.800308)
      TYPE_DISTAL bone, start: (26.0327, 196.07, -45.0324), end: (37.1086, 196.86, -59.5538), direction: (-0.605892, -0.0432115, 0.794373)
    TYPE_RING, id: 353, length: 62.673965mm, width: 17.584766mm
      TYPE_METACARPAL bone, start: (-71.5676, 168.799, 52.0194), end: (-36.1127, 181.702, 4.17133), direction: (-0.581855, -0.211757, 0.785241)
      TYPE_PROXIMAL bone, start: (-36.1127, 181.702, 4.17133), end: (-10.9702, 184.348, -31.1822), direction: (-0.578483, -0.0608752, 0.813419)
      TYPE_INTERMEDIATE bone, start: (-10.9702, 184.348, -31.1822), end: (5.18983, 184.231, -52.7464), direction: (-0.599681, 0.00433456, 0.800227)
      TYPE_DISTAL bone, start: (5.18983, 184.231, -52.7464), end: (16.3483, 183.245, -67.0591), direction: (-0.613942, 0.0542712, 0.787483)
    TYPE_PINKY, id: 354, length: 49.13523mm, width: 15.620165mm
      TYPE_METACARPAL bone, start: (-78.7001, 160.009, 44.0487), end: (-52.9075, 172.886, -4.43402), direction: (-0.457266, -0.228292, 0.859529)
      TYPE_PROXIMAL bone, start: (-52.9075, 172.886, -4.43402), end: (-38.0098, 176.584, -35.2153), direction: (-0.433119, -0.107522, 0.894901)
      TYPE_INTERMEDIATE bone, start: (-38.0098, 176.584, -35.2153), end: (-29.3172, 177.882, -52.0898), direction: (-0.456877, -0.0682212, 0.88691)
      TYPE_DISTAL bone, start: (-29.3172, 177.882, -52.0898), end: (-21.3576, 178.511, -66.8342), direction: (-0.474705, -0.0374895, 0.879346)
     */
    
    public void readHand(String str)
    {
        String[] strPerData=str.split("\t");
        if(strPerData[0].equalsIgnoreCase("FALSE")) handType=0; else handType=1;
        
        palm.put(0, Double.parseDouble(strPerData[1]));
        palm.put(1, Double.parseDouble(strPerData[2]));
        palm.put(2, Double.parseDouble(strPerData[3]));
        
        arm.put(0, Double.parseDouble(strPerData[4]));
        arm.put(1, Double.parseDouble(strPerData[5]));
        arm.put(2, Double.parseDouble(strPerData[6]));
        
        wrist.put(0, Double.parseDouble(strPerData[7]));
        wrist.put(1, Double.parseDouble(strPerData[8]));
        wrist.put(2, Double.parseDouble(strPerData[9]));
        
        elbow.put(0, Double.parseDouble(strPerData[10]));
        elbow.put(1, Double.parseDouble(strPerData[11]));
        elbow.put(2, Double.parseDouble(strPerData[12]));
        
        pitch=strPerData[13];
        roll=strPerData[14];
        yaw=strPerData[15];
        
        thumbMetacarpal.put(0, Double.parseDouble(strPerData[16]));
        thumbMetacarpal.put(1, Double.parseDouble(strPerData[17]));
        thumbMetacarpal.put(2, Double.parseDouble(strPerData[18]));
        thumbProximal.put(0, Double.parseDouble(strPerData[19]));
        thumbProximal.put(1, Double.parseDouble(strPerData[20]));
        thumbProximal.put(2, Double.parseDouble(strPerData[21]));
        thumbIntermediate.put(0, Double.parseDouble(strPerData[22]));
        thumbIntermediate.put(1, Double.parseDouble(strPerData[23]));
        thumbIntermediate.put(2, Double.parseDouble(strPerData[24]));
        thumbDistal.put(0, Double.parseDouble(strPerData[25]));
        thumbDistal.put(1, Double.parseDouble(strPerData[26]));
        thumbDistal.put(2, Double.parseDouble(strPerData[27]));
        thumbEnd.put(0, Double.parseDouble(strPerData[28]));
        thumbEnd.put(1, Double.parseDouble(strPerData[29]));
        thumbEnd.put(2, Double.parseDouble(strPerData[30]));
        
        indexMetacarpal.put(0, Double.parseDouble(strPerData[31]));
        indexMetacarpal.put(1, Double.parseDouble(strPerData[32]));
        indexMetacarpal.put(2, Double.parseDouble(strPerData[33]));
        indexProximal.put(0, Double.parseDouble(strPerData[34]));
        indexProximal.put(1, Double.parseDouble(strPerData[35]));
        indexProximal.put(2, Double.parseDouble(strPerData[36]));
        indexIntermediate.put(0, Double.parseDouble(strPerData[37]));
        indexIntermediate.put(1, Double.parseDouble(strPerData[38]));
        indexIntermediate.put(2, Double.parseDouble(strPerData[39]));
        indexDistal.put(0, Double.parseDouble(strPerData[40]));
        indexDistal.put(1, Double.parseDouble(strPerData[41]));
        indexDistal.put(2, Double.parseDouble(strPerData[42]));
        indexEnd.put(0, Double.parseDouble(strPerData[43]));
        indexEnd.put(1, Double.parseDouble(strPerData[44]));
        indexEnd.put(2, Double.parseDouble(strPerData[45]));
        
        middleMetacarpal.put(0, Double.parseDouble(strPerData[46]));
        middleMetacarpal.put(1, Double.parseDouble(strPerData[47]));
        middleMetacarpal.put(2, Double.parseDouble(strPerData[48]));
        middleProximal.put(0, Double.parseDouble(strPerData[49]));
        middleProximal.put(1, Double.parseDouble(strPerData[50]));
        middleProximal.put(2, Double.parseDouble(strPerData[51]));
        middleIntermediate.put(0, Double.parseDouble(strPerData[52]));
        middleIntermediate.put(1, Double.parseDouble(strPerData[53]));
        middleIntermediate.put(2, Double.parseDouble(strPerData[54]));
        middleDistal.put(0, Double.parseDouble(strPerData[55]));
        middleDistal.put(1, Double.parseDouble(strPerData[56]));
        middleDistal.put(2, Double.parseDouble(strPerData[57]));
        middleEnd.put(0, Double.parseDouble(strPerData[58]));
        middleEnd.put(1, Double.parseDouble(strPerData[59]));
        middleEnd.put(2, Double.parseDouble(strPerData[60]));
        
        ringMetacarpal.put(0, Double.parseDouble(strPerData[61]));
        ringMetacarpal.put(1, Double.parseDouble(strPerData[62]));
        ringMetacarpal.put(2, Double.parseDouble(strPerData[63]));
        ringProximal.put(0, Double.parseDouble(strPerData[64]));
        ringProximal.put(1, Double.parseDouble(strPerData[65]));
        ringProximal.put(2, Double.parseDouble(strPerData[66]));
        ringIntermediate.put(0, Double.parseDouble(strPerData[67]));
        ringIntermediate.put(1, Double.parseDouble(strPerData[68]));
        ringIntermediate.put(2, Double.parseDouble(strPerData[69]));
        ringDistal.put(0, Double.parseDouble(strPerData[70]));
        ringDistal.put(1, Double.parseDouble(strPerData[71]));
        ringDistal.put(2, Double.parseDouble(strPerData[72]));
        ringEnd.put(0, Double.parseDouble(strPerData[73]));
        ringEnd.put(1, Double.parseDouble(strPerData[74]));
        ringEnd.put(2, Double.parseDouble(strPerData[75]));
        
        pinkyMetacarpal.put(0, Double.parseDouble(strPerData[76]));
        pinkyMetacarpal.put(1, Double.parseDouble(strPerData[77]));
        pinkyMetacarpal.put(2, Double.parseDouble(strPerData[78]));
        pinkyProximal.put(0, Double.parseDouble(strPerData[79]));
        pinkyProximal.put(1, Double.parseDouble(strPerData[80]));
        pinkyProximal.put(2, Double.parseDouble(strPerData[81]));
        pinkyIntermediate.put(0, Double.parseDouble(strPerData[82]));
        pinkyIntermediate.put(1, Double.parseDouble(strPerData[83]));
        pinkyIntermediate.put(2, Double.parseDouble(strPerData[84]));
        pinkyDistal.put(0, Double.parseDouble(strPerData[85]));
        pinkyDistal.put(1, Double.parseDouble(strPerData[86]));
        pinkyDistal.put(2, Double.parseDouble(strPerData[87]));
        pinkyEnd.put(0, Double.parseDouble(strPerData[88]));
        pinkyEnd.put(1, Double.parseDouble(strPerData[89]));
        pinkyEnd.put(2, Double.parseDouble(strPerData[90]));
    }
    
    public DoubleMatrix getMatrix(){
        DoubleMatrix retval = new DoubleMatrix(3,29);
        
        retval.putColumn(0, palm.sub(wrist));
        retval.putColumn(1, arm.sub(wrist));
        retval.putColumn(2, wrist.sub(wrist));
        retval.putColumn(3, elbow.sub(wrist));
        
        retval.putColumn(4, thumbMetacarpal.sub(wrist));
        retval.putColumn(5, thumbProximal.sub(wrist));
        retval.putColumn(6, thumbIntermediate.sub(wrist));
        retval.putColumn(7, thumbDistal.sub(wrist));
        retval.putColumn(8, thumbEnd.sub(wrist));
        
        retval.putColumn(9, indexMetacarpal.sub(wrist));
        retval.putColumn(10, indexProximal.sub(wrist));
        retval.putColumn(11, indexIntermediate.sub(wrist));
        retval.putColumn(12, indexDistal.sub(wrist));
        retval.putColumn(13, indexEnd.sub(wrist));
        
        retval.putColumn(14, middleMetacarpal.sub(wrist));
        retval.putColumn(15, middleProximal.sub(wrist));
        retval.putColumn(16, middleIntermediate.sub(wrist));
        retval.putColumn(17, middleDistal.sub(wrist));
        retval.putColumn(18, middleEnd.sub(wrist));
        
        retval.putColumn(19, ringMetacarpal.sub(wrist));
        retval.putColumn(20, ringProximal.sub(wrist));
        retval.putColumn(21, ringIntermediate.sub(wrist));
        retval.putColumn(22, ringDistal.sub(wrist));
        retval.putColumn(23, ringEnd.sub(wrist));
        
        retval.putColumn(24, pinkyMetacarpal.sub(wrist));
        retval.putColumn(25, pinkyProximal.sub(wrist));
        retval.putColumn(26, pinkyIntermediate.sub(wrist));
        retval.putColumn(27, pinkyDistal.sub(wrist));
        retval.putColumn(28, pinkyEnd.sub(wrist));
        return retval;
    }
    
    public void setMatrix(int handType, DoubleMatrix matrix)
    {
        this.handType=handType;
        
        palm=matrix.getColumn(0);
        arm=matrix.getColumn(1);
        wrist=matrix.getColumn(2);
        elbow=matrix.getColumn(3);
        
        thumbMetacarpal=matrix.getColumn(4);
        thumbProximal=matrix.getColumn(5);
        thumbIntermediate=matrix.getColumn(6);
        thumbDistal=matrix.getColumn(7);
        thumbEnd=matrix.getColumn(8);
        
        indexMetacarpal=matrix.getColumn(9);
        indexProximal=matrix.getColumn(10);
        indexIntermediate=matrix.getColumn(11);
        indexDistal=matrix.getColumn(12);
        indexEnd=matrix.getColumn(13);
        
        middleMetacarpal=matrix.getColumn(14);
        middleProximal=matrix.getColumn(15);
        middleIntermediate=matrix.getColumn(16);
        middleDistal=matrix.getColumn(17);
        middleEnd=matrix.getColumn(18);
        
        ringMetacarpal=matrix.getColumn(19);
        ringProximal=matrix.getColumn(20);
        ringIntermediate=matrix.getColumn(21);
        ringDistal=matrix.getColumn(22);
        ringEnd=matrix.getColumn(23);
        
        pinkyMetacarpal=matrix.getColumn(24);
        pinkyProximal=matrix.getColumn(25);
        pinkyIntermediate=matrix.getColumn(26);
        pinkyDistal=matrix.getColumn(27);
        pinkyEnd=matrix.getColumn(28);
    }
    
    public String printData()
    {
        StringBuilder retval=new StringBuilder();
        // type
        if (handType==1) {
            retval.append("TRUE").append("\t");
        }
        else {
            retval.append("FALSE").append("\t");
        }
        
        // hand info
        // palm
        retval.append(palm.get(0)).append("\t");
        retval.append(palm.get(1)).append("\t");
        retval.append(palm.get(2)).append("\t");
        
        // arm
        retval.append(arm.get(0)).append("\t");
        retval.append(arm.get(1)).append("\t");
        retval.append(arm.get(2)).append("\t");
        
        // wrist
        retval.append(wrist.get(0)).append("\t");
        retval.append(wrist.get(1)).append("\t");
        retval.append(wrist.get(2)).append("\t");
        
        // elbow
        retval.append(elbow.get(0)).append("\t");
        retval.append(elbow.get(1)).append("\t");
        retval.append(elbow.get(2)).append("\t");
        
        // pitch, roll, yaw
//        retval.append(hand.direction().pitch()).append("\t");
//        retval.append(hand.palmNormal().roll()).append("\t");
//        retval.append(hand.direction().yaw()).append("\t");
        
        // finger
        retval.append(thumbMetacarpal.get(0)).append("\t");
        retval.append(thumbMetacarpal.get(1)).append("\t");
        retval.append(thumbMetacarpal.get(2)).append("\t");
        retval.append(thumbProximal.get(0)).append("\t");
        retval.append(thumbProximal.get(1)).append("\t");
        retval.append(thumbProximal.get(2)).append("\t");
        retval.append(thumbIntermediate.get(0)).append("\t");
        retval.append(thumbIntermediate.get(1)).append("\t");
        retval.append(thumbIntermediate.get(2)).append("\t");
        retval.append(thumbDistal.get(0)).append("\t");
        retval.append(thumbDistal.get(1)).append("\t");
        retval.append(thumbDistal.get(2)).append("\t");
        retval.append(thumbEnd.get(0)).append("\t");
        retval.append(thumbEnd.get(1)).append("\t");
        retval.append(thumbEnd.get(2)).append("\t");
        
        retval.append(indexMetacarpal.get(0)).append("\t");
        retval.append(indexMetacarpal.get(1)).append("\t");
        retval.append(indexMetacarpal.get(2)).append("\t");
        retval.append(indexProximal.get(0)).append("\t");
        retval.append(indexProximal.get(1)).append("\t");
        retval.append(indexProximal.get(2)).append("\t");
        retval.append(indexIntermediate.get(0)).append("\t");
        retval.append(indexIntermediate.get(1)).append("\t");
        retval.append(indexIntermediate.get(2)).append("\t");
        retval.append(indexDistal.get(0)).append("\t");
        retval.append(indexDistal.get(1)).append("\t");
        retval.append(indexDistal.get(2)).append("\t");
        retval.append(indexEnd.get(0)).append("\t");
        retval.append(indexEnd.get(1)).append("\t");
        retval.append(indexEnd.get(2)).append("\t");
        
        retval.append(middleMetacarpal.get(0)).append("\t");
        retval.append(middleMetacarpal.get(1)).append("\t");
        retval.append(middleMetacarpal.get(2)).append("\t");
        retval.append(middleProximal.get(0)).append("\t");
        retval.append(middleProximal.get(1)).append("\t");
        retval.append(middleProximal.get(2)).append("\t");
        retval.append(middleIntermediate.get(0)).append("\t");
        retval.append(middleIntermediate.get(1)).append("\t");
        retval.append(middleIntermediate.get(2)).append("\t");
        retval.append(middleDistal.get(0)).append("\t");
        retval.append(middleDistal.get(1)).append("\t");
        retval.append(middleDistal.get(2)).append("\t");
        retval.append(middleEnd.get(0)).append("\t");
        retval.append(middleEnd.get(1)).append("\t");
        retval.append(middleEnd.get(2)).append("\t");
        
        retval.append(ringMetacarpal.get(0)).append("\t");
        retval.append(ringMetacarpal.get(1)).append("\t");
        retval.append(ringMetacarpal.get(2)).append("\t");
        retval.append(ringProximal.get(0)).append("\t");
        retval.append(ringProximal.get(1)).append("\t");
        retval.append(ringProximal.get(2)).append("\t");
        retval.append(ringIntermediate.get(0)).append("\t");
        retval.append(ringIntermediate.get(1)).append("\t");
        retval.append(ringIntermediate.get(2)).append("\t");
        retval.append(ringDistal.get(0)).append("\t");
        retval.append(ringDistal.get(1)).append("\t");
        retval.append(ringDistal.get(2)).append("\t");
        retval.append(ringEnd.get(0)).append("\t");
        retval.append(ringEnd.get(1)).append("\t");
        retval.append(ringEnd.get(2)).append("\t");
        
        retval.append(pinkyMetacarpal.get(0)).append("\t");
        retval.append(pinkyMetacarpal.get(1)).append("\t");
        retval.append(pinkyMetacarpal.get(2)).append("\t");
        retval.append(pinkyProximal.get(0)).append("\t");
        retval.append(pinkyProximal.get(1)).append("\t");
        retval.append(pinkyProximal.get(2)).append("\t");
        retval.append(pinkyIntermediate.get(0)).append("\t");
        retval.append(pinkyIntermediate.get(1)).append("\t");
        retval.append(pinkyIntermediate.get(2)).append("\t");
        retval.append(pinkyDistal.get(0)).append("\t");
        retval.append(pinkyDistal.get(1)).append("\t");
        retval.append(pinkyDistal.get(2)).append("\t");
        retval.append(pinkyEnd.get(0)).append("\t");
        retval.append(pinkyEnd.get(1)).append("\t");
        retval.append(pinkyEnd.get(2)).append("\t");
        
        return retval.toString();
    }
    
    public static String printHand(Frame frame)
    {
        StringBuilder retval=new StringBuilder();
        retval.append(frame.timestamp()).append("\t");
        retval.append(printHand(frame.hands().get(0))).append("\n");
        return retval.toString();
    }
    
    public static String printHand(Hand hand)
    {
        StringBuilder retval=new StringBuilder();
        
        // type
        retval.append(hand.isRight()).append("\t");
        
        // hand info
        // palm
        retval.append(hand.palmPosition().getX()).append("\t");
        retval.append(hand.palmPosition().getY()).append("\t");
        retval.append(hand.palmPosition().getZ()).append("\t");
        
        // arm
        retval.append(hand.arm().direction().getX()).append("\t");
        retval.append(hand.arm().direction().getY()).append("\t");
        retval.append(hand.arm().direction().getZ()).append("\t");
        
        // wrist
        retval.append(hand.wristPosition().getX()).append("\t");
        retval.append(hand.wristPosition().getY()).append("\t");
        retval.append(hand.wristPosition().getZ()).append("\t");
        
        // elbow
        retval.append(hand.arm().elbowPosition().getX()).append("\t");
        retval.append(hand.arm().elbowPosition().getY()).append("\t");
        retval.append(hand.arm().elbowPosition().getZ()).append("\t");
        
        // pitch, roll, yaw
        retval.append(hand.direction().pitch()).append("\t");
        retval.append(hand.palmNormal().roll()).append("\t");
        retval.append(hand.direction().yaw()).append("\t");
        
        // finger
        for (Finger finger : hand.fingers()) {
//            System.out.println("    " + finger.type() + ", id: " + finger.id()
//                             + ", length: " + finger.length()
//                             + "mm, width: " + finger.width() + "mm");

            //Get Bones
            for(Bone.Type boneType : Bone.Type.values()) {
                Bone bone = finger.bone(boneType);
//                System.out.println("      " + bone.type()
//                                 + " bone, start: " + bone.prevJoint()
//                                 + ", end: " + bone.nextJoint()
//                                 + ", direction: " + bone.direction());
                retval.append(bone.prevJoint().getX()).append("\t");
                retval.append(bone.prevJoint().getY()).append("\t");
                retval.append(bone.prevJoint().getZ()).append("\t");
                if(bone.type()==Bone.Type.TYPE_DISTAL)
                {
                    retval.append(bone.nextJoint().getX()).append("\t");
                    retval.append(bone.nextJoint().getY()).append("\t");
                    retval.append(bone.nextJoint().getZ()).append("\t");
                }
            }
        }
        
        return retval.toString();
    }
}
