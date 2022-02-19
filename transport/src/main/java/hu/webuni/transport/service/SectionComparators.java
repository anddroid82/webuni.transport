package hu.webuni.transport.service;

import java.util.Comparator;

import hu.webuni.transport.model.Section;

public class SectionComparators {

	public static Comparator<Section> createComparatorBySectionNumber() {
		return new Comparator<Section>() {
			@Override
			public int compare(Section o1, Section o2) {
				return o1.getNumber()-o2.getNumber();
			}
		};
	}
	
}
