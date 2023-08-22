package org.koreait.models.member;

import lombok.RequiredArgsConstructor;
import org.koreait.constants.UserType;
import org.koreait.controllers.member.JoinForm;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSaveService {
    private final UsersRepository repository;
    private final PasswordEncoder encoder;

    public void save(JoinForm joinForm) {
        Users users = new ModelMapper().map(joinForm, Users.class);
        users.setType(UserType.USER);

        users.setUserPw(encoder.encode(joinForm.getUserPw()));

        repository.saveAndFlush(users);
    }
}