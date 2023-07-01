package com.cloudstorage.common.jsonb.expression;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import org.hibernate.query.sqm.NodeBuilder;
import org.hibernate.query.sqm.function.SelfRenderingSqmFunction;
import org.hibernate.query.sqm.produce.function.StandardArgumentsValidators;
import org.hibernate.query.sqm.produce.function.StandardFunctionReturnTypeResolvers;
import org.hibernate.query.sqm.tree.SqmTypedNode;
import org.hibernate.query.sqm.tree.expression.SqmFunction;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class JsonbStringSelect extends SelfRenderingSqmFunction<String> {

    public JsonbStringSelect(List<? extends SqmTypedNode<?>> arguments, NodeBuilder nodeBuilder) {
        super(JsonbSelectStringRenderingFunction.INSTANCE,
                JsonbSelectStringRenderingFunction.INSTANCE,
                arguments,
                JsonbSelectStringRenderingFunction.TYPE,
                StandardArgumentsValidators.exactly(2),
                StandardFunctionReturnTypeResolvers.invariant(JsonbSelectStringRenderingFunction.TYPE),
                nodeBuilder,
                JsonbSelectStringRenderingFunction.FUNCTION_NAME);
    }

    public static SqmFunction<String> select(Expression<?> root, String path, CriteriaBuilder cb) {
        return new JsonbStringSelect(
                prepareParameters((SqmTypedNode<?>) root, path, (NodeBuilder) cb),
                (NodeBuilder) cb
        );
    }

    private static List<? extends SqmTypedNode<?>> prepareParameters(SqmTypedNode<?> root, String path, NodeBuilder cb) {
        Assert.notNull(root, "Root must not null");
        Assert.hasLength(path, "Path must not be empty");

        List<SqmTypedNode<?>> params = new ArrayList<>();
        params.add(root);
        params.add(cb.literal(path));
        return params;
    }
}
