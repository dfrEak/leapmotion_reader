/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leapmotion;

import Util.helper;
import com.leapmotion.leap.Arm;
import com.leapmotion.leap.Bone;
import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Tool;
import com.leapmotion.leap.Vector;
import domain.AngleData;
import domain.Global;
import domain.KaiyinModel;
import domain.LeapData;
import domain.LeapHand;
import domain.RajeshModel;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jblas.DoubleMatrix;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Eric
 */
public class motiondetection extends javax.swing.JFrame {
    
    // Create a sample listener and controller
    leapMotionListener listener = new leapMotionListener();
    Controller controller = new Controller();

    public class leapMotionListener extends Listener {
        boolean print;
        int iFrame;
        long prevTime;
        int deltaTime;
        long stopTime;
        
        public void onInit(Controller controller) {
            System.out.println("Initialized");
            print=false;
            deltaTime=0;
        }

        public void onConnect(Controller controller) {
            System.out.println("Connected");
            controller.enableGesture(Gesture.Type.TYPE_SWIPE);
            controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
            controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
            controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        }

        public void onDisconnect(Controller controller) {
            //Note: not dispatched when running in a debugger.
            System.out.println("Disconnected");
        }

        public void onExit(Controller controller) {
            System.out.println("Exited");
        }

        public void onFrame(Controller controller) {
            // Get the most recent frame and report some basic information
            Frame frame = controller.frame();
//                    System.out.println(frame.timestamp());
                    prevTime=frame.timestamp();
//                    System.out.println(prevTime+"---"+deltaTime);
//            System.out.println(frame.currentFramesPerSecond());
                    
            ////////////////////////////////////// 2nd tab //////////////////////////////////////////
            if(print)
            {
                
                // for simplicity
//                if(prevTime==0 || (frame.timestamp()-prevTime)<deltaTime)
                if(stopTime==0)
                {
                    stopTime=frame.timestamp()+5000000;
                }
//                System.out.println(LeapData.printHand(controller.frame().hands().get(0)));
//                System.out.println(LeapData.printHand(controller.frame()));
                resultArea2.append(LeapData.printHand(controller.frame()));
                if(stopTime<frame.timestamp())
                {
                    // set var
//                    iFrame++;
//                    if(iFrame==Integer.parseInt(nFrame.getText()))
//                    {
                        System.out.println("stop");
                        notifLabel2.setText("Finish");
                        print=false;
//                    }
                }
            }
            
            ////////////////////////////////////// 3rd tab //////////////////////////////////////////
            if(StartToggleButton3.isSelected())
            {
                if(Global.singleton().getIndexEnd()==null)
                {
                    // do nothing
                }
                else
                {
                    // compare with prev
                    Double deltaX=Global.singleton().getIndexEnd().get(0)-
                            controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getX();
                    Double deltaY=Global.singleton().getIndexEnd().get(1)-
                            controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getY();
                    Double deltaZ=Global.singleton().getIndexEnd().get(2)-
                            controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getZ();
                    Double distance=Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ);
                    long deltaSec=controller.frame().timestamp()-Global.singleton().getTimestamp();
                    Double speed=distance/deltaSec;
//                    resultArea3.append(deltaX+"\t"+deltaY+"\t"+deltaZ+"\t");
                    resultArea3.append(controller.frame().timestamp()+"\t");
                    resultArea3.append(controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getX()+"\t"
                        +controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getY()+"\t"
                        +controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getZ()+"\t");
                    resultArea3.append(distance+"\t"+speed+"\n");
                    resultArea3.setCaretPosition(resultArea3.getDocument().getLength());
                    if(distance>Double.parseDouble(maxSpeed3.getText()))
                        maxSpeed3.setText(distance.toString());
                }
                // all do it
                DoubleMatrix temp=new DoubleMatrix(3);
                temp.put(0, controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getX());
                temp.put(1, controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getY());
                temp.put(2, controller.frame().hands().get(0).fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getZ());
                Global.singleton().setIndexEnd(temp);
                Global.singleton().setTimestamp(controller.frame().timestamp());
            }
            
            
            /*
            System.out.println("Frame id: " + frame.id()
                             + ", timestamp: " + frame.timestamp()
                             + ", hands: " + frame.hands().count()
                             + ", fingers: " + frame.fingers().count()
                             + ", tools: " + frame.tools().count()
                             + ", gestures " + frame.gestures().count());
            text1.setText(String.valueOf(frame.id()));

            //Get hands
            for(Hand hand : frame.hands()) {
                String handType = hand.isLeft() ? "Left hand" : "Right hand";
                System.out.println("  " + handType + ", id: " + hand.id()
                                 + ", palm position: " + hand.palmPosition());

                // Get the hand's normal vector and direction
                Vector normal = hand.palmNormal();
                Vector direction = hand.direction();

                // Calculate the hand's pitch, roll, and yaw angles
                System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
                                 + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
                                 + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");

                // Get arm bone
                Arm arm = hand.arm();
                System.out.println("  Arm direction: " + arm.direction()
                                 + ", wrist position: " + arm.wristPosition()
                                 + ", elbow position: " + arm.elbowPosition());

                // Get fingers
                for (Finger finger : hand.fingers()) {
                    System.out.println("    " + finger.type() + ", id: " + finger.id()
                                     + ", length: " + finger.length()
                                     + "mm, width: " + finger.width() + "mm");

                    //Get Bones
                    for(Bone.Type boneType : Bone.Type.values()) {
                        Bone bone = finger.bone(boneType);
                        System.out.println("      " + bone.type()
                                         + " bone, start: " + bone.prevJoint()
                                         + ", end: " + bone.nextJoint()
                                         + ", direction: " + bone.direction());
                    }
                }
            }

            // Get tools
            for(Tool tool : frame.tools()) {
                System.out.println("  Tool id: " + tool.id()
                                 + ", position: " + tool.tipPosition()
                                 + ", direction: " + tool.direction());
            }

            GestureList gestures = frame.gestures();
            for (int i = 0; i < gestures.count(); i++) {
                Gesture gesture = gestures.get(i);

                switch (gesture.type()) {
                    case TYPE_CIRCLE:
                        CircleGesture circle = new CircleGesture(gesture);

                        // Calculate clock direction using the angle between circle normal and pointable
                        String clockwiseness;
                        if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2) {
                            // Clockwise if angle is less than 90 degrees
                            clockwiseness = "clockwise";
                        } else {
                            clockwiseness = "counterclockwise";
                        }

                        // Calculate angle swept since last frame
                        double sweptAngle = 0;
                        if (circle.state() != Gesture.State.STATE_START) {
                            CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
                            sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
                        }

                        System.out.println("  Circle id: " + circle.id()
                                   + ", " + circle.state()
                                   + ", progress: " + circle.progress()
                                   + ", radius: " + circle.radius()
                                   + ", angle: " + Math.toDegrees(sweptAngle)
                                   + ", " + clockwiseness);
                        break;
                    case TYPE_SWIPE:
                        SwipeGesture swipe = new SwipeGesture(gesture);
                        System.out.println("  Swipe id: " + swipe.id()
                                   + ", " + swipe.state()
                                   + ", position: " + swipe.position()
                                   + ", direction: " + swipe.direction()
                                   + ", speed: " + swipe.speed());
                        break;
                    case TYPE_SCREEN_TAP:
                        ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                        System.out.println("  Screen Tap id: " + screenTap.id()
                                   + ", " + screenTap.state()
                                   + ", position: " + screenTap.position()
                                   + ", direction: " + screenTap.direction());
                        break;
                    case TYPE_KEY_TAP:
                        KeyTapGesture keyTap = new KeyTapGesture(gesture);
                        System.out.println("  Key Tap id: " + keyTap.id()
                                   + ", " + keyTap.state()
                                   + ", position: " + keyTap.position()
                                   + ", direction: " + keyTap.direction());
                        break;
                    default:
                        System.out.println("Unknown gesture type.");
                        break;
                }
            }

            if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
                System.out.println();
            }
            */
            updateLabelLeapMotion(frame);
//            if(print==1)
//            {
//                System.out.println(frame.id()+" "+frame.hands().count());
//                System.out.println(data.printHand(frame.hands().get(0)));
//                print=0;
//            }
        }
        
        public void startPrint()
        {
            print=true;
            iFrame=0;
            prevTime=0;
            deltaTime=1000/Integer.parseInt(nFrame.getText());
            stopTime=0;
//            deltaTime=Math.floorDiv(1000, Integer.parseInt(nFrame.getText()));
//            deltaTime*=1000;
            System.out.println(deltaTime);
            System.out.println("start");
            notifLabel2.setText("Processing");
        }
        
        public void updateLabelLeapMotion(Frame frame){
            for(Hand hand : frame.hands()) {
                if(hand.isLeft())
                {
                    dataLeftWristLabel.setText(String.valueOf(hand.wristPosition()));
                    dataLeftPalmLabel.setText(String.valueOf(hand.palmPosition()));
                    dataThumbMetaFingerLabel.setText(String.valueOf(hand.fingers().get(0).bone(Bone.Type.TYPE_METACARPAL).direction()));
                    dataThumbProxiFingerLabel.setText(String.valueOf(hand.fingers().get(0).bone(Bone.Type.TYPE_PROXIMAL).direction()));
                    dataThumbInterFingerLabel.setText(String.valueOf(hand.fingers().get(0).bone(Bone.Type.TYPE_INTERMEDIATE).direction()));
                    dataThumbDistalFingerLabel.setText(String.valueOf(hand.fingers().get(0).bone(Bone.Type.TYPE_DISTAL).direction()));
                    
//                    dataThumbMetaFingerLabel.setText(String.valueOf(hand.fingers().get(0).bone(Bone.Type.TYPE_DISTAL).nextJoint().getY()));
//                    dataThumbProxiFingerLabel.setText(String.valueOf(hand.fingers().get(1).bone(Bone.Type.TYPE_DISTAL).nextJoint().getY()));
//                    dataThumbInterFingerLabel.setText(String.valueOf(hand.fingers().get(2).bone(Bone.Type.TYPE_DISTAL).nextJoint().getY()));
//                    dataThumbDistalFingerLabel.setText(String.valueOf(hand.fingers().get(3).bone(Bone.Type.TYPE_DISTAL).nextJoint().getY()));
                    
                    Vector wristpalmdist=hand.palmPosition().minus(hand.wristPosition());
                    dataWristPalmRollLabel.setText(String.valueOf(wristpalmdist.roll()));
                    dataWristPalmPitchLabel.setText(String.valueOf(wristpalmdist.pitch()));
                    dataWristPalmYawLabel.setText(String.valueOf(wristpalmdist.yaw()));
                    TaitBrianRotation(frame);
                }
                else
                {
                    dataRightWristLabel.setText(String.valueOf(hand.wristPosition()));
                    dataRightPalmLabel.setText(String.valueOf(hand.palmPosition()));
                    dataRightFingerLabel.setText(String.valueOf(hand.fingers().count()));
                }
            }
        }
        
        public void updateTab4()
        {
            // add count by 1
            countText.setText(String.valueOf(Integer.parseInt(countText.getText())+1));
            
            // update result
            AngleData angleResult=new AngleData();
            angleResult.create(controller.frame().hands().get(0));
            resultArea4.append(countText.getText());
            resultArea4.append("\t");
            resultArea4.append(angleResult.print());
            resultArea4.append("\t");
            resultArea4.append(gestureText.getText());
            resultArea4.append("\n");
            
            // update raw
            rawArea4.append(countText.getText());
            rawArea4.append("\t");
            rawArea4.append(LeapData.printHand(controller.frame().hands().get(0)));
            rawArea4.append("\t");
            rawArea4.append(gestureText.getText());
            rawArea4.append("\n");
        }
    }
    
    public void TaitBrianRotation(Frame frame){
        float sinR,cosR,sinP,cosP,sinY,cosY;
        DoubleMatrix pitchEle=new DoubleMatrix(3,3);
        DoubleMatrix rollEle=new DoubleMatrix(3,3);
        DoubleMatrix yawEle=new DoubleMatrix(3,3);
        
        Vector wristmid;
        
        Vector indexFinger=new Vector();
        Vector middleFinger=new Vector();
        // get finger
        for (Finger finger : frame.hands().get(0).fingers()) {
            if(finger.type()==Finger.Type.TYPE_INDEX)
            {
                indexFinger=finger.bone(Bone.Type.TYPE_METACARPAL).prevJoint();
            }
            else if(finger.type()==Finger.Type.TYPE_MIDDLE)
            {
                middleFinger=finger.bone(Bone.Type.TYPE_METACARPAL).prevJoint();
            }
        }
        
        wristmid = middleFinger.minus(frame.hand(0).wristPosition());
        
        
        //paper
//        DoubleMatrix rollEle=new DoubleMatrix(3,3);
//        rollEle.put(0,0,1).put(0,1,0).put(0,2,0);
//        rollEle.put(1,0,0).put(1,1,cosR).put(1,2,-sinR);
//        rollEle.put(2,0,0).put(2,1,sinR).put(2,2,cosR);
//        
//        DoubleMatrix pitchEle=new DoubleMatrix(3,3);
//        pitchEle.put(0,0,cosP).put(0,1,0).put(0,2,sinP);
//        pitchEle.put(1,0,0).put(1,1,1).put(1,2,0);
//        pitchEle.put(2,0,-sinP).put(2,1,0).put(2,2,cosP);
//        
//        DoubleMatrix yawEle=new DoubleMatrix(3,3);
//        yawEle.put(0,0,cosY).put(0,1,-sinY).put(0,2,0);
//        yawEle.put(1,0,sinY).put(1,1,cosY).put(1,2,0);
//        yawEle.put(2,0,0).put(2,1,0).put(2,2,1);
        
        // leap motion
        //x-axis
//        pitchEle.put(0,0,1).put(0,1,0).put(0,2,0);
//        pitchEle.put(1,0,0).put(1,1,cosP).put(1,2,-sinP);
//        pitchEle.put(2,0,0).put(2,1,sinP).put(2,2,cosP);
        
        //z-axis
        sinR=(float) Math.sin(-wristmid.roll());
        cosR=(float) Math.cos(-wristmid.roll());
        // set roll matrix
        rollEle.put(0,0,cosR).put(0,1,-sinR).put(0,2,0);
        rollEle.put(1,0,sinR).put(1,1,cosR).put(1,2,0);
        rollEle.put(2,0,0).put(2,1,0).put(2,2,1);
        
        //y-axis
//        yawEle.put(0,0,cosY).put(0,1,0).put(0,2,sinY);
//        yawEle.put(1,0,0).put(1,1,1).put(1,2,0);
//        yawEle.put(2,0,-sinY).put(2,1,0).put(2,2,cosY);
        
        DoubleMatrix result;
        DoubleMatrix point=new DoubleMatrix(3);
        
        point.put(0, wristmid.getX());
        point.put(1, wristmid.getY());
        point.put(2, wristmid.getZ());
//        System.out.print(wristmid.toString());
        
        // rotate using roll
        result=rollEle.mmul(point);
//        System.out.print(" 1st "+result.toString());
        
        Vector intermediateVector=new Vector((float)result.get(0), (float)result.get(1), (float)result.get(2));
//        sinP=(float) Math.sin(Math.PI/2-init2.pitch());
//        cosP=(float) Math.cos(Math.PI/2-init2.pitch());
        sinP=(float) Math.sin(Math.PI/2-intermediateVector.pitch());
        cosP=(float) Math.cos(Math.PI/2-intermediateVector.pitch());
        
        // set pitch matrix
        pitchEle.put(0,0,1).put(0,1,0).put(0,2,0);
        pitchEle.put(1,0,0).put(1,1,cosP).put(1,2,-sinP);
        pitchEle.put(2,0,0).put(2,1,sinP).put(2,2,cosP);
        // rotate using pitch
        result=pitchEle.mmul(result);
//        System.out.println(" 2nd "+result.toString());
//        DoubleMatrix result2=pitchEle.mmul(result);
        
//        point.put(0, wristmid.getX());
//        point.put(1, wristmid.getY());
//        point.put(2, wristmid.getZ());
//        result=pitchEle.mmul(rollEle.mmul(point));
//        System.out.print(" 3rd "+result.toString());
        
        //testing for index and ring
        // why x always -
        DoubleMatrix index=new DoubleMatrix(3);
        DoubleMatrix middle=new DoubleMatrix(3);
        
//        System.out.print(indexFinger.toString()+" - "+middleFinger.toString()+" to ");
        point=new DoubleMatrix(3);
        point.put(0, indexFinger.getX()-frame.hand(0).wristPosition().getX());
        point.put(1, indexFinger.getY()-frame.hand(0).wristPosition().getY());
        point.put(2, indexFinger.getZ()-frame.hand(0).wristPosition().getZ());
        index=pitchEle.mmul(rollEle.mmul(point));
        
        point=new DoubleMatrix(3);
        point.put(0, middleFinger.getX()-frame.hand(0).wristPosition().getX());
        point.put(1, middleFinger.getY()-frame.hand(0).wristPosition().getY());
        point.put(2, middleFinger.getZ()-frame.hand(0).wristPosition().getZ());
        middle=pitchEle.mmul(rollEle.mmul(point));
        
//        Vector handOrientation=new Vector((float)(index.get(0)-middle.get(0)), 
//                (float)(index.get(1)-middle.get(1)), (float)(index.get(2)-middle.get(2)));
        Vector handOrientation = indexFinger.minus(middleFinger);
        
        sinY=(float) Math.sin(handOrientation.yaw());
        cosY=(float) Math.cos(handOrientation.yaw());
        // set yaw matrix
        yawEle.put(0,0,cosY).put(0,1,0).put(0,2,sinY);
        yawEle.put(1,0,0).put(1,1,1).put(1,2,0);
        yawEle.put(2,0,-sinY).put(2,1,0).put(2,2,cosY);
        // rotate using yaw
        result=yawEle.mmul(result); 
//        System.out.println(" 3rd "+result.toString());
        
        point=new DoubleMatrix(3);
        point.put(0, handOrientation.getX());
        point.put(1, handOrientation.getY());
        point.put(2, handOrientation.getZ());
        result=yawEle.mmul(point);
//        System.out.println(" 3rd "+point.toString()+" - "+result.toString());
        
        
        
        result=yawEle.mmul(pitchEle.mmul(rollEle.mmul(index)));
//        System.out.print("index "+result.toString());
        
        result=yawEle.mmul(pitchEle.mmul(rollEle.mmul(middle)));
//        System.out.println(" middle "+result.toString());
        
        
        
        /*
        // preparation for face-up and face-down hand
        sinY=(float) Math.cos(Math.PI);
        cosY=(float) Math.cos(Math.PI);
        yawEle.put(0,0,cosY).put(0,1,0).put(0,2,sinY);
        yawEle.put(1,0,0).put(1,1,1).put(1,2,0);
        yawEle.put(2,0,-sinY).put(2,1,0).put(2,2,cosY);
        // rotate using pitch
        result=yawEle.mmul(result);
        
        */
        
//        System.out.println(init2.toString());
//        System.out.println(wristpalm.magnitude()+" point1 "+point.toString()+" "+result.toString()+" "+result2.toString());
        
        //roll pi-roll
//        System.out.println(wristpalm.angleTo(new Vector(0, wristpalm.getY(), wristpalm.getZ()))
        //pitch
//        System.out.println(wristpalm.angleTo(new Vector(wristpalm.getX(), wristpalm.getY(), 0))
//                +" "+wristpalm.pitch()+" "+wristpalm.roll()+" "+wristpalm.yaw()+" "+point.toString());
        
//        return frame;
    }
    
    /**
     * Creates new form motiondetection
     */
    public motiondetection() {
        initComponents();
        this.setVisible(true);
    }
    
    /*
    private void updateChart() {
    XYDataset dataset = createXYdataset();
    JFreeChart chart = createChart(dataset);
    JPanel chartPanel = new ChartPanel(chart);
    chartPanel.setSize(jPanelGraph.getSize());
    jPanelGraph.add(chartPanel);
    jPanelGraph.getParent().validate();
    }
    private XYDataset createXYdataset() {
        XYSeries series = new XYSeries("");
        series.add(1, 1);
        series.add(2, 0);
        series.add(3, 1);
        series.add(7, 0);
//        int rows = jTable.getRowCount();
//        if (rows > 0) {
//            int ms = 0;
//            for (int row = 0; row < rows; row++) {
//                series.add(ms, 1);
//                ms += Integer.parseInt(
//                        jTable.getValueAt(row, PULSE_ON).toString());
//                series.add(ms, 1);
//                series.add(ms, 0);
//                ms += Integer.parseInt(
//                        jTable.getValueAt(row, PULSE_OFF).toString());
//                series.add(ms, 0);
//            }
//        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }
    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            null,      // chart title
            "ms",                      // x axis label
            null,                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setRangeGridlinesVisible(false);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        text1 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dataLeftWristLabel = new javax.swing.JLabel();
        dataLeftPalmLabel = new javax.swing.JLabel();
        dataThumbMetaFingerLabel = new javax.swing.JLabel();
        dataThumbProxiFingerLabel = new javax.swing.JLabel();
        dataThumbInterFingerLabel = new javax.swing.JLabel();
        dataThumbDistalFingerLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dataWristPalmRollLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dataWristPalmPitchLabel = new javax.swing.JLabel();
        dataWristPalmYawLabel = new javax.swing.JLabel();
        Save = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dataRightFingerLabel = new javax.swing.JLabel();
        dataRightPalmLabel = new javax.swing.JLabel();
        dataRightWristLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        buttonTake2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        nFrame = new javax.swing.JTextField();
        notifLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArea2 = new javax.swing.JTextArea();
        clearButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        StartToggleButton3 = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultArea3 = new javax.swing.JTextArea();
        clearButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        maxSpeed3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanelGraph = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultArea4 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        resetButton4 = new javax.swing.JButton();
        takeButton4 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        rawArea4 = new javax.swing.JTextArea();
        gestureText = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        decreaseButton = new javax.swing.JButton();
        increaseButton = new javax.swing.JButton();
        saveButton4_1 = new javax.swing.JButton();
        saveButton4_2 = new javax.swing.JButton();
        increaseGesture = new javax.swing.JButton();
        decreaseGesture = new javax.swing.JButton();
        countText = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        rawArea5 = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        processButton5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        resultArea5 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        rawArea6 = new javax.swing.JTextArea();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        resultArea6 = new javax.swing.JTextArea();
        processButton6 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        rawArea7 = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        resultArea7 = new javax.swing.JTextArea();
        processButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Stop");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        text1.setText(" ");

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("MS UI Gothic", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Leap Motion Exploration");

        jLabel3.setText("By : d_frEak ");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Data from leap motion");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Wrist");

        jLabel5.setText("Palm");

        jLabel6.setText("Finger");

        jLabel7.setText("Left Hand");

        dataLeftWristLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataLeftPalmLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataThumbMetaFingerLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataThumbProxiFingerLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataThumbInterFingerLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataThumbDistalFingerLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Wrist-palm");

        dataWristPalmRollLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("roll");

        jLabel14.setText("pitch");

        jLabel15.setText("yaw");

        dataWristPalmPitchLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataWristPalmYawLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Save.setText("Save Current Info");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataThumbDistalFingerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataThumbInterFingerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataThumbProxiFingerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataThumbMetaFingerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                            .addComponent(dataLeftWristLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataLeftPalmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataWristPalmRollLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataWristPalmYawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataWristPalmPitchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Save)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dataThumbMetaFingerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(dataLeftWristLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(dataLeftPalmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataThumbProxiFingerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataThumbInterFingerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataThumbDistalFingerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(dataWristPalmRollLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addComponent(dataWristPalmPitchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(dataWristPalmYawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Save)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Wrist");

        jLabel9.setText("Palm");

        jLabel10.setText("Finger");

        jLabel11.setText("Right Hand");

        dataRightFingerLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataRightPalmLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dataRightWristLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataRightFingerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataRightWristLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataRightPalmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataRightFingerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(dataRightWristLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataRightPalmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setText("Speed data");

        buttonTake2.setText("Take some frame");
        buttonTake2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTake2ActionPerformed(evt);
            }
        });

        jLabel17.setText("N Frame per sec");

        jLabel18.setText("note: normally leap motion has 30 fps if idle and 110 fps if not idle");

        nFrame.setText("12");

        notifLabel2.setText("Finish");

        resultArea2.setColumns(20);
        resultArea2.setRows(5);
        jScrollPane1.setViewportView(resultArea2);

        clearButton2.setText("Clear");
        clearButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton2ActionPerformed(evt);
            }
        });

        jLabel19.setText("(not used)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(nFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(notifLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearButton2))
                            .addComponent(jLabel16)
                            .addComponent(buttonTake2))
                        .addGap(0, 236, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonTake2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(nFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(notifLabel2)
                    .addComponent(clearButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab2", jPanel4);

        jLabel20.setText("Speed data");

        StartToggleButton3.setText("Start");
        StartToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartToggleButton3ActionPerformed(evt);
            }
        });

        resultArea3.setColumns(20);
        resultArea3.setRows(5);
        resultArea3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(resultArea3);

        clearButton3.setText("Clear");
        clearButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton3ActionPerformed(evt);
            }
        });

        jLabel21.setText("Max speed");

        maxSpeed3.setText("0");

        jButton2.setText("update graph");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(126, 126, 126)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(maxSpeed3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 164, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(StartToggleButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearButton3)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(jScrollPane2)
                    .addGap(14, 14, 14)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(maxSpeed3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartToggleButton3)
                    .addComponent(clearButton3))
                .addContainerGap(382, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("tab3", jPanel5);

        resultArea4.setColumns(20);
        resultArea4.setRows(5);
        jScrollPane3.setViewportView(resultArea4);

        jLabel22.setText("Result");

        resetButton4.setText("Reset");
        resetButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButton4ActionPerformed(evt);
            }
        });

        takeButton4.setText("Take");
        takeButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeButton4ActionPerformed(evt);
            }
        });

        jLabel23.setText("Raw");

        rawArea4.setColumns(20);
        rawArea4.setRows(5);
        jScrollPane4.setViewportView(rawArea4);

        gestureText.setText("1");

        jLabel24.setText("Gesture");

        jLabel25.setText("Count");

        decreaseButton.setText("Decrease Count");
        decreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseButtonActionPerformed(evt);
            }
        });

        increaseButton.setText("Increase Count");
        increaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseButtonActionPerformed(evt);
            }
        });

        saveButton4_1.setText("Save Result");
        saveButton4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton4_1ActionPerformed(evt);
            }
        });

        saveButton4_2.setText("Save Raw");
        saveButton4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton4_2ActionPerformed(evt);
            }
        });

        increaseGesture.setText("Increase Gesture");
        increaseGesture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseGestureActionPerformed(evt);
            }
        });

        decreaseGesture.setText("Decrease Gesture");
        decreaseGesture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseGestureActionPerformed(evt);
            }
        });

        countText.setText("0");

        javax.swing.GroupLayout jPanelGraphLayout = new javax.swing.GroupLayout(jPanelGraph);
        jPanelGraph.setLayout(jPanelGraphLayout);
        jPanelGraphLayout.setHorizontalGroup(
            jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGraphLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanelGraphLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton4_2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelGraphLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(takeButton4)
                        .addGap(107, 107, 107)
                        .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(countText, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(gestureText))
                        .addGap(7, 7, 7)
                        .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(increaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(increaseGesture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(decreaseGesture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(decreaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveButton4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resetButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelGraphLayout.setVerticalGroup(
            jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGraphLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(resetButton4)
                    .addComponent(takeButton4)
                    .addComponent(jLabel25)
                    .addComponent(increaseButton)
                    .addComponent(decreaseButton)
                    .addComponent(countText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton4_1)
                    .addComponent(increaseGesture)
                    .addComponent(decreaseGesture)
                    .addComponent(gestureText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(saveButton4_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanelGraph);

        rawArea5.setColumns(20);
        rawArea5.setRows(5);
        jScrollPane5.setViewportView(rawArea5);

        jLabel26.setText("Raw");

        jLabel27.setText("Processed");

        processButton5.setText("Process");
        processButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButton5ActionPerformed(evt);
            }
        });

        resultArea5.setColumns(20);
        resultArea5.setRows(5);
        jScrollPane6.setViewportView(resultArea5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(40, 40, 40)
                                .addComponent(processButton5)))
                        .addGap(0, 508, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(processButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab5", jPanel6);

        jLabel28.setText("Rajesh Model");

        jLabel29.setText("Raw");

        rawArea6.setColumns(20);
        rawArea6.setRows(5);
        jScrollPane7.setViewportView(rawArea6);

        jLabel30.setText("Rajesh Model");

        resultArea6.setColumns(20);
        resultArea6.setRows(5);
        jScrollPane8.setViewportView(resultArea6);

        processButton6.setText("Process");
        processButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(processButton6)))
                        .addGap(0, 514, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30))
                    .addComponent(processButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab6", jPanel7);

        jLabel31.setText("Kaiyin Model");

        jLabel32.setText("Raw");

        rawArea7.setColumns(20);
        rawArea7.setRows(5);
        jScrollPane9.setViewportView(rawArea7);

        jLabel33.setText("Kaiyin Model");

        resultArea7.setColumns(20);
        resultArea7.setRows(5);
        jScrollPane10.setViewportView(resultArea7);

        processButton7.setText("Process");
        processButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(18, 18, 18)
                                .addComponent(processButton7)))
                        .addGap(0, 519, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33))
                    .addComponent(processButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab7", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startButton)
                                .addGap(18, 18, 18)
                                .addComponent(text1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text1)
                    .addComponent(startButton))
                .addGap(2, 2, 2)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        // Have the sample listener receive events from the controller
        controller.addListener(listener);
    }//GEN-LAST:event_startButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Remove the sample listener when done
        controller.removeListener(listener);    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
//        listener.print();
        System.out.println(LeapData.printHand(controller.frame().hands().get(0)));
    }//GEN-LAST:event_SaveActionPerformed

    private void buttonTake2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTake2ActionPerformed
        // TODO add your handling code here:
        listener.startPrint();
    }//GEN-LAST:event_buttonTake2ActionPerformed

    private void clearButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton2ActionPerformed
        // TODO add your handling code here:
        resultArea2.setText("");
    }//GEN-LAST:event_clearButton2ActionPerformed

    private void clearButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton3ActionPerformed
        // TODO add your handling code here:
        resultArea3.setText("");
        maxSpeed3.setText("0");
        Global.singleton().reset();
    }//GEN-LAST:event_clearButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        updateChart();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void resetButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButton4ActionPerformed
        // TODO add your handling code here:
        resultArea4.setText("");
        rawArea4.setText("");
        countText.setText("0");
    }//GEN-LAST:event_resetButton4ActionPerformed

    private void takeButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takeButton4ActionPerformed
        // TODO add your handling code here:
        listener.updateTab4();
    }//GEN-LAST:event_takeButton4ActionPerformed

    private void decreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseButtonActionPerformed
        // TODO add your handling code here:
        countText.setText(String.valueOf(Integer.parseInt(countText.getText())-1));
    }//GEN-LAST:event_decreaseButtonActionPerformed

    private void increaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseButtonActionPerformed
        // TODO add your handling code here:
        countText.setText(String.valueOf(Integer.parseInt(countText.getText())+1));
    }//GEN-LAST:event_increaseButtonActionPerformed

    private void saveButton4_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton4_1ActionPerformed
        // TODO add your handling code here:
        JFileChooser tChooser = new JFileChooser();
        FileNameExtensionFilter tFilter = new FileNameExtensionFilter(
                "CSV File (*.csv)", "csv", "CSV");
        tChooser.setFileFilter(tFilter);
        tChooser.setDialogTitle("Save result");
        int tOption = tChooser.showSaveDialog(this);
        if (tOption == JFileChooser.APPROVE_OPTION) {
            File tFile = tChooser.getSelectedFile();
            String tPathFile = tFile.getAbsolutePath();
            if (!tPathFile.endsWith(".csv")) {
                tPathFile += ".csv";
            }
            File tOutputFile = new File(tPathFile);
            helper.save(tOutputFile, resultArea4.getText());
            JOptionPane.showMessageDialog(this, "result saved to " + tOutputFile.getAbsolutePath(),"Save Success",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_saveButton4_1ActionPerformed

    private void saveButton4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton4_2ActionPerformed
        // TODO add your handling code here:
        JFileChooser tChooser = new JFileChooser();
        FileNameExtensionFilter tFilter = new FileNameExtensionFilter(
                "CSV File (*.csv)", "csv", "CSV");
        tChooser.setFileFilter(tFilter);
        tChooser.setDialogTitle("Save raw");
        int tOption = tChooser.showSaveDialog(this);
        if (tOption == JFileChooser.APPROVE_OPTION) {
            File tFile = tChooser.getSelectedFile();
            String tPathFile = tFile.getAbsolutePath();
            if (!tPathFile.endsWith(".csv")) {
                tPathFile += ".csv";
            }
            File tOutputFile = new File(tPathFile);
            helper.save(tOutputFile, rawArea4.getText());
            JOptionPane.showMessageDialog(this, "raw saved to " + tOutputFile.getAbsolutePath(),"Save Success",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_saveButton4_2ActionPerformed

    private void increaseGestureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseGestureActionPerformed
        // TODO add your handling code here:
        gestureText.setText(String.valueOf(Integer.parseInt(gestureText.getText())+1));
    }//GEN-LAST:event_increaseGestureActionPerformed

    private void decreaseGestureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseGestureActionPerformed
        // TODO add your handling code here:
        gestureText.setText(String.valueOf(Integer.parseInt(gestureText.getText())-1));
    }//GEN-LAST:event_decreaseGestureActionPerformed

    private void processButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButton5ActionPerformed
        // TODO add your handling code here:
        resultArea5.setText("");
        
        String[] strPerData=rawArea5.getText().split("\n");
        for(int i=0;i<strPerData.length;i++)
        {
            resultArea5.append(LeapHand.print(strPerData[i]));
            resultArea5.append("\n");
        }
    }//GEN-LAST:event_processButton5ActionPerformed

    private void processButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButton6ActionPerformed
        // TODO add your handling code here:
        resultArea6.setText("");
        
        String[] strPerData=rawArea6.getText().split("\n");
        for(int i=0;i<strPerData.length;i++)
        {
            resultArea6.append(RajeshModel.print(strPerData[i]));
            resultArea6.append("\n");
        }
    }//GEN-LAST:event_processButton6ActionPerformed

    private void processButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButton7ActionPerformed
        // TODO add your handling code here:
        resultArea7.setText("");
        
        String[] strPerData=rawArea7.getText().split("\n");
        for(int i=0;i<strPerData.length;i++)
        {
            resultArea7.append(KaiyinModel.print(strPerData[i]));
            resultArea7.append("\n");
        }
    }//GEN-LAST:event_processButton7ActionPerformed

    private void StartToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartToggleButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StartToggleButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(motiondetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(motiondetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(motiondetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(motiondetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new motiondetection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Save;
    private javax.swing.JToggleButton StartToggleButton3;
    private javax.swing.JButton buttonTake2;
    private javax.swing.JButton clearButton2;
    private javax.swing.JButton clearButton3;
    private javax.swing.JTextField countText;
    private javax.swing.JLabel dataLeftPalmLabel;
    private javax.swing.JLabel dataLeftWristLabel;
    private javax.swing.JLabel dataRightFingerLabel;
    private javax.swing.JLabel dataRightPalmLabel;
    private javax.swing.JLabel dataRightWristLabel;
    private javax.swing.JLabel dataThumbDistalFingerLabel;
    private javax.swing.JLabel dataThumbInterFingerLabel;
    private javax.swing.JLabel dataThumbMetaFingerLabel;
    private javax.swing.JLabel dataThumbProxiFingerLabel;
    private javax.swing.JLabel dataWristPalmPitchLabel;
    private javax.swing.JLabel dataWristPalmRollLabel;
    private javax.swing.JLabel dataWristPalmYawLabel;
    private javax.swing.JButton decreaseButton;
    private javax.swing.JButton decreaseGesture;
    private javax.swing.JTextField gestureText;
    private javax.swing.JButton increaseButton;
    private javax.swing.JButton increaseGesture;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelGraph;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel maxSpeed3;
    private javax.swing.JTextField nFrame;
    private javax.swing.JLabel notifLabel2;
    private javax.swing.JButton processButton5;
    private javax.swing.JButton processButton6;
    private javax.swing.JButton processButton7;
    private javax.swing.JTextArea rawArea4;
    private javax.swing.JTextArea rawArea5;
    private javax.swing.JTextArea rawArea6;
    private javax.swing.JTextArea rawArea7;
    private javax.swing.JButton resetButton4;
    private javax.swing.JTextArea resultArea2;
    private javax.swing.JTextArea resultArea3;
    private javax.swing.JTextArea resultArea4;
    private javax.swing.JTextArea resultArea5;
    private javax.swing.JTextArea resultArea6;
    private javax.swing.JTextArea resultArea7;
    private javax.swing.JButton saveButton4_1;
    private javax.swing.JButton saveButton4_2;
    private javax.swing.JButton startButton;
    private javax.swing.JButton takeButton4;
    private javax.swing.JLabel text1;
    // End of variables declaration//GEN-END:variables
}
