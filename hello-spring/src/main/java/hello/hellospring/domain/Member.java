package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //JPA가 관리하는 엔티티
public class Member {

    //@Id << 얘는 PK야
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 알아서 생성해주는 오토인크리먼트같은거를 IDENTITIY라 칭함
    private Long id;

    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
