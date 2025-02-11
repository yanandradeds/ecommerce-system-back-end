package com.udemy.util;

import com.udemy.repository.dto.CardDTO;
import com.udemy.vo.PokemonVO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomUtils {

    public static class CustomModelMapper {

        static ModelMapper mapper = new org.modelmapper.ModelMapper();

        public static PokemonVO mapping (CardDTO cardDTO){
            return mapper.map(cardDTO,PokemonVO.class);
        }

        public static CardDTO mapping (PokemonVO pokemonVO){
            return mapper.map(pokemonVO,CardDTO.class);
        }

        public static ArrayList<PokemonVO> mappingList (List<CardDTO> cardDTOList) {
            ArrayList<PokemonVO> list = new ArrayList<>();
            cardDTOList.forEach( card -> list.add(mapping(card)) );
            return list;
        }

    }

}
