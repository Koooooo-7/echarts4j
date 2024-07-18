# echarts4j-tpl-spec

> WIP
*🍩 An extension for echarts4j for tpl specification.*

---

### 📝 RunBook

Use the tpl spec aims to use the `e4j` file as the IDL to config the basic
Echarts template, either the generic logic inside to make the canvas more flexible and savable.

**If you define a template `foo.e4j`**

```e4j
// a Canvas
Canvas myEcharts4j [

// a basic line chart
  line myLineChart {
  // chart title
   title=>myLine
   // chart toolbox configs
   toolbox=> dataView restore saveAsImage
  }

// a basic bar chart
  bar myBarChart {
   title=>myBar
   toolbox=> saveAsImage
  }
]
```

**You will get the generated Canvas**

```

// the canvas already contains those basic charts inside.
Canvas tpl = Echarts4jC.parse(resource);

```

### 📃 License

Apache-2.0 license [©KoyZhuang](https://github.com/Koooooo-7/echarts4j/blob/main/LICENSE)