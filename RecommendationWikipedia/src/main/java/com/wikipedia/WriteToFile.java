package com.wikipedia;

import com.datatorrent.api.Context;
import com.datatorrent.lib.io.fs.AbstractFileOutputOperator;
import org.apache.mahout.math.Vector;

import java.util.HashMap;

/**
 * Created by anurag on 28/6/16.
 */
public class WriteToFile extends AbstractFileOutputOperator<String> {

    int opId;

    @Override
    public void setup(Context.OperatorContext context) {
        super.setup(context);
        opId =context.getId();
    }

    public WriteToFile(){
        filePath="output/";
    }
    @Override
    protected String getFileName(String integerVectorHashMap) {
        return "Recommendation"+opId+".txt" ;
    }

    @Override
    protected byte[] getBytesForTuple(String integerVectorHashMap) {
        return integerVectorHashMap.toString().getBytes();
    }

    @Override
    protected void processTuple(String tuple) {
        super.processTuple(tuple);
    }
}
