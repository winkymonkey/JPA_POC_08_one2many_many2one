package org.example.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.example.jpa.domain.Branch;
import org.example.jpa.domain.Student;
import org.example.jpa.domain.Subject;


public class App {
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			emf = Persistence.createEntityManagerFactory("jbd-pu");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			Branch branch1 = getBranch1();
			branch1.addSubject(getSubject1());
			branch1.addSubject(getSubject2());
			branch1.getSubjects().forEach(subject -> subject.addBranch(branch1));
				List<Student> studentsA = new ArrayList<>();
				branch1.setStudents(studentsA);
				branch1.addStudent(getStudent1());
				branch1.addStudent(getStudent2());
			
//			Branch branch2 = getBranch2();
//			branch2.addSubject(getSubject1());
//			branch2.addSubject(getSubject3());
//			branch2.getSubjects().forEach(subject -> subject.addBranch(branch2));
//				List<Student> studentsB = new ArrayList<>();
//				branch1.setStudents(studentsB);
//				branch1.addStudent(getStudent1());
//				branch1.addStudent(getStudent2());

			entityManager.persist(branch1);
//			entityManager.persist(branch2);
			transaction.commit();
			
			
			// Bi-Directional Test
			Subject sub = entityManager.find(Subject.class, new ArrayList<>(branch1.getSubjects()).get(0).getSubjectId());
			sub.getBranches().forEach(branch -> {
				System.err.println("Branch Name: " + branch.getBranchName());
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
			entityManager.close();
			emf.close();
		}
	}
	
	private static Branch getBranch1() {
		Branch branch = new Branch();
		branch.setBranchName("Computer Science and Engineering");
		branch.setBranchShortName("CSE");
		branch.setDescription("CSE department offers courses");
		return branch;
	}

	private static Branch getBranch2() {
		Branch branch = new Branch();
		branch.setBranchName("Information Technology");
		branch.setBranchShortName("IT");
		branch.setDescription("IT is the business side of computers");
		return branch;
	}

	private static Subject getSubject1() {
		Subject subject = new Subject();
		subject.setSubjectName("Software Engineering");
		subject.setSubjectDesc("Apply key aspects of software engineering");
		return subject;
	}

	private static Subject getSubject2() {
		Subject subject = new Subject();
		subject.setSubjectName("Distributed System");
		subject.setSubjectDesc("Explore recent advances in distributed computing");
		return subject;
	}

	private static Subject getSubject3() {
		Subject subject = new Subject();
		subject.setSubjectName("Business Analysis and Optimization");
		subject.setSubjectDesc("understand the factors that impact the business strategy");
		return subject;
	}
	
	private static Student getStudent1() {
		Student student = new Student();
		student.setFirstName("Peter");
		student.setLastName("Milanovich");
		student.setContactNo("+1-408-575-1317");
		return student;
	}

	private static Student getStudent2() {
		Student student = new Student();
		student.setFirstName("Rosy");
		student.setLastName("Larsen");
		student.setContactNo("+1-408-575-1219");
		return student;
	}
}
