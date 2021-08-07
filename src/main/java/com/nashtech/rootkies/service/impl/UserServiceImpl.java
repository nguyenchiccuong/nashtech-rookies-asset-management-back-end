package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.UserConverter;
import com.nashtech.rootkies.dto.PageDTO;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private final UserRepository repository;

    @Autowired
    private final UserConverter converter;

    @Override
    public PageDTO findAllUser(Pageable pageable, Specification specification) {
        try{
            Page<User> page =  repository.findAll(specification , pageable);
            PageDTO pageDTO= converter.pageToPageDto(page);
            return  pageDTO;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }



    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    /*@Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(ErrorCode.ERR_USER_NOT_FOUND);
        }
        return userRepository.findById(id).get();
    }

    @Override
    public boolean createUser(User user) throws UpdateDataFailException {
        try{
            userRepository.save(user);
            return true;
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_CREATE_USER_FAIL);
        }

    }*/
}
