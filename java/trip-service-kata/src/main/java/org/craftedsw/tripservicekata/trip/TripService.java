package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {
	// 유저의 여행 목록 조회
	// 파라메터로 유저를 받고 세션 정보에서 로그인 유저 정보를 조회하고 로그인하지 않았다면 예외를 던진다.
	// 로그인 했다면 로그인 유저가 파라메터로 전달된 유저와 친구인지 확인하고 
	// 서로 친구라면 전달된 유저의 여행 목록을 조회한다.
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedInUser = getLoggedInUser();
		
		if(loggedInUser == null){
			throw new UserNotLoggedInException();
		}
		
		List<Trip> tripList = new ArrayList<Trip>();
		if (loggedInUser != null) {
			// if 구문 안에서만 사용되고 있으므로, 옮긴다
			// 변수가 여기저기 사용되고 있으면, 사용되는 위치에 가깝게 모아야 한다.
			//boolean isFriend = false;
			
			/*for (User friend : user.getFriends()) {
				if (friend.equals(loggedInUser)) {
					isFriend = true;
					break;
				}
			}*/
			if (user.isFriendsWith(loggedInUser)) { // 인라인 작업
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

	// 소스를  수정하는 유일한 방법은 IDE의 리팩토링 기능을 이용하는 것
	// 연결점(연결점(Seam)은 클래스들이 이어지는 지점)을 만든다.
	// TripService와 UserSession 사이에 연결점을 만듦
	// TripService가 다른 클래스에 접근하는 부분을 분리
	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}
	
}
