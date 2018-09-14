package iie.ac.cn.kgserver.repository;

import iie.ac.cn.kgserver.domain.DeductionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeductionTypeRepository extends JpaRepository<DeductionType, String> {
    public List<DeductionType> findAllByIdIn(List<String> ids);
}
