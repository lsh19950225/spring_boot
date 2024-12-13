package org.sist.sb06_sbb8.answer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = -1127425662L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswer answer = new QAnswer("answer");

    public final org.sist.sb06_sbb8.user.QSiteUser author;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final org.sist.sb06_sbb8.question.QQuestion question;

    public final SetPath<org.sist.sb06_sbb8.user.SiteUser, org.sist.sb06_sbb8.user.QSiteUser> voter = this.<org.sist.sb06_sbb8.user.SiteUser, org.sist.sb06_sbb8.user.QSiteUser>createSet("voter", org.sist.sb06_sbb8.user.SiteUser.class, org.sist.sb06_sbb8.user.QSiteUser.class, PathInits.DIRECT2);

    public QAnswer(String variable) {
        this(Answer.class, forVariable(variable), INITS);
    }

    public QAnswer(Path<? extends Answer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswer(PathMetadata metadata, PathInits inits) {
        this(Answer.class, metadata, inits);
    }

    public QAnswer(Class<? extends Answer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new org.sist.sb06_sbb8.user.QSiteUser(forProperty("author")) : null;
        this.question = inits.isInitialized("question") ? new org.sist.sb06_sbb8.question.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

