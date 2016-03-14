// Generated from sql.g4 by ANTLR 4.5.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sqlParser}.
 */
public interface sqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql2003Parser}.
	 * @param ctx the parse tree
	 */
	void enterSql2003Parser(sqlParser.Sql2003ParserContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql2003Parser}.
	 * @param ctx the parse tree
	 */
	void exitSql2003Parser(sqlParser.Sql2003ParserContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_executable_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_executable_statement(sqlParser.Sql_executable_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_executable_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_executable_statement(sqlParser.Sql_executable_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_schema_statement(sqlParser.Sql_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_schema_statement(sqlParser.Sql_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_schema_definition_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_schema_definition_statement(sqlParser.Sql_schema_definition_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_schema_definition_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_schema_definition_statement(sqlParser.Sql_schema_definition_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_schema_manipulation_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_schema_manipulation_statement(sqlParser.Sql_schema_manipulation_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_schema_manipulation_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_schema_manipulation_statement(sqlParser.Sql_schema_manipulation_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_data_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_data_statement(sqlParser.Sql_data_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_data_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_data_statement(sqlParser.Sql_data_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#schema_definition}.
	 * @param ctx the parse tree
	 */
	void enterSchema_definition(sqlParser.Schema_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#schema_definition}.
	 * @param ctx the parse tree
	 */
	void exitSchema_definition(sqlParser.Schema_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#table_definition}.
	 * @param ctx the parse tree
	 */
	void enterTable_definition(sqlParser.Table_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#table_definition}.
	 * @param ctx the parse tree
	 */
	void exitTable_definition(sqlParser.Table_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#drop_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterDrop_schema_statement(sqlParser.Drop_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#drop_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitDrop_schema_statement(sqlParser.Drop_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterAlter_table_statement(sqlParser.Alter_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitAlter_table_statement(sqlParser.Alter_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#drop_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterDrop_table_statement(sqlParser.Drop_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#drop_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitDrop_table_statement(sqlParser.Drop_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_database_statement}.
	 * @param ctx the parse tree
	 */
	void enterAlter_database_statement(sqlParser.Alter_database_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_database_statement}.
	 * @param ctx the parse tree
	 */
	void exitAlter_database_statement(sqlParser.Alter_database_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterShow_schema_statement(sqlParser.Show_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitShow_schema_statement(sqlParser.Show_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#use_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterUse_schema_statement(sqlParser.Use_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#use_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitUse_schema_statement(sqlParser.Use_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(sqlParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(sqlParser.ColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#tipo_literal}.
	 * @param ctx the parse tree
	 */
	void enterTipo_literal(sqlParser.Tipo_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#tipo_literal}.
	 * @param ctx the parse tree
	 */
	void exitTipo_literal(sqlParser.Tipo_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(sqlParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(sqlParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#constraintType}.
	 * @param ctx the parse tree
	 */
	void enterConstraintType(sqlParser.ConstraintTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#constraintType}.
	 * @param ctx the parse tree
	 */
	void exitConstraintType(sqlParser.ConstraintTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(sqlParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(sqlParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#rename_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterRename_table_statement(sqlParser.Rename_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#rename_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitRename_table_statement(sqlParser.Rename_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#accion}.
	 * @param ctx the parse tree
	 */
	void enterAccion(sqlParser.AccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#accion}.
	 * @param ctx the parse tree
	 */
	void exitAccion(sqlParser.AccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterShow_table_statement(sqlParser.Show_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitShow_table_statement(sqlParser.Show_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_column_statement}.
	 * @param ctx the parse tree
	 */
	void enterShow_column_statement(sqlParser.Show_column_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_column_statement}.
	 * @param ctx the parse tree
	 */
	void exitShow_column_statement(sqlParser.Show_column_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#logic}.
	 * @param ctx the parse tree
	 */
	void enterLogic(sqlParser.LogicContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#logic}.
	 * @param ctx the parse tree
	 */
	void exitLogic(sqlParser.LogicContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#relational}.
	 * @param ctx the parse tree
	 */
	void enterRelational(sqlParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#relational}.
	 * @param ctx the parse tree
	 */
	void exitRelational(sqlParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#insert_value}.
	 * @param ctx the parse tree
	 */
	void enterInsert_value(sqlParser.Insert_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#insert_value}.
	 * @param ctx the parse tree
	 */
	void exitInsert_value(sqlParser.Insert_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#update_value}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_value(sqlParser.Update_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#update_value}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_value(sqlParser.Update_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#delete_value}.
	 * @param ctx the parse tree
	 */
	void enterDelete_value(sqlParser.Delete_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#delete_value}.
	 * @param ctx the parse tree
	 */
	void exitDelete_value(sqlParser.Delete_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#select_value}.
	 * @param ctx the parse tree
	 */
	void enterSelect_value(sqlParser.Select_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#select_value}.
	 * @param ctx the parse tree
	 */
	void exitSelect_value(sqlParser.Select_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(sqlParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(sqlParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#list_values}.
	 * @param ctx the parse tree
	 */
	void enterList_values(sqlParser.List_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#list_values}.
	 * @param ctx the parse tree
	 */
	void exitList_values(sqlParser.List_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(sqlParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(sqlParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(sqlParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(sqlParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(sqlParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(sqlParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void enterInt_literal(sqlParser.Int_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void exitInt_literal(sqlParser.Int_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void enterFloat_literal(sqlParser.Float_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void exitFloat_literal(sqlParser.Float_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#date_literal}.
	 * @param ctx the parse tree
	 */
	void enterDate_literal(sqlParser.Date_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#date_literal}.
	 * @param ctx the parse tree
	 */
	void exitDate_literal(sqlParser.Date_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#char_literal}.
	 * @param ctx the parse tree
	 */
	void enterChar_literal(sqlParser.Char_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#char_literal}.
	 * @param ctx the parse tree
	 */
	void exitChar_literal(sqlParser.Char_literalContext ctx);
}