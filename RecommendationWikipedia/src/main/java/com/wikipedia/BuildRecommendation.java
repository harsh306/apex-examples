package com.wikipedia;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;
import com.sun.tools.javac.util.Assert;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class BuildRecommendation extends BaseOperator implements LoggerFactory {
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
                    //String x[]= tuple.getItemPair().split(":");
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

    @Override
    public Logger makeNewLoggerInstance(String s) {
        Logger log =Logger.getLogger(BuildRecommendation.class);
        log.info(s);
        return  log;
    }
    /*
    public ArrayList<String> getCoCount(int Yindex, Map.Entry<String,Integer> tuple) {

        ArrayList<String> a = new ArrayList<>();
        Iterator<String> keyIterator = tuple.keySet().iterator();
        // makeNewLoggerInstance("tuple :" +tuple);
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Pattern pattern = Pattern.compile("(\\d+)");
            Matcher m = pattern.matcher(key);
            m.find();
            Integer X = Integer.parseInt(m.group());
            m.find();
            Integer Y = Integer.parseInt(m.group());
            Integer co_count = tuple.get(key);
            //makeNewLoggerInstance("Y ::" +Y+ " X ::"+X);
            if (Y.intValue() == Yindex)
                a.add(""+co_count+" "+X +" "+Y);

        }
        makeNewLoggerInstance("for "+Yindex+"= :" +a);
        return a;
    }*/
}
