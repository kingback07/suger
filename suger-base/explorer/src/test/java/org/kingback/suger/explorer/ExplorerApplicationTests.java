package org.kingback.suger.explorer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingback.suger.explorer.myquene.QueneUnit;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExplorerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testblockQuene(){
        QueneUnit quene=new QueneUnit();
        quene.getObj();
        String s="a";
        quene.setObj(s);
        String a=(String)quene.getObj();
        Assert.assertEquals(a,"a");
    }

    @Test
    public void testString(){
        String s1="this is String";
        StringBuilder sb=new StringBuilder(s1);
        sb.append(" Yea");
        System.out.println("String Test:"+(s1));
    }

}
