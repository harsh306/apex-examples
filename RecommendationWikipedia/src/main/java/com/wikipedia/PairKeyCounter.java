package com.wikipedia;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.lib.util.BaseUniqueKeyCounter;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import org.apache.commons.lang.mutable.MutableInt;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by harsh on 1/7/16.
 */

public class PairKeyCounter extends BaseUniqueKeyCounter<Long> implements LoggerFactory {
     private boolean change=false;
    int oId;

    @Override
    public void setup(Context.OperatorContext context) {
        super.setup(context);
        oId=context.getId();

    }

    public PairKeyCounter(){

    }
    public final transient DefaultInputPort<Long> inputPort= new DefaultInputPort<Long>() {
        @Override
        public void process(Long tuple) {
                PairKeyCounter.this.processTuple(tuple);
        }
    };
    public final transient DefaultOutputPort<MyEntry> outputPort= new DefaultOutputPort<>();


    @Override
    public void processTuple(Long tuple) {

        change = true;
        super.processTuple(tuple);


    }

    @Override
    public void beginWindow(long windowId){
        super.beginWindow(windowId);
        change = false;
    }

    @Override
    public void endWindow(){


        if(change) {
             super.endWindow();
            Iterator<Map.Entry<Long, MutableInt>> ite = map.entrySet().iterator();
            MyEntry mye = new MyEntry();
            while (ite.hasNext()) {
                Map.Entry<Long,MutableInt> k= ite.next();//1:1,3
                mye.setItemPair(k.getKey());
                mye.setCoCount(k.getValue());
                outputPort.emit(mye);
            }
        }
        map.clear();
    }
    @Override
    public Logger makeNewLoggerInstance(String s) {
        Logger log= Logger.getLogger(PairKeyCounter.class);
        log.info(s);
        return log;
    }
}
