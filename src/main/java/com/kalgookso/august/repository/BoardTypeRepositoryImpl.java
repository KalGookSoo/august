package com.kalgookso.august.repository;

import com.kalgookso.august.entity.BoardType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * BoardTypeRepositoryImpl는 BoardTypeRepository 인터페이스를 구현하는 클래스입니다.
 * 이 클래스는 JPA의 EntityManager를 사용하여 BoardType 엔티티에 대한 CRUD 작업을 수행합니다.
 */
public class BoardTypeRepositoryImpl implements BoardTypeRepository {

    /**
     * EntityManager는 JPA의 핵심 인터페이스로, 엔티티를 관리합니다.
     * @PersistenceContext 어노테이션을 사용하여 스프링 컨테이너에서 EntityManager를 주입받습니다.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 주어진 ID에 해당하는 BoardType 엔티티를 찾습니다.
     *
     * @param id 찾을 BoardType 엔티티의 ID.
     * @return 찾은 BoardType 엔티티를 포함하는 Optional, 또는 BoardType 엔티티를 찾지 못한 경우 빈 Optional.
     */
    @Override
    public Optional<BoardType> findById(String id) {
        return Optional.ofNullable(em.find(BoardType.class, id));
    }

    /**
     * 주어진 BoardType 엔티티를 저장합니다.
     * 만약 BoardType 엔티티의 ID가 null이라면, 엔티티를 새로 생성하고,
     * 그렇지 않다면 기존의 엔티티를 업데이트합니다.
     *
     * @param boardType 저장할 BoardType 엔티티.
     * @return 저장된 BoardType 엔티티.
     */
    @Override
    public BoardType save(BoardType boardType) {
        if (boardType.getId() == null) {
            em.persist(boardType);
            return boardType;
        } else {
            return em.merge(boardType);
        }
    }

    /**
     * 주어진 ID에 해당하는 BoardType 엔티티를 삭제합니다.
     * 만약 BoardType 엔티티를 찾지 못한다면 아무 작업도 수행하지 않습니다.
     *
     * @param id 삭제할 BoardType 엔티티의 ID.
     */
    @Override
    public void deleteById(String id) {
        BoardType boardType = em.find(BoardType.class, id);
        if (boardType != null) {
            em.remove(boardType);
        }
    }

}