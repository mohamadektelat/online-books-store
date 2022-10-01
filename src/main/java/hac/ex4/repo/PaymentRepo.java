//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.repo;

//----------------------------------------------------------------------------------------------------------------------

import hac.ex4.Model.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//----------------------------------------------------------------------------------------------------------------------

/**
 * PaymentRepo
 */
@Repository
public interface PaymentRepo extends JpaRepository<PaymentTransaction, Long> {
}

//----------------------------------------------------------------------------------------------------------------------