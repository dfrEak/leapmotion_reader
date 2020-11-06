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
public class demo extends javax.swing.JFrame {
    
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
            deltaTime++;
            if(deltaTime>=Integer.parseInt(frameRate.getText()))
            {
                deltaTime=0;
                updateLabelLeapMotion(frame);
            }
//            if(print==1)
//            {
//                System.out.println(frame.id()+" "+frame.hands().count());
//                System.out.println(data.printHand(frame.hands().get(0)));
//                print=0;
//            }
        }
        
        
        public void updateLabelLeapMotion(Frame frame){
            AngleData result;
            for(Hand hand : frame.hands()) {
                result=LeapHand.preProcess(hand);
                thumbA.setText(String.valueOf(result.getThumbAngleA()));
                thumbB.setText(String.valueOf(result.getThumbAngleB()));
                thumbC.setText(String.valueOf(result.getThumbAngleC()));
                
                indexA.setText(String.valueOf(result.getIndexAngleA()));
                indexB.setText(String.valueOf(result.getIndexAngleB()));
                indexC.setText(String.valueOf(result.getIndexAngleC()));
                
                middleA.setText(String.valueOf(result.getMiddleAngleA()));
                middleB.setText(String.valueOf(result.getMiddleAngleB()));
                middleC.setText(String.valueOf(result.getMiddleAngleC()));
                
                ringA.setText(String.valueOf(result.getRingAngleA()));
                ringB.setText(String.valueOf(result.getRingAngleB()));
                ringC.setText(String.valueOf(result.getRingAngleC()));
                
                pinkyA.setText(String.valueOf(result.getPinkyAngleA()));
                pinkyB.setText(String.valueOf(result.getPinkyAngleB()));
                pinkyC.setText(String.valueOf(result.getPinkyAngleC()));
                
                spaceA.setText(String.valueOf(result.getSpaceAngleA()));
                spaceB.setText(String.valueOf(result.getSpaceAngleB()));
                spaceC.setText(String.valueOf(result.getSpaceAngleC()));
                spaceD.setText(String.valueOf(result.getSpaceAngleD()));
            }
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
    public demo() {
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
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        thumbA = new javax.swing.JLabel();
        thumbB = new javax.swing.JLabel();
        thumbC = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        indexA = new javax.swing.JLabel();
        indexB = new javax.swing.JLabel();
        indexC = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ringA = new javax.swing.JLabel();
        ringB = new javax.swing.JLabel();
        ringC = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pinkyA = new javax.swing.JLabel();
        pinkyB = new javax.swing.JLabel();
        pinkyC = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        middleA = new javax.swing.JLabel();
        middleB = new javax.swing.JLabel();
        middleC = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        spaceA = new javax.swing.JLabel();
        spaceB = new javax.swing.JLabel();
        spaceC = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        spaceD = new javax.swing.JLabel();
        frameRate = new javax.swing.JTextField();

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

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Thumb");

        jLabel4.setText("ThumbAngleA");

        jLabel5.setText("ThumbAngleB");

        jLabel6.setText("ThumbAngleC");

        thumbA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        thumbB.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        thumbC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(thumbA, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(thumbB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(thumbC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(thumbA, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(thumbB, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(thumbC, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Index");

        jLabel9.setText("IndexAngleA");

        jLabel10.setText("IndexAngleB");

        jLabel11.setText("IndexAngleC");

        indexA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        indexB.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        indexC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(indexA, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(indexB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(indexC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(indexA, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addComponent(indexB, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(indexC, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("Ring");

        jLabel13.setText("RingAngleA");

        jLabel14.setText("RingAngleB");

        jLabel15.setText("RingAngleC");

        ringA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ringB.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ringC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ringA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ringB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ringC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(ringA, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addComponent(ringB, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(ringC, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setText("Pinky");

        jLabel17.setText("PinkyAngleA");

        jLabel18.setText("PinkyAngleB");

        jLabel19.setText("PnkyAngleC");

        pinkyA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pinkyB.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pinkyC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pinkyA, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(11, 11, 11)
                        .addComponent(pinkyB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(12, 12, 12)
                        .addComponent(pinkyC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(pinkyA, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addComponent(pinkyB, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(pinkyC, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setText("Middle");

        jLabel21.setText("MiddleAngleA");

        jLabel22.setText("MiddleAngleB");

        jLabel23.setText("MiddleAngleC");

        middleA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        middleB.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        middleC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(middleA, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(middleB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(middleC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(middleA, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22))
                    .addComponent(middleB, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(middleC, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel24.setText("Space");

        jLabel25.setText("SpaceAngleA");

        jLabel26.setText("SpaceAngleB");

        jLabel27.setText("SpaceAngleC");

        spaceA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        spaceB.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        spaceC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setText("SpaceAngleD");

        spaceD.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spaceA, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spaceB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spaceC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spaceD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25)
                                    .addComponent(spaceA, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26))
                            .addComponent(spaceB, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(spaceC, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28))
                    .addComponent(spaceD, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        frameRate.setText("1");
        frameRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frameRateActionPerformed(evt);
            }
        });

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(frameRate, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(startButton)
                    .addComponent(frameRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
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

    private void frameRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frameRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frameRateActionPerformed

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
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new demo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField frameRate;
    private javax.swing.JLabel indexA;
    private javax.swing.JLabel indexB;
    private javax.swing.JLabel indexC;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel middleA;
    private javax.swing.JLabel middleB;
    private javax.swing.JLabel middleC;
    private javax.swing.JLabel pinkyA;
    private javax.swing.JLabel pinkyB;
    private javax.swing.JLabel pinkyC;
    private javax.swing.JLabel ringA;
    private javax.swing.JLabel ringB;
    private javax.swing.JLabel ringC;
    private javax.swing.JLabel spaceA;
    private javax.swing.JLabel spaceB;
    private javax.swing.JLabel spaceC;
    private javax.swing.JLabel spaceD;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel thumbA;
    private javax.swing.JLabel thumbB;
    private javax.swing.JLabel thumbC;
    // End of variables declaration//GEN-END:variables
}
