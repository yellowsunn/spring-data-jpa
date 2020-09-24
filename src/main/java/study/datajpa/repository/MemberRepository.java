package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy(); //find...By '...' 에 설명 문자가 들어가도 된다.

    List<Member> findTop3HelloBy(); //Top 3개만

//    @Query(name = "Member.findByUsername")
    // 지워도 먼저 Named 쿼리로 찾고, 그 다음 메소드 이름으로 쿼리를 생성한다.
    // named 쿼리는 실무에서 거의 사용 X, 애플리케이션 로딩 시점에서 문법 체킹을 하는 장점이 있음
    List<Member> findByUsername(@Param("username") String username);
}
