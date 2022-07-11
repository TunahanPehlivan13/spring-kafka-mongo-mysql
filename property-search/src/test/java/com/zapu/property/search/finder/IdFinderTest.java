package com.zapu.property.search.finder;

import com.zapu.property.search.client.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class IdFinderTest {

    @InjectMocks
    IdFinder<CategoryDto> idFinder;

    @Test
    void shouldGetUniqueIdIfExist() {
        CategoryDto category1 = new CategoryDto();
        category1.setId(1L);
        category1.setName("name1");

        CategoryDto category2 = new CategoryDto();
        category2.setId(2L);
        category2.setName("name2");

        Iterable<CategoryDto> items = Set.of(category1, category2);

        CategoryDto category = idFinder.find(1L, items);

        assertEquals(category.getName(), "name1");
        assertThat(category.getId(), is(1L));
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenIdNotMatch() {
        CategoryDto category1 = new CategoryDto();
        category1.setId(1L);
        category1.setName("name1");

        CategoryDto category2 = new CategoryDto();
        category2.setId(2L);
        category2.setName("name2");

        Iterable<CategoryDto> items = Set.of(category1, category2);

        assertThrows(IllegalArgumentException.class, () -> idFinder.find(3L, items));
    }
}