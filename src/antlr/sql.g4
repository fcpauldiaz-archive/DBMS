/*
 * PD
 * UVG
 * 2016
 */

grammar sql;


//LEXER SPECIFICATION

INT :     'int' ;
CHAR :    'char' ;
BOOLEAN : 'boolean';
DATETIME: 'datetime';

fragment LETTER : ('a'..'z'|'A'..'Z') ;
fragment DIGIT :'0'..'9' ;
fragment ASCII : (' ' ..'~') | '\\' | '\'' | '\"' | '\t' | '\n' ;
fragment TWO_DIGITS   : DIGIT DIGIT ;
fragment THREE_DIGITS : DIGIT TWO_DIGITS ;
fragment FOUR_DIGITS  : DIGIT THREE_DIGITS ;
fragment YEAR      : FOUR_DIGITS ;                   // year
fragment MONTH       : DIGIT | TWO_DIGITS ;        // month of year.
fragment DAY       : DIGIT | TWO_DIGITS ; 

//* \'
ID : LETTER ( LETTER | DIGIT )* ;
NUM : DIGIT ( DIGIT )* ;
FLOAT: DIGIT ( DIGIT )* ('.' (DIGIT)* )? ;
Char : '\'' ASCII '\'';
DATE: YEAR '-' MONTH '-' DAY ;
 
 

WS : 
    [\t\r\n\f ]+ -> skip
    ;

COMMENT
    :   ( '//' ~[\r\n]* '\r'? '\n'	
        | '/*' .*? '*/'	
        ) -> skip
    ;


// PARSER SPECIFICATION

sql2003Parser 
	:	
            ( sql_executable_statement )+
	;

sql_executable_statement 

	:	sql_schema_statement 
	|	sql_data_statement
	;

sql_schema_statement  
	:	sql_schema_definition_statement 
	|	sql_schema_manipulation_statement       
	;

sql_schema_definition_statement  
	:	schema_definition 
	|	table_definition 
	;

sql_schema_manipulation_statement 
	:	drop_schema_statement
	|	alter_table_statement 
	|	drop_table_statement 
        |       alter_database_statement
        |       use_schema_statement
        |       show_schema_statement
        |       rename_table_statement
        |       show_table_statement
        |       show_column_statement
    ;
sql_data_statement  

	:	 
        select_value 
	;


schema_definition: create database ID ';';

create: 'CREATE'|'create';

database: 'DATABASE'|'database';

table: 'TABLE'|'table';

drop: 'DROP'|'drop';

alter: 'ALTER'|'alter';

rename: 'RENAME' |'rename';

to: 'TO'|'to';

show: 'SHOW'|'show';

use: 'USE'|'use';

int: 'INT'|'int';

float: 'FLOAT'|'float';

char: 'CHAR'|'char';

date: 'DATE'|'date';

constraint_terminal: 'CONSTRAINT'|'constraint';

table_definition: create table ID '(' (column)+ ')' ';';

drop_schema_statement: drop database ID ';';

alter_table_statement: alter table ID accion ';';

drop_table_statement: drop table ID ';';

alter_database_statement: alter database ID rename to ID ';' ;

 show_schema_statement: show database ';';

use_schema_statement: use database ID ';';

column: (ID tipo_literal | constraint) ',' ;

tipo_literal: int | float | char | date ;

constraint: constraint_terminal constraintType;

primary: 'PRIMARY'|'primary';

key: 'KEY'|'key';

foreign: 'FOREIGN'|'foreign';

references: 'REFERENCES'|'references';

check: 'CHECK'|'check';

constraintType:
            ID primary key '(' ID (',' ID)*')'
        |   ID foreign key  '(' ID (',' ID)*')' references ID '(' ID (',' ID)*')'
        |   ID check  '('ID exp ID ')'
        ;

exp: logic | relational;

rename_table_statement: alter table ID rename to ID ';';

add: 'ADD'|'add';

drop: 'DROP'|'drop';

column_terminal: 'COLUMN'|'column';

accion:
          add column ID tipo_literal (constraint)
        | add constraint
        | drop column_terminal ID 
        | drop constraint_terminal ID
    ;

show: 'SHOW'|'show';

tables: 'TABLES'|'tables';

from: 'FROM'|'from';

show_table_statement: show tables ';';

show_column_statement: show column_terminal from ID ';';
         
and: 'AND'|'and';  

or: 'OR'|'or';

not: 'NOT'|'not';
          
logic: and | or | not;
relational: '<' | '<=' | '>' | '>=' | '<>' | '=' ;

insert: 'INSERT'|'insert';

update: 'UPDATE'|'update';

delete: 'DELETE'|'delete';

select: 'SELECT'|'select';

set: 'SET'|'set';

where: 'WHERE'|'where';

order: 'ORDER'|'order';

by: 'BY' | 'by';

asc: 'ASC'|'asc';

desc: 'DESC'|'desc';

into: 'INTO'|'into';

values: 'VALUES'|'values';


insert_value: insert into (column) values (list_values) ';';

update_value: update ID set column '=' value where condition ';' ;

delete_value: delete from ID where condition ';' ;

select_value: select ('*' | ID (',' ID)* ) from ID where condition  (order by (asc | desc))? ';';

              
condition: ID '=' ID ;         
              
list_values : (value (',' (value))* ) ;
         
value: tipo;
              
tipo: literal;


literal:  
        int_literal
    |   float_literal
    |   date_literal
    |   char_literal
    ;

int_literal: NUM;
float_literal: FLOAT;
date_literal: DATE;
char_literal: CHAR;



              
 