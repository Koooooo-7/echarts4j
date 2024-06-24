<#ftl strip_whitespace=true>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${canvas.title}</title>
    <script data-asset-type="echarts4j-echarts-asset" src="${canvas.echartsAsset?no_esc}" ></script>
    <#list canvas.appendJsAssets as jsAsset>
    <script data-asset-type="echarts4j-custom-js-asset" src="${jsAsset?trim?no_esc}" ></script>
    </#list>
    <#list canvas.appendCssAssets as cssAsset>
    <link data-asset-type="echarts4j-custom-css-asset" href="${cssAsset?trim?no_esc}" rel="stylesheet">
    </#list>
</head>
<body>
<#list charts?keys as chartId>
    <#assign chart = charts[chartId]>
    <#assign chartId = chartId>
    <#assign options = chart.options>
    <#assign functions = chart.functions>
<!-- echarts4j_${chartId} -->
<div class="container" data-echarts4j-container-id="${chartId}">
    <div class="item" id="echarts4j_${chartId}" style="height:${chart.height};width:${chart.width};"></div>
</div>
<script data-echarts4j-script-id="${chartId}" type="text/javascript">
    "use strict";
    const echarts4j_${chartId} = echarts.init(document.getElementById('echarts4j_${chartId}'))
    const echarts4j_option_${chartId} = ${chart.options?no_esc}
    echarts4j_${chartId}.setOption(echarts4j_option_${chartId})

    <#list functions as func>
    ${func?remove_beginning(":")?no_esc}
    </#list>
</script>
<!-- /echarts4j_${chartId} -->

</#list>
</body>
</html>
