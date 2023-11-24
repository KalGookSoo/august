package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 게시글 명령 저장소 구현 클래스입니다.
 * 이 클래스는 ArticleCommandRepository 인터페이스를 구현하며, EntityManager를 사용하여 게시글에 대한 생성, 수정, 삭제 명령을 처리합니다.
 */
public class ArticleCommandRepositoryImpl implements ArticleCommandRepository {

    /**
     * EntityManager는 JPA의 핵심 인터페이스로, 엔티티를 관리합니다.
     * @PersistenceContext 어노테이션을 사용하여 스프링 컨테이너에서 EntityManager를 주입받습니다.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 주어진 Article 엔티티를 저장하는 메서드입니다.
     * 만약 Article 엔티티의 ID가 null이라면, 엔티티를 새로 생성하고,
     * 그렇지 않다면 기존의 엔티티를 업데이트합니다.
     *
     * @param article 저장할 Article 엔티티.
     * @return 저장된 Article 엔티티.
     */
    @Override
    public Article save(Article article) {
        if (article.getId() == null) {
            em.persist(article);
            return article;
        } else {
            return em.merge(article);
        }
    }

    /**
     * 주어진 ID에 해당하는 Article 엔티티를 삭제하는 메서드입니다.
     * 만약 Article 엔티티를 찾지 못한다면 아무 작업도 수행하지 않습니다.
     *
     * @param id 삭제할 Article 엔티티의 ID.
     */
    @Override
    public void deleteById(String id) {
        Article article = em.find(Article.class, id);
        if (article != null) {
            em.remove(article);
        }
    }

}