package OnlineBookResellingSystem.OBRS_BackEnd.user.repository;

import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Long>
{
User findByuserName(String username);

    Optional<User> findByEmail(@NonNull String username);
}
