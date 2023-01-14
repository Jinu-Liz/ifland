package com.archive.ifland.service;

import com.archive.ifland.controller.MemberForm;

public interface MemberService {

  void createMember(MemberForm memberForm);

  void sendEmailForNewPassword(String account);

}
