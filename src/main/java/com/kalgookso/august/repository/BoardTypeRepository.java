package com.kalgookso.august.repository;

import com.kalgookso.august.entity.BoardType;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * BoardTypeRepository는 Spring Data JPA의 Repository 인터페이스를 확장하는 인터페이스입니다.
 * 이 인터페이스는 BoardType 엔티티에 대한 기본적인 CRUD 작업을 제공합니다.
 */
public interface BoardTypeRepository extends Repository<BoardType, String> {

    /**
     * 주어진 ID에 해당하는 BoardType 엔티티를 찾습니다.
     *
     * @param id 찾을 BoardType 엔티티의 ID.
     * @return 찾은 BoardType 엔티티를 포함하는 Optional, 또는 BoardType 엔티티를 찾지 못한 경우 빈 Optional.
     */
    Optional<BoardType> findById(String id);

    /**
     * 주어진 BoardType 엔티티를 저장합니다.
     *
     * @param boardType 저장할 BoardType 엔티티.
     * @return 저장된 BoardType 엔티티.
     */
    BoardType save(BoardType boardType);

    /**
     * 주어진 ID에 해당하는 BoardType 엔티티를 삭제합니다.
     *
     * @param id 삭제할 BoardType 엔티티의 ID.
     */
    void deleteById(String id);

}