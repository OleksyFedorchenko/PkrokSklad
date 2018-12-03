package com.pkrok;

import com.pkrok.entity.FirmEntity;
import com.pkrok.entity.RoleEntity;
import com.pkrok.entity.TypeEntity;
import com.pkrok.entity.UserEntity;
import com.pkrok.exceptions.ResourceNotFoundException;
import com.pkrok.repository.*;
import com.pkrok.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;
@EnableWebMvc
@SpringBootApplication
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FirmRepository firmRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            RoleEntity entity = new RoleEntity();
            entity.setName("ADMIN");

            roleRepository.save(entity);

            entity = new RoleEntity();
            entity.setName("USER");

            roleRepository.save(entity);
        }

        if (userRepository.count() == 0) {
            UserEntity user = new UserEntity();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("123"));

            RoleEntity role = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            System.out.println(role.getName());
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            System.out.println(roles.isEmpty());

            userRepository.save(user);
        }

        if(firmRepository.count()==0){
            FirmEntity firm=new FirmEntity();
            firm.setName("Tryumf");
            modelMapper.map(firm, FirmEntity.class);
            firmRepository.save(firm);
        }

        if(typeRepository.count()==0){
            TypeEntity type=new TypeEntity();
            type.setName("Cups");
            modelMapper.map(type, TypeEntity.class);
            typeRepository.save(type);
        }
    }
}
