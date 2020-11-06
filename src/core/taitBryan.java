/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.leapmotion.leap.Vector;
import domain.LeapData;
import org.jblas.DoubleMatrix;

/**
 *
 * @author Eric
 */
public class taitBryan {
    public static LeapData calculate(LeapData data)
    {
        float sinR,cosR,sinP,cosP,sinY,cosY;
        DoubleMatrix pitchEle=new DoubleMatrix(3,3);
        DoubleMatrix rollEle=new DoubleMatrix(3,3);
        DoubleMatrix yawEle=new DoubleMatrix(3,3);
        
        Vector wrist=new Vector();
        wrist.setX((float) data.getWrist().get(0));
        wrist.setY((float) data.getWrist().get(1));
        wrist.setZ((float) data.getWrist().get(2));
        
        Vector indexFinger=new Vector();
        indexFinger.setX((float) data.getIndexMetacarpal().get(0));
        indexFinger.setY((float) data.getIndexMetacarpal().get(1));
        indexFinger.setZ((float) data.getIndexMetacarpal().get(2));
        
        Vector middleFinger=new Vector();
        middleFinger.setX((float) data.getMiddleMetacarpal().get(0));
        middleFinger.setY((float) data.getMiddleMetacarpal().get(1));
        middleFinger.setZ((float) data.getMiddleMetacarpal().get(2));
        
        Vector wristmid=middleFinger.minus(wrist);
        
        // z-axis
        sinR=(float) Math.sin(-wristmid.roll());
        cosR=(float) Math.cos(-wristmid.roll());
        // set roll matrix
        rollEle.put(0,0,cosR).put(0,1,-sinR).put(0,2,0);
        rollEle.put(1,0,sinR).put(1,1,cosR).put(1,2,0);
        rollEle.put(2,0,0).put(2,1,0).put(2,2,1);
        
        DoubleMatrix result;
        DoubleMatrix point=new DoubleMatrix(3);
        
        point.put(0, wristmid.getX());
        point.put(1, wristmid.getY());
        point.put(2, wristmid.getZ());
        
        // rotate using roll
        result=rollEle.mmul(point);
        
        
        Vector intermediateVector=new Vector((float)result.get(0), (float)result.get(1), (float)result.get(2));

        // x-axis
        sinP=(float) Math.sin(Math.PI/2-intermediateVector.pitch());
        cosP=(float) Math.cos(Math.PI/2-intermediateVector.pitch());
        // set pitch matrix
        pitchEle.put(0,0,1).put(0,1,0).put(0,2,0);
        pitchEle.put(1,0,0).put(1,1,cosP).put(1,2,-sinP);
        pitchEle.put(2,0,0).put(2,1,sinP).put(2,2,cosP);
        
        // rotate using pitch
        result=pitchEle.mmul(result);

        
        Vector handOrientation = indexFinger.minus(middleFinger);
        
        // for yaw left rotation is +
        sinY=(float) Math.sin(handOrientation.yaw());
        cosY=(float) Math.cos(handOrientation.yaw());
        // set yaw matrix
        yawEle.put(0,0,cosY).put(0,1,0).put(0,2,sinY);
        yawEle.put(1,0,0).put(1,1,1).put(1,2,0);
        yawEle.put(2,0,-sinY).put(2,1,0).put(2,2,cosY);
        // rotate using yaw
        result=yawEle.mmul(result); 
        
//        point=new DoubleMatrix(3);
//        point.put(0, handOrientation.getX());
//        point.put(1, handOrientation.getY());
//        point.put(2, handOrientation.getZ());
//        result=yawEle.mmul(point);
//        System.out.println(result.toString());
        
        System.out.println(result.toString());
        
        // same result 1
//        result=yawEle.mmul(pitchEle.mmul(rollEle.mmul(data.getMiddleEnd().sub(data.getWrist()))));
//        System.out.println(data.getMiddleEnd());
//        System.out.println(data.getMiddleEnd().sub(data.getWrist()));
//        System.out.println(result.toString());
        
        // same result 2
//        DoubleMatrix allEle=yawEle.mmul(pitchEle.mmul(rollEle));
//        result=allEle.mmul(data.getMiddleEnd().sub(data.getWrist()));
//        System.out.println(data.getMiddleEnd());
//        System.out.println(data.getMiddleEnd().sub(data.getWrist()));
//        System.out.println(result.toString());
        
//        DoubleMatrix tes = new DoubleMatrix(3,5);
//        tes.putColumn(0, data.getMiddleMetacarpal().sub(data.getWrist()));
//        tes.putColumn(1, data.getMiddleIntermediate().sub(data.getWrist()));
//        tes.putColumn(2, data.getMiddleProximal().sub(data.getWrist()));
//        tes.putColumn(3, data.getMiddleDistal().sub(data.getWrist()));
//        tes.putColumn(4, data.getMiddleEnd().sub(data.getWrist()));
//        result=yawEle.mmul(pitchEle.mmul(rollEle.mmul(tes)));
//        System.out.println(tes.toString());
//        System.out.println(result.toString());
//        System.out.println(result.getColumn(4).toString());
        
        result=yawEle.mmul(pitchEle.mmul(rollEle.mmul(data.getMatrix())));
//        System.out.println(data.getMatrix().toString());
//        System.out.println("--------------------");
//        System.out.println(result.toString());
//        System.out.println("--------------------");
//        System.out.println(result.getColumn(18).toString());
        data.setMatrix(data.getHandType(), result);
        
        return data;
    }
}
