package com.wikipedia;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class BuildRecommendation extends BaseOperator {
    Vector R;
    List<HashMap<Integer,Vector>> userMaps;
    Integer cnt;
    long windowId;


    @Override
    public void beginWindow(long windowId) {
        this.windowId = windowId;
    }

    public transient  final DefaultOutputPort<String> Rout = new DefaultOutputPort<>();
    int operatorId;
    @Override
    public void setup(Context.OperatorContext context) {
        R= new RandomAccessSparseVector(Integer.MAX_VALUE,100);
        userMaps =new ArrayList<>();
        cnt=new Integer(0);
        super.setup(context);
        operatorId=context.getId();
    }

    public transient final DefaultInputPort<HashMap<Integer,Vector>> userVector = new DefaultInputPort<HashMap<Integer, Vector>>() {
        @Override
        public void process(HashMap<Integer, Vector> tuple) {
            userMaps.add(tuple);

        }
    };

    public transient final DefaultInputPort<MyEntry> xyInput = new DefaultInputPort<MyEntry>() {
        @Override
        public void process(MyEntry tuple) {

            Iterator<HashMap<Integer,Vector>> userIterator=userMaps.iterator();
            while(userIterator.hasNext()) {
                HashMap<Integer, Vector> userMap = userIterator.next();

                Iterator<Integer> userIds = userMap.keySet().iterator();
                while(userIds.hasNext()){
                    Integer uid=userIds.next();
                    StringBuilder userItem = new StringBuilder();
                    userItem.append(""+uid);
                    Vector itemPref=userMap.get(uid);
                    Double answer=0.0;
                    int x = (int)(tuple.getItemPair().longValue() >> 32);
                    int y = (int)tuple.getItemPair().longValue();
                    userItem.append(" :: "+x+":"+y +" ");
                    double pref = itemPref.get(y);
                    answer = pref * tuple.getCoCount().doubleValue();
                    userItem.append(answer.toString()+"\n");
                    Rout.emit(userItem.toString());

                }
            }


        }


    };

    @Override
    public void endWindow() {

    }

}
