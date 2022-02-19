package com.archive.ifland.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class SNS {

  private String instagram;

  private String facebook;

  private String blog;

}
