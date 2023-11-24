package com.kalgookso.august.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "tb_acl_object_identity")
public class AclObjectIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "object_id_class")
    private AclClass objectIdClass;

    @Column(name = "object_id_identity")
    private Long objectIdIdentity;

    @ManyToOne
    @JoinColumn(name = "parent_object")
    private AclObjectIdentity parentObject;

    @ManyToOne
    @JoinColumn(name = "owner_sid")
    private AclSid ownerSid;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "entries_inheriting")
    private boolean entriesInheriting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AclClass getObjectIdClass() {
        return objectIdClass;
    }

    public void setObjectIdClass(AclClass objectIdClass) {
        this.objectIdClass = objectIdClass;
    }

    public Long getObjectIdIdentity() {
        return objectIdIdentity;
    }

    public void setObjectIdIdentity(Long objectIdIdentity) {
        this.objectIdIdentity = objectIdIdentity;
    }

    public AclObjectIdentity getParentObject() {
        return parentObject;
    }

    public void setParentObject(AclObjectIdentity parentObject) {
        this.parentObject = parentObject;
    }

    public AclSid getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(AclSid ownerSid) {
        this.ownerSid = ownerSid;
    }

    public boolean isEntriesInheriting() {
        return entriesInheriting;
    }

    public void setEntriesInheriting(boolean entriesInheriting) {
        this.entriesInheriting = entriesInheriting;
    }

}