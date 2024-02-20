package com.example.weatherootd.reposotory;

import com.example.weatherootd.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private static final ConcurrentHashMap<String, Member> store = new ConcurrentHashMap<>();


    public Member save(Member member){
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(String memberId){
        Member member = store.get(memberId);
        if(member != null){
            return member;
        }
        return null;
    }

    public Member findByName(String memberName){
        for (Member member : store.values()) {
            if(Objects.equals(memberName, member.getName()))
                return member;
        }
        return null;
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(String memberId, Member inputParam){
        Member findMember = findById(memberId);
        findMember.setName(inputParam.getName());
        findMember.setAge(inputParam.getAge());
        findMember.setSex(inputParam.getSex());
        findMember.setStyle(inputParam.getStyle());
    }

    public void delete(String memberId){
        store.remove(memberId);
    }

    public void clearStore(){
        store.clear();
    }
}
