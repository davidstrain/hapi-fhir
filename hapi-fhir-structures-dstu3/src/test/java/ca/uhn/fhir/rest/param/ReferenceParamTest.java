package ca.uhn.fhir.rest.param;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.util.TestUtil;

public class ReferenceParamTest {

	private FhirContext ourCtx = FhirContext.forDstu3();

	@Test
	public void testWithResourceType() {
		
		ReferenceParam rp = new ReferenceParam();
		rp.setValueAsQueryToken(ourCtx, null, null, "Location/123");
		assertEquals("Location", rp.getResourceType());
		assertEquals("123", rp.getIdPart());
		assertEquals(null, rp.getQueryParameterQualifier());
		
	}
	
	@Test
	public void testWithResourceTypeAsQualifier() {
		
		ReferenceParam rp = new ReferenceParam();
		rp.setValueAsQueryToken(ourCtx, null, ":Location", "123");
		assertEquals("Location", rp.getResourceType());
		assertEquals("123", rp.getIdPart());
		assertEquals(null, rp.getQueryParameterQualifier());

	}


	@Test
	public void testWithResourceTypeAsQualifierAndChain() {

		ReferenceParam rp = new ReferenceParam();
		rp.setValueAsQueryToken(ourCtx, null, ":Location.name", "FOO");
		assertEquals("Location", rp.getResourceType());
		assertEquals("FOO", rp.getIdPart());
		assertEquals(":Location.name", rp.getQueryParameterQualifier());
		assertEquals("name", rp.getChain());

	}

	@AfterClass
	public static void afterClassClearContext() {
		TestUtil.clearAllStaticFieldsForUnitTest();
	}

}
