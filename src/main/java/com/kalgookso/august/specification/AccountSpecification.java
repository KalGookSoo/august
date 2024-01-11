package com.kalgookso.august.specification;

import com.kalgookso.august.entity.account.Account;
import org.springframework.data.jpa.domain.Specification;

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
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("emailId"), "%" + emailId + "%");
    }

    public static Specification<Account> emailDomainContains(String emailDomain) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("emailDomain"), "%" + emailDomain + "%");
    }

    public static Specification<Account> contactNumberContains(String contactNumber) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("contactNumber"), "%" + contactNumber + "%");
    }

}