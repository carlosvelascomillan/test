// Copyright (c) 2011 by CaseNET, LLC
//
// This file is protected by Federal Copyright Law, with all rights
// reserved. No part of this file may be reproduced, stored in a
// retrieval system, translated, transcribed, or transmitted, in any
// form, or by any means manual, electric, electronic, mechanical,
// electro-magnetic, chemical, optical, or otherwise, without prior
// explicit written permission from CaseNET, LLC.
package com.casenet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.common.base.Objects;

/**
 * The Class Diagnosis.
 */
@Entity
public class Diagnosis {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private Long id;

    /** The description. */
    @Column(name = "diagnosis_desc")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String description;

    /** The member. */
    @ManyToOne(targetEntity=Member.class)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * Instantiates a new diagnosis.
     *
     * @param member the member
     * @param description the description
     */
    public Diagnosis(final Member member, final String description) {
        this.member = member;
        this.description = description;
    }

    /**
     * Instantiates a new diagnosis.
     */
    public Diagnosis() {
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the member.
     *
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the member.
     *
     * @param member the new member
     */
    public void setMember(final Member member) {
        this.member = member;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, description);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Diagnosis other = (Diagnosis) obj;
        return Objects.equal(this.id, other.id) && Objects
                .equal(this.description, other.description);
    }
}
