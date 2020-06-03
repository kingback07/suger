package org.kingback.suger.explorer.readProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ReadCustomYml {
    @Autowired
    private Environment environment;

    public String getCustomYmlStr() {
        String ressql = environment.getProperty("metrix.sql.C");

        return ressql;
    }

}
