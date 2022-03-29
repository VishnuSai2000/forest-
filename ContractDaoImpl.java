package com.cap.fms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cap.fms.entities.Contract;
import com.cap.fms.repository.ContractDao;

import util.JPAUtil;

public class ContractDaoImpl implements ContractDao {
	EntityManager entityManager;
	
	public ContractDaoImpl()
	{
		super();
		entityManager=JPAUtil.getEntityManager();
	}

	public Contract getContract(String contractNumber) {
		String qstr="SELECT Contract from Contract contract WHERE contract.contractNumber LIKE :pcontractNumber ";
		TypedQuery<Contract> query=entityManager.createQuery(qstr,Contract.class);
		query.setParameter("pcontractNumber",contractNumber);
		return query.getSingleResult();
	}

	public boolean addContract(Contract contract) {
		try {
		 entityManager.persist(contract);
		 return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public boolean updateContract(Contract contract) {
		try {
			 entityManager.merge(contract);
			 return true;
			}
			catch(Exception e)
			{
				return false;
			}
	}

	public boolean deleteContract(String contractNumber) {
		try {
		String qstr="SELECT cotract form Contract Contract WHERE contract.contractNumber LIKE :pcontractNumber";
		TypedQuery<Contract> query=entityManager.createQuery(qstr,Contract.class);
		query.setParameter("pcontractNumber",contractNumber);
		entityManager.remove(query.getSingleResult());
		return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public List<Contract> getAllContracts() {
		String qstr="SELECT contract from Contract contract ";
		TypedQuery<Contract> query=entityManager.createQuery(qstr,Contract.class);
		return query.getResultList();
	}
}