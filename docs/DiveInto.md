# echarts4j

After the [QuickStart](QuickStart.md), I suppose you already have the knowledge base of echarts4j.
There is nothing special.

Forgive me that I will put the `Options` entities away since I don't want to waste your time.

There is only 3 things I'm going to show you for now.

- The Canvas
- The Render
- The Context Injection

## Canvas

In the codebase, `Canvas` is the abstract of the charts' container.
It holds all the charts and layout.
Additionally, as a `HTML Canvas` by default, you could add some custom assets to HTML page.

For the whole flexibility, I make the `Canvas` and the `Canvas Builder` can be converted into each other.
In other words, `Canvas` is created by a `Builder`, and vice versa, `Canvas` can degenerate as a `Builder` to reuse the
Canvas.

```
        Canvas.builder()
                .addCharts(
                    LineChart.builder()
                        .chartId("myChart")
                        .build()

                )
                .replaceEchartsAsset("replaceYourOwnLocalEchartsAssetOrOtherCDN")
                .appendJSAssets("jsAssets")
                .build()
                // reto a builder
                .asBuilder()
                .addCharts(......)
                .updateChart("myChart", line -> line.map(chart -> chart.addJSFunction(FuncStr.of("OtherStuff"))))
                .build();
```

`Canvas` is `Canvas`, what it should be render out is depends on the Render.

## Render

The Render aims to render the `Canvas` out to the HTML or other place, it depends on what the `Render` you use.
In echarts4j, the builtin `DefaultRender` is responsible for rendering the `Canvas` to HTML page.
Besides, with the extensibility of Render, you can use `echarts4j-chart-server` to render it as a live update page,
use the `echarts4j-snapshot-playwright` to render it as image...etc.

Echarts4j uses plugin mechanism to load Renders, you could create you own one easily.
And you can use `RenderProvider` to get the specific mounted Render your want.

```

public interface Render {

    /**
     * A simple render for Canvas with default output located {@code new File("echarts4j.html") }.
     *
     * @param canvas The {@link Canvas} instance
     */
    default void render(Canvas canvas) {
        render(canvas, new File("echarts4j.html"));
    }

    default void render(Canvas canvas, File file) {
        try (Writer writer = new FileWriter(file)) {
            render(canvas, writer);
        } catch (IOException e) {
            throw new RenderException(e);
        }
    }

    /**
     * @param canvas The {@link Canvas} instance
     * @param writer The Writer instance, <strong>Note:</strong> it needs to manually close by user.
     */
    void render(Canvas canvas, Writer writer);

    /**
     * The Order to build Render Chain, <strong>Note:</strong> all {@link Render}s should not have same order.
     *
     * @return the Render order
     */
    int order();

    /**
     * Provide the next lower {@link Render} from the chain.
     */
    default void setPrevious(Render render) {
    }

```

!> The Render is chained, and `Canvas.renderX()` methods use the `head Render of Renders` by default.

## Context Injection

The Context injection has 2 parts:

- HTML template 
- JS function injection support

**HTML template**  
The HTML template uses the [Apache/FreeMarker](https://freemarker.apache.org/) to build in echarts4j, it is quite lite
and useful.

**JS function**  
echarts4j supports the pure JS function string injection.

```
        final String JsFuncStr = "console.log('echarts4j!');";
        final String JsFuncStrWithInstanceInjection = "console.log(%MY_ECHARTS%.getOption());";
        Canvas.builder()
                .addCharts(
                        LineChart.builder()
                                .options(ChartOption.builder().build())
                                .build()
                                .addJSFunction(FuncStr.of(JsFuncStr))
                                .addJSFunction(FuncStr.of(JsFuncStrWithInstanceInjection))

                )
                .build()
                .renderTo(new File("js.html"));

```

The render snippets :

```html

<script data-echarts4j-script-id="myChart" type="text/javascript">
    "use strict";
    const echarts4j_myChart = echarts.init(document.getElementById('echarts4j_myChart'))
    const echarts4j_option_myChart = {"series": [], "xAxis": {}, "yAxis": {}}
    echarts4j_myChart.setOption(echarts4j_option_myChart)

    console.log('echarts4j!');
    console.log(echarts4j_myChart.getOption());

</script>

```

The `FuncStr` wrappers value is considered as raw content when it is serialized.
So, we could do the tricky to make the JS out!

Importantly, you may find the `%MY_ECHARTS%` keyword in the JS FuncString.
It is the placeholder, echarts4j will inject the Echarts instance before render.
It should benefit users who want to operate Echarts Instance in custom JS func.


---

Thank you for patiently reading through this to the end.
That's all about current echarts4j, let's make it more in further!





