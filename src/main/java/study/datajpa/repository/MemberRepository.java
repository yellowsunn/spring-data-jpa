package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy(); //find...By '...' 에 설명 문자가 들어가도 된다.

    List<Member> findTop3HelloBy(); //Top 3개만

//    @Query(name = "Member.findByUsername")
    // 지워도 먼저 Named 쿼리로 찾고, 그 다음 메소드 이름으로 쿼리를 생성한다.
    // named 쿼리는 실무에서 거의 사용 X, 애플리케이션 로딩 시점에서 문법 체킹을 하는 장점이 있음
    List<Member> findByUsername(@Param("username") String username);

    // 권장하는 기능
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username); //컬렉션
    Member findMemberByUsername(String username); //단건
    Optional<Member> findOptionalByUsername(String username); //단건 Optional

    Page<Member> findByAge(int age, Pageable pageable);
    Slice<Member> findSliceByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m where m.age = :age",
            countQuery = "select count(m.username) from Member m")  // count 쿼리 분리
    Page<Member> findCountQuery(@Param("age") int age, Pageable pageable);

    @Modifying(clearAutomatically = true)  //Insert, Update, Delete 와 같은 DDL 명령일 경우
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
}
