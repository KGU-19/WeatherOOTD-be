package com.example.weatherootd.service;

import com.example.weatherootd.domain.Ootd;
import com.example.weatherootd.reposotory.OotdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OotdService {
    private final OotdRepository ootdRepository;

    /**
     * 추가
     * @param ootd
     * @return
     */
    public Long add(Ootd ootd){
        ootdRepository.save(ootd);
        return ootd.getId();
    }

    //삭제
    public void delete(Ootd ootd){
        ootdRepository.delete(ootd);
    }

    //변경
    public Long update(Ootd ootd){
        ootdRepository.update(ootd.getId(), ootd);
        return ootd.getId();
    }

}
