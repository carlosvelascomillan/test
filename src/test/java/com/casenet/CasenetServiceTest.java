// Copyright (c) 2011 by CaseNET, LLC
//
// This file is protected by Federal Copyright Law, with all rights
// reserved. No part of this file may be reproduced, stored in a
// retrieval system, translated, transcribed, or transmitted, in any
// form, or by any means manual, electric, electronic, mechanical,
// electro-magnetic, chemical, optical, or otherwise, without prior
// explicit written permission from CaseNET, LLC.
package com.casenet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Class CasenetServiceTest.
 */
public class CasenetServiceTest extends BaseTest {

    /** The casenet service. */
    @Autowired
    CasenetService casenetService;

    /**
     * Test find members by diagnoses from xml.
     */
    @Test
    public void testFindMembersByDiagnosesFromXml() {

    	// Arrange
    	String colorBlindness = "color blindness";
    	String fatness = "fatness";
    	String notFound = "notFound";
    	
		// Act
    	Set<Member> membersColorBlindness = casenetService.findMembersByDiagnosesFromXml(colorBlindness);
    	Set<Member> membersFatness = casenetService.findMembersByDiagnosesFromXml(fatness);
    	Set<Member> membersNotFound	= casenetService.findMembersByDiagnosesFromXml(notFound);
    	
    	// Assert
    	Assert.assertEquals(membersColorBlindness.size(), 1);
    	
    	Member memberColorBlindness = membersColorBlindness.iterator().next();
    	Assert.assertEquals(memberColorBlindness.getName(), "Michal");
    	Assert.assertEquals(memberColorBlindness.getAddresses().size(), 1);
    	Assert.assertEquals(memberColorBlindness.getAddresses().get(0).getStreet(), "Hybernska");
    	Assert.assertEquals(memberColorBlindness.getAddresses().get(0).getNumber().intValue(), 24);
    	Assert.assertEquals(memberColorBlindness.getDiagnosis().size(), 1);
    	Assert.assertEquals(memberColorBlindness.getDiagnosis().iterator().next().getDescription(), "color blindness");
    	
    	Assert.assertEquals(membersFatness.size(), 1);
    	
    	Member memberFatness = membersFatness.iterator().next();
    	Assert.assertEquals(memberFatness.getName(), "Jarek");
    	Assert.assertEquals(memberFatness.getDiagnosis().size(), 2);
    	Assert.assertEquals(memberFatness.getAddresses().size(), 1);
    	Assert.assertEquals(memberFatness.getAddresses().get(0).getStreet(), "Petyrkova");
    	Assert.assertEquals(memberFatness.getAddresses().get(0).getNumber().intValue(), 1);
    	Assert.assertEquals(memberFatness.getDiagnosis().size(), 2);
    	
    	Diagnosis diagnosisFatness = new Diagnosis();
		diagnosisFatness.setDescription("fatness");
		
		Diagnosis diagnosisDeafness = new Diagnosis();
		diagnosisDeafness.setDescription("deafness");
		
    	Assert.assertTrue(memberFatness.getDiagnosis().contains(diagnosisFatness));
    	Assert.assertTrue(memberFatness.getDiagnosis().contains(diagnosisDeafness));
		
    	Assert.assertEquals(membersNotFound.size(), 0);
    	
    }

}
