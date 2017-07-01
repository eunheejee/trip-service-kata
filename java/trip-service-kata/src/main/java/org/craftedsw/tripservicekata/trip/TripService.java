package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {
	@Autowired private TripDAO tripDAO;
	
	// ������ ���� ��� ��ȸ
	// �Ķ���ͷ� ������ �ް� ���� �������� �α��� ���� ������ ��ȸ�ϰ� �α������� �ʾҴٸ� ���ܸ� ������.
	// �α��� �ߴٸ� �α��� ������ �Ķ���ͷ� ���޵� ������ ģ������ Ȯ���ϰ� 
	// ���� ģ����� ���޵� ������ ���� ����� ��ȸ�Ѵ�.
	public List<Trip> getFriendTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
		
		validate(loggedInUser);
		
		// 2. ģ���� ���� ���� ��ȸ
		return friend.isFriendsWith(loggedInUser) ? tripsBy(friend) : noTrips(); 
		
	}

	private void validate(User loggedInUser) {
		// 1. User ���� ����
		if(loggedInUser == null){
			// getLoggedInUser()�� �ι� ȣ��ǰ� ������, �ζ��ν��Ѽ� �ڵ尡 ����������.
			// ���ɿ� ������ ���ٸ� ok.
			throw new UserNotLoggedInException();
		}
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private List<Trip> tripsBy(User user) {
		return tripDAO.tripsBy(user);
	}

}
