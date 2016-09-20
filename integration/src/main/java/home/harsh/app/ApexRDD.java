package home.harsh.app;

/**
 * Created by harsh on 20/9/16.
 */

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.rdd.RDD;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ClassTag;

public class ApexRDD extends RDD implements StreamingApplication{
    /*public ApexRDD(RDD oneParent, ClassTag evidence$2) {
        super(oneParent, evidence$2);
    }*/


    public ApexRDD(SparkContext _sc, Seq deps, ClassTag evidence$1) {
        super(_sc, deps, evidence$1);
    }

    @Override
    public Iterator compute(Partition partition, TaskContext taskContext) {
        return null;
    }

    @Override
    public Partition[] getPartitions() {
        return new Partition[0];
    }

    @Override
    public void populateDAG(DAG dag, Configuration configuration) {

    }
}
