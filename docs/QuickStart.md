# echarts4j

Echarts4j does rely on the power of [Apache Echarts](https://echarts.apache.org/), so we don't cost too much time into
details.
Instead, we do practice, let's see how you could create a chart with the [Apache Echarts](https://echarts.apache.org/)
docs step by step.

## Echarts

You could open the [Apache Demo Page](https://echarts.apache.org/examples/en/index.html) and find all the charts echarts
provided. Ideally, echarts4j could support all of them in further (wait your contribution also !).

I don't want to make the sample too simple and useless, so we pick
the [Line Chart - Temperature Change in the Coming Week](https://echarts.apache.org/examples/en/index.html#chart-type-line)
. (the right bottom corner in the screenshot below)

![](./assets/quick-start-echarts-line.png ':size=60%')

Click it, you can jump to the [Echarts Editor](https://echarts.apache.org/examples/en/editor.html?c=line-marker) page to
see how does it create.

There is what it is I pasted below, I suppose we should see them the same.

```
option = {
  title: {
    text: 'Temperature Change in the Coming Week'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {},
  toolbox: {
    show: true,
    feature: {
      dataZoom: {
        yAxisIndex: 'none'
      },
      dataView: { readOnly: false },
      magicType: { type: ['line', 'bar'] },
      restore: {},
      saveAsImage: {}
    }
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value',
    axisLabel: {
      formatter: '{value} °C'
    }
  },
  series: [
    {
      name: 'Highest',
      type: 'line',
      data: [10, 11, 13, 11, 12, 12, 9],
      markPoint: {
        data: [
          { type: 'max', name: 'Max' },
          { type: 'min', name: 'Min' }
        ]
      },
      markLine: {
        data: [{ type: 'average', name: 'Avg' }]
      }
    },
    {
      name: 'Lowest',
      type: 'line',
      data: [1, -2, 2, 5, 3, 2, 0],
      markPoint: {
        data: [{ name: '周最低', value: -2, xAxis: 1, yAxis: -1.5 }]
      },
      markLine: {
        data: [
          { type: 'average', name: 'Avg' },
          [
            {
              symbol: 'none',
              x: '90%',
              yAxis: 'max'
            },
            {
              symbol: 'circle',
              label: {
                position: 'start',
                formatter: 'Max'
              },
              type: 'max',
              name: '最高点'
            }
          ]
        ]
      }
    }
  ]
};
```

We can see all the thing is called an `option`, it contains lots of `keys` ( we could name they are `mino options`
also ).
The `option` is the global option layer for Echarts instance, the `mino option` is what we need to config usually for a
chart.

You are so smart to be aware of those things easily:

**General configs:**

- title  
  Config the chart title ( we can see in the chart obviously).
- toolbox  
  Config the `Toolbox`s ( we can see in the chart top right corner).

- xAxis

- yAxis
  ...

**Series configs:**
> In Echarts, the series is the real part to indicate what the chart type is.
> You could try to change one of the series type from `line` to `bar` in the sample to see what happens.

Series

- type: what the chart should be.
- data: the dataset of the chart.

The other options for the charts, literally the single series.

- markPoint
- markLine
- ...

---

## Echarts4j

I believe you already understand how does Echarts make charts, let me see how does echarts4j to do it.
Talk is cheap, show you the code.

> Too long to show here directly...plz open the code blocks below :)


<details>

  <summary>The code to create the chart</summary>

> The `Chart` defines all the options.

```go
    final LineChart lineChart = LineChart.builder()
                .options(ChartOption.builder()
                        .title(Title.builder().text("Temperature Change in the Coming Week").build())
                        .tooltip(Tooltip.builder().trigger("axis").build())
                        .legend(Legend.builder().build())
                        .toolbox(Toolbox.builder()
                                .show(true)
                                .feature(Toolbox.Feature.builder()
                                        .dataView(Toolbox.DataView.builder().readOnly(true).build())
                                        .restore(Toolbox.Restore.builder().build())
                                        .saveAsImage(Toolbox.SaveAsImage.builder().build())
                                        .build())
                                .build())
                        .xAxis(XAxis.builder()
                                .type("category")
                                .boundaryGap(FuncStr.of(false))
                                .data(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"))
                                .build())
                        .yAxis(YAxis.builder()
                                .type("value")
                                .axisLabel(YAxis.AxisLabel.builder()
                                        .formatter(FuncStr.ofStr("{value} °C"))
                                        .build())
                                .build())
                        .build()
                        // first series
                        .addSeries(LineChartSeries.builder()
                                .name("Highest")
                                .type("line") // it will be auto config if not set since we use the LineChartSeries
                                .data(Arrays.asList(10, 11, 13, 11, 12, 12, 9))
                                .markPoint(MarkPoint.builder()
                                        .data(Arrays.asList(
                                                // use the builtin pojo
                                                MarkPoint.MarkPointDataItem.builder()
                                                        .type("max")
                                                        .name("Max")
                                                        .build()
                                                // use the data helper for custom Map is also fine
                                                , DataHelper.create()
                                                        .addField("type", String.class)
                                                        .addNameField()
                                                        .build()
                                                        .addData("min", "Min")
                                                        .get().get(0)
                                        ))
                                        .build())
                                .markLine(MarkLine.builder()
                                        .data(DataHelper.create()
                                                .addField("type", String.class)
                                                .addNameField()
                                                .build()
                                                .addData("average", "Avg")
                                                .get())
                                        .build())
                                .build())
                        // second series
                        .addSeries(LineChartSeries.builder()
                                .name("Lowest")
                                .data(Arrays.asList(1, -2, 2, 5, 3, 2, 0))
                                .markPoint(
                                        MarkPoint.builder()
                                                .data(DataHelper.create()
                                                        .addNameField()
                                                        .addValueField(Integer.class)
                                                        .addField("xAxis", Integer.class)
                                                        .addField("yAxis", Double.class)
                                                        .build()
                                                        .addData("周最低", -2, 1, -1.5)
                                                        .get())
                                                .build()
                                )
                                .markLine(MarkLine.builder()
                                        .data(DataHelper.create()
                                                .addField("type", String.class)
                                                .addNameField()
                                                .build()
                                                .addData("average", "Avg")
                                                .get()
                                        )
                                        .build())
                                .build())
                )
                .build();
```

</details>

<details>
<summary>The code to render it out</summary>

> The `Canvas` is the `Charts`' Container with `render` support to HTML(default), Image...

```go
 Canvas.builder()
        .addCharts(lineChart)
        .build()
        .renderTo(new File("Temperature-Change-in-the-Coming-Week.html"));

```

</details>


It is quite simple and straightforward, isn't it?!

You may see some stuff such as `DataHelper` or `FunStr`, they are just some utilities for options build.

Now, you already know how to play with echarts4j.
You may have questions - `How do you know what configurations I can use in chart?`

Biu~ Here it is!
The  [Echarts Configurations](https://echarts.apache.org/en/option.html#title) lists almost the things you want.
The only problem is echarts4j has not sync all the options already, feel free to make you contributions here!


---
If you are still interested in the echarts4j itself. Plz continue goto [DiveInto](DiveInto.md).
