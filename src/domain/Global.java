/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.jblas.DoubleMatrix;

/**
 *
 * @author Eric
 */
public class Global {
    private static Global global=null;
    
    public static Global singleton()
    {
        if(global==null)
            global=new Global();
        return global;
    }
    
    private DoubleMatrix indexEnd=null;
    private long timestamp=0;

    public DoubleMatrix getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(DoubleMatrix indexEnd) {
        this.indexEnd = indexEnd;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public void reset()
    {
        indexEnd=null;
        timestamp=0;
    }
}
