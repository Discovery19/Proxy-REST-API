package org.example.services.auth_services;


import org.example.models.Role;
import org.example.models.User;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.example.DTO.UserRegistrationDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final BCryptPasswordEncoder passwordEncoder;

   public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
      super();
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public User save(UserRegistrationDto registrationDto) {
      var user = new User(
                  registrationDto.getEmail(),
                   passwordEncoder.encode(registrationDto
                          .getPassword()),
              List.of(roleRepository.findByName("POST")));
      return userRepository.save(user);
   }

   @Override
   @Cacheable("users")
   public UserDetails loadUserByUsername(String username)
         throws UsernameNotFoundException {
      var user = userRepository.findByEmail(username);
      if (user == null) {
         throw new UsernameNotFoundException
               ("Invalid username or password.");

      }
      return new org.springframework.security
            .core.userdetails.User(user.getEmail(),
               user.getPassword(),
           mapRolesToAuthorities(user.getRoles()));
   }
   @Cacheable("roles")
   private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

      return roles.stream()
            .map(role -> new SimpleGrantedAuthority
                  (role.getName()))
            .toList();
   }

   @Override
   public List<User> getAll() {
      return userRepository.findAll();
   }
}
