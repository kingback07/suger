package org.kingback.suger.learning;

import org.junit.Assert;
import org.junit.Test;
import org.kingback.suger.learning.datastructures.array.StringCompare;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

@SpringBootTest
public class Test4String {

    private String[] genarateData(){
        return new String[]{"flat","flash","flating"};
//        return new String[]{"dog","racecar","car"};
//        return new String[]{"a","a"};
    }

    @Test
    public void testStringCompareBM(){
        String s1="fskjfhidsjfdsljfkldsjkfladsjklfdsefd";
        String s2="skj";
        StringCompare.compareByBF(s1,s2);
    }

    @Test
    public void getLongestPrefixBTest(){
        String[] strs=genarateData();
        String res=StringCompare.longestCommonPrefix(strs);
        Assert.assertEquals("fla",res);

        HashMap hm;
        Hashtable ht;
        Deque dq;
        Stack st;
    }




}
