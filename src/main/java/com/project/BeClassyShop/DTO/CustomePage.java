package com.project.BeClassyShop.DTO;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CustomePage<T> {
	 private List<T> content;
	 private CustomPageable pageable;

	public CustomePage(Page<T> page) {
		this.content = page.getContent();
		this.pageable = new CustomPageable(page.getPageable().getPageNumber(), page.getPageable().getPageSize(),
				page.getTotalElements());
	}

	@Data
	private class CustomPageable {
		private int pageNumber;
		private int pageSize;
		private long totalElements;

		public CustomPageable(int pageNumber, int pageSize, long totalElements) {
			this.pageNumber = pageNumber;
			this.pageSize = pageSize;
			this.totalElements = totalElements;
		}
	}
}
