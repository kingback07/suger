package org.suger.winter.bean;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.suger.test.MainTest;
import org.suger.winter.annotation.Componet;
import org.suger.winter.annotation.ComponetScan;

/**
 * 基于注解的bean 容器
 * 参照 Spring 中的 ApplicationContext
 * Created on 2021-02-03
 */
public class AnnotaionApplicationContext implements BeanFactory {

    private Map<String, Object> singletonBeanMap = new HashMap();

    public AnnotaionApplicationContext(Class<MainTest> mainTestClass) {
        // 从启动类中读取ComponentScan注解，获取需要加载的类
        String packPath = "";

        if (mainTestClass.isAnnotationPresent(ComponetScan.class)) {
            ComponetScan componetScan = mainTestClass.getAnnotation(ComponetScan.class);
            packPath = componetScan.value();
            packPath = packPath.replace(".", "/");
        }
        System.out.println("packPath-->" + packPath);
        //使用ClassLoader 拉取资源文件
        ClassLoader clsLoader = AnnotaionApplicationContext.class.getClassLoader();
        URL rsurl = clsLoader.getResource(packPath);
        //从path中解析出需要加载的类
        File file = new File(rsurl.getFile());
        System.out.println("File existed-->" + file.exists());
        if (file.isDirectory()) {
            File[] classFiles = file.listFiles();
            for (File clsFile : classFiles) {
                String sourcePath = clsFile.getAbsolutePath();
                sourcePath = sourcePath.substring(sourcePath.indexOf("org"), sourcePath.indexOf(".class"));
                sourcePath = sourcePath.replace("/", ".");
                System.out.println("FileName-->" + sourcePath);
                Class cls = null;
                try {
                    cls = clsLoader.loadClass(sourcePath);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (cls.isAnnotationPresent(Componet.class)) {
                    Componet compo = (Componet) cls.getAnnotation(Componet.class);
                    String clsKey = cls.getSimpleName();
                    if (compo.value() != null && compo.value().trim().length() > 0) {
                        clsKey = compo.value();
                    }
                    try {
                        singletonBeanMap.put(clsKey, cls.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("initial completed .....");
    }

    public Object getBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }
}
