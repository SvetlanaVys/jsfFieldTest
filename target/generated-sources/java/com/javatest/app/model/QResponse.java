package com.javatest.app.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResponse is a Querydsl query type for Response
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResponse extends EntityPathBase<Response> {

    private static final long serialVersionUID = -290620288L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResponse response = new QResponse("response");

    public final StringPath content = createString("content");

    public final QField field;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> row = createNumber("row", Integer.class);

    public QResponse(String variable) {
        this(Response.class, forVariable(variable), INITS);
    }

    public QResponse(Path<? extends Response> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResponse(PathMetadata metadata, PathInits inits) {
        this(Response.class, metadata, inits);
    }

    public QResponse(Class<? extends Response> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.field = inits.isInitialized("field") ? new QField(forProperty("field")) : null;
    }

}

