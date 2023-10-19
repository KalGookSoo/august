package com.kalgookso.august.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 권한
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_authority")
@Setter
@Getter
public class Authority {

    /**
     * 권한 식별자
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    /**
     * 이름
     */
    private String name;

    /**
     * 계정
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    /**
     * 생성자
     * @param name 이름
     */
    public Authority(String name) {
        this.name = name;
    }

    /**
     * 기본 생성자
     * JPA에서는 기본 생성자가 필요하다.
     * 기본 생성자를 protected로 설정하여 외부에서 호출하지 못하도록 했다.
     * JPA는 리플렉션을 사용하기 때문에 기본 생성자가 필요하다.
     * 기본 생성자를 protected로 설정하면 외부에서 호출하지 못하고 내부에서는 호출할 수 있기 때문에 JPA에서는 문제가 없다.
     *
     * @see <a href="https://www.inflearn.com/questions/12886">JPA 기본 생성자</a>
     */
    protected Authority() {
    }

}
