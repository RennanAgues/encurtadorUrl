package br.com.encurtadorurl.interfaces.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import br.com.encurtadorurl.entity.EncurtadorUrl;
import br.com.encurtadorurl.utils.constantes.ConstantesDataBase;

public interface IEncurtadorUrlDAO extends Repository<EncurtadorUrl, Long> {

	Page<EncurtadorUrl> findAll (Pageable pageable);
	
	List<EncurtadorUrl> findByUrlAllIgnoringCase(@Param(ConstantesDataBase.PARAM_URL) String url);
	
	List<EncurtadorUrl> findByAliasAllIgnoringCase(@Param(ConstantesDataBase.PARAM_ALIAS) String alias);
	
	EncurtadorUrl save(EncurtadorUrl encurtadorUrl);
}