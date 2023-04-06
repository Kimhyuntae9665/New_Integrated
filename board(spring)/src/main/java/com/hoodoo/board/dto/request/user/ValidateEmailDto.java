package com.hoodoo.board.dto.request.user;

import javax.validation.constraints.Email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value="유저 이메일 중복 체크 Request Body")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateEmailDto {
    @ApiModelProperty(value="유저 이메일",example = "id@email.com",required = true)
    @Email
    private String email;
}
