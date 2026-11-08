package co.istad.chhaya.webmvc.service;

import co.istad.chhaya.webmvc.domain.Category;
import co.istad.chhaya.webmvc.domain.Product;
import co.istad.chhaya.webmvc.dto.CreateProductRequest;
import co.istad.chhaya.webmvc.dto.ProductDto;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import co.istad.chhaya.webmvc.dto.UpdateStatusRequest;
import co.istad.chhaya.webmvc.mapper.ProductMapper;
import co.istad.chhaya.webmvc.repository.CategoryRepository;
import co.istad.chhaya.webmvc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service // tell spring like that it a service bean
@RequiredArgsConstructor // Lombok create constructor for inject repositories
public class ProductServiceImpl implements ProductService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

//    helper method for convert the products Entity to Product response
// Helper method for convert the products Entity to Product response
//private ProductResponse mapToProductResponse(Product product) {
//    return ProductResponse.builder()
//            .code(product.getCode())
//            .name(product.getName())
//            .description(product.getDescription())
//            .price(product.getPrice())
//            .picture(product.getPicture())
//            .isAvailable(product.getIsAvailable())
//            .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
//            .build();
//}


    @Override
    public Page<ProductResponse> searchByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return productRepository.findByNameContainingIgnoreCaseAndIsAvailableTrue(name, pageable)
                .map(productMapper::productToProductResponse);
    }

    @Override
    public void deleteByCode(String code) {
        Product foundProduct = productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("product code: %s not found" , code)
                ));
        productRepository.delete(foundProduct);
    }

    @Override
    public ProductResponse getByCode(String code) {
        return productRepository.findById(code)
                .map(productMapper::productToProductResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("product code: %s not found" , code)
                ));
    }

    @Override
    public ProductResponse updateStatusByCode(String code, UpdateStatusRequest updateStatusRequest) {
        // TODO:
        Product foundProduct = productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("product code: %s not found" , code)
                ));
        // if available
        foundProduct.setIsAvailable(updateStatusRequest.status());
        Product updateProduct = productRepository.save(foundProduct);

        return productMapper.productToProductResponse(updateProduct);
    }

    @Override
    public ProductResponse patchByCode(String code, ProductDto productDto) {
        Product foundProduct = productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("product code: %s not found", code)
                ));
        // check condition
        if (productDto.name() != null)
            foundProduct.setName(productDto.name());

        if (productDto.description() != null)
            foundProduct.setDescription(productDto.description());

        if (productDto.price() != null)
            foundProduct.setPrice(productDto.price());

        if (productDto.picture() != null)
            foundProduct.setPicture(productDto.picture());

        if (productDto.categoryId() != null) {
            Category category = categoryRepository
                    .findById(productDto.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Category with id: " + productDto.categoryId() + " not found"
                    ));
            foundProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.save(foundProduct);

        return productMapper.productToProductResponse(updatedProduct);
    }

    @Override
    public ProductResponse createNew(CreateProductRequest createProductRequest) {
        // 1. find category by categoryID
        Category category = categoryRepository.findById(createProductRequest.categoryId())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category with id: " + createProductRequest.categoryId() + "not found"
                        )
                );
        // 2. Create New Product Entity
        Product product = new Product();
        int randomNumber = new java.util.Random().nextInt(90000) + 10000; // បង្កើតលេខ ៥ ខ្ទង់ (10000 - 99999)
        product.setCode("ISTAD-PRO-" + randomNumber);
        product.setName(createProductRequest.name());
        product.setDescription(createProductRequest.description());
        product.setPrice(createProductRequest.price());
        product.setPicture(createProductRequest.picture());
        product.setIsAvailable(true);
        product.setCategory(category);
        // 3. save product into Postgres
        Product saveProduct = productRepository.save(product);
        // 4. return ProductRequest from DTO
        return productMapper.productToProductResponse(saveProduct);
    }

    @Override
    public Page<ProductResponse> getAll(int pageNumber, int pageSize) {
        // 1. Create Page request (Short Name A-Z)
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByIsAvailable(true, pageable).map(
                productMapper::productToProductResponse
        );
    }
}
