package com.example.weatherootd.reposotory;

import com.example.weatherootd.domain.Ootd;
import com.example.weatherootd.domain.Style;
import com.example.weatherootd.domain.WeatherType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class OotdRepository {
    private static final ConcurrentHashMap<Long, Ootd> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(1);

    public Ootd save(Ootd ootd){
        ootd.setId(sequence.getAndIncrement());
        store.put(ootd.getId(), ootd);
        return ootd;
    }

    public Ootd findById(Long ootdId){
        Ootd ootd = store.get(ootdId);
        if(ootd != null){
            return ootd;
        }
        throw new IllegalArgumentException("존재하지 않는 ID");
    }


    public List<Ootd> findAll(){
        return new ArrayList<>(store.values());
    }

    public List<Ootd> findAllByTemperature(int temperature) throws NoSuchObjectException {
        if(temperature > 40 || temperature < -30)
            throw new IllegalArgumentException("찾을 수 없는 온도");

        ArrayList<Ootd> ootdArrayList = new ArrayList<>();
        for (Ootd ootd : store.values()) {
            int abs = Math.abs(ootd.getTemperature() - temperature);
            if(abs < 5){
                ootdArrayList.add(ootd);
            }
        }
        if(ootdArrayList.isEmpty())
            throw new NoSuchObjectException("해당 OOTD를 찾을 수 없음");

        return ootdArrayList;
    }

    public List<Ootd> findAllByStyle(Style style) throws NoSuchObjectException {
        ArrayList<Ootd> ootdArrayList = new ArrayList<>();
        for (Ootd ootd : store.values()) {
            if(ootd.getStyle() == style){
                ootdArrayList.add(ootd);
            }
        }

        if(ootdArrayList.isEmpty())
            throw new NoSuchObjectException("해당하는 OOTD를 찾을 수 없음");

        return ootdArrayList;

    }

    public List<Ootd> findAllByWeatherType(WeatherType weatherType) throws NoSuchObjectException {
        ArrayList<Ootd> ootdArrayList = new ArrayList<>();
        for (Ootd ootd : store.values()) {
            if(ootd.getWeatherType() == weatherType){
                ootdArrayList.add(ootd);
            }
        }

        if(ootdArrayList.isEmpty())
            throw new NoSuchObjectException("해당하는 OOTD를 찾을 수 없음");

        return ootdArrayList;
    }


    public void update(Long ootdId, Ootd ootd){
        Ootd findOotd = findById(ootdId);
        findOotd.setStyle(ootd.getStyle());
        findOotd.setTemperature(ootd.getTemperature());
        findOotd.setWeatherType(ootd.getWeatherType());
        findOotd.setDateTime(ootd.getDateTime());
    }

    public void clearStore(){
        store.clear();
    }


}
