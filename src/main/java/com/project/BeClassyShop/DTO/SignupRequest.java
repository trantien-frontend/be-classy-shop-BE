package com.project.BeClassyShop.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignupRequest {
	@Valid
	
	@NotBlank(message = "First name không được rỗng")
	@NotNull(message =  "First name không mandatory")
	@Size(min = 4, max = 255, message = "First name trong khoảng từ 5 - 255 ký tự")
	private String firstName;	
	@NotBlank(message = "Last name không được rỗng")
	@NotNull(message =  "Last name không mandatory")
	@Size(min = 4, max = 255, message = "Last name trong khoảng từ 5 - 255 ký tự")
	private String lastName;
	@NotBlank(message = "Phone không được rỗng")
	@NotNull(message =  "Phone name không mandatory")
	@Size( max = 11, message = "Số điện thoại không hợp lệ")
	private String phone; 
	@NotBlank(message = "First name không được rỗng")
	@NotNull(message =  "First name không mandatory")
	private String email;
	@NotBlank(message = "Password không được rỗng")
	@NotNull(message =  "Password không mandatory")
	@Size(min = 4, max = 20, message = "password trong khoảng từ 5 - 255 ký tự")
	private String password;
}
