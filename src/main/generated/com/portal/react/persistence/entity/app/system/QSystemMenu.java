package com.portal.react.persistence.entity.app.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSystemMenu is a Querydsl query type for SystemMenu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSystemMenu extends EntityPathBase<SystemMenu> {

    private static final long serialVersionUID = -1767092056L;

    public static final QSystemMenu systemMenu = new QSystemMenu("systemMenu");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath filePath = createString("filePath");

    public final StringPath icon = createString("icon");

    public final NumberPath<Integer> lvl = createNumber("lvl", Integer.class);

    public final StringPath menuCd = createString("menuCd");

    public final StringPath menuPath = createString("menuPath");

    public final StringPath menuType = createString("menuType");

    public final StringPath mobileYn = createString("mobileYn");

    public final StringPath name = createString("name");

    public final StringPath nameEng = createString("nameEng");

    public final StringPath nameEtc = createString("nameEtc");

    public final StringPath pcYn = createString("pcYn");

    public final StringPath prntCd = createString("prntCd");

    public final NumberPath<Integer> sortSq = createNumber("sortSq", Integer.class);

    public final StringPath systemCd = createString("systemCd");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath updateUser = createString("updateUser");

    public final StringPath useYn = createString("useYn");

    public QSystemMenu(String variable) {
        super(SystemMenu.class, forVariable(variable));
    }

    public QSystemMenu(Path<? extends SystemMenu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemMenu(PathMetadata metadata) {
        super(SystemMenu.class, metadata);
    }

}

