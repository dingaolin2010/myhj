/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.service${subModuleName};

//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ${packageName}.${moduleName}.entity${subModuleName}.${ClassName};
import ${packageName}.${moduleName}.repository${subModuleName}.${ClassName}Dao;

/**
 * ${functionName}Service
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Component
@Transactional(readOnly = true)
public class ${ClassName}Service{

	@Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public ${ClassName} get(Long id) {
		return ${className}Dao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void save(${ClassName} ${className}) {
		${className}Dao.save(${className});
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		${className}Dao.delete(id);
	}
	
}
