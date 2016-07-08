package com.wikipedia;

import org.apache.commons.collections.list.TreeList;
import org.apache.mahout.cf.taste.impl.recommender.ByValueRecommendedItemComparator;
import org.apache.mahout.cf.taste.impl.recommender.GenericRecommendedItem;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anurag on 19/6/16.
 */
public class Test {


    public static void main(String args[]){
        int x=9607597;
        int y=67890;
        long l = ByteBuffer.allocate(8).putInt(x).putInt(y).getLong(0);

        ByteBuffer buffer = ByteBuffer.allocate(8).putLong(l);
        System.out.println(l);
    }
}
