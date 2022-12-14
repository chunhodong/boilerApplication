package com.bronze.boiler.repository;


import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.category.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class CategoryRepositoryTest {



    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @DisplayName("리파지토리")
    void 카테고리조회_카테고리정보확인() {
        Category parentCategory = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category = categoryRepository.save(Category.builder().name("카테고리2").parent(parentCategory).build());
        assertThat(category.getParent()).isEqualTo(parentCategory);
    }



}
