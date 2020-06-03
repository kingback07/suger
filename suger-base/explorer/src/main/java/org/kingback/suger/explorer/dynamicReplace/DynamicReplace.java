package org.kingback.suger.explorer.dynamicReplace;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicReplace {

    public static String replaceString(String template, HashMap<String, Object> param) {


        Pattern p = Pattern.compile("\\{\\{(.*?)\\}\\}");

        Matcher m = p.matcher(template);

        HashMap<String, String> refParam = new HashMap<>();
        while (m.find()) {
            String s1 = m.group();
            String fieldName = s1.substring(2, s1.length() - 2);
            refParam.put(fieldName, s1);
            System.out.println(s1);

            Object val = param.get(fieldName);
            if (val == null) {
                val = " ";
            }
            if (val instanceof String) {
                val = (" '" + val + "' ");
            }

            template = template.replace(s1, val.toString());
        }

        //遍历字符串，执行替换操作

        return template;
    }

}
