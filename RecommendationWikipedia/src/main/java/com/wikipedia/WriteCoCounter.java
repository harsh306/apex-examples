package com.wikipedia;

import com.datatorrent.api.Context;
import com.datatorrent.lib.io.fs.AbstractFileOutputOperator;

/**
 * Created by harsh on 8/7/16.
 */
public class WriteCoCounter extends AbstractFileOutputOperator<String> {

    int opId;

    @Override
    public void setup(Context.OperatorContext context) {
        super.setup(context);
        opId =context.getId();
    }

    public WriteCoCounter(){
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
