package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional//test에 있으면 db rollback 한다
    @Rollback(value = false)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("memberA");
        //when
        Long saveId = memberRepository.save(member);
        System.out.println("saveId = " + saveId);
        System.out.println("member = " + member.getId());
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("(findMember==member) =: " + (findMember==member));


    }

}