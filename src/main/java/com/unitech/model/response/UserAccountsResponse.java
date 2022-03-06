package com.unitech.model.response;

import com.unitech.model.entity.Accounts;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class UserAccountsResponse extends CommonResponse{

    private List<Accounts> accounts;

}
