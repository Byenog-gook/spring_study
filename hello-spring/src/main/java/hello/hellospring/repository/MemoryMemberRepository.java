package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 결과가없으면 null이거나 그렇게 될텐데 과거에는 그렇게했지만 요즘은 optional이라는걸로 깜사서 얘가 NULL이여도 감싸서 반환을해줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                    .filter(member -> member.getName().equals(name))
                    .findAny();
    }
    //Member에서 getName이 파라미터로 넘어온 name이랑 같은경우에만 filtering이 되고 그중에 찾으면 반환을 하는것임..
    //vlaues() 에 의해 루프를 다돌면서 찾아지면 반환하는데 끝까지없으면 Optinal 에 null이 포함되서 반환됨

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
