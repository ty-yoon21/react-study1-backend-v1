package com.portal.react.persistence.entity.app.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSystemUser is a Querydsl query type for SystemUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSystemUser extends EntityPathBase<SystemUser> {

    private static final long serialVersionUID = -1766840556L;

    public static final QSystemUser systemUser = new QSystemUser("systemUser");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final SetPath<SystemAuth, QSystemAuth> systemAuths = this.<SystemAuth, QSystemAuth>createSet("systemAuths", SystemAuth.class, QSystemAuth.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QSystemUser(String variable) {
        super(SystemUser.class, forVariable(variable));
    }

    public QSystemUser(Path<? extends SystemUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemUser(PathMetadata metadata) {
        super(SystemUser.class, metadata);
    }

}

