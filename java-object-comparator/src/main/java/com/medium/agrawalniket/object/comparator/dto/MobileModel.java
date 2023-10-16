package com.medium.agrawalniket.object.comparator.dto;

import com.medium.agrawalniket.object.comparator.entity.MobileApplications;
import com.medium.agrawalniket.object.comparator.util.EnumConstants.OperatingSystem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class MobileModel {

	private String modelName;
	private Double version;
	private boolean availablity;
	private Long modelPrice;
	private OperatingSystem osSystem;
	private MobileApplications mobileApps;

}

