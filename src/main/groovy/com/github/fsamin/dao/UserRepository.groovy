package com.github.fsamin.dao

import com.github.fsamin.models.Role
import com.github.fsamin.models.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Service

@Service
interface UserRepository extends ElasticsearchRepository<User, Long> {
    User findById(Long id)
    Page<User> findByEmail(String email, Pageable pageable)
    Page<User> findAll(Pageable pageable)
    Page<User> findByAuthorities(Role role, Pageable pageable)
}
