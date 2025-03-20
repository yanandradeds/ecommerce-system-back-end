package com.udemy.util;

import com.udemy.dto.CardDTO;
import com.udemy.model.Card;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomUtils {

    public static class CustomModelMapper {

        static ModelMapper mapper = new org.modelmapper.ModelMapper();

        public static CardDTO mapping (Card data){
            return mapper.map(data ,CardDTO.class);
        }

        public static Card mapping (CardDTO cardDTO){
            return mapper.map(cardDTO, Card.class);
        }

        public static ArrayList<CardDTO> mappingList (List<Card> dataList) {
            ArrayList<CardDTO> list = new ArrayList<>();
            dataList.forEach( card -> list.add(mapping(card)) );
            return list;
        }

    }

}
