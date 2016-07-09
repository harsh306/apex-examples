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
        int y=500998;
       /* long l = ByteBuffer.allocate(16).putInt(x).putInt(y).getLong(0);

        ByteBuffer buffer = ByteBuffer.allocate(16).putLong(l);
        x = buffer.getInt(0);
        y = buffer.getInt(1);*/
        long l = (((long)x) << 32) | (y & 0xffffffffL);
        int a = (int)(l >> 32);
        int b = (int)l;
        System.out.println(l+" x"+a+" y"+b);
    }
}
