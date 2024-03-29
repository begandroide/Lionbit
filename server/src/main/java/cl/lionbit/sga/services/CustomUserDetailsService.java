package cl.lionbit.sga.services;

import cl.lionbit.sga.entities.Role;
import cl.lionbit.sga.entities.User;
import cl.lionbit.sga.repositories.RoleRepository;
import cl.lionbit.sga.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Page<User> findPaginated( String filter,Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActivated(true);
        user.setCreateAt(new Date());

        HashSet<Role> roles = new HashSet<>();
        
        for (Role item: user.getRoles()) {
            roles.add(roleRepository.findByRole(item.getRole()));
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    public User update(Long id, User user){
        User toUpdate = userRepository.findById(id).get();

        toUpdate.setLastName(user.getLastName());
        toUpdate.setFirstName(user.getFirstName());
        toUpdate.setRoles(user.getRoles());
        toUpdate.setEmail(user.getEmail());
        toUpdate.setOffice(user.getOffice());
        toUpdate.setPhoneNumber(user.getPhoneNumber());

        if (user.getPassword() != null){
            toUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            toUpdate.setPassword(user.getPassword());
        }

        return userRepository.save(toUpdate);
    }

    public String delete(Long id){
        userRepository.deleteById(id);
        return "Deleted user with id" + id;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    };

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
