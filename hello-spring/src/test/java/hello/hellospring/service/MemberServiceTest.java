package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    //테스트 파일 메소드명은 과감하게 한글로 바꿔도됨..
    //메모리 클리어 할려고 만든 객체
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void aferEach() {
        memberRepository.clearStore();
    }




    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given 이런 상황이주어져서
        Member member1 = new Member();
        member1.setName("duplicate");

        Member member2 = new Member();
        member2.setName("duplicate");


       //when 이게 실행됐을때
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //예외처리 에러메세지 일치 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //이미 있는 이름을 회원가입하려고했을때 예외가발생해야함 예외가발생하지않으면 fail메서드 실행
//
//       try {
//           memberService.join(member2);
//           fail();
//
//       } catch (IllegalStateException e) {
//           assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//       }
        //then 이게 나와야돼

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}