package org.koreait.models.member;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Users;
import org.koreait.repositories.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repository.findByUserId(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(users.getType().name()));

        return MemberInfo.builder()
                .userNo(users.getUserNo())
                .userId(users.getUserId())
                .userPw(users.getUserPw())
                .userNm(users.getUserNm())
                .authorities(authorities)
                .build();
    }
}