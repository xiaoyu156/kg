package iie.ac.cn.kgserver.repository;

import iie.ac.cn.kgserver.domain.DeductionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeductionRuleRepository extends JpaRepository<DeductionRule, String> {
}
