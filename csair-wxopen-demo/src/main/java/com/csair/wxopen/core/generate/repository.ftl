/**
 * 基于Spring Data JPA的Dao接口, 自动根据接口生成实现.
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 * 
 * Spring Data JPA 还会解释新增方法名生成新方法的实现.
 */
package ${packageName}.${moduleName}.repository${subModuleName};

import org.springframework.data.repository.CrudRepository;

import ${packageName}.${moduleName}.entity.${ClassName};

/**
 * ${functionName}DAO接口
 * @author ${classAuthor}
 * @version ${classVersion}
 */
public interface ${ClassName}Dao  extends CrudRepository<${ClassName},${idType}> {
	
}
