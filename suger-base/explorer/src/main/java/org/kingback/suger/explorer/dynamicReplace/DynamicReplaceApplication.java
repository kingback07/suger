package org.kingback.suger.explorer.dynamicReplace;

import java.util.HashMap;

public class DynamicReplaceApplication {
    public static void main(String[] args) {

        String sqlTemp = "select * from testTable where dt={{current}} and exp_id={{expId}} and end={{current}}";

        HashMap<String, Object> paraMap = new HashMap<>();

        paraMap.put("current", "2019-03-26");
        paraMap.put("expId", 33);

        String res = DynamicReplace.replaceString(sqlTemp, paraMap);

        System.out.println(res);

    }
}
