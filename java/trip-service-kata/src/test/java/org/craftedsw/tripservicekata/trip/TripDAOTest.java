package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {

	// 기존 Static 메소드와 동일한 기능을 하는 인스턴스 메소드를 만듦
	// 기존 메소드에서 static을 지우면 변경할 사항이 많으므로 수정하지 않는다.
	@Test(expected = CollaboratorCallException.class) public void
	should_throw_exception_when_retrieving_user_trips(){
		new TripDAO().tripsBy(new User());
	}
}
