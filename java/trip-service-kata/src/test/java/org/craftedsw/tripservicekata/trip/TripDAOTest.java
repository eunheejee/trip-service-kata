package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {

	// ���� Static �޼ҵ�� ������ ����� �ϴ� �ν��Ͻ� �޼ҵ带 ����
	// ���� �޼ҵ忡�� static�� ����� ������ ������ �����Ƿ� �������� �ʴ´�.
	@Test(expected = CollaboratorCallException.class) public void
	should_throw_exception_when_retrieving_user_trips(){
		new TripDAO().tripsBy(new User());
	}
}
