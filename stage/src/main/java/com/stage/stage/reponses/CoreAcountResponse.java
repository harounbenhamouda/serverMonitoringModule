package com.stage.stage.reponses;

import java.util.List;

import com.stage.stage.entity.Core_Accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CoreAcountResponse<T> {



private  String code ;

private T Data;
private int total;

}
