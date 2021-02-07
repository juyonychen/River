grammar RuleSQL;
// The keywords or preserve words for alert rule model
SELECT: S E L E C T;

FROM: F R O M;
HAVING: H A V I N G;
WHERE: W H E R E;
ORDER: O R D E R;
SPLITBY: S P L I T B Y;
BY: B Y;
GROUP: G R O U P;
INTO: I N T O;
AS: A S;

NULL: N U L L;
NOT: N O T | '!';
DISTINCT: D I S T I N C T;
DISTINCTROW: D I S T I N C T R O W;

TABLE: T A B L E;
VIEW: V I E W;
IF: I F;

ALL: A L L;
UNION: U N I O N;
EXCEPT: E X C E P T;
INTERSECT: I N T E R S E C T;
MINUS: M I N U S;
INNER: I N N E R;
LEFT: L E F T;
RIGHT: R I G H T;
FULL: F U L L;
OUTER: O U T E R;
JOIN: J O I N;
ON: O N;
SCHEMA: S C H E M A;
CAST: C A S T;
COLUMN: C O L U M N;
USE: U S E;
DATABASE: D A T A B A S E;
TO: T O;

AND: A N D | '&&';
OR: O R | '||';
XOR: X O R;
CASE: C A S E;
WHEN: W H E N;
THEN: T H E N;
ELSE: E L S E;
END: E N D;
EXISTS: E X I S T S;
IN: I N;

ASC: A S C;
DESC: D E S C;
IS: I S;
LIKE: L I K E;
BETWEEN: B E T W E E N;

TRUE: T R U E;
FALSE: F A L S E;
//SPLIT: S P L I T;
NOW: N O W;
//SSTR: S S T R;
//SINT: S I N T;
//SDOUBLE: S D O U B L E;

LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
LBRACKET: '[';
RBRACKET: ']';
SEMI: ';';
COMMA: ',';
DOT: '.';
DOTDOT: '..';
DOTDOTDOT: '..,';
EQ: '=';
GT: '>';
LT: '<';
BANGBANG: '!!';
TILDE: '~';
QUES: '?';
COLON: ': ';
COLONEQ: ': =';
EQEQ: '==';
LTEQ: '<=';
LTEQGT: '<=>';
LTGT: '<>';
GTEQ: '>=';
NEQ: '!=';
BANGGT: '!>';
BANGLT: '!<';
BARBARSLASH: '||/';
BARSLASH: '|/';
PLUS: '+';
SUB: '-';
STAR: '*';
SLASH: '/';
MOD: '%';
AMP: '&';
BAR: '|';
CARET: '^';
LTLT: '<<';
GTGT: '>>';
MONKEYS_AT: '@';
POUND: '#';
QUOTES: '"'|'\'';
UNDERLINE: '_';

INT: DIGIT+;
FLOAT: DIGIT+ '.' DIGIT* | '.' DIGIT+;
NEG_INT: SUB INT;
NEG_FLOAT: SUB FLOAT;
SYSDATE: S Y S D A T E;
//FJLIST: F J L I S T;
//COUNT: C O U N T;
//SUM: S U M;
//AVG: A V G;
STRING: '"' ( ~'"' | '""' )* '"';
TABLENAME: ('json'|'split'|'jsonList'|'splitList');
ID: ID_LETTER (ID_LETTER | DIGIT |UNDERLINE)*;


VARIABLE: QUES ID;
fragment ID_LETTER: [a-zA-Z] ;
fragment DIGIT: [0-9];
fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];

WS: [ \r\n\t\u000C]+ -> channel(HIDDEN);



// The rule dsl for metric calculation
calculateStatement
:
    SELECT
        // Caculated metrics and meta data
        metrics
    FROM
    // splitmessage or jsonmessage else unsupport
        source
    (
    WHERE
            filterConditions
    )?
    (
    SPLITBY
            splitByExpr
    )?

EOF
;

filterConditions
    :filterCondition
    ;



filterCondition
    : LPAREN filterCondition RPAREN # lrExpr
	| left = filterCondition AND right = filterCondition # andOpr
	| left = filterCondition OR right = filterCondition # orOpr
	| basicBoolExpr # basicExpr
;

basicBoolExpr
:
    left = metricValue
    option =
    (
		EQ
		| EQEQ
		| GT
		| LT
		| GTEQ
		| LTEQ
		| NEQ
	)
	right = metricValue # compareExpr
	| left = metricValue option = inToken right = collection # inExpr
	| left = metricValue option = likeToken right = likeMessage # likeExpr
;

metricValue
    : LPAREN metricValue RPAREN # LRValue
    	| left = metricValue op =
    	(
    		STAR
    		| SLASH
    		| MOD
    	) right = metricValue # MulValue
    	| left = metricValue op =
    	(
    		PLUS
    		| SUB
    	) right = metricValue # AddValue
//    	| ID LPAREN metricValue RPAREN # AggregationValue
        |  identity # columnValue
;

metrics
    :metric(COMMA metric)*
;
inToken
:
    (NOT)? IN
;
likeToken
:
    LIKE
;
likeMessage
:
     identity
;
collection
:
    LPAREN identity (COMMA identity)* RPAREN
;
metric
    :metricValue(AS columnName = ID)*
;

identity
    :
      ID LPAREN aggParameters RPAREN #AggFunction
//    | ID LPAREN metricValue aggregateFunctionParameters RPAREN # AggregationValue
    | INT # intEle
    | UNDERLINE #aliasEle
    | FLOAT # floatEle
    | NEG_INT # negativeIntEle
    | NEG_FLOAT # negativeFloatELe
    | (FALSE | TRUE) #booleanEle
    | STRING # stringEle
    | ID # idEle
;


aggregateFunctionParameters
:
    // Make this length be optional, which would be query from the storageQueue
    (COMMA startOffset = INT)?
    (COMMA endOffset = INT)?
    (COMMA filterCondition)?
;
splitByExpr
    : splitByValue
    ;

splitByValue
    : STRING
    ;
source
    : fromtable
    ;
fromtable :
   TABLENAME (LPAREN tableParam = STRING RPAREN)? # fromtype
   ;
dateParameters
    :  ( dateFormat= STRING )?
    ;
aggParameters
    : (firstIndex = ID) | (firstIndex = INT)
    ;