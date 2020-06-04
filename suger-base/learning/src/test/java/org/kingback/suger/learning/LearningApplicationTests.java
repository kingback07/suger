package org.kingback.suger.learning;

import org.junit.Test;
import org.kingback.suger.learning.datastructures.array.ArrayOrderSimple;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LearningApplicationTests {

    private void printArray(int[] array){
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<array.length-1;i++){
            sb.append(array[i]+",");
        }
        sb.append(array[array.length-1]+"]");
        System.out.println(sb.toString());
    }

    @Test
    public void arrayOrderByInsertTest(){
        int[] array=new int[]{10,5,9,6,2,3,8,7,1,4};
        ArrayOrderSimple.orderByInsert(array);
        printArray(array);
    }


    @Test
    public void arrayOrderByBubbleTest(){
        int[] array=new int[]{10,5,9,6,2,3,8,7,1,4};
        ArrayOrderSimple.orderByBubble(array);
        printArray(array);
    }

    @Test
    public void arrayOrderBySelectTest(){
        int[] array=new int[]{10,5,9,6,2,3,8,7,1,4};
        ArrayOrderSimple.orderBySelected(array);
        printArray(array);
    }

}
