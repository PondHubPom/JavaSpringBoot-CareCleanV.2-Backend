package com.Hoime.CareClean.repository;

import com.Hoime.CareClean.model.entity.MemberListEntity;
import com.tdunning.math.stats.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberListRepository extends ElasticsearchRepository<MemberListEntity,String>{

}
