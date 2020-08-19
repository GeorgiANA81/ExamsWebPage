package com.exams.backend.rest;

import com.exams.backend.dto.UserDataDTO;
import com.exams.backend.dto.UserResponseDTO;
import com.exams.backend.entity.UserEntity;
import com.exams.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
public class UserRest {
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> result = userService.getAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserResponseDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("email") String email) {
        UserResponseDTO result = modelMapper.map(userService.getOneByEmail(email), UserResponseDTO.class);

        // create the response depending on the result
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDataDTO userDataDTO) {
        UserResponseDTO result = modelMapper.map(
                userService.createOrUpdate(
                        modelMapper.map(userDataDTO, UserEntity.class)
                ),
                UserResponseDTO.class
        );

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
