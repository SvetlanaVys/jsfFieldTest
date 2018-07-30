package com.javatest.app.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QField is a Querydsl query type for Field
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QField extends EntityPathBase<Field> {

    private static final long serialVersionUID = -1302316773L;

    public static final QField field = new QField("field");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath label = createString("label");

    public final ListPath<Option, QOption> options = this.<Option, QOption>createList("options", Option.class, QOption.class, PathInits.DIRECT2);

    public final BooleanPath required = createBoolean("required");

    public final NumberPath<Integer> rowNumber = createNumber("rowNumber", Integer.class);

    public final StringPath type = createString("type");

    public QField(String variable) {
        super(Field.class, forVariable(variable));
    }

    public QField(Path<? extends Field> path) {
        super(path.getType(), path.getMetadata());
    }

    public QField(PathMetadata metadata) {
        super(Field.class, metadata);
    }

}

