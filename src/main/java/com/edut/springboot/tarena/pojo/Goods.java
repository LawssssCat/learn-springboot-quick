package com.edut.springboot.tarena.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

//@Setter
//@Getter
//@ToString
//@Accessors(chain = true)
@AllArgsConstructor
@Data
public class Goods {
	
	private Long id ; 
	private String name ; 
	private String remark ; 
	private Date createdTime; 
	public Goods() {
		
	}
}
