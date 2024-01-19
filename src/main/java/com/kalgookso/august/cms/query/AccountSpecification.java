package com.kalgookso.august.cms.query;

import com.kalgookso.august.cms.entity.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

public class AccountSpecification {

    public static <T> Specification<T> usernameEquals(String username) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username);
    }

    public static Specification<Account> usernameContains(String username) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<Account> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Account> emailIdContains(String emailId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email").get("id"), "%" + emailId + "%");
    }

    public static Specification<Account> contactNumberContains(String contactNumber) {
        return (root, query, criteriaBuilder) -> {
            Path<Object> first = root.get("contactNumber").get("first");
            Path<Object> middle = root.get("contactNumber").get("middle");
            Path<Object> last = root.get("contactNumber").get("last");
            Expression<String> concat = criteriaBuilder.concat(criteriaBuilder.concat(first.as(String.class), middle.as(String.class)), last.as(String.class));
            return criteriaBuilder.like(concat, "%" + contactNumber + "%");
        };
    }

}