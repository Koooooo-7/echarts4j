grammar Echarts4j;

start: canvas EOF;

canvas: 'Canvas' ID '[' charts ']' EOF;

charts: chart+;

chart: type ID '{' option+ '}';

option: title | toolbox | name  ;

toolbox: 'toolbox=>' feature+;

title: 'title=>'ID;

name: 'name=>'ID;

feature: 'dataView' | 'restore' | 'saveAsImage';

type: 'line' | 'bar';


ID: [a-zA-Z_][a-zA-Z0-9_]*;
WS: [ \t\r\n]+ -> skip;


