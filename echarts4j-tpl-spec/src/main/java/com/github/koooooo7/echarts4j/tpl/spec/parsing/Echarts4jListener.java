package com.github.koooooo7.echarts4j.tpl.spec.parsing;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Echarts4jParser}.
 */
public interface Echarts4jListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(Echarts4jParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(Echarts4jParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#canvas}.
	 * @param ctx the parse tree
	 */
	void enterCanvas(Echarts4jParser.CanvasContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#canvas}.
	 * @param ctx the parse tree
	 */
	void exitCanvas(Echarts4jParser.CanvasContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#charts}.
	 * @param ctx the parse tree
	 */
	void enterCharts(Echarts4jParser.ChartsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#charts}.
	 * @param ctx the parse tree
	 */
	void exitCharts(Echarts4jParser.ChartsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#chart}.
	 * @param ctx the parse tree
	 */
	void enterChart(Echarts4jParser.ChartContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#chart}.
	 * @param ctx the parse tree
	 */
	void exitChart(Echarts4jParser.ChartContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(Echarts4jParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(Echarts4jParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#toolbox}.
	 * @param ctx the parse tree
	 */
	void enterToolbox(Echarts4jParser.ToolboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#toolbox}.
	 * @param ctx the parse tree
	 */
	void exitToolbox(Echarts4jParser.ToolboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(Echarts4jParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(Echarts4jParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(Echarts4jParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(Echarts4jParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterFeature(Echarts4jParser.FeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitFeature(Echarts4jParser.FeatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link Echarts4jParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(Echarts4jParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Echarts4jParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(Echarts4jParser.TypeContext ctx);
}