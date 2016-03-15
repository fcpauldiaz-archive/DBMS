// Generated from /Users/usuario/NetBeansProjects/DBMS/src/antlr/sql.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sqlParser}.
 */
public interface sqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sqlParser#accion}.
	 * @param ctx the parse tree
	 */
	void enterAccion(@NotNull sqlParser.AccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#accion}.
	 * @param ctx the parse tree
	 */
	void exitAccion(@NotNull sqlParser.AccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#use_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterUse_schema_statement(@NotNull sqlParser.Use_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#use_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitUse_schema_statement(@NotNull sqlParser.Use_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(@NotNull sqlParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(@NotNull sqlParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#table_definition}.
	 * @param ctx the parse tree
	 */
	void enterTable_definition(@NotNull sqlParser.Table_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#table_definition}.
	 * @param ctx the parse tree
	 */
	void exitTable_definition(@NotNull sqlParser.Table_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_column_statement}.
	 * @param ctx the parse tree
	 */
	void enterShow_column_statement(@NotNull sqlParser.Show_column_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_column_statement}.
	 * @param ctx the parse tree
	 */
	void exitShow_column_statement(@NotNull sqlParser.Show_column_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_schema_definition_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_schema_definition_statement(@NotNull sqlParser.Sql_schema_definition_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_schema_definition_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_schema_definition_statement(@NotNull sqlParser.Sql_schema_definition_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_data_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_data_statement(@NotNull sqlParser.Sql_data_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_data_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_data_statement(@NotNull sqlParser.Sql_data_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterShow_schema_statement(@NotNull sqlParser.Show_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitShow_schema_statement(@NotNull sqlParser.Show_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_schema_statement(@NotNull sqlParser.Sql_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_schema_statement(@NotNull sqlParser.Sql_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull sqlParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull sqlParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#constraintType}.
	 * @param ctx the parse tree
	 */
	void enterConstraintType(@NotNull sqlParser.ConstraintTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#constraintType}.
	 * @param ctx the parse tree
	 */
	void exitConstraintType(@NotNull sqlParser.ConstraintTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void enterInt_literal(@NotNull sqlParser.Int_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void exitInt_literal(@NotNull sqlParser.Int_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_schema_manipulation_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_schema_manipulation_statement(@NotNull sqlParser.Sql_schema_manipulation_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_schema_manipulation_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_schema_manipulation_statement(@NotNull sqlParser.Sql_schema_manipulation_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterAlter_table_statement(@NotNull sqlParser.Alter_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitAlter_table_statement(@NotNull sqlParser.Alter_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#list_values}.
	 * @param ctx the parse tree
	 */
	void enterList_values(@NotNull sqlParser.List_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#list_values}.
	 * @param ctx the parse tree
	 */
	void exitList_values(@NotNull sqlParser.List_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_database_statement}.
	 * @param ctx the parse tree
	 */
	void enterAlter_database_statement(@NotNull sqlParser.Alter_database_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_database_statement}.
	 * @param ctx the parse tree
	 */
	void exitAlter_database_statement(@NotNull sqlParser.Alter_database_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void enterFloat_literal(@NotNull sqlParser.Float_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void exitFloat_literal(@NotNull sqlParser.Float_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql2003Parser}.
	 * @param ctx the parse tree
	 */
	void enterSql2003Parser(@NotNull sqlParser.Sql2003ParserContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql2003Parser}.
	 * @param ctx the parse tree
	 */
	void exitSql2003Parser(@NotNull sqlParser.Sql2003ParserContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#drop_schema_statement}.
	 * @param ctx the parse tree
	 */
	void enterDrop_schema_statement(@NotNull sqlParser.Drop_schema_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#drop_schema_statement}.
	 * @param ctx the parse tree
	 */
	void exitDrop_schema_statement(@NotNull sqlParser.Drop_schema_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(@NotNull sqlParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(@NotNull sqlParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#delete_value}.
	 * @param ctx the parse tree
	 */
	void enterDelete_value(@NotNull sqlParser.Delete_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#delete_value}.
	 * @param ctx the parse tree
	 */
	void exitDelete_value(@NotNull sqlParser.Delete_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#select_value}.
	 * @param ctx the parse tree
	 */
	void enterSelect_value(@NotNull sqlParser.Select_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#select_value}.
	 * @param ctx the parse tree
	 */
	void exitSelect_value(@NotNull sqlParser.Select_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull sqlParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull sqlParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#date_literal}.
	 * @param ctx the parse tree
	 */
	void enterDate_literal(@NotNull sqlParser.Date_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#date_literal}.
	 * @param ctx the parse tree
	 */
	void exitDate_literal(@NotNull sqlParser.Date_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#char_literal}.
	 * @param ctx the parse tree
	 */
	void enterChar_literal(@NotNull sqlParser.Char_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#char_literal}.
	 * @param ctx the parse tree
	 */
	void exitChar_literal(@NotNull sqlParser.Char_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#sql_executable_statement}.
	 * @param ctx the parse tree
	 */
	void enterSql_executable_statement(@NotNull sqlParser.Sql_executable_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#sql_executable_statement}.
	 * @param ctx the parse tree
	 */
	void exitSql_executable_statement(@NotNull sqlParser.Sql_executable_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#drop_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterDrop_table_statement(@NotNull sqlParser.Drop_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#drop_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitDrop_table_statement(@NotNull sqlParser.Drop_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(@NotNull sqlParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(@NotNull sqlParser.ColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#insert_value}.
	 * @param ctx the parse tree
	 */
	void enterInsert_value(@NotNull sqlParser.Insert_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#insert_value}.
	 * @param ctx the parse tree
	 */
	void exitInsert_value(@NotNull sqlParser.Insert_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#schema_definition}.
	 * @param ctx the parse tree
	 */
	void enterSchema_definition(@NotNull sqlParser.Schema_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#schema_definition}.
	 * @param ctx the parse tree
	 */
	void exitSchema_definition(@NotNull sqlParser.Schema_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#tipo_literal}.
	 * @param ctx the parse tree
	 */
	void enterTipo_literal(@NotNull sqlParser.Tipo_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#tipo_literal}.
	 * @param ctx the parse tree
	 */
	void exitTipo_literal(@NotNull sqlParser.Tipo_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull sqlParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull sqlParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#update_value}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_value(@NotNull sqlParser.Update_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#update_value}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_value(@NotNull sqlParser.Update_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#rename_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterRename_table_statement(@NotNull sqlParser.Rename_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#rename_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitRename_table_statement(@NotNull sqlParser.Rename_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(@NotNull sqlParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(@NotNull sqlParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_table_statement}.
	 * @param ctx the parse tree
	 */
	void enterShow_table_statement(@NotNull sqlParser.Show_table_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_table_statement}.
	 * @param ctx the parse tree
	 */
	void exitShow_table_statement(@NotNull sqlParser.Show_table_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#relational}.
	 * @param ctx the parse tree
	 */
	void enterRelational(@NotNull sqlParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#relational}.
	 * @param ctx the parse tree
	 */
	void exitRelational(@NotNull sqlParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#logic}.
	 * @param ctx the parse tree
	 */
	void enterLogic(@NotNull sqlParser.LogicContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#logic}.
	 * @param ctx the parse tree
	 */
	void exitLogic(@NotNull sqlParser.LogicContext ctx);
}