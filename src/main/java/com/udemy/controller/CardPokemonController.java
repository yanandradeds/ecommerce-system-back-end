package com.udemy.controller;


import com.udemy.dto.CardDTO;
import com.udemy.service.CardRepositoryService;
import com.udemy.util.CustomUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
@Tag(name = "Card Pokemon", description = "cards details")
public class CardPokemonController {


    @Autowired
    private CardRepositoryService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find all cards")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CardDTO.class))) )
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Page<CardDTO>> fetchAllCard(@RequestParam(value = "limit", defaultValue = "12") Integer limit,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page){
        return ResponseEntity.ok(service.findAllCards(page,limit));
    }

    @Operation(summary = "Insert a new row of card")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CardDTO.class)) )
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public CardDTO createCard(@RequestBody CardDTO body){
        service.createCard(CustomUtils.CustomModelMapper.mapping(body));
        return body;
    }

    @Operation(summary = "Search a card by her id")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CardDTO.class)) )
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @GetMapping(value = "/{id}")
    public CardDTO findById(@PathVariable Integer id){
        return CustomUtils.CustomModelMapper.mapping(service.findByIdCard(id));
    }

    @PostMapping(value = "/update")
    @Operation(summary = "Update some data in card")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CardDTO.class)) )
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    public void updateCard(@RequestBody CardDTO body){
        service.updateCard(body);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete a card by your id")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    public void deleteCardById(@PathVariable Integer id){
        service.deleteRowCard(id);
    }

}
