package com.javatest.app.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOption is a Querydsl query type for Option
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOption extends EntityPathBase<Option> {

    private static final long serialVersionUID = -1452542860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOption option = new QOption("option");

    public final QField field;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QOption(String variable) {
        this(Option.class, forVariable(variable), INITS);
    }

    public QOption(Path<? extends Option> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOption(PathMetadata metadata, PathInits inits) {
        this(Option.class, metadata, inits);
    }

    public QOption(Class<? extends Option> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.field = inits.isInitialized("field") ? new QField(forProperty("field")) : null;
    }

}

