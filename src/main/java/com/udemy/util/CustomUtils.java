package com.udemy.util;

import com.udemy.dto.CardDTO;
import com.udemy.model.CardModel;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomUtils {

    public static class CustomModelMapper {

        static ModelMapper mapper = new org.modelmapper.ModelMapper();

        public static CardDTO mapping (CardModel data){
            return mapper.map(data ,CardDTO.class);
        }

        public static CardModel mapping (CardDTO cardDTO){
            return mapper.map(cardDTO, CardModel.class);
        }

        public static ArrayList<CardDTO> mappingList (List<CardModel> dataList) {
            ArrayList<CardDTO> list = new ArrayList<>();
            dataList.forEach( card -> list.add(mapping(card)) );
            return list;
        }

    }

}
