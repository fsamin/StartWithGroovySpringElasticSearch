package com.github.fsamin.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;
import com.github.fsamin.models.User;

@Service
interface UserRepository extends ElasticsearchRepository<User, Long> {
    User findById(Long id)
    Page<User> findByEmail(String email, Pageable pageable)
    Page<User> findAll(Pageable pageable)
}
