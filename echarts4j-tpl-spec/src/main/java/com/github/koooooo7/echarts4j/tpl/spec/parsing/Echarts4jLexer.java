package com.github.koooooo7.echarts4j.tpl.spec.parsing;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Echarts4jLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, ID=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "ID", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Canvas'", "'['", "']'", "'{'", "'}'", "'toolbox=>'", "'title=>'", 
			"'name=>'", "'dataView'", "'restore'", "'saveAsImage'", "'line'", "'bar'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "ID", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Echarts4jLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Echarts4j.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000f{\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0005\rp\b\r\n\r\f\rs\t\r\u0001\u000e\u0004"+
		"\u000ev\b\u000e\u000b\u000e\f\u000ew\u0001\u000e\u0001\u000e\u0000\u0000"+
		"\u000f\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u0001\u0000\u0003\u0003\u0000AZ__az\u0004\u000009AZ__az\u0003"+
		"\u0000\t\n\r\r  |\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000"+
		"\u0000\u0003&\u0001\u0000\u0000\u0000\u0005(\u0001\u0000\u0000\u0000\u0007"+
		"*\u0001\u0000\u0000\u0000\t,\u0001\u0000\u0000\u0000\u000b.\u0001\u0000"+
		"\u0000\u0000\r8\u0001\u0000\u0000\u0000\u000f@\u0001\u0000\u0000\u0000"+
		"\u0011G\u0001\u0000\u0000\u0000\u0013P\u0001\u0000\u0000\u0000\u0015X"+
		"\u0001\u0000\u0000\u0000\u0017d\u0001\u0000\u0000\u0000\u0019i\u0001\u0000"+
		"\u0000\u0000\u001bm\u0001\u0000\u0000\u0000\u001du\u0001\u0000\u0000\u0000"+
		"\u001f \u0005C\u0000\u0000 !\u0005a\u0000\u0000!\"\u0005n\u0000\u0000"+
		"\"#\u0005v\u0000\u0000#$\u0005a\u0000\u0000$%\u0005s\u0000\u0000%\u0002"+
		"\u0001\u0000\u0000\u0000&\'\u0005[\u0000\u0000\'\u0004\u0001\u0000\u0000"+
		"\u0000()\u0005]\u0000\u0000)\u0006\u0001\u0000\u0000\u0000*+\u0005{\u0000"+
		"\u0000+\b\u0001\u0000\u0000\u0000,-\u0005}\u0000\u0000-\n\u0001\u0000"+
		"\u0000\u0000./\u0005t\u0000\u0000/0\u0005o\u0000\u000001\u0005o\u0000"+
		"\u000012\u0005l\u0000\u000023\u0005b\u0000\u000034\u0005o\u0000\u0000"+
		"45\u0005x\u0000\u000056\u0005=\u0000\u000067\u0005>\u0000\u00007\f\u0001"+
		"\u0000\u0000\u000089\u0005t\u0000\u00009:\u0005i\u0000\u0000:;\u0005t"+
		"\u0000\u0000;<\u0005l\u0000\u0000<=\u0005e\u0000\u0000=>\u0005=\u0000"+
		"\u0000>?\u0005>\u0000\u0000?\u000e\u0001\u0000\u0000\u0000@A\u0005n\u0000"+
		"\u0000AB\u0005a\u0000\u0000BC\u0005m\u0000\u0000CD\u0005e\u0000\u0000"+
		"DE\u0005=\u0000\u0000EF\u0005>\u0000\u0000F\u0010\u0001\u0000\u0000\u0000"+
		"GH\u0005d\u0000\u0000HI\u0005a\u0000\u0000IJ\u0005t\u0000\u0000JK\u0005"+
		"a\u0000\u0000KL\u0005V\u0000\u0000LM\u0005i\u0000\u0000MN\u0005e\u0000"+
		"\u0000NO\u0005w\u0000\u0000O\u0012\u0001\u0000\u0000\u0000PQ\u0005r\u0000"+
		"\u0000QR\u0005e\u0000\u0000RS\u0005s\u0000\u0000ST\u0005t\u0000\u0000"+
		"TU\u0005o\u0000\u0000UV\u0005r\u0000\u0000VW\u0005e\u0000\u0000W\u0014"+
		"\u0001\u0000\u0000\u0000XY\u0005s\u0000\u0000YZ\u0005a\u0000\u0000Z[\u0005"+
		"v\u0000\u0000[\\\u0005e\u0000\u0000\\]\u0005A\u0000\u0000]^\u0005s\u0000"+
		"\u0000^_\u0005I\u0000\u0000_`\u0005m\u0000\u0000`a\u0005a\u0000\u0000"+
		"ab\u0005g\u0000\u0000bc\u0005e\u0000\u0000c\u0016\u0001\u0000\u0000\u0000"+
		"de\u0005l\u0000\u0000ef\u0005i\u0000\u0000fg\u0005n\u0000\u0000gh\u0005"+
		"e\u0000\u0000h\u0018\u0001\u0000\u0000\u0000ij\u0005b\u0000\u0000jk\u0005"+
		"a\u0000\u0000kl\u0005r\u0000\u0000l\u001a\u0001\u0000\u0000\u0000mq\u0007"+
		"\u0000\u0000\u0000np\u0007\u0001\u0000\u0000on\u0001\u0000\u0000\u0000"+
		"ps\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000"+
		"\u0000r\u001c\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000tv\u0007"+
		"\u0002\u0000\u0000ut\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000"+
		"wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000"+
		"\u0000yz\u0006\u000e\u0000\u0000z\u001e\u0001\u0000\u0000\u0000\u0003"+
		"\u0000qw\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}