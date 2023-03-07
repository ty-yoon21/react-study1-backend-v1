package com.portal.react.persistence.entity.app.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSystemAuth is a Querydsl query type for SystemAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSystemAuth extends EntityPathBase<SystemAuth> {

    private static final long serialVersionUID = -1767433999L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSystemAuth systemAuth = new QSystemAuth("systemAuth");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath role = createString("role");

    public final QSystemUser systemUser;

    public QSystemAuth(String variable) {
        this(SystemAuth.class, forVariable(variable), INITS);
    }

    public QSystemAuth(Path<? extends SystemAuth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSystemAuth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSystemAuth(PathMetadata metadata, PathInits inits) {
        this(SystemAuth.class, metadata, inits);
    }

    public QSystemAuth(Class<? extends SystemAuth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.systemUser = inits.isInitialized("systemUser") ? new QSystemUser(forProperty("systemUser")) : null;
    }

}

