package com.Hoime.CareClean.repository;

import com.Hoime.CareClean.model.entity.MemberEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository extends ElasticsearchRepository<MemberEntity, String> {
    MemberEntity findByEmail(String email);
    MemberEntity findByName(String name);
    MemberEntity findByTel(String tel);
    List<MemberEntity> findByAdmin(boolean admin);
    Optional<MemberEntity> findByEmailAndBirthday(String username, LocalDate birthday);
}
