package co.istad.chhaya.webmvc.mapper;

import co.istad.chhaya.webmvc.domain.Product;
import co.istad.chhaya.webmvc.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // Target = ProductResponse
    // Source = Product
    public ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .picture(product.getPicture())
                .isAvailable(product.getIsAvailable())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
