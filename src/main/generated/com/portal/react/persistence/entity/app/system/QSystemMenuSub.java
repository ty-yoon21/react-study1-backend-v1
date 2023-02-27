package com.portal.react.persistence.entity.app.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSystemMenuSub is a Querydsl query type for SystemMenuSub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSystemMenuSub extends EntityPathBase<SystemMenuSub> {

    private static final long serialVersionUID = -25209736L;

    public static final QSystemMenuSub systemMenuSub = new QSystemMenuSub("systemMenuSub");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath filePath = createString("filePath");

    public final StringPath icon = createString("icon");

    public final StringPath kafkaOffset = createString("kafkaOffset");

    public final NumberPath<Integer> lvl = createNumber("lvl", Integer.class);

    public final StringPath menuCd = createString("menuCd");

    public final StringPath menuPath = createString("menuPath");

    public final StringPath menuType = createString("menuType");

    public final StringPath messageKey = createString("messageKey");

    public final StringPath mobileYn = createString("mobileYn");

    public final StringPath name = createString("name");

    public final StringPath nameEng = createString("nameEng");

    public final StringPath nameEtc = createString("nameEtc");

    public final StringPath pcYn = createString("pcYn");

    public final StringPath prntCd = createString("prntCd");

    public final NumberPath<Integer> sortSq = createNumber("sortSq", Integer.class);

    public final StringPath systemCd = createString("systemCd");

    public final StringPath topic = createString("topic");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath updateUser = createString("updateUser");

    public final StringPath useYn = createString("useYn");

    public QSystemMenuSub(String variable) {
        super(SystemMenuSub.class, forVariable(variable));
    }

    public QSystemMenuSub(Path<? extends SystemMenuSub> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemMenuSub(PathMetadata metadata) {
        super(SystemMenuSub.class, metadata);
    }

}

