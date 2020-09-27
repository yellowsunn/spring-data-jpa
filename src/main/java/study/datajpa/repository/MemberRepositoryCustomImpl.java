package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
// 구현 클래스의 이름은 반드시 "DataJpa 인터페이스 이름 + 'Impl'" or "사용자 정의 인터페이스 이름 + 'Impl'"
// 즉, MemberRepositoryImpl or MemberRepositoryCustomImpl 이어야 한다.
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
