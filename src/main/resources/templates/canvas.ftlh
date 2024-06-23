<#ftl strip_whitespace=true>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${canvas.title}</title>
    <script data-asset-type="java-echarts-echarts-asset" src="${canvas.echartsAsset?no_esc}" ></script>
    <#list canvas.appendJsAssets as jsAsset>
    <script data-asset-type="java-echarts-custom-js-asset" src="${jsAsset?trim?no_esc}" ></script>
    </#list>
    <#list canvas.appendCssAssets as cssAsset>
    <link data-asset-type="java-echarts-custom-css-asset" href="${cssAsset?trim?no_esc}" rel="stylesheet">
    </#list>
</head>
<body>
<#list charts?keys as chartId>
    <#assign chart = charts[chartId]>
    <#assign chartId = chartId>
    <#assign options = chart.options>
    <#assign functions = chart.functions>
<!-- java_echarts_${chartId} -->
<div class="container" data-java-echarts-container-id="${chartId}">
    <div class="item" id="java_echarts_${chartId}" style="height:${chart.height};width:${chart.width};"></div>
</div>
<script data-java-echarts-script-id="${chartId}" type="text/javascript">
    "use strict";
    const java_echarts_${chartId} = echarts.init(document.getElementById('java_echarts_${chartId}'))
    const java_echarts_option_${chartId} = ${chart.options?no_esc}
    java_echarts_${chartId}.setOption(java_echarts_option_${chartId})

    <#list functions as func>
    ${func?remove_beginning(":")?no_esc}
    </#list>
</script>
<!-- /java_echarts_${chartId} -->

</#list>
</body>
</html>
