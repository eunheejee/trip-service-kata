package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {
	// ������ ���� ��� ��ȸ
	// �Ķ���ͷ� ������ �ް� ���� �������� �α��� ���� ������ ��ȸ�ϰ� �α������� �ʾҴٸ� ���ܸ� ������.
	// �α��� �ߴٸ� �α��� ������ �Ķ���ͷ� ���޵� ������ ģ������ Ȯ���ϰ� 
	// ���� ģ����� ���޵� ������ ���� ����� ��ȸ�Ѵ�.
	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		
		// 1. User ���� ����
		if(loggedInUser == null){
			// getLoggedInUser()�� �ι� ȣ��ǰ� ������, �ζ��ν��Ѽ� �ڵ尡 ����������.
			// ���ɿ� ������ ���ٸ� ok.
			throw new UserNotLoggedInException();
		}
		
		// 2. ģ���� ���� ���� ��ȸ
		return user.isFriendsWith(loggedInUser) ? tripsBy(user) : noTrips(); 
		
	}

	protected ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

}
