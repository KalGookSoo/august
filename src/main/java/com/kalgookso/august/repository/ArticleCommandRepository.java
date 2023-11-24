package com.kalgookso.august.repository;

import com.kalgookso.august.entity.Article;
import org.springframework.data.repository.Repository;

public interface ArticleCommandRepository extends Repository<Article, String> {

    /**
     * 주어진 Article 엔티티를 저장합니다.
     *
     * @param article 저장할 Article 엔티티.
     * @return 저장된 Article 엔티티.
     */
    Article save(Article article);

    /**
     * 주어진 ID에 해당하는 Article 엔티티를 삭제합니다.
     *
     * @param id 삭제할 Article 엔티티의 ID.
     */
    void deleteById(String id);

}