package com.edut.springboot.tarena.pojo;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

//@Setter
//@Getter
//@ToString
//@Accessors(chain = true)
@Data
public class Goods {
	
	private Integer id ; 
	private String name ; 
	private String remark ; 
	private Date createdTime; 
	
}
