package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy(); //find...By '...' 에 설명 문자가 들어가도 된다.

    List<Member> findTop3HelloBy(); //Top 3개만
}
