package framework.cluster;

import framework.common.URL4Rpc;

import java.util.List;
import java.util.Random;

/**
 * 负载均衡实现（此处为了简便，直接写random）
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class LoadBalance {

    public static URL4Rpc selectByRandom(List<URL4Rpc> urlList) {
        Random random = new Random();
        int size = urlList.size();
        int randomIndex = random.nextInt(size);
        return urlList.get(randomIndex);
    }

}
