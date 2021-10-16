package com.demo.employees.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class QueryParser {

    public static Map parseQuery(String where) {
        if (StringUtils.isEmpty(where)) {
            return null;
        }
        Map queryParams = new HashMap<>();
        Expression expr = null;
        try {
            expr = CCJSqlParserUtil.parseCondExpression(where);
            expr.accept(new ExpressionVisitorAdapter() {
                @Override
                public void visit(AndExpression expr) {
                    if (expr.getLeftExpression() instanceof AndExpression) {
                        expr.getLeftExpression().accept(this);
                    } else if ((expr.getLeftExpression() instanceof EqualsTo)) {
                        expr.getLeftExpression().accept(this);
                    }
                    expr.getRightExpression().accept(this);
                }

                @Override
                public void visit(EqualsTo expr) {
                    String key = expr.getLeftExpression().toString();
                    String value = removeQuotes(expr.getRightExpression().toString());
                    queryParams.put(key, value);
                }
            });
        } catch (JSQLParserException e) {
            throw new ServiceException(ExceptionCode.BAD_REQUEST.getResponseCode(), "Invalid where clause");
        }
        return queryParams;
    }

    private static String removeQuotes(String value) {
        return StringUtils.isEmpty(value) ? null : (value.startsWith("'") && value.endsWith("'") ? value.substring(1, value.length() - 1) : value);
    }
}
