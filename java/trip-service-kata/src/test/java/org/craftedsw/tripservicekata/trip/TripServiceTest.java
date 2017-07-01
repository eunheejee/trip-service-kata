package org.craftedsw.tripservicekata.trip;

import static org.craftedsw.tripservicekata.trip.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRAZIL = new Trip();
	private static final Trip TO_LONDON = new Trip();

	// 실제 TripService 인스턴스가 생성될 때에 Mockito가 TripService 클래스를 분석해서 
	// TripService가 사용하는 Mock들을 자동으로 주입시키는 것.
	@Mock private TripDAO tripDAO;
	@InjectMocks @Spy private TripService tripService = new TripService();
	

	// 유저가 로그인하지 않았으면 예외를 던지는 테스트
	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_an_exception_when_user_is_not_logged_in() {

		tripService.getFriendTrips(UNUSED_USER, GUEST);
	}

	// 유저는 로그인 된 상태인데 조회하려는 유저와는 친구가 아닌 경우
	@Test
	public void should_not_return_any_trips_when_users_are_not_friends() {
		User friend = aUser()
						.friendsWith(ANOTHER_USER)
						.withTrips(TO_BRAZIL)
						.build();

		List<Trip> friendTrips = tripService.getFriendTrips(friend, REGISTERED_USER);

		assertThat(friendTrips.size(), is(0));
	}

	// 유저가 친구인 경우
	@Test
	public void should_return_friend_trips_when_users_are_friends() {
		User friend = aUser()
						.friendsWith(ANOTHER_USER, REGISTERED_USER)
						.withTrips(TO_BRAZIL, TO_LONDON)
						.build();
		
		// Mock DAO가 여행목록을 반환하게 함
		given(tripDAO.tripsBy(friend)).willReturn(friend.trips());
		
		List<Trip> friendTrips = tripService.getFriendTrips(friend, REGISTERED_USER);

		assertThat(friendTrips.size(), is(2));
	}

}
