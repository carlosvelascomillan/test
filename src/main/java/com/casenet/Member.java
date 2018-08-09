// Copyright (c) 2011 by CaseNET, LLC
//
// This file is protected by Federal Copyright Law, with all rights
// reserved. No part of this file may be reproduced, stored in a
// retrieval system, translated, transcribed, or transmitted, in any
// form, or by any means manual, electric, electronic, mechanical,
// electro-magnetic, chemical, optical, or otherwise, without prior
// explicit written permission from CaseNET, LLC.
package com.casenet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.google.common.base.Objects;

/**
 * The Class Member.
 */
@Entity
public class Member {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    /** The name. */
    @Column(name = "member_name")
    private String name;

    /** The diagnosis. */
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "member")
    private Set<Diagnosis> diagnosis = new HashSet<Diagnosis>();
    
    /** The addresses. */
    @Transient
    private List<Address> addresses = new ArrayList<Address>();

    /**
     * Instantiates a new member.
     *
     * @param name the name
     */
    public Member(final String name) {
        this.name = name;
    }

    /**
     * Instantiates a new member.
     */
    public Member() {
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the diagnosis.
     *
     * @return the diagnosis
     */
    public Set<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis.
     *
     * @param diagnosis the new diagnosis
     */
    public void setDiagnosis(final Set<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    /**
     * Gets the addresses.
     *
     * @return the addresses
     */
    public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * Sets the addresses.
	 *
	 * @param addresses the new addresses
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
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
        final Member other = (Member) obj;
        return Objects.equal(this.id, other.id) && Objects.equal(this.name, other.name);
    }
}
