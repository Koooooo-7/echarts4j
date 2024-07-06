<#ftl strip_whitespace=true>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="echarts4j - an inevitable charts library for Java.">
    <title>${canvas.title}</title>
    <script data-asset-type="echarts4j-echarts-asset" src="${canvas.echartsAsset?no_esc}" ></script>
    <#list canvas.appendJsAssets as jsAsset>
    <script data-asset-type="echarts4j-custom-js-asset" src="${jsAsset?trim?no_esc}" ></script>
    </#list>
    <#list canvas.appendCssAssets as cssAsset>
    <link data-asset-type="echarts4j-custom-css-asset" href="${cssAsset?trim?no_esc}" rel="stylesheet">
    </#list>

    <#if canvas.layout == "center">
        <style> .container {display: flex;justify-content: center;align-items: center;} .item {margin: auto;} </style>
    </#if>
    <#if canvas.layout == "none">
<#--        placeholder for withou layout-->
    </#if>
</head>
<body>

<#list charts?keys as chartId>
    <#assign chart = charts[chartId]>
    <#assign chartId = chartId>
    <#assign options = chart.options>
    <#assign functions = chart.functions>
    <#assign listeners = chart.listeners>
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
    ${func?no_esc}
    </#list>

    <#list listeners as listener>
    <#if listener.query?has_content>
    echarts4j_${chartId}.on(${listener.eventName?no_esc}, ${listener.query?no_esc}, ${listener.handler?no_esc});
    <#else>
    echarts4j_${chartId}.on(${listener.eventName?no_esc}, ${listener.handler?no_esc});
    </#if>
    </#list>
</script>
<!-- /echarts4j_${chartId} -->

</#list>
</body>
</html>
