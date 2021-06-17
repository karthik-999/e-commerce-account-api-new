package com.account.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.account.app.dto.CreateUserRequestModel;
import com.account.app.dto.CreateUserResponseModel;
import com.account.app.dto.UserResponseModel;

@FeignClient(value = "user-api/user-app/users/", decode404 = true)
public interface IUserService {

	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails);

	@GetMapping(value="/{userId}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId);


}
