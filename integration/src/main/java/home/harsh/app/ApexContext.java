package home.harsh.app;

/**
 * Created by harsh on 14/9/16.
 */

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.rdd.RDD;

public class ApexContext extends SparkContext{
    ApexContext ac;
    public ApexContext(){
         ac= (ApexContext) SparkContext.getOrCreate();

    }
    public RDD<LabeledPoint>  ReturnRDD(){

        
        RDD<LabeledPoint> toRDD = MLUtils.loadLibSVMFile(ac,"../path/" );
        return toRDD;
    }
    @Override
    public SparkConf getConf() {
        return super.getConf();
    }


    @Override
    public RDD<String> textFile(String path, int minPartitions) {
        return super.textFile(path, minPartitions);
    }

    @Override
    public int textFile$default$2() {
        return super.textFile$default$2();
    }
}
