package com.udemy.controller;


import com.udemy.repository.dto.CardDTO;
import com.udemy.util.CustomUtils;
import com.udemy.vo.PokemonVO;
import com.udemy.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = { "/pokemon", "/pokemon/"})
@Tag(name = "Card Pokemon", description = "cards details")
public class MyApiController {



    @Autowired
    private PokemonService service;
    private final Logger logger = Logger.getLogger(MyApiController.class.getName());

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find all cards", tags = {"Cards"})
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PokemonVO.class))) )
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ArrayList<PokemonVO> findAll(){
        logger.info("Searching all data from pokemon table");
        return CustomUtils.CustomModelMapper.mappingList(service.findAllPokemon());
    }

    @Operation(summary = "Insert a new row of card")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = PokemonVO.class)) )
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public PokemonVO createCardDatabase(@RequestBody PokemonVO actual){
        logger.info("A criar uma nova inserção na tabela de pokemons");
        CardDTO entity = CustomUtils.CustomModelMapper.mapping(actual);
        return CustomUtils.CustomModelMapper.mapping(service.createRowPokemon(entity));
    }

    @Operation(summary = "Search a card by her id")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = PokemonVO.class)) )
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping(value = "/{index}")
    public PokemonVO findById(@PathVariable Integer index){
        logger.info("finding card by id");
        return CustomUtils.CustomModelMapper.mapping(service.findByIndexPokemon(index));
    }
}
