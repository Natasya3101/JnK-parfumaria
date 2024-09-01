package com.parfumaria.be.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.parfumaria.be.models.Category;
import com.parfumaria.be.models.User;
import com.parfumaria.be.repositorys.CategoryRepository;
import com.parfumaria.be.repositorys.UsersRepository;

@Component
public class InitialDataLoader implements ApplicationRunner{
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(usersRepository.findAll().isEmpty()){
            User user = User.builder()
            .email("Natasya@gmail.com")
            .password(passwordEncoder.encode("Nanat"))
            .build();
            usersRepository.saveAndFlush(user);
        }

        if (categoryRepository.findAll().isEmpty()) {
            Category category = new Category();
            category.setName("Men");
            categoryRepository.save(category);

            category = new Category();
            category.setName("Women");
            categoryRepository.save(category);

            category = new Category();
            category.setName("Unisex");
            categoryRepository.save(category);
        }
    }
}
