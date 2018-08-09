// Copyright (c) 2011 by CaseNET, LLC
//
// This file is protected by Federal Copyright Law, with all rights
// reserved. No part of this file may be reproduced, stored in a
// retrieval system, translated, transcribed, or transmitted, in any
// form, or by any means manual, electric, electronic, mechanical,
// electro-magnetic, chemical, optical, or otherwise, without prior
// explicit written permission from CaseNET, LLC.
package com.casenet;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * The Class MemberRepository.
 */
@Repository
public class MemberRepository {

    /** The Constant FIND_MEMBERS_BY_DIAGNOSIS. */
    private static final String FIND_MEMBERS_BY_DIAGNOSIS = "SELECT m FROM Member m JOIN m.diagnosis d WHERE d.description = :diagnosisDescription";
    
	/** The em. */
	@PersistenceContext
    EntityManager em;

    /**
     * Find members by diagnosis.
     *
     * @param diagnosisDescription the diagnosis description
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List<Member> findMembersByDiagnosis(String diagnosisDescription) {
    	
    	Query query = em.createQuery(FIND_MEMBERS_BY_DIAGNOSIS);
    	query.setParameter("diagnosisDescription", diagnosisDescription);
    	
        return (List<Member>)query.getResultList();
    }

}
