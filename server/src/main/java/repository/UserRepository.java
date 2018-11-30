package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findOneByID(String id);
	Page<User> findAll(Pageable pageable);
}
