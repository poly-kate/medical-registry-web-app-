package medregistry.service;

import medregistry.entity.Role;
import medregistry.entity.User;
import medregistry.repositories.RoleRepository;
import medregistry.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder; //кодировщик паролей

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Пользователь не был найден");
        }
        return user;
    }

    public boolean saveUser(User user){
        User userFromDb =
                userRepository.findByUsername(user.getUsername());
        if (userFromDb != null){
            return false;
        }

        Role role = roleRepository.findById(1).get();
        user.getRoles().add(role);
        user.setPassword(encoder.encode(user.getPassword()));
        role.getUsers().add(user);
        userRepository.save(user);
        return true;
    }

    public String getCurrentUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    //!!! - через регистрацию
    //логин и пароль можно заменить
    public void createAndSetUser(String login){
        User user = new User();
        user.setUsername(login);
        user.setPassword(login);
        saveUser(user);
    }


    /*public boolean deleteByName(String name)
    {
        //проверка на существование
        //userRepository.deleteB(user);
        return true;
        //if (userRepository.findByUsername(user.getUsername()){

        //}
        //if (user == null){
        //    throw new UsernameNotFoundException("Пользователь не был найден");
       // }
    }*/

}
