package com.restaurant.server.web.controller;

import com.restaurant.server.model.binding.UserRegisterBindingModel;
import com.restaurant.server.model.service.user.UserServiceModel;
import com.restaurant.server.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserAuthenticationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserAuthenticationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @CrossOrigin("*")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@Valid @RequestBody(required = false) UserRegisterBindingModel user,
                                            BindingResult bindingResult){

        if (user == null){
            return ResponseEntity.badRequest().build();
        }

        if (bindingResult.hasErrors() || !user.getPassword().equals(user.getRepeatPassword())) {
            return ResponseEntity.badRequest().build();
        }

        boolean existUser = this.userService.isExistUser(user.getUsername());

        if (existUser){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        this.userService.register(this.modelMapper.map(user, UserServiceModel.class));


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
