package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    private MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberservice = " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createFrom() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
        //회원가입이 끝나면 홈화면으로 돌려보내는 코드
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();//List형식으로 members 라는 객체에 모든 회원데이터를 담아놨고
        model.addAttribute("members", members);  //model 이라는 객체에 members라는 키에 members(회원데이터를) addAttribute를 통해 담아놓았음
        return "members/memberList";
    }
}
