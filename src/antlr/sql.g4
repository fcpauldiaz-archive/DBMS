/*
 * PD
 * UVG
 * 2016
 */

grammar sql;


//LEXER SPECIFICATION

BOOLEAN : 'boolean';
DATETIME: 'datetime';

fragment LETTER : ('a'..'z'|'A'..'Z' | '_') ;
fragment DIGIT :'0'..'9' ;
fragment ASCII : (' ' .. '+' ) | ('-' .. '~') | '\\' | '\'' | '\"' | '\t' | '\n' ;
fragment TWO_DIGITS   : DIGIT DIGIT ;
fragment THREE_DIGITS : DIGIT TWO_DIGITS ;
fragment FOUR_DIGITS  : DIGIT THREE_DIGITS ;
fragment YEAR      : FOUR_DIGITS ;                  
fragment MONTH     : DIGIT | TWO_DIGITS ;         
fragment DAY       : DIGIT | TWO_DIGITS ; 

//* \'
ID : LETTER ( LETTER | DIGIT )* ;
NUM : DIGIT (DIGIT)*;
FLOAT:  DIGIT (DIGIT)* ('.' (DIGIT)*);
DATE: '\'' YEAR '-' MONTH '-' DAY '\'';
CHAR :'\'' (ASCII)* '\'';

create: 'CREATE'|'create';

database: 'DATABASE'|'database';

database_plural: 'DATABASES' | 'databases';

table: 'TABLE'|'table';

drop: 'DROP'|'drop';

alter: 'ALTER'|'alter';

rename: 'RENAME' |'rename';

to: 'TO'|'to';

show: 'SHOW'|'show';

use: 'USE'|'use';

int_terminal: 'INT'|'int';

float_terminal: 'FLOAT'|'float';

char_name:  'CHAR'|'char';

char_terminal: char_name '(' NUM ')';

date_terminal: 'DATE'|'date';

constraint_terminal: 'CONSTRAINT'|'constraint';

primary: 'PRIMARY'|'primary';

key: 'KEY'|'key';

foreign: 'FOREIGN'|'foreign';

references: 'REFERENCES'|'references';

check: 'CHECK'|'check';

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
 
tables: 'TABLES'|'tables';

from: 'FROM'|'from';

add: 'ADD'|'add';


column_terminal: 'COLUMN'|'column';

column_terminal_plural: 'COLUMNS' | 'columns';

and: 'AND'|'and';  

or: 'OR'|'or';

not: 'NOT'|'not';
 

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
    |   insert_value
    |   update_value
    |   delete_value
    ;


schema_definition: create database ID ';';



table_definition: create table ID '(' (column_or_constraint) ')' ';';

column_or_constraint: (column | constraint)+ ;

drop_schema_statement: drop database ID ';';

alter_table_statement: alter table ID accion ';';

drop_table_statement: drop table ID ';';

alter_database_statement: alter database ID rename to ID ';' ;

 show_schema_statement: show database_plural ';';

use_schema_statement: use database ID ';';

column: (ID tipo_literal)  (',')? ;

tipo_literal: int_terminal | float_terminal | char_terminal | date_terminal ;

constraint: constraint_terminal constraintType ;



constraintType:
            ID primary key '(' id_list')' (',')?                                #constraintPrimaryKey                    
        |   ID foreign key  '(' id_list')' references ID '(' id_list ')' (',')? #constraintForeignKey
        |   ID check  '('ID exp (num_or_id) ')' (',')?                          #constraintCheck
        ;

num_or_id: NUM | ID;

id_list: ID (',' ID)*;

exp: logic | relational;

rename_table_statement: alter table ID rename to ID ';';



accion:
          add column_terminal ID tipo_literal (constraint)? #accionAddColumn
        | add constraint                                    #accionAddConstraint
        | drop column_terminal ID                           #accionDropColumn
        | drop constraint_terminal ID                       #accionDropConstraint
    ;




show_table_statement: show tables ';';

show_column_statement: show column_terminal_plural from ID ';';
         

insert_value: insert into ID (insert_column_names)?  values '(' list_values ')'  ';';

insert_column_names: '(' ID (',' ID)* ')';
          
logic: and | or | not;
relational: '<' | '<=' | '>' | '>=' | '<>' | '=' ;


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



              
 