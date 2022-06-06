package org.example.jpa.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BRANCH_ID")
	private int branchId;

	@Column(name = "BRANCH_NAME")
	private String branchName;

	@Column(name = "BRANCH_SHORT_NAME")
	private String branchShortName;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BRANCH_SUBJECT", 
		joinColumns = {@JoinColumn(name = "BRANCH_ID")}, 
		inverseJoinColumns = {@JoinColumn(name = "SUBJECT_ID")}
	)
	private Set<Subject> subjects = new HashSet<>();
	
	@OneToMany(mappedBy = "branch", cascade = {CascadeType.ALL})
	private List<Student> students;
	
	
	
	/* *************************************************************** */
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchShortName() {
		return branchShortName;
	}
	public void setBranchShortName(String branchShortName) {
		this.branchShortName = branchShortName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	public void addSubject(Subject subject){
		this.subjects.add(subject);
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setBranch(this);
		return student;
	}
	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setBranch(null);
		return student;
	}
}	
