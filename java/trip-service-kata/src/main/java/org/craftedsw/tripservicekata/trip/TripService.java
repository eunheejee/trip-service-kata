package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {
	// ������ ���� ��� ��ȸ
	// �Ķ���ͷ� ������ �ް� ���� �������� �α��� ���� ������ ��ȸ�ϰ� �α������� �ʾҴٸ� ���ܸ� ������.
	// �α��� �ߴٸ� �α��� ������ �Ķ���ͷ� ���޵� ������ ģ������ Ȯ���ϰ� 
	// ���� ģ����� ���޵� ������ ���� ����� ��ȸ�Ѵ�.
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedInUser = getLoggedInUser();
		
		if(loggedInUser == null){
			throw new UserNotLoggedInException();
		}
		
		List<Trip> tripList = new ArrayList<Trip>();
		if (loggedInUser != null) {
			// if ���� �ȿ����� ���ǰ� �����Ƿ�, �ű��
			// ������ �������� ���ǰ� ������, ���Ǵ� ��ġ�� ������ ��ƾ� �Ѵ�.
			//boolean isFriend = false;
			
			/*for (User friend : user.getFriends()) {
				if (friend.equals(loggedInUser)) {
					isFriend = true;
					break;
				}
			}*/
			if (user.isFriendsWith(loggedInUser)) { // �ζ��� �۾�
				tripList = tripsBy(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	// �ҽ���  �����ϴ� ������ ����� IDE�� �����丵 ����� �̿��ϴ� ��
	// ������(������(Seam)�� Ŭ�������� �̾����� ����)�� �����.
	// TripService�� UserSession ���̿� �������� ����
	// TripService�� �ٸ� Ŭ������ �����ϴ� �κ��� �и�
	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}
	
}
