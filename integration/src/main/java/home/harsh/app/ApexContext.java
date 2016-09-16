package home.harsh.app;

/**
 * Created by harsh on 14/9/16.
 */
import org.apache.spark.*;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.*;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import scala.Tuple2;
import scala.collection.Map;

public class ApexContext extends SparkContext{
    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public Map<String, Tuple2<Object, Object>> getExecutorMemoryStatus() {
        return super.getExecutorMemoryStatus();
    }

    @Override
    public boolean isLocal() {
        return super.isLocal();
    }

    @Override
    public SparkConf getConf() {
        return super.getConf();
    }
}
