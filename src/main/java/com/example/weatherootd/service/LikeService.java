package com.example.weatherootd.service;

import com.example.weatherootd.domain.Like;
import com.example.weatherootd.domain.Member;
import com.example.weatherootd.domain.Ootd;
import com.example.weatherootd.reposotory.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    /**
     * like 설정
     * @param member
     * @param ootd
     */
    public void like(Member member, Ootd ootd){
        Like like = new Like();
        like.setMember(member);
        like.setOotd(ootd);

        likeRepository.save(like);
    }

    /**
     * like 취소
     * @param member
     * @param ootd
     */
    public void unlike(Member member, Ootd ootd){
        Like like = likeRepository.findMemberAndOotd(member, ootd);
        likeRepository.delete(like);
    }

    /**
     * 해당 멤버의 like한 ootd
     * @param member
     * @return
     */
    public List<Ootd> getMembersLike(Member member){
        return likeRepository.findOotdByMember(member);
    }

}
