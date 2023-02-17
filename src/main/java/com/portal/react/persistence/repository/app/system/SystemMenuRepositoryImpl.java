package com.portal.react.persistence.repository.app.system;

import com.portal.react.persistence.dto.system.SystemMenuDto;
import com.portal.react.persistence.entity.app.system.QSystemMenu;
import com.portal.react.persistence.entity.app.system.SystemMenu;
import com.portal.react.persistence.repository.app.AppQuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

//import com.querydsl.jpa.impl.JPAQueryFactory;

public class SystemMenuRepositoryImpl extends AppQuerydslRepositorySupport implements SystemMenuRepositoryCustom
{


    private QSystemMenu qSystemMenu = QSystemMenu.systemMenu;

    private final JPAQueryFactory queryFactory;



    public SystemMenuRepositoryImpl(@Qualifier("appJpaQueryFactory")JPAQueryFactory queryFactory) {
        super(SystemMenu.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Long findListCnt(String menuCd, String systemCd) {

        BooleanBuilder builder = new BooleanBuilder();

        return queryFactory.select(qSystemMenu.count())
                .from(qSystemMenu)
                .where(qSystemMenu.menuCd.eq(menuCd).and(qSystemMenu.systemCd.eq(systemCd)))
                .orderBy(qSystemMenu.menuCd.asc())
                .fetchOne();

    }

    @Override
    public List<SystemMenuDto> findByParams(Map<String, Object> param) {

        String systemCd = (String) param.get("systemCd");
        String keyword = (String) param.get("keyword");
        String year = (String) param.get("year");
        String month = (String) param.get("month");
        System.out.println("##########################SysetmMenuRepositoryImpl - findByParams() systemCd : "+systemCd);
        System.out.println("##########################SysetmMenuRepositoryImpl - findByParams() keyword : "+param);


        StringTemplate createTime = Expressions.stringTemplate("DATE_FORMAT({0},{1}", qSystemMenu.createTime, ConstantImpl.create("%Y-%m-%d"));
        StringTemplate updateTime = Expressions.stringTemplate("DATE_FORMAT({0},{1}", qSystemMenu.createTime, ConstantImpl.create("%Y-%m-%d"));


        System.out.println("##########################SysetmMenuRepositoryImpl - queryFactory GoGo ");
        return queryFactory.select(Projections.bean(SystemMenuDto.class,
                    qSystemMenu.systemCd,
                    qSystemMenu.prntCd,
                    qSystemMenu.menuCd,
                    //qSystemMenu.name.as("systemName"),
                    qSystemMenu.name,
                    qSystemMenu.nameEng,
                    qSystemMenu.nameEtc,
                    qSystemMenu.menuPath,
                    qSystemMenu.icon,
                    qSystemMenu.filePath,
                    qSystemMenu.menuType,
                    qSystemMenu.pcYn,
                    qSystemMenu.mobileYn,
                    qSystemMenu.sortSq,
                    qSystemMenu.useYn,
                    //createTime.as("createTime"),
                    qSystemMenu.createUser,
                    //updateTime.as("updateTime"),
                    qSystemMenu.updateUser,
                    qSystemMenu.lvl
        ))
                .from(qSystemMenu)
/*                .where(
                        systemCdEq("10322"),
                        nameLike(keyword)
                )
                .orderBy(qSystemMenu.systemCd.asc(), qSystemMenu.prntCd.asc(), qSystemMenu.menuCd.asc())*/
                .fetch();
    }

    private BooleanExpression systemCdEq(String str) {
        return StringUtils.hasText(str) ? qSystemMenu.systemCd.eq(str) : null;
    }

    private BooleanExpression nameLike(String str) {
        return StringUtils.hasText(str) ? qSystemMenu.name.contains(str) : null;
    }


}
