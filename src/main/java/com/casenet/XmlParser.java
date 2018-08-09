// Copyright (c) 2011 by CaseNET, LLC
//
// This file is protected by Federal Copyright Law, with all rights
// reserved. No part of this file may be reproduced, stored in a
// retrieval system, translated, transcribed, or transmitted, in any
// form, or by any means manual, electric, electronic, mechanical,
// electro-magnetic, chemical, optical, or otherwise, without prior
// explicit written permission from CaseNET, LLC.
package com.casenet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The Class XmlParser.
 */
@Service
public class XmlParser {
	
    /** The Constant MEMBER_TAG. */
    private static final String MEMBER_TAG = "member";
	
	/** The Constant NAME_TAG. */
	private static final String NAME_TAG = "name";
	
	/** The Constant ADDRESS_TAG. */
	private static final String ADDRESS_TAG = "address";
	
	/** The Constant STREET_TAG. */
	private static final String STREET_TAG = "street";
	
	/** The Constant NUMBER_TAG. */
	private static final String NUMBER_TAG = "number";
	
	/** The Constant DIAGNOSIS_TAG. */
	private static final String DIAGNOSIS_TAG = "diagnosis";
	
	/** The casenet xml. */
    @Value("casenet.xml")
    Resource casenetXml;

    /**
     * Find diagnoses.
     *
     * @return the list
     */
    public List<Member> findDiagnoses() {

    	List<Member> memberList = new ArrayList<Member>();
    	
    	try {
			InputStream inputStream = casenetXml.getInputStream();
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			
			NodeList nodes = document.getElementsByTagName(MEMBER_TAG);
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Member member = new Member();
				List<Address> addressList = new ArrayList<Address>();
				Set<Diagnosis> diagnosisSet = new HashSet<Diagnosis>();
				
				Element memberElement = (Element) nodes.item(i);
				
				NodeList nameNodeList = memberElement.getElementsByTagName(NAME_TAG);
				Element nameElment = (Element) nameNodeList.item(0);
				member.setName(nameElment.getFirstChild().getTextContent());
				
				// Add addresses to member
				addAddressesToMember(member, addressList, memberElement);
				
				// Add diagnosis to member
				addDianosisToMember(member, diagnosisSet, memberElement);
				
				memberList.add(member);
			}
			
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new RuntimeException("Error while parsing XML", e);
		}
    	
        return memberList;
    }

	/**
	 * Adds the addresses to member.
	 *
	 * @param member the member
	 * @param addressList the address list
	 * @param memberElement the member element
	 */
	private void addAddressesToMember(Member member, List<Address> addressList, Element memberElement) {
		NodeList addressesNodeList = memberElement.getElementsByTagName(ADDRESS_TAG);
		for (int j = 0; j < addressesNodeList.getLength(); j++) {
			Address address = new Address();
			
			NodeList streetNodeList = memberElement.getElementsByTagName(STREET_TAG);
			Element streetElement = (Element) streetNodeList.item(j);
			address.setStreet(streetElement.getFirstChild().getTextContent());
			
			NodeList numberNodeList = memberElement.getElementsByTagName(NUMBER_TAG);
			Element numberElement = (Element) numberNodeList.item(j);
			address.setNumber(Integer.parseInt(numberElement.getFirstChild().getTextContent()));
			
			addressList.add(address);
		}
		
		member.setAddresses(addressList);
	}

	/**
	 * Adds the dianosis.
	 *
	 * @param member the member
	 * @param diagnosisSet the diagnosis set
	 * @param memberElement the member element
	 */
	private void addDianosisToMember(Member member, Set<Diagnosis> diagnosisSet, Element memberElement) {
		NodeList diagnosisNodeList = memberElement.getElementsByTagName(DIAGNOSIS_TAG);
		for (int k= 0; k < diagnosisNodeList.getLength(); k++) {
			Diagnosis diagnosis = new Diagnosis();
			
			Element diagnosisElement = (Element) diagnosisNodeList.item(k);
			diagnosis.setDescription(diagnosisElement.getFirstChild().getTextContent());
			diagnosisSet.add(diagnosis);
		}
		
		member.setDiagnosis(diagnosisSet);
	}

}
