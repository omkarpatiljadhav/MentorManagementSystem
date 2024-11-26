package com.nit.mentors.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mentor implements Serializable
{
	
	
	private static final long serialVersionUID = 1L;
	private String mentorId;
    private String mentorName;
    private String designation;
    private double salary;
    private long mobileNo;
    private Address address;
        
}
