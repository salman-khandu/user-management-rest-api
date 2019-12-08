/**
 * 
 */
package com.example.user.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.example.user.dto.UserSearchCriteriaDTO;
import com.example.user.model.User;

/**
 * User Specification for search user with different fields.
 * 
 * @author Salman.Khandu
 *
 */

public class UserSpecification {

	private UserSpecification() {

	}

	public static Specification<User> build(UserSearchCriteriaDTO userSearchCriteriaDTO) {
		return new Specification<User>() {

			final List<Predicate> predicates = new ArrayList<>();

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> user, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				if (StringUtils.isNotBlank(userSearchCriteriaDTO.getName())) {
					predicates.add(criteriaBuilder.like(user.get("name"), userSearchCriteriaDTO.getName() + "%"));
				}

				if (StringUtils.isNotBlank(userSearchCriteriaDTO.getEmail())) {
					predicates.add(criteriaBuilder.like(user.get("email"), userSearchCriteriaDTO.getEmail() + "%"));
				}
				if (StringUtils.isNotBlank(userSearchCriteriaDTO.getPhoneNumber())) {
					predicates
							.add(criteriaBuilder.like(user.get("phoneNumber"), userSearchCriteriaDTO.getPhoneNumber()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}
}
