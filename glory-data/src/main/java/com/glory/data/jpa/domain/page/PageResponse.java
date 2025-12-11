/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.page;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.domain.type.ResultStatus;
import jakarta.annotation.Nonnull;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author : YY
 * @date : 2025/11/18
 * @descprition :
 *
 */

public class PageResponse<E> {
	private Integer status = ResultStatus.Success.getValue();
	private List<E> elements;
	private List<String> messages;
	private Page page;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<E> getElements() {
		return elements;
	}

	public void setElements(@Nonnull List<E> elements) {
		if (null == this.elements) {
			this.elements = new ArrayList<>();
		}
		Optional.of(elements).ifPresent(this.elements::addAll);
	}

	@JsonIgnore
	public PageResponse<E> addElement(@Nonnull E ele) {
		if (null == this.elements) {
			this.elements = new ArrayList<>();
		}
		this.elements.add(ele);
		return this;
	}

	@JsonIgnore
	public boolean hasContent() {
		return !CollectionUtils.isEmpty(this.elements);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(@Nonnull List<String> messages) {
		if (null == this.messages) {
			this.elements = new ArrayList<>();
		}
		this.messages.addAll(messages);
	}

	@JsonIgnore
	public PageResponse<E> addMessage(@Nonnull String message) {
		if (null == this.messages) {
			this.elements = new ArrayList<>();
		}
		this.messages.add(message);
		return this;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@JsonIgnore
	public void initPage(Page page){
		Optional.ofNullable(page).ifPresent(p->{
			if (null == this.page){
				this.page = new Page();
			}
			this.page.initPageBase(p);
		});
	}

	@JsonIgnore
	public <T> List<T> map(@Nonnull Function<E, T> converter) {
		if (null == this.elements) {
			return null;
		}
		return this.elements.stream().map(converter).toList();
	}
}
