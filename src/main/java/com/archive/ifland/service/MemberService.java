package com.archive.ifland.service;

import com.archive.ifland.controller.MemberForm;
import com.archive.ifland.dto.response.AuthResponse;

public interface MemberService {

  void createMember(MemberForm memberForm);

  AuthResponse sendEmailForNewPassword(String account);

}
