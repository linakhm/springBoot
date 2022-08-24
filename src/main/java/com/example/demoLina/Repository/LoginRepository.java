package com.example.demoLina.Repository;
import com.example.demoLina.Model.Login;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface LoginRepository extends JpaRepository<Login,Long>{
    Login findByUsernameAndPassword(String username, String password);


}
