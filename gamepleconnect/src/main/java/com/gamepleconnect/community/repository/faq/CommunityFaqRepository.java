package com.gamepleconnect.community.repository.faq;

import com.gamepleconnect.community.domain.faq.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityFaqRepository extends JpaRepository<Faq,Long>, CommunityFaqCustomRepository {

}
