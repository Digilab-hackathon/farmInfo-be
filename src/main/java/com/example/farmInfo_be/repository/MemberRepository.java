package com.example.farmInfo_be.repository;

import com.example.farmInfo_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPhone(String phone);
}