package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 회원을 저장하면 저장된 회원이 반환된다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); //Optional 이라는건 java 8 부터 생겨난 기능인데 findById나 findByName을 수행할때 NULL 값일때 Optional이라는걸로 감싸서 반환하는걸 선호하게됨
    List<Member> findAll(); //저장되어있는 회원리스트를 모두 반환

}
