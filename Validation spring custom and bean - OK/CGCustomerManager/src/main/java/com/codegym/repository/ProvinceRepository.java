package com.codegym.repository;

import com.codegym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProvinceRepository extends PagingAndSortingRepository<Province, Long> {
    //Page<Province> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
