package com.medium.agrawalniket.object.comparator.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectComparatorUtil {

	private ObjectComparatorUtil() {

	}

	static final Logger logger = LoggerFactory.getLogger(ObjectComparatorUtil.class);

	static final Set<String> dtoPackages = new HashSet<>();

	// Add all packages which contains user defined pojo
	static {
		dtoPackages.add("com.medium.agrawalniket.object.comparator.dto");
		dtoPackages.add("com.medium.agrawalniket.object.comparator.entity");
	}

	public static List<String> compareAndGetDiff(Object firstObj, Object secondObj, String parentName) {
		try {

			// Check if both comparison objects are of same type
			if (Objects.nonNull(firstObj) && Objects.nonNull(secondObj)
					&& !(firstObj.getClass().getName().equals(secondObj.getClass().getName()))) {
				return Collections.emptyList();
			}

			List<String> diffValFieldList = new ArrayList<>();
			logger.info("Comparison started for Object: {}", firstObj.getClass().getSimpleName());

			for (Field field : firstObj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				String fieldName = parentName + firstObj.getClass().getSimpleName() + "." + field.getName();
				Object value1 = field.get(firstObj);
				Object value2 = field.get(secondObj);

				if (Objects.isNull(value1) && Objects.isNull(value2)) {
					// If field is null in both object then move to next field for comparison
					logger.info("{} is null for both objects", fieldName);
					continue;
				} else if (Objects.isNull(value1) || Objects.isNull(value2)) {
					// If one of the object is having null value then add it changed properties
					diffValFieldList.add(fieldName);
					continue;
				}

				// Logic to check if field is user defined object , if yes call recursively to
				// compare fields of User Object
				String value1Class = value1.getClass().getName();
				String value2Class = value2.getClass().getName();

				if (value1Class.equals(value2Class) && validateUserDefinedObj(value1.getClass().getName())) {
					logger.info(
							"{} is a user defined object, so calling same function again to compare independent object",
							value1.getClass().getName());
					diffValFieldList.addAll(compareAndGetDiff(value1, value2,
							parentName.concat(firstObj.getClass().getSimpleName() + ".")));
				} else if (!Objects.equals(value1, value2)) {
					logger.info("{} field value is different in both objects", fieldName);
					diffValFieldList.add(fieldName);
				}

			}
			return diffValFieldList;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("Error while comparing objects");
		}
		return Collections.emptyList();
	}

	/**
	 * Method to check if comparison object is an User Object from DTO packages
	 */
	private static boolean validateUserDefinedObj(String dtoPackageName) {
		for (String packageName : dtoPackages) {
			if (dtoPackageName.startsWith(packageName)) {
				return true;
			}
		}

		return false;
	}
}
