package com.archive.ifland.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
public class PageDto {

  private int currentPage;

  private int startPage;

  private int endPage;

  private int totalPages;

  private long totalContents;

  private boolean hasPrevious;

  private boolean hasNext;

  public PageDto(Page<?> page, Pageable pageable) {
    currentPage = pageable.getPageNumber();
    totalPages = page.getTotalPages();
    totalContents = page.getTotalElements();
    endPage = (int) Math.ceil((currentPage + 1) / 10.0) * 10;   // 10페이지씩 끊어서
    startPage = endPage - 9;    // 1부터 시작해야함

    hasPrevious = startPage != 1;
    hasNext = endPage < totalPages;

    endPage = Math.min(endPage, totalPages);    // 전체페이지와 비교하여 진짜 마지막 페이지 셋팅
  }

}
