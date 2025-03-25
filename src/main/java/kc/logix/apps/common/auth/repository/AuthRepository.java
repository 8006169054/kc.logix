package kc.logix.apps.common.auth.repository;

import static kc.logix.common.entity.QAccount.account;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;

import kainos.framework.data.querydsl.support.repository.KainosRepositorySupport;
import kc.logix.common.dto.SessionDto;

@Repository
public class AuthRepository extends KainosRepositorySupport {

	/**
	 * 
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public SessionDto dbLogin(String id, String password) throws Exception {
		return select(Projections.bean(SessionDto.class
				, account.id
				, account.name
				, account.mail
				))
				.from(account)
				.where(account.id.eq(id).and(account.password.eq(password)))
				.fetchOne();
	}
//	
//	/**
//	 * 
//	 * @param id
//	 * @param pw
//	 * @return
//	 * @throws Exception
//	 */
//	public SessionDto imlogin(String id, String pw) throws Exception {
//		return null;
//	}
}
