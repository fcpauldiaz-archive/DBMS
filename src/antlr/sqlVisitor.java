// Generated from sql.g4 by ANTLR 4.5.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link sqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface sqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql2003Parser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql2003Parser(sqlParser.Sql2003ParserContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql_executable_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_executable_statement(sqlParser.Sql_executable_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql_schema_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_schema_statement(sqlParser.Sql_schema_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql_schema_definition_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_schema_definition_statement(sqlParser.Sql_schema_definition_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql_schema_manipulation_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_schema_manipulation_statement(sqlParser.Sql_schema_manipulation_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#sql_data_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_data_statement(sqlParser.Sql_data_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#schema_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchema_definition(sqlParser.Schema_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#table_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_definition(sqlParser.Table_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#drop_schema_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_schema_statement(sqlParser.Drop_schema_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#alter_table_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_table_statement(sqlParser.Alter_table_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#drop_table_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_table_statement(sqlParser.Drop_table_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#alter_database_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_database_statement(sqlParser.Alter_database_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#show_schema_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_schema_statement(sqlParser.Show_schema_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#use_schema_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse_schema_statement(sqlParser.Use_schema_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn(sqlParser.ColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#tipo_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_literal(sqlParser.Tipo_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint(sqlParser.ConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#constraintType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintType(sqlParser.ConstraintTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(sqlParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#rename_table_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename_table_statement(sqlParser.Rename_table_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#accion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccion(sqlParser.AccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#show_table_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_table_statement(sqlParser.Show_table_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#show_column_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_column_statement(sqlParser.Show_column_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#logic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic(sqlParser.LogicContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#relational}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational(sqlParser.RelationalContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#insert_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_value(sqlParser.Insert_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_value(sqlParser.Update_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_value(sqlParser.Delete_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_value(sqlParser.Select_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(sqlParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#list_values}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_values(sqlParser.List_valuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(sqlParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(sqlParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(sqlParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#int_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_literal(sqlParser.Int_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#float_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat_literal(sqlParser.Float_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#date_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_literal(sqlParser.Date_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#char_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar_literal(sqlParser.Char_literalContext ctx);
}