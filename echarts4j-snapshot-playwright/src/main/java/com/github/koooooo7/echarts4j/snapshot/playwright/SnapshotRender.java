package com.github.koooooo7.echarts4j.snapshot.playwright;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.render.DefaultRender;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import java.io.File;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SnapshotRender implements Render {
    private static final String HTML_SUFFIX = ".html";
    private static final Set<String> validSnapshotSuffix = new HashSet<>(Arrays.asList("jpg", "png", "jpeg"));

    @Override
    public void render(Canvas canvas) {
        render(canvas, new File("echarts4j.png"));
    }

    @Override
    public void render(Canvas canvas, File file) {
        renderSnapshot(canvas, file);
    }

    @Override
    public void render(Canvas canvas, Writer writer) {
        throw new UnsupportedOperationException("Not support render image to a writer for now");
    }

    @Override
    public int order() {
        return Integer.MAX_VALUE - 1;
    }


    private void renderSnapshot(Canvas canvas, File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("Can not find a valid image format");
        }

        String currentImgSuffix = fileName.substring(lastDotIndex + 1);
        if (!validSnapshotSuffix.contains(currentImgSuffix.toLowerCase())) {
            throw new IllegalArgumentException("Only support the valid image format:" + validSnapshotSuffix);
        }
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true))) {

            String htmlPath = fileName.substring(0, lastDotIndex) + HTML_SUFFIX;
            File html = new File(htmlPath);
            RenderProvider.getRender(DefaultRender.class).render(canvas, html);

            Page page = browser.newPage();
            String htmlFilePath = Paths.get(htmlPath).toUri().toString();
            page.navigate(htmlFilePath);

            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get(fileName)));

            if (html.exists()) {
                final boolean ignore = html.delete();
            }
        }
    }
}
