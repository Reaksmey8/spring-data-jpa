package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.domain.Product;
import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductDto;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.dto.UpdateStatusRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    // Define logic in here
        // search by name
    Page<ProductResponse> searchByName(String name, int pageNumber, int pageSize);
        //Delete by code
    void deleteByCode(String code);
        // Get by code
    ProductResponse getByCode(String code);
        //Put updateStatusByCode
    ProductResponse updateStatusByCode(String code, UpdateStatusRequest updateStatusRequest);
        // patch by code
    ProductResponse patchByCode(String code, ProductDto productDto);
        // creat new product
    ProductResponse createNew(CreateProductRequest createProductRequest);
        // get all product by pagination
    Page<ProductResponse> getAll(int pageNumber, int pageSize);
}
