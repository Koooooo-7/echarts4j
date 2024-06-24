package com.github.koooooo7.echarts.render;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TemplateManager {

    private static final Configuration CONFIG;
    private static final String TPL = "canvas.ftlh";

    static {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);
        ClassLoader classLoader = TemplateManager.class.getClassLoader();
        cfg.setTemplateLoader(new ClassTemplateLoader(classLoader, "templates"));
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIG = cfg;
    }


    static Template getTemplate() throws IOException {
        return CONFIG.getTemplate(TPL);
    }


}
