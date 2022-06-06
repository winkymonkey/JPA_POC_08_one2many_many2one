package org.example.jpa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SUBJECT_ID")
	private int subjectId;
	
	@Column(name = "SUBJECT_NAME")
	private String subjectName;

	@Column(name = "SUBJECT_DESC")
	private String subjectDesc;

	@ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
	private Set<Branch> branches = new HashSet<>();
	
	
	
	/* *************************************************************** */
	public int getSubjectId() {
		return this.subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return this.subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Set<Branch> getBranches() {
		return branches;
	}
	public String getSubjectDesc() {
		return this.subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}
	public void addBranch(Branch branch){
		this.branches.add(branch);
	}
}