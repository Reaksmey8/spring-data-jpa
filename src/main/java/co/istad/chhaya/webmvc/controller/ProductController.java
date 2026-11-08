package co.istad.chhaya.webmvc.controller;

import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.dto.ProductDto;
import co.istad.chhaya.webmvc.dto.UpdateStatusRequest;
import co.istad.chhaya.webmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;   // Inject productService
    // CREUD operation
    // 1. Get all Products with Pagination
    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        log.info("getAllProduct request: pageNumber={}, pageSize={}", pageNumber, pageSize);
        return productService.getAll(pageNumber, pageSize); // 👈 Call productService
    }

    // 2, Get product by code
    @GetMapping("/{code}")
    public ProductResponse getProductById(@PathVariable String code){
        log.info("getProductByCode: {} ", code );
        return productService.getByCode(code);
    }
    // 3. Create new product (POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNew(
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        log.info("createNew product: {}", createProductRequest);
        return productService.createNew(createProductRequest); //  Call productService
    }

    // 4. Update product (PUT)
    @PutMapping("/{code}/status")
    public ProductResponse updateStatusByCode(
             @PathVariable String code ,
             @Valid @RequestBody
            UpdateStatusRequest updateStatusRequest
            ){
        log.info("updateStatusByCode: {}", code, updateStatusRequest );
        return productService.updateStatusByCode(code, updateStatusRequest);
    }

    // 5. Delete product (DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code){
        log.info("deleteByCode: {}", code);
        productService.deleteByCode(code);
    }
    // 6, Patch product
    @PatchMapping("/{code}")
    public ProductResponse patchByCode(
           @Valid @PathVariable String code,
            @RequestBody
            ProductDto productDto
    ){
        log.info("patchByCode: {}" , code, productDto);
        return productService.patchByCode(code, productDto);
    }
    // search by name
    @GetMapping("/search")
    public Page<ProductResponse> searchByName(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ){
        return productService.searchByName(name, pageNumber, pageSize);
    }

}
