package com.udemy.controller;


import com.udemy.model.Product;
import com.udemy.service.ProductService;
import com.udemy.value.object.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Endpoints")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @Operation(summary = "Find all products")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @GetMapping
    public Page<Product> findAllProduct(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10")Integer limit) {
        return service.findAllProducts(page, limit);
    }


    @Operation(summary = "Find product by id")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @GetMapping("/find/{id}")
    public Product findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
       return service.createProduct(product);
    }

    @Operation(summary = "Update a product")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }
}
