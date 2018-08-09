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

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Class MemberRepositoryTest.
 */
public class MemberRepositoryTest extends BaseTest {

    /** The member repository. */
    @Autowired
    private MemberRepository memberRepository;

    /**
     * Test find members by diagnosis.
     */
    @Test
    public void testFindMembersByDiagnosis() {
    	
    	// Arrange
    	String colorBlindness = "color blindness";
    	String fatness = "fatness";
    	String notFound = "notFound";
    	
		// Act
    	List<Member> membersColorBlindness = memberRepository.findMembersByDiagnosis(colorBlindness);
    	List<Member> membersFatness = memberRepository.findMembersByDiagnosis(fatness);
    	List<Member> membersNotFound = memberRepository.findMembersByDiagnosis(notFound);
    	
    	// Assert
    	Assert.assertEquals(membersColorBlindness.size(), 2);
    	
    	Assert.assertEquals(membersFatness.size(), 1);
    	Assert.assertEquals(membersFatness.get(0).getName(), "Jarek");
    	Assert.assertEquals(membersFatness.get(0).getDiagnosis().size(), 2);
    	
    	Assert.assertEquals(membersNotFound.size(), 0);
    }

}
