package com.bronze.boiler.domain.product.converter;

import com.bronze.boiler.domain.category.converter.CategoryConverter;
import com.bronze.boiler.domain.product.dto.ReqProductDto;
import com.bronze.boiler.domain.product.dto.ProductOptionDto;
import com.bronze.boiler.domain.product.dto.ResProductDetailDto;
import com.bronze.boiler.domain.product.dto.ResProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {


    public static ResProductDto toProductDto(Product product, List<ProductImage> productImages) {

        return ResProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .originPrice(product.getOriginPrice())
                .sellPrice(product.getSellPrice())
                .imageUrls(productImages == null ? Collections.EMPTY_LIST : productImages
                        .stream()
                        .filter(productImage -> !StringUtils.isEmpty(productImage.getDomain()))
                        .map(productImage -> productImage.getDomain().concat(productImage.getPath()))
                        .collect(Collectors.toList()))
                .build();
    }


    public static ResProductDetailDto toProductDto(Product product, List<ProductImage> productImages, List<ProductOption> productOptions) {

        return ResProductDetailDto.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .category(CategoryConverter.toCategoryDto(product.getCategory()))
                .description(product.getDescription())
                .originPrice(product.getOriginPrice())
                .sellPrice(product.getSellPrice())
                .sellerInfo(product.getSellerInfo())
                .refundInfo(product.getRefundInfo())
                .savePoint(product.getSavePoint())
                .imageUrls(productImages == null ? Collections.EMPTY_LIST : productImages
                        .stream()
                        .filter(productImage -> !StringUtils.isEmpty(productImage.getDomain()))
                        .map(productImage -> productImage.getDomain().concat(productImage.getPath()))
                        .collect(Collectors.toList()))
                .options(productOptions == null ? Collections.EMPTY_LIST : productOptions
                        .stream()
                        .map(productOption -> ProductOptionDto
                                .builder()
                                .id(productOption.getId())
                                .type(productOption.getType())
                                .value(productOption.getValue())
                                .build())
                        .collect(Collectors.toList())
                )
                .sizeInfo(product.getSizeInfo())
                .status(product.getStatus())
                .build();
    }


    public static ResProductDetailDto toProductDto(Product product) {
        return ResProductDetailDto.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .category(CategoryConverter.toCategoryDto(product.getCategory()))
                .description(product.getDescription())
                .originPrice(product.getOriginPrice())
                .sellPrice(product.getSellPrice())
                .sellerInfo(product.getSellerInfo())
                .refundInfo(product.getRefundInfo())
                .savePoint(product.getSavePoint())
                .sizeInfo(product.getSizeInfo())
                .status(product.getStatus())
                .build();
    }

    public static Product toProduct(ReqProductDto reqProductDto) {
        return Product.builder()
                .name(reqProductDto.getName())
                .code(reqProductDto.getCode())
                .category(CategoryConverter.toCategory(reqProductDto.getCategory()))
                .description(reqProductDto.getDescription())
                .originPrice(reqProductDto.getOriginPrice())
                .sellPrice(reqProductDto.getSellPrice())
                .sellerInfo(reqProductDto.getSellerInfo())
                .refundInfo(reqProductDto.getRefundInfo())
                .savePoint(reqProductDto.getSavePoint())
                .sizeInfo(reqProductDto.getSizeInfo())
                .status(reqProductDto.getStatus())
                .build();

    }
}
