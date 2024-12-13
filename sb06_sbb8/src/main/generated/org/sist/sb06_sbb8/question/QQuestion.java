package org.sist.sb06_sbb8.question;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = 1625380994L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final ListPath<org.sist.sb06_sbb8.answer.Answer, org.sist.sb06_sbb8.answer.QAnswer> answerList = this.<org.sist.sb06_sbb8.answer.Answer, org.sist.sb06_sbb8.answer.QAnswer>createList("answerList", org.sist.sb06_sbb8.answer.Answer.class, org.sist.sb06_sbb8.answer.QAnswer.class, PathInits.DIRECT2);

    public final org.sist.sb06_sbb8.user.QSiteUser author;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final SetPath<org.sist.sb06_sbb8.user.SiteUser, org.sist.sb06_sbb8.user.QSiteUser> voter = this.<org.sist.sb06_sbb8.user.SiteUser, org.sist.sb06_sbb8.user.QSiteUser>createSet("voter", org.sist.sb06_sbb8.user.SiteUser.class, org.sist.sb06_sbb8.user.QSiteUser.class, PathInits.DIRECT2);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new org.sist.sb06_sbb8.user.QSiteUser(forProperty("author")) : null;
    }

}

