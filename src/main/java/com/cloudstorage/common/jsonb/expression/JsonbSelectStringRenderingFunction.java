package com.cloudstorage.common.jsonb.expression;

import org.hibernate.query.sqm.function.AbstractSqmSelfRenderingFunctionDescriptor;
import org.hibernate.query.sqm.produce.function.StandardArgumentsValidators;
import org.hibernate.query.sqm.produce.function.StandardFunctionArgumentTypeResolvers;
import org.hibernate.query.sqm.produce.function.StandardFunctionReturnTypeResolvers;
import org.hibernate.sql.ast.SqlAstNodeRenderingMode;
import org.hibernate.sql.ast.SqlAstTranslator;
import org.hibernate.sql.ast.spi.SqlAppender;
import org.hibernate.sql.ast.tree.SqlAstNode;
import org.hibernate.sql.ast.tree.expression.Literal;
import org.hibernate.type.BasicType;
import org.hibernate.type.descriptor.java.StringJavaType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.hibernate.type.internal.BasicTypeImpl;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;

public class JsonbSelectStringRenderingFunction extends AbstractSqmSelfRenderingFunctionDescriptor {
    public static final BasicType<String> TYPE = new BasicTypeImpl<>(StringJavaType.INSTANCE, VarcharJdbcType.INSTANCE);
    public static final JsonbSelectStringRenderingFunction INSTANCE = new JsonbSelectStringRenderingFunction();
    public static final String FUNCTION_NAME = "JSONB_STRING_SELECT_NAME";

    private JsonbSelectStringRenderingFunction() {
        super(FUNCTION_NAME,
                StandardArgumentsValidators.exactly(2),
                StandardFunctionReturnTypeResolvers.invariant(TYPE),
                StandardFunctionArgumentTypeResolvers.argumentsOrImplied());
    }

    @Override
    public void render(SqlAppender sqlAppender, List<? extends SqlAstNode> sqlAstArguments, SqlAstTranslator<?> walker) {
        Iterator<? extends SqlAstNode> iterator = sqlAstArguments.iterator();
        SqlAstNode root = iterator.next();
        walker.render(root, SqlAstNodeRenderingMode.DEFAULT);
        sqlAppender.append("#>>'{");
        sqlAppender.append(preparePathString(iterator.next()));
        sqlAppender.append("}'");
    }

    private String preparePathString(SqlAstNode pathArg) {
        Assert.isAssignable(Literal.class, pathArg.getClass(), "The second arg must be literal path");
        String stringLiteral = String.valueOf(((Literal) pathArg).getLiteralValue());
        Assert.hasText(stringLiteral, "Path must not be empty");
        return String.join(",", stringLiteral.split("\\."));
    }
}
