package com.cxyz.check.other;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/11/18.
 */

public class MineMap<K,V> extends HashMap<K,V> {


    //观察者
    private MineObserver<K,V> observer = null;

    public MineObserver<K, V> getObserver() {
        return observer;
    }

    public void setObserver(MineObserver<K, V> observer) {
        this.observer = observer;
    }

    //当不传入时默认为hashmap
    public MineMap()
    {
        super();
    }

    public MineMap(HashMap<K,V> hashMap)
    {
        super(hashMap);
    }

    public MineMap(HashMap<K,V> hashMap,MineObserver<K,V> mineObserver)
    {
        super(hashMap);
        this.observer = mineObserver;
    }

    @Override
    public V put(K key, V value) {
        final V put = super.put(key, value);
        if(observer!=null)
        {
            observer.onPut(key,value);
        }
        return put;
    }

    @Override
    public V remove(Object key) {
        V value = super.remove(key);
        if(observer!=null)
        {
            observer.onRemove((K) key,value);
        }
        return value;
    }

    /**
     * 我的map的观察者，用以观察数据的变化
     * @param <K>
     * @param <V>
     */
    public interface MineObserver<K,V>{
        /**
         * 当map进行remove时调用
         * @param key
         * @param value
         */
         void onRemove(K key,V value);

        /**
         * 当map进行put时调用
         * @param key
         * @param value
         */
         void onPut(K key,V value);
    }
}
