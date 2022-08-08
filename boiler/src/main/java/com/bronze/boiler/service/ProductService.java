package com.bronze.boiler.service;


import com.bronze.boiler.domain.product.converter.ProductConverter;
import com.bronze.boiler.domain.product.dto.ProductDto;
import com.bronze.boiler.domain.product.dto.ProductOptionDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.filter.Page;
import com.bronze.boiler.repository.ProductImageRepository;
import com.bronze.boiler.repository.ProductOptionRepository;
import com.bronze.boiler.repository.ProductRepository;
import com.bronze.boiler.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    /**
     * 상품dto DB에 저장
     * @param productDto 상품dto
     * @return 저장된상품dto
     */
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productRepository.save(ProductConverter.toProduct(productDto));
        return ProductConverter.toProductDto(product);
    }

    /**
     * 상품삭제
     * @param productId 상품아이디
     * @return 삭제상태상품dto
     */
    public ProductDto closeProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));
        product.close();
        return ProductConverter.toProductDto(product);
    }

    /**
     * 상품데이터조회
     * @param productId 상품아이디
     * @return 상품상세데이터
     */
    public ProductDto getProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));
        List<ProductImage> productImages = productImageRepository.findAllByProduct(product);
        List<ProductOption> productOptions = productOptionRepository.findAllByProduct(product);
        return ProductConverter.toProductDto(product,productImages,productOptions);
    }

    /**
     * 상품목록조회
     * @param page 상품페이지
     * @return 상품목록데이터
     */
    public Response<ProductDto> getProducts(Page page) {

        Long count = productRepository.count();
        List<Product> products = productRepository.findAllByPage(page);
        List<ProductImage> productImages = productImageRepository.findAllByProductIn(products);

        return Response.<ProductDto>builder()
                .total(count)
                .currentPage(page.getPageNum())
                .list(products
                        .stream()
                        .map(product -> ProductConverter.toProductDto(product, productImages
                                        .stream()
                                        .filter(productImage -> productImage.getProduct().equals(product))
                                        .collect(Collectors.toList()),null))
                        .collect(Collectors.toList())).build();

    }
}