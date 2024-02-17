package com.example.weatherootd.reposotory;

import com.example.weatherootd.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private static final ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(1);


    public Member save(Member member){
        member.setId(sequence.getAndIncrement());
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long memberId){
        Member member = store.get(memberId);
        if(member != null){
            return member;
        }
        throw new IllegalArgumentException("존재하지 않는 ID");
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long memberId, Member inputParam){
        Member findMember = findById(memberId);
        findMember.setName(inputParam.getName());
        findMember.setAge(inputParam.getAge());
        findMember.setSex(inputParam.getSex());
        findMember.setStyle(inputParam.getStyle());
    }

    public void clearStore(){
        store.clear();
    }
}
