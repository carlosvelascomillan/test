package com.casenet;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Class XmlParserTest.
 */
public class XmlParserTest extends BaseTest {

	/** The xml parser. */
	@Autowired
	private XmlParser xmlParser;
	
	/**
	 * Test find diagnoses.
	 */
	@Test
	public void testFindDiagnoses() {
		
		// Arrange & Act
		List<Member> members = xmlParser.findDiagnoses();
		
		// Assert
		Assert.assertEquals(members.size(), 2);
		
		Member member1 = members.get(0);
		Assert.assertEquals(member1.getName(), "Michal");
		Assert.assertEquals(member1.getAddresses().size(), 1);
		Assert.assertEquals(member1.getAddresses().get(0).getStreet(), "Hybernska");
		Assert.assertEquals(member1.getAddresses().get(0).getNumber().intValue(), 24);
		Assert.assertEquals(member1.getDiagnosis().size(), 1);
		Assert.assertEquals(member1.getDiagnosis().iterator().next().getDescription(), "color blindness");
		
		Member member2 = members.get(1);
		Assert.assertEquals(member2.getName(), "Jarek");
		Assert.assertEquals(member2.getAddresses().size(), 1);
		Assert.assertEquals(member2.getAddresses().get(0).getStreet(), "Petyrkova");
		Assert.assertEquals(member2.getAddresses().get(0).getNumber().intValue(), 1);
		Assert.assertEquals(member2.getDiagnosis().size(), 2);
		
		Set<Diagnosis> diagnosisSet = member2.getDiagnosis();
		Diagnosis diagnosisFatness = new Diagnosis();
		diagnosisFatness.setDescription("fatness");
		
		Diagnosis diagnosisDeafness = new Diagnosis();
		diagnosisDeafness.setDescription("deafness");
		
		Assert.assertTrue(diagnosisSet.contains(diagnosisFatness));
		Assert.assertTrue(diagnosisSet.contains(diagnosisDeafness));
	}
}
