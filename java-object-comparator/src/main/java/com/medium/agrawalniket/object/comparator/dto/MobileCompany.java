package com.medium.agrawalniket.object.comparator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class MobileCompany {

	private String companyName;
	private Integer startYear;
	private String founderName;
}
