package org.kingback.suger.explorer.myquene;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟一个阻塞队列
 * 超过20条记录时写阻塞
 * 无数据时读阻塞
 */
public class QueneUnit {

    private List<Object> _ds = new ArrayList<Object>();

    private int maxCount = 20;

    private volatile int curCount = 0;

    private final Object readKey = new Object();
    private final Object writeKey = new Object();

    public Object getObj() {
        try {
            if (curCount == 0) {
                readKey.wait();
            }
            Object res = _ds.get(curCount);
            curCount--;
            writeKey.notify();

            return res;
        } catch (Exception ex) {
            return null;
        } finally {
        }
    }

    public void setObj(Object val) {
        try {
            if (curCount >= maxCount) {
                writeKey.wait();
            }
            _ds.add(val);
            curCount++;
            readKey.notify();
        } catch (Exception ex) {

        } finally {
        }
    }

}
