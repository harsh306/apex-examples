package com.wikipedia;

import com.datatorrent.api.DefaultPartition;
import com.datatorrent.api.Operator;
import com.datatorrent.common.partitioner.StatelessPartitioner;
import com.google.common.collect.Lists;

import java.util.Collection;

/**
 * Created by harsh on 4/7/16.
 */
public class StatelessAllPartitioner<T extends Operator> extends StatelessPartitioner<T>
{
    public StatelessAllPartitioner(){}
    private int partitionCount = 1;
    public StatelessAllPartitioner(String value)
    {
        this(Integer.parseInt(value));
    }
    public StatelessAllPartitioner(int partitionCount)
    {
        this.partitionCount = partitionCount;
    }
    public void setPartitionCount(int partitionCount)
    {
        this.partitionCount = partitionCount;
    }
    public int getPartitionCount()
    {
        return partitionCount;
    }

    @Override
    public Collection<Partition<T>> definePartitions(Collection<Partition<T>> partitions, PartitioningContext context)
    {
        final int newPartitionCount = DefaultPartition.getRequiredPartitionCount(context, this.getPartitionCount());

        //Get a partition
        DefaultPartition<T> partition = (DefaultPartition<T>)partitions.iterator().next();
        Collection<Partition<T>> newPartitions;

        if (partitions.iterator().next().getStats() == null) {
            // first call to define partitions
            newPartitions = Lists.newArrayList();

            for (int partitionCounter = 0; partitionCounter < newPartitionCount; partitionCounter++) {
                newPartitions.add(new DefaultPartition<T>(partition.getPartitionedInstance()));
            }

        } else {
            // define partitions is being called again
            if (context.getParallelPartitionCount() != 0) {
                newPartitions = repartitionParallel(partitions, context);
            } else if (partition.getPartitionKeys().isEmpty()) {
                newPartitions = repartitionInputOperator(partitions);
            } else {
                newPartitions = repartition(partitions);
            }
        }

        return newPartitions;
    }
}
