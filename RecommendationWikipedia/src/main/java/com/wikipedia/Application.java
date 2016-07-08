/**
 * Put your copyright and license info here.
 */
package com.wikipedia;
import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import org.apache.hadoop.conf.Configuration;

@ApplicationAnnotation(name="WikipediaApplication")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
      ReadFile readFile = dag.addOperator("readFile",new ReadFile());
      CreateItemPair itemPair=dag.addOperator("create item pair",new CreateItemPair());
      PairKeyCounter counter= dag.addOperator("counter",new PairKeyCounter());
    //  ConsoleOutputOperator cons =dag.addOperator("console",ConsoleOutputOperator.class);
      WriteToFile writeToFile = dag.addOperator("FileWriter", new WriteToFile());
      BuildRecommendation buildR= dag.addOperator("buildR",new BuildRecommendation());
//1
      dag.addStream("ReadFile to createitempair " ,readFile.output,itemPair.hashInput);
      dag.addStream("readfile to Br",readFile.vector,buildR.userVector);
//2
      dag.addStream("createitempair to paircounter",itemPair.coOccures,counter.inputPort);
//3
      dag.addStream("pairCounter to Br",counter.outputPort, buildR.xyInput);
//4
      dag.addStream("Update Recommendations",buildR.Rout,writeToFile.input);
      dag.setInputPortAttribute(writeToFile.input, DAG.PortContext.PARTITION_PARALLEL, true );

//@ignore
      //dag.addStream("pairCounter to console",counter.outputPort, cons.input);

  }
}