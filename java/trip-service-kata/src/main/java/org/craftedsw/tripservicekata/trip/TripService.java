package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {
	@Autowired private TripDAO tripDAO;
	
	// 유저의 여행 목록 조회
	// 파라메터로 유저를 받고 세션 정보에서 로그인 유저 정보를 조회하고 로그인하지 않았다면 예외를 던진다.
	// 로그인 했다면 로그인 유저가 파라메터로 전달된 유저와 친구인지 확인하고 
	// 서로 친구라면 전달된 유저의 여행 목록을 조회한다.
	public List<Trip> getFriendTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
		
		validate(loggedInUser);
		
		// 2. 친구의 여행 정보 조회
		return friend.isFriendsWith(loggedInUser) ? tripsBy(friend) : noTrips(); 
		
	}

	private void validate(User loggedInUser) {
		// 1. User 정보 검증
		if(loggedInUser == null){
			// getLoggedInUser()가 두번 호출되고 있지만, 인라인시켜서 코드가 간단해졌다.
			// 성능에 문제가 없다면 ok.
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
