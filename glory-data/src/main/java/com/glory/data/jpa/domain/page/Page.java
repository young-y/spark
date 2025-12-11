/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.page;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/17
 * @descprition :
 *
 */

public class Page {
	private int number;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean next;
	private boolean desc = true;
	private List<String> sortProperties;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public boolean hasNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public List<String> getSortProperties() {
		return sortProperties;
	}

	public void setSortProperties(List<String> sortProperties) {
		if (!CollectionUtils.isEmpty(sortProperties)) {
			if (null == this.sortProperties) {
				this.sortProperties = new ArrayList<>();
			}
			this.sortProperties.addAll(sortProperties);
		}
	}

	@JsonIgnore
	public Page addSortProperty(String property) {
		if (StringUtils.hasLength(property)) {
			if (null == this.sortProperties) {
				this.sortProperties = new ArrayList<>();
			}
			this.sortProperties.add(property);
		}
		return this;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@JsonIgnore
	public int nextPageNumber() {
		if (number + 1 >= getTotalPages()) {
			return number;
		}
		return number + 1;
	}

	@JsonIgnore
	public PageRequest createPageRequest() {
		if (CollectionUtils.isEmpty(sortProperties)) {
			return PageRequest.of(number, size);
		} else {
			return PageRequest.of(number, size, desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortProperties.toArray(String[]::new));
		}
	}

	@JsonIgnore
	public void initPageBase(Page page) {
		Optional.ofNullable(page).ifPresent(p -> {
			setDesc(p.desc);
			setNumber(p.number);
			setSize(p.size);
			setSortProperties(p.sortProperties);
		});
	}
}
