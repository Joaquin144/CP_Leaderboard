package com.devcommop.joaquin.seceleaderboard.domain.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SortHashMap {

    public static HashMap<String, Integer> sortByValueDescending(HashMap<String, Integer> hm) {
        //aim: convert the given map into list
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        //aim: sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //aim: convert this list into hashmap
        HashMap<String, Integer> ans = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> entryItem: list){
            ans.put(entryItem.getKey(), entryItem.getValue());
        }

        //aim: return the answer
        return ans;
    }

}
