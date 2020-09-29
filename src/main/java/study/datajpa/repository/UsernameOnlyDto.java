package study.datajpa.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UsernameOnlyDto {

    private final String username;
}
