// Generated from /Users/usuario/NetBeansProjects/DBMS/src/antlr/sql.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class sqlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__93=1, T__92=2, T__91=3, T__90=4, T__89=5, T__88=6, T__87=7, T__86=8, 
		T__85=9, T__84=10, T__83=11, T__82=12, T__81=13, T__80=14, T__79=15, T__78=16, 
		T__77=17, T__76=18, T__75=19, T__74=20, T__73=21, T__72=22, T__71=23, 
		T__70=24, T__69=25, T__68=26, T__67=27, T__66=28, T__65=29, T__64=30, 
		T__63=31, T__62=32, T__61=33, T__60=34, T__59=35, T__58=36, T__57=37, 
		T__56=38, T__55=39, T__54=40, T__53=41, T__52=42, T__51=43, T__50=44, 
		T__49=45, T__48=46, T__47=47, T__46=48, T__45=49, T__44=50, T__43=51, 
		T__42=52, T__41=53, T__40=54, T__39=55, T__38=56, T__37=57, T__36=58, 
		T__35=59, T__34=60, T__33=61, T__32=62, T__31=63, T__30=64, T__29=65, 
		T__28=66, T__27=67, T__26=68, T__25=69, T__24=70, T__23=71, T__22=72, 
		T__21=73, T__20=74, T__19=75, T__18=76, T__17=77, T__16=78, T__15=79, 
		T__14=80, T__13=81, T__12=82, T__11=83, T__10=84, T__9=85, T__8=86, T__7=87, 
		T__6=88, T__5=89, T__4=90, T__3=91, T__2=92, T__1=93, T__0=94, BOOLEAN=95, 
		DATETIME=96, ID=97, NUM=98, FLOAT=99, DATE=100, CHAR=101, WS=102, COMMENT=103;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "':'", "';'", 
		"'<'", "'='", "'>'", "'?'", "'@'", "'A'", "'B'", "'C'", "'D'", "'E'", 
		"'F'", "'G'", "'H'", "'I'", "'J'", "'K'", "'L'", "'M'", "'N'", "'O'", 
		"'P'", "'Q'", "'R'", "'S'", "'T'", "'U'", "'V'", "'W'", "'X'", "'Y'", 
		"'Z'", "'['", "'\\'", "']'", "'^'", "'_'", "'`'", "'a'", "'b'", "'c'", 
		"'d'", "'e'", "'f'", "'g'"
	};
	public static final String[] ruleNames = {
		"T__93", "T__92", "T__91", "T__90", "T__89", "T__88", "T__87", "T__86", 
		"T__85", "T__84", "T__83", "T__82", "T__81", "T__80", "T__79", "T__78", 
		"T__77", "T__76", "T__75", "T__74", "T__73", "T__72", "T__71", "T__70", 
		"T__69", "T__68", "T__67", "T__66", "T__65", "T__64", "T__63", "T__62", 
		"T__61", "T__60", "T__59", "T__58", "T__57", "T__56", "T__55", "T__54", 
		"T__53", "T__52", "T__51", "T__50", "T__49", "T__48", "T__47", "T__46", 
		"T__45", "T__44", "T__43", "T__42", "T__41", "T__40", "T__39", "T__38", 
		"T__37", "T__36", "T__35", "T__34", "T__33", "T__32", "T__31", "T__30", 
		"T__29", "T__28", "T__27", "T__26", "T__25", "T__24", "T__23", "T__22", 
		"T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", "T__14", 
		"T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", 
		"T__4", "T__3", "T__2", "T__1", "T__0", "BOOLEAN", "DATETIME", "LETTER", 
		"DIGIT", "ASCII", "TWO_DIGITS", "THREE_DIGITS", "FOUR_DIGITS", "YEAR", 
		"MONTH", "DAY", "ID", "NUM", "FLOAT", "DATE", "CHAR", "WS", "COMMENT"
	};


	public sqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "sql.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2i\u036a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3"+
		"%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3"+
		"(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3"+
		",\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3"+
		"/\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\3\64\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\3"+
		"8\38\38\39\39\39\3:\3:\3:\3:\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3<\3<\3"+
		"<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3?\3@\3@\3A\3"+
		"A\3A\3A\3A\3A\3A\3B\3B\3B\3B\3B\3B\3B\3C\3C\3D\3D\3D\3D\3D\3E\3E\3E\3"+
		"E\3F\3F\3F\3F\3G\3G\3G\3G\3G\3H\3H\3H\3H\3I\3I\3I\3J\3J\3J\3J\3J\3J\3"+
		"J\3K\3K\3K\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3N\3N\3N\3N\3N\3N\3N\3O\3O\3"+
		"O\3O\3O\3P\3P\3Q\3Q\3Q\3R\3R\3R\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T\3"+
		"U\3U\3U\3U\3V\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3Y\3Y\3"+
		"Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3]\3"+
		"]\3]\3^\3^\3^\3^\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3`\3`\3`\3`\3`\3`\3"+
		"`\3`\3a\3a\3a\3a\3a\3a\3a\3a\3a\3b\3b\3c\3c\3d\5d\u02ff\nd\3e\3e\3e\3"+
		"f\3f\3f\3g\3g\3g\3h\3h\3i\3i\5i\u030e\ni\3j\3j\5j\u0312\nj\3k\3k\3k\7"+
		"k\u0317\nk\fk\16k\u031a\13k\3l\5l\u031d\nl\3l\3l\7l\u0321\nl\fl\16l\u0324"+
		"\13l\3m\5m\u0327\nm\3m\3m\7m\u032b\nm\fm\16m\u032e\13m\3m\3m\7m\u0332"+
		"\nm\fm\16m\u0335\13m\3n\3n\3n\3n\3n\3n\3n\3n\3o\3o\7o\u0341\no\fo\16o"+
		"\u0344\13o\3o\3o\3p\6p\u0349\np\rp\16p\u034a\3p\3p\3q\3q\3q\3q\7q\u0353"+
		"\nq\fq\16q\u0356\13q\3q\5q\u0359\nq\3q\3q\3q\3q\3q\7q\u0360\nq\fq\16q"+
		"\u0363\13q\3q\3q\5q\u0367\nq\3q\3q\3\u0361\2r\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S"+
		"+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081"+
		"B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093K\u0095"+
		"L\u0097M\u0099N\u009bO\u009dP\u009fQ\u00a1R\u00a3S\u00a5T\u00a7U\u00a9"+
		"V\u00abW\u00adX\u00afY\u00b1Z\u00b3[\u00b5\\\u00b7]\u00b9^\u00bb_\u00bd"+
		"`\u00bfa\u00c1b\u00c3\2\u00c5\2\u00c7\2\u00c9\2\u00cb\2\u00cd\2\u00cf"+
		"\2\u00d1\2\u00d3\2\u00d5c\u00d7d\u00d9e\u00dbf\u00ddg\u00dfh\u00e1i\3"+
		"\2\6\5\2C\\aac|\5\2\13\f\"-/\u0080\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u036f"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2"+
		"{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2"+
		"\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097"+
		"\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2"+
		"\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9"+
		"\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2"+
		"\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb"+
		"\3\2\2\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00d5\3\2\2"+
		"\2\2\u00d7\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2\2\2\u00df"+
		"\3\2\2\2\2\u00e1\3\2\2\2\3\u00e3\3\2\2\2\5\u00eb\3\2\2\2\7\u00f6\3\2\2"+
		"\2\t\u00fb\3\2\2\2\13\u00ff\3\2\2\2\r\u0104\3\2\2\2\17\u010a\3\2\2\2\21"+
		"\u010e\3\2\2\2\23\u0114\3\2\2\2\25\u011f\3\2\2\2\27\u0125\3\2\2\2\31\u012c"+
		"\3\2\2\2\33\u012e\3\2\2\2\35\u0135\3\2\2\2\37\u013b\3\2\2\2!\u0144\3\2"+
		"\2\2#\u0148\3\2\2\2%\u014d\3\2\2\2\'\u014f\3\2\2\2)\u0156\3\2\2\2+\u015d"+
		"\3\2\2\2-\u015f\3\2\2\2/\u0167\3\2\2\2\61\u016f\3\2\2\2\63\u0177\3\2\2"+
		"\2\65\u017e\3\2\2\2\67\u0182\3\2\2\29\u0188\3\2\2\2;\u018f\3\2\2\2=\u0196"+
		"\3\2\2\2?\u019e\3\2\2\2A\u01a1\3\2\2\2C\u01a3\3\2\2\2E\u01aa\3\2\2\2G"+
		"\u01af\3\2\2\2I\u01b4\3\2\2\2K\u01b7\3\2\2\2M\u01be\3\2\2\2O\u01c5\3\2"+
		"\2\2Q\u01ca\3\2\2\2S\u01cf\3\2\2\2U\u01d6\3\2\2\2W\u01dd\3\2\2\2Y\u01e3"+
		"\3\2\2\2[\u01ed\3\2\2\2]\u01f0\3\2\2\2_\u01f8\3\2\2\2a\u01fb\3\2\2\2c"+
		"\u0201\3\2\2\2e\u0208\3\2\2\2g\u0212\3\2\2\2i\u0217\3\2\2\2k\u0219\3\2"+
		"\2\2m\u021d\3\2\2\2o\u0221\3\2\2\2q\u0225\3\2\2\2s\u0228\3\2\2\2u\u022c"+
		"\3\2\2\2w\u0231\3\2\2\2y\u023c\3\2\2\2{\u0240\3\2\2\2}\u0247\3\2\2\2\177"+
		"\u024d\3\2\2\2\u0081\u024f\3\2\2\2\u0083\u0256\3\2\2\2\u0085\u025d\3\2"+
		"\2\2\u0087\u025f\3\2\2\2\u0089\u0264\3\2\2\2\u008b\u0268\3\2\2\2\u008d"+
		"\u026c\3\2\2\2\u008f\u0271\3\2\2\2\u0091\u0275\3\2\2\2\u0093\u0278\3\2"+
		"\2\2\u0095\u027f\3\2\2\2\u0097\u0282\3\2\2\2\u0099\u0287\3\2\2\2\u009b"+
		"\u028c\3\2\2\2\u009d\u0293\3\2\2\2\u009f\u0298\3\2\2\2\u00a1\u029a\3\2"+
		"\2\2\u00a3\u029d\3\2\2\2\u00a5\u02a0\3\2\2\2\u00a7\u02a6\3\2\2\2\u00a9"+
		"\u02ac\3\2\2\2\u00ab\u02b0\3\2\2\2\u00ad\u02b6\3\2\2\2\u00af\u02bb\3\2"+
		"\2\2\u00b1\u02c1\3\2\2\2\u00b3\u02c3\3\2\2\2\u00b5\u02c7\3\2\2\2\u00b7"+
		"\u02cc\3\2\2\2\u00b9\u02d5\3\2\2\2\u00bb\u02d9\3\2\2\2\u00bd\u02dd\3\2"+
		"\2\2\u00bf\u02e8\3\2\2\2\u00c1\u02f0\3\2\2\2\u00c3\u02f9\3\2\2\2\u00c5"+
		"\u02fb\3\2\2\2\u00c7\u02fe\3\2\2\2\u00c9\u0300\3\2\2\2\u00cb\u0303\3\2"+
		"\2\2\u00cd\u0306\3\2\2\2\u00cf\u0309\3\2\2\2\u00d1\u030d\3\2\2\2\u00d3"+
		"\u0311\3\2\2\2\u00d5\u0313\3\2\2\2\u00d7\u031c\3\2\2\2\u00d9\u0326\3\2"+
		"\2\2\u00db\u0336\3\2\2\2\u00dd\u033e\3\2\2\2\u00df\u0348\3\2\2\2\u00e1"+
		"\u0366\3\2\2\2\u00e3\u00e4\7H\2\2\u00e4\u00e5\7Q\2\2\u00e5\u00e6\7T\2"+
		"\2\u00e6\u00e7\7G\2\2\u00e7\u00e8\7K\2\2\u00e8\u00e9\7I\2\2\u00e9\u00ea"+
		"\7P\2\2\u00ea\4\3\2\2\2\u00eb\u00ec\7E\2\2\u00ec\u00ed\7Q\2\2\u00ed\u00ee"+
		"\7P\2\2\u00ee\u00ef\7U\2\2\u00ef\u00f0\7V\2\2\u00f0\u00f1\7T\2\2\u00f1"+
		"\u00f2\7C\2\2\u00f2\u00f3\7K\2\2\u00f3\u00f4\7P\2\2\u00f4\u00f5\7V\2\2"+
		"\u00f5\6\3\2\2\2\u00f6\u00f7\7H\2\2\u00f7\u00f8\7T\2\2\u00f8\u00f9\7Q"+
		"\2\2\u00f9\u00fa\7O\2\2\u00fa\b\3\2\2\2\u00fb\u00fc\7w\2\2\u00fc\u00fd"+
		"\7u\2\2\u00fd\u00fe\7g\2\2\u00fe\n\3\2\2\2\u00ff\u0100\7e\2\2\u0100\u0101"+
		"\7j\2\2\u0101\u0102\7c\2\2\u0102\u0103\7t\2\2\u0103\f\3\2\2\2\u0104\u0105"+
		"\7c\2\2\u0105\u0106\7n\2\2\u0106\u0107\7v\2\2\u0107\u0108\7g\2\2\u0108"+
		"\u0109\7t\2\2\u0109\16\3\2\2\2\u010a\u010b\7u\2\2\u010b\u010c\7g\2\2\u010c"+
		"\u010d\7v\2\2\u010d\20\3\2\2\2\u010e\u010f\7h\2\2\u010f\u0110\7n\2\2\u0110"+
		"\u0111\7q\2\2\u0111\u0112\7c\2\2\u0112\u0113\7v\2\2\u0113\22\3\2\2\2\u0114"+
		"\u0115\7T\2\2\u0115\u0116\7G\2\2\u0116\u0117\7H\2\2\u0117\u0118\7G\2\2"+
		"\u0118\u0119\7T\2\2\u0119\u011a\7G\2\2\u011a\u011b\7P\2\2\u011b\u011c"+
		"\7E\2\2\u011c\u011d\7G\2\2\u011d\u011e\7U\2\2\u011e\24\3\2\2\2\u011f\u0120"+
		"\7q\2\2\u0120\u0121\7t\2\2\u0121\u0122\7f\2\2\u0122\u0123\7g\2\2\u0123"+
		"\u0124\7t\2\2\u0124\26\3\2\2\2\u0125\u0126\7k\2\2\u0126\u0127\7p\2\2\u0127"+
		"\u0128\7u\2\2\u0128\u0129\7g\2\2\u0129\u012a\7t\2\2\u012a\u012b\7v\2\2"+
		"\u012b\30\3\2\2\2\u012c\u012d\7?\2\2\u012d\32\3\2\2\2\u012e\u012f\7f\2"+
		"\2\u012f\u0130\7g\2\2\u0130\u0131\7n\2\2\u0131\u0132\7g\2\2\u0132\u0133"+
		"\7v\2\2\u0133\u0134\7g\2\2\u0134\34\3\2\2\2\u0135\u0136\7e\2\2\u0136\u0137"+
		"\7j\2\2\u0137\u0138\7g\2\2\u0138\u0139\7e\2\2\u0139\u013a\7m\2\2\u013a"+
		"\36\3\2\2\2\u013b\u013c\7f\2\2\u013c\u013d\7c\2\2\u013d\u013e\7v\2\2\u013e"+
		"\u013f\7c\2\2\u013f\u0140\7d\2\2\u0140\u0141\7c\2\2\u0141\u0142\7u\2\2"+
		"\u0142\u0143\7g\2\2\u0143 \3\2\2\2\u0144\u0145\7k\2\2\u0145\u0146\7p\2"+
		"\2\u0146\u0147\7v\2\2\u0147\"\3\2\2\2\u0148\u0149\7p\2\2\u0149\u014a\7"+
		"w\2\2\u014a\u014b\7n\2\2\u014b\u014c\7n\2\2\u014c$\3\2\2\2\u014d\u014e"+
		"\7*\2\2\u014e&\3\2\2\2\u014f\u0150\7v\2\2\u0150\u0151\7c\2\2\u0151\u0152"+
		"\7d\2\2\u0152\u0153\7n\2\2\u0153\u0154\7g\2\2\u0154\u0155\7u\2\2\u0155"+
		"(\3\2\2\2\u0156\u0157\7e\2\2\u0157\u0158\7q\2\2\u0158\u0159\7n\2\2\u0159"+
		"\u015a\7w\2\2\u015a\u015b\7o\2\2\u015b\u015c\7p\2\2\u015c*\3\2\2\2\u015d"+
		"\u015e\7.\2\2\u015e,\3\2\2\2\u015f\u0160\7r\2\2\u0160\u0161\7t\2\2\u0161"+
		"\u0162\7k\2\2\u0162\u0163\7o\2\2\u0163\u0164\7c\2\2\u0164\u0165\7t\2\2"+
		"\u0165\u0166\7{\2\2\u0166.\3\2\2\2\u0167\u0168\7E\2\2\u0168\u0169\7Q\2"+
		"\2\u0169\u016a\7N\2\2\u016a\u016b\7W\2\2\u016b\u016c\7O\2\2\u016c\u016d"+
		"\7P\2\2\u016d\u016e\7U\2\2\u016e\60\3\2\2\2\u016f\u0170\7R\2\2\u0170\u0171"+
		"\7T\2\2\u0171\u0172\7K\2\2\u0172\u0173\7O\2\2\u0173\u0174\7C\2\2\u0174"+
		"\u0175\7T\2\2\u0175\u0176\7[\2\2\u0176\62\3\2\2\2\u0177\u0178\7X\2\2\u0178"+
		"\u0179\7C\2\2\u0179\u017a\7N\2\2\u017a\u017b\7W\2\2\u017b\u017c\7G\2\2"+
		"\u017c\u017d\7U\2\2\u017d\64\3\2\2\2\u017e\u017f\7C\2\2\u017f\u0180\7"+
		"U\2\2\u0180\u0181\7E\2\2\u0181\66\3\2\2\2\u0182\u0183\7E\2\2\u0183\u0184"+
		"\7J\2\2\u0184\u0185\7G\2\2\u0185\u0186\7E\2\2\u0186\u0187\7M\2\2\u0187"+
		"8\3\2\2\2\u0188\u0189\7W\2\2\u0189\u018a\7R\2\2\u018a\u018b\7F\2\2\u018b"+
		"\u018c\7C\2\2\u018c\u018d\7V\2\2\u018d\u018e\7G\2\2\u018e:\3\2\2\2\u018f"+
		"\u0190\7F\2\2\u0190\u0191\7G\2\2\u0191\u0192\7N\2\2\u0192\u0193\7G\2\2"+
		"\u0193\u0194\7V\2\2\u0194\u0195\7G\2\2\u0195<\3\2\2\2\u0196\u0197\7e\2"+
		"\2\u0197\u0198\7q\2\2\u0198\u0199\7n\2\2\u0199\u019a\7w\2\2\u019a\u019b"+
		"\7o\2\2\u019b\u019c\7p\2\2\u019c\u019d\7u\2\2\u019d>\3\2\2\2\u019e\u019f"+
		"\7@\2\2\u019f\u01a0\7?\2\2\u01a0@\3\2\2\2\u01a1\u01a2\7>\2\2\u01a2B\3"+
		"\2\2\2\u01a3\u01a4\7V\2\2\u01a4\u01a5\7C\2\2\u01a5\u01a6\7D\2\2\u01a6"+
		"\u01a7\7N\2\2\u01a7\u01a8\7G\2\2\u01a8\u01a9\7U\2\2\u01a9D\3\2\2\2\u01aa"+
		"\u01ab\7f\2\2\u01ab\u01ac\7g\2\2\u01ac\u01ad\7u\2\2\u01ad\u01ae\7e\2\2"+
		"\u01aeF\3\2\2\2\u01af\u01b0\7f\2\2\u01b0\u01b1\7c\2\2\u01b1\u01b2\7v\2"+
		"\2\u01b2\u01b3\7g\2\2\u01b3H\3\2\2\2\u01b4\u01b5\7>\2\2\u01b5\u01b6\7"+
		"@\2\2\u01b6J\3\2\2\2\u01b7\u01b8\7t\2\2\u01b8\u01b9\7g\2\2\u01b9\u01ba"+
		"\7p\2\2\u01ba\u01bb\7c\2\2\u01bb\u01bc\7o\2\2\u01bc\u01bd\7g\2\2\u01bd"+
		"L\3\2\2\2\u01be\u01bf\7K\2\2\u01bf\u01c0\7P\2\2\u01c0\u01c1\7U\2\2\u01c1"+
		"\u01c2\7G\2\2\u01c2\u01c3\7T\2\2\u01c3\u01c4\7V\2\2\u01c4N\3\2\2\2\u01c5"+
		"\u01c6\7P\2\2\u01c6\u01c7\7W\2\2\u01c7\u01c8\7N\2\2\u01c8\u01c9\7N\2\2"+
		"\u01c9P\3\2\2\2\u01ca\u01cb\7U\2\2\u01cb\u01cc\7J\2\2\u01cc\u01cd\7Q\2"+
		"\2\u01cd\u01ce\7Y\2\2\u01ceR\3\2\2\2\u01cf\u01d0\7E\2\2\u01d0\u01d1\7"+
		"T\2\2\u01d1\u01d2\7G\2\2\u01d2\u01d3\7C\2\2\u01d3\u01d4\7V\2\2\u01d4\u01d5"+
		"\7G\2\2\u01d5T\3\2\2\2\u01d6\u01d7\7E\2\2\u01d7\u01d8\7Q\2\2\u01d8\u01d9"+
		"\7N\2\2\u01d9\u01da\7W\2\2\u01da\u01db\7O\2\2\u01db\u01dc\7P\2\2\u01dc"+
		"V\3\2\2\2\u01dd\u01de\7V\2\2\u01de\u01df\7C\2\2\u01df\u01e0\7D\2\2\u01e0"+
		"\u01e1\7N\2\2\u01e1\u01e2\7G\2\2\u01e2X\3\2\2\2\u01e3\u01e4\7F\2\2\u01e4"+
		"\u01e5\7C\2\2\u01e5\u01e6\7V\2\2\u01e6\u01e7\7C\2\2\u01e7\u01e8\7D\2\2"+
		"\u01e8\u01e9\7C\2\2\u01e9\u01ea\7U\2\2\u01ea\u01eb\7G\2\2\u01eb\u01ec"+
		"\7U\2\2\u01ecZ\3\2\2\2\u01ed\u01ee\7D\2\2\u01ee\u01ef\7[\2\2\u01ef\\\3"+
		"\2\2\2\u01f0\u01f1\7h\2\2\u01f1\u01f2\7q\2\2\u01f2\u01f3\7t\2\2\u01f3"+
		"\u01f4\7g\2\2\u01f4\u01f5\7k\2\2\u01f5\u01f6\7i\2\2\u01f6\u01f7\7p\2\2"+
		"\u01f7^\3\2\2\2\u01f8\u01f9\7d\2\2\u01f9\u01fa\7{\2\2\u01fa`\3\2\2\2\u01fb"+
		"\u01fc\7Y\2\2\u01fc\u01fd\7J\2\2\u01fd\u01fe\7G\2\2\u01fe\u01ff\7T\2\2"+
		"\u01ff\u0200\7G\2\2\u0200b\3\2\2\2\u0201\u0202\7e\2\2\u0202\u0203\7t\2"+
		"\2\u0203\u0204\7g\2\2\u0204\u0205\7c\2\2\u0205\u0206\7v\2\2\u0206\u0207"+
		"\7g\2\2\u0207d\3\2\2\2\u0208\u0209\7f\2\2\u0209\u020a\7c\2\2\u020a\u020b"+
		"\7v\2\2\u020b\u020c\7c\2\2\u020c\u020d\7d\2\2\u020d\u020e\7c\2\2\u020e"+
		"\u020f\7u\2\2\u020f\u0210\7g\2\2\u0210\u0211\7u\2\2\u0211f\3\2\2\2\u0212"+
		"\u0213\7K\2\2\u0213\u0214\7P\2\2\u0214\u0215\7V\2\2\u0215\u0216\7Q\2\2"+
		"\u0216h\3\2\2\2\u0217\u0218\7=\2\2\u0218j\3\2\2\2\u0219\u021a\7C\2\2\u021a"+
		"\u021b\7P\2\2\u021b\u021c\7F\2\2\u021cl\3\2\2\2\u021d\u021e\7P\2\2\u021e"+
		"\u021f\7Q\2\2\u021f\u0220\7V\2\2\u0220n\3\2\2\2\u0221\u0222\7c\2\2\u0222"+
		"\u0223\7u\2\2\u0223\u0224\7e\2\2\u0224p\3\2\2\2\u0225\u0226\7>\2\2\u0226"+
		"\u0227\7?\2\2\u0227r\3\2\2\2\u0228\u0229\7C\2\2\u0229\u022a\7F\2\2\u022a"+
		"\u022b\7F\2\2\u022bt\3\2\2\2\u022c\u022d\7h\2\2\u022d\u022e\7t\2\2\u022e"+
		"\u022f\7q\2\2\u022f\u0230\7o\2\2\u0230v\3\2\2\2\u0231\u0232\7e\2\2\u0232"+
		"\u0233\7q\2\2\u0233\u0234\7p\2\2\u0234\u0235\7u\2\2\u0235\u0236\7v\2\2"+
		"\u0236\u0237\7t\2\2\u0237\u0238\7c\2\2\u0238\u0239\7k\2\2\u0239\u023a"+
		"\7p\2\2\u023a\u023b\7v\2\2\u023bx\3\2\2\2\u023c\u023d\7M\2\2\u023d\u023e"+
		"\7G\2\2\u023e\u023f\7[\2\2\u023fz\3\2\2\2\u0240\u0241\7x\2\2\u0241\u0242"+
		"\7c\2\2\u0242\u0243\7n\2\2\u0243\u0244\7w\2\2\u0244\u0245\7g\2\2\u0245"+
		"\u0246\7u\2\2\u0246|\3\2\2\2\u0247\u0248\7Q\2\2\u0248\u0249\7T\2\2\u0249"+
		"\u024a\7F\2\2\u024a\u024b\7G\2\2\u024b\u024c\7T\2\2\u024c~\3\2\2\2\u024d"+
		"\u024e\7,\2\2\u024e\u0080\3\2\2\2\u024f\u0250\7U\2\2\u0250\u0251\7G\2"+
		"\2\u0251\u0252\7N\2\2\u0252\u0253\7G\2\2\u0253\u0254\7E\2\2\u0254\u0255"+
		"\7V\2\2\u0255\u0082\3\2\2\2\u0256\u0257\7w\2\2\u0257\u0258\7r\2\2\u0258"+
		"\u0259\7f\2\2\u0259\u025a\7c\2\2\u025a\u025b\7v\2\2\u025b\u025c\7g\2\2"+
		"\u025c\u0084\3\2\2\2\u025d\u025e\7\60\2\2\u025e\u0086\3\2\2\2\u025f\u0260"+
		"\7F\2\2\u0260\u0261\7T\2\2\u0261\u0262\7Q\2\2\u0262\u0263\7R\2\2\u0263"+
		"\u0088\3\2\2\2\u0264\u0265\7c\2\2\u0265\u0266\7f\2\2\u0266\u0267\7f\2"+
		"\2\u0267\u008a\3\2\2\2\u0268\u0269\7m\2\2\u0269\u026a\7g\2\2\u026a\u026b"+
		"\7{\2\2\u026b\u008c\3\2\2\2\u026c\u026d\7F\2\2\u026d\u026e\7C\2\2\u026e"+
		"\u026f\7V\2\2\u026f\u0270\7G\2\2\u0270\u008e\3\2\2\2\u0271\u0272\7K\2"+
		"\2\u0272\u0273\7P\2\2\u0273\u0274\7V\2\2\u0274\u0090\3\2\2\2\u0275\u0276"+
		"\7V\2\2\u0276\u0277\7Q\2\2\u0277\u0092\3\2\2\2\u0278\u0279\7T\2\2\u0279"+
		"\u027a\7G\2\2\u027a\u027b\7P\2\2\u027b\u027c\7C\2\2\u027c\u027d\7O\2\2"+
		"\u027d\u027e\7G\2\2\u027e\u0094\3\2\2\2\u027f\u0280\7v\2\2\u0280\u0281"+
		"\7q\2\2\u0281\u0096\3\2\2\2\u0282\u0283\7E\2\2\u0283\u0284\7J\2\2\u0284"+
		"\u0285\7C\2\2\u0285\u0286\7T\2\2\u0286\u0098\3\2\2\2\u0287\u0288\7f\2"+
		"\2\u0288\u0289\7t\2\2\u0289\u028a\7q\2\2\u028a\u028b\7r\2\2\u028b\u009a"+
		"\3\2\2\2\u028c\u028d\7u\2\2\u028d\u028e\7g\2\2\u028e\u028f\7n\2\2\u028f"+
		"\u0290\7g\2\2\u0290\u0291\7e\2\2\u0291\u0292\7v\2\2\u0292\u009c\3\2\2"+
		"\2\u0293\u0294\7u\2\2\u0294\u0295\7j\2\2\u0295\u0296\7q\2\2\u0296\u0297"+
		"\7y\2\2\u0297\u009e\3\2\2\2\u0298\u0299\7@\2\2\u0299\u00a0\3\2\2\2\u029a"+
		"\u029b\7Q\2\2\u029b\u029c\7T\2\2\u029c\u00a2\3\2\2\2\u029d\u029e\7q\2"+
		"\2\u029e\u029f\7t\2\2\u029f\u00a4\3\2\2\2\u02a0\u02a1\7C\2\2\u02a1\u02a2"+
		"\7N\2\2\u02a2\u02a3\7V\2\2\u02a3\u02a4\7G\2\2\u02a4\u02a5\7T\2\2\u02a5"+
		"\u00a6\3\2\2\2\u02a6\u02a7\7y\2\2\u02a7\u02a8\7j\2\2\u02a8\u02a9\7g\2"+
		"\2\u02a9\u02aa\7t\2\2\u02aa\u02ab\7g\2\2\u02ab\u00a8\3\2\2\2\u02ac\u02ad"+
		"\7W\2\2\u02ad\u02ae\7U\2\2\u02ae\u02af\7G\2\2\u02af\u00aa\3\2\2\2\u02b0"+
		"\u02b1\7v\2\2\u02b1\u02b2\7c\2\2\u02b2\u02b3\7d\2\2\u02b3\u02b4\7n\2\2"+
		"\u02b4\u02b5\7g\2\2\u02b5\u00ac\3\2\2\2\u02b6\u02b7\7k\2\2\u02b7\u02b8"+
		"\7p\2\2\u02b8\u02b9\7v\2\2\u02b9\u02ba\7q\2\2\u02ba\u00ae\3\2\2\2\u02bb"+
		"\u02bc\7H\2\2\u02bc\u02bd\7N\2\2\u02bd\u02be\7Q\2\2\u02be\u02bf\7C\2\2"+
		"\u02bf\u02c0\7V\2\2\u02c0\u00b0\3\2\2\2\u02c1\u02c2\7+\2\2\u02c2\u00b2"+
		"\3\2\2\2\u02c3\u02c4\7c\2\2\u02c4\u02c5\7p\2\2\u02c5\u02c6\7f\2\2\u02c6"+
		"\u00b4\3\2\2\2\u02c7\u02c8\7F\2\2\u02c8\u02c9\7G\2\2\u02c9\u02ca\7U\2"+
		"\2\u02ca\u02cb\7E\2\2\u02cb\u00b6\3\2\2\2\u02cc\u02cd\7F\2\2\u02cd\u02ce"+
		"\7C\2\2\u02ce\u02cf\7V\2\2\u02cf\u02d0\7C\2\2\u02d0\u02d1\7D\2\2\u02d1"+
		"\u02d2\7C\2\2\u02d2\u02d3\7U\2\2\u02d3\u02d4\7G\2\2\u02d4\u00b8\3\2\2"+
		"\2\u02d5\u02d6\7p\2\2\u02d6\u02d7\7q\2\2\u02d7\u02d8\7v\2\2\u02d8\u00ba"+
		"\3\2\2\2\u02d9\u02da\7U\2\2\u02da\u02db\7G\2\2\u02db\u02dc\7V\2\2\u02dc"+
		"\u00bc\3\2\2\2\u02dd\u02de\7t\2\2\u02de\u02df\7g\2\2\u02df\u02e0\7h\2"+
		"\2\u02e0\u02e1\7g\2\2\u02e1\u02e2\7t\2\2\u02e2\u02e3\7g\2\2\u02e3\u02e4"+
		"\7p\2\2\u02e4\u02e5\7e\2\2\u02e5\u02e6\7g\2\2\u02e6\u02e7\7u\2\2\u02e7"+
		"\u00be\3\2\2\2\u02e8\u02e9\7d\2\2\u02e9\u02ea\7q\2\2\u02ea\u02eb\7q\2"+
		"\2\u02eb\u02ec\7n\2\2\u02ec\u02ed\7g\2\2\u02ed\u02ee\7c\2\2\u02ee\u02ef"+
		"\7p\2\2\u02ef\u00c0\3\2\2\2\u02f0\u02f1\7f\2\2\u02f1\u02f2\7c\2\2\u02f2"+
		"\u02f3\7v\2\2\u02f3\u02f4\7g\2\2\u02f4\u02f5\7v\2\2\u02f5\u02f6\7k\2\2"+
		"\u02f6\u02f7\7o\2\2\u02f7\u02f8\7g\2\2\u02f8\u00c2\3\2\2\2\u02f9\u02fa"+
		"\t\2\2\2\u02fa\u00c4\3\2\2\2\u02fb\u02fc\4\62;\2\u02fc\u00c6\3\2\2\2\u02fd"+
		"\u02ff\t\3\2\2\u02fe\u02fd\3\2\2\2\u02ff\u00c8\3\2\2\2\u0300\u0301\5\u00c5"+
		"c\2\u0301\u0302\5\u00c5c\2\u0302\u00ca\3\2\2\2\u0303\u0304\5\u00c5c\2"+
		"\u0304\u0305\5\u00c9e\2\u0305\u00cc\3\2\2\2\u0306\u0307\5\u00c5c\2\u0307"+
		"\u0308\5\u00cbf\2\u0308\u00ce\3\2\2\2\u0309\u030a\5\u00cdg\2\u030a\u00d0"+
		"\3\2\2\2\u030b\u030e\5\u00c5c\2\u030c\u030e\5\u00c9e\2\u030d\u030b\3\2"+
		"\2\2\u030d\u030c\3\2\2\2\u030e\u00d2\3\2\2\2\u030f\u0312\5\u00c5c\2\u0310"+
		"\u0312\5\u00c9e\2\u0311\u030f\3\2\2\2\u0311\u0310\3\2\2\2\u0312\u00d4"+
		"\3\2\2\2\u0313\u0318\5\u00c3b\2\u0314\u0317\5\u00c3b\2\u0315\u0317\5\u00c5"+
		"c\2\u0316\u0314\3\2\2\2\u0316\u0315\3\2\2\2\u0317\u031a\3\2\2\2\u0318"+
		"\u0316\3\2\2\2\u0318\u0319\3\2\2\2\u0319\u00d6\3\2\2\2\u031a\u0318\3\2"+
		"\2\2\u031b\u031d\7/\2\2\u031c\u031b\3\2\2\2\u031c\u031d\3\2\2\2\u031d"+
		"\u031e\3\2\2\2\u031e\u0322\5\u00c5c\2\u031f\u0321\5\u00c5c\2\u0320\u031f"+
		"\3\2\2\2\u0321\u0324\3\2\2\2\u0322\u0320\3\2\2\2\u0322\u0323\3\2\2\2\u0323"+
		"\u00d8\3\2\2\2\u0324\u0322\3\2\2\2\u0325\u0327\7/\2\2\u0326\u0325\3\2"+
		"\2\2\u0326\u0327\3\2\2\2\u0327\u0328\3\2\2\2\u0328\u032c\5\u00c5c\2\u0329"+
		"\u032b\5\u00c5c\2\u032a\u0329\3\2\2\2\u032b\u032e\3\2\2\2\u032c\u032a"+
		"\3\2\2\2\u032c\u032d\3\2\2\2\u032d\u032f\3\2\2\2\u032e\u032c\3\2\2\2\u032f"+
		"\u0333\7\60\2\2\u0330\u0332\5\u00c5c\2\u0331\u0330\3\2\2\2\u0332\u0335"+
		"\3\2\2\2\u0333\u0331\3\2\2\2\u0333\u0334\3\2\2\2\u0334\u00da\3\2\2\2\u0335"+
		"\u0333\3\2\2\2\u0336\u0337\7)\2\2\u0337\u0338\5\u00cfh\2\u0338\u0339\7"+
		"/\2\2\u0339\u033a\5\u00d1i\2\u033a\u033b\7/\2\2\u033b\u033c\5\u00d3j\2"+
		"\u033c\u033d\7)\2\2\u033d\u00dc\3\2\2\2\u033e\u0342\7)\2\2\u033f\u0341"+
		"\5\u00c7d\2\u0340\u033f\3\2\2\2\u0341\u0344\3\2\2\2\u0342\u0340\3\2\2"+
		"\2\u0342\u0343\3\2\2\2\u0343\u0345\3\2\2\2\u0344\u0342\3\2\2\2\u0345\u0346"+
		"\7)\2\2\u0346\u00de\3\2\2\2\u0347\u0349\t\4\2\2\u0348\u0347\3\2\2\2\u0349"+
		"\u034a\3\2\2\2\u034a\u0348\3\2\2\2\u034a\u034b\3\2\2\2\u034b\u034c\3\2"+
		"\2\2\u034c\u034d\bp\2\2\u034d\u00e0\3\2\2\2\u034e\u034f\7\61\2\2\u034f"+
		"\u0350\7\61\2\2\u0350\u0354\3\2\2\2\u0351\u0353\n\5\2\2\u0352\u0351\3"+
		"\2\2\2\u0353\u0356\3\2\2\2\u0354\u0352\3\2\2\2\u0354\u0355\3\2\2\2\u0355"+
		"\u0358\3\2\2\2\u0356\u0354\3\2\2\2\u0357\u0359\7\17\2\2\u0358\u0357\3"+
		"\2\2\2\u0358\u0359\3\2\2\2\u0359\u035a\3\2\2\2\u035a\u0367\7\f\2\2\u035b"+
		"\u035c\7\61\2\2\u035c\u035d\7,\2\2\u035d\u0361\3\2\2\2\u035e\u0360\13"+
		"\2\2\2\u035f\u035e\3\2\2\2\u0360\u0363\3\2\2\2\u0361\u0362\3\2\2\2\u0361"+
		"\u035f\3\2\2\2\u0362\u0364\3\2\2\2\u0363\u0361\3\2\2\2\u0364\u0365\7,"+
		"\2\2\u0365\u0367\7\61\2\2\u0366\u034e\3\2\2\2\u0366\u035b\3\2\2\2\u0367"+
		"\u0368\3\2\2\2\u0368\u0369\bq\2\2\u0369\u00e2\3\2\2\2\23\2\u02fe\u030d"+
		"\u0311\u0316\u0318\u031c\u0322\u0326\u032c\u0333\u0342\u034a\u0354\u0358"+
		"\u0361\u0366\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}