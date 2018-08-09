// Copyright (c) 2011 by CaseNET, LLC
//
// This file is protected by Federal Copyright Law, with all rights
// reserved. No part of this file may be reproduced, stored in a
// retrieval system, translated, transcribed, or transmitted, in any
// form, or by any means manual, electric, electronic, mechanical,
// electro-magnetic, chemical, optical, or otherwise, without prior
// explicit written permission from CaseNET, LLC.
package com.casenet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * The Class CasenetService.
 */
@Service
public class CasenetService {

	/** The xml parser. */
	@Autowired
	private XmlParser xmlParser;
	
    /**
     * Find members by diagnoses from xml.
     *
     * @param diagnosisDescription the diagnosis description
     * @return the sets the
     */
    public Set<Member> findMembersByDiagnosesFromXml(String diagnosisDescription) {
    	
    	Set<Member> memberSet = new HashSet<Member>();
    	
    	List<Member> memberList = xmlParser.findDiagnoses();
    	if (!CollectionUtils.isEmpty(memberList)) {
    		for (Member member : memberList) {
				for(Diagnosis diagnosis : member.getDiagnosis()) {
					if (diagnosis.getDescription().contains(diagnosisDescription)) {
						memberSet.add(member);
					}
				}
			}
    	}
        return memberSet;
    }

}
