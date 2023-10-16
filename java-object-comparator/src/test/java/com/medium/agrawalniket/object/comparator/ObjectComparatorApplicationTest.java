package com.medium.agrawalniket.object.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import com.medium.agrawalniket.object.comparator.dto.MobileCompany;
import com.medium.agrawalniket.object.comparator.dto.MobileModel;
import com.medium.agrawalniket.object.comparator.entity.MobileApplications;
import com.medium.agrawalniket.object.comparator.util.ObjectComparatorUtil;
import com.medium.agrawalniket.object.comparator.util.EnumConstants.OperatingSystem;

public class ObjectComparatorApplicationTest {


	@Test
	public void testSuccessComparison() {

		MobileCompany oneplusCompany = MobileCompany.builder().setCompanyName("Oneplus").setStartYear(2000).build();
		MobileCompany appleCompany = MobileCompany.builder().setCompanyName("Apple").setStartYear(1980)
				.setFounderName("Steve Jobs").build();

		MobileApplications androidApps = MobileApplications.builder().setAppNameList(Arrays.asList("App1", "App2"))
				.setMobileCompany(oneplusCompany).build();
		MobileApplications appleApps = MobileApplications.builder()
				.setAppNameList(Arrays.asList("App1", "App2", "App3")).setMobileCompany(appleCompany).build();

		MobileModel androidModel = MobileModel.builder().setAvailablity(true).setModelName("OnePlus7")
				.setModelPrice(30000L).setOsSystem(OperatingSystem.ANDROID).setVersion(1.0).setMobileApps(androidApps)
				.build();

		MobileModel appleModel = MobileModel.builder().setAvailablity(true).setModelName("Apple6").setModelPrice(70000L)
				.setOsSystem(OperatingSystem.APPLE).setVersion(1.0).setMobileApps(appleApps).build();

		List<String> output = ObjectComparatorUtil.compareAndGetDiff(androidModel, appleModel, "");
		assertEquals(7, output.size());
	}

	@Test
	public void testCompareDiffObjects() {
		MobileCompany oneplusCompany = MobileCompany.builder().setCompanyName("Oneplus").setStartYear(2000).build();
		MobileApplications androidApps = MobileApplications.builder().setAppNameList(Arrays.asList("App1", "App2"))
				.setMobileCompany(oneplusCompany).build();
		List<String> output = ObjectComparatorUtil.compareAndGetDiff(oneplusCompany, androidApps, "");
		assertEquals(0, output.size());
	}

	@Test
	public void testOneNullFieldComparison() {
		MobileCompany oneplusCompany = MobileCompany.builder().setCompanyName("Oneplus").setStartYear(2000).build();
		MobileCompany appleCompany = MobileCompany.builder().setCompanyName("Apple").setStartYear(1980)
				.setFounderName("Steve Jobs").build();
		List<String> output = ObjectComparatorUtil.compareAndGetDiff(appleCompany, oneplusCompany, "");
		assertTrue(output.contains("MobileCompany.founderName"));
	}

}
