package com.wikipedia;

import org.apache.commons.lang.mutable.MutableInt;

/**
 * Created by harsh on 4/7/16.
 */
public class MyEntry  {

    public MyEntry(){

    }
    Long  itemPair;
    MutableInt coCount;
    public MyEntry(Long itemPair, MutableInt coCount){
        this.itemPair=itemPair;
        this.coCount=coCount;
    }

    public Long getItemPair() {
        return itemPair;
    }

    public MutableInt getCoCount() {
        return coCount;
    }

    public void setCoCount(MutableInt coCount) {
        this.coCount = coCount;
    }

    public void setItemPair(Long itemPair) {
        this.itemPair = itemPair;
    }
}

