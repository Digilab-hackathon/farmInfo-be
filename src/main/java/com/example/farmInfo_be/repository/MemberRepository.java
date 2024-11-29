package com.example.farmInfo_be.repository;

import com.example.farmInfo_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNameAndBirthDateAndPhoneNumber(String name, LocalDate birthDate, String phoneNumber);

    Optional<Member> findByPhoneNumber(String phoneNumber);
}
