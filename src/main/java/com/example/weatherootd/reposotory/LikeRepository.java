package com.example.weatherootd.reposotory;

import com.example.weatherootd.domain.Like;
import com.example.weatherootd.domain.Member;
import com.example.weatherootd.domain.Ootd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class LikeRepository {
    private static final ConcurrentHashMap<Long, Like> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(1);

    public Like save(Like like){
        like.setId(sequence.getAndIncrement());
        store.put(like.getId(), like);
        return like;
    }

    public Like findById(Long likeId){
        Like like = store.get(likeId);
        if(like != null){
            return like;
        }
        throw new IllegalArgumentException("존재하지 않는 ID");
    }

    private List<Like> findAll(){
        return new ArrayList<>(store.values());
    }

    public List<Ootd> findOotdByMember(Member member){
        ArrayList<Ootd> ootdArrayList = new ArrayList<>();

        for (Like like : store.values()) {
            if(member.equals(like.getMember())){
                ootdArrayList.add(like.getOotd());
            }
        }

        return ootdArrayList;
    }

    public Like findMemberAndOotd(Member member, Ootd ootd){
        for (Like like : store.values()) {
            if(member.equals(like.getMember()) && ootd.equals(like.getOotd())){
                return like;
            }
        }
        throw new IllegalArgumentException("해당 like를 찾을 수 없음");
    }

    public void delete(Like like){
        store.remove(like.getId());
    }
}
