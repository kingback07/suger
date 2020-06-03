package org.kingback.suger.demo.demo4InnerCall;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    private static final String template = "pro=%d&secret=%s&time=%d";


    public static String generateSignature(int productId, String appSecret, long time) {

        String sortedParams = String.format(template, productId, appSecret, time);
        return DigestUtils.md5Hex(sortedParams);
    }

    public static boolean validSignature(int productId, String appSecret, long time, long timeOut, String sign) {
        long diff = System.currentTimeMillis() - time;
        if (diff > timeOut) {
            logger.error("sign time out,diff:{},time:{}", diff, time);
            return false;
        }

        String computed = generateSignature(productId, appSecret, time);
        boolean match = Objects.equals(sign, computed);
        if (!match) {
            logger.error("sign err, origin:{}, computed:{}", sign, computed);
        }
        return match;
    }
}