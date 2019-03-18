package org.kingback.suger.acto.PromethusDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class PromethusController {

    Logger logger=LoggerFactory.getLogger(PromethusController.class);

    @RequestMapping("/testlong")
    public void testLongQueryC() throws InterruptedException {
        System.out.println("开始访问");
        Thread.sleep(1000);
        System.out.println("访问结束");
        logger.info("xxxx");
    }

}
