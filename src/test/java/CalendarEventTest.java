import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calendar.Meeting;
import calendar.MeetingCalendar;


class CalendarEventTest
{

	MeetingCalendar cal;
	
	
	@BeforeEach
	void setUp()
	{	
		cal = new MeetingCalendar();
		
		
	}
	void checkMeeting(String Description, String Location, GregorianCalendar startTime, GregorianCalendar endTime)
	{
		Meeting meeting = cal.findMeeting(startTime);
		assertEquals(Description, meeting.getDescription());
		assertEquals(Location, meeting.getLocation());
		assertEquals(startTime, meeting.getStartTime());
		assertEquals(endTime, meeting.getEndTime());

		
	}
	@Test
	
	void testOneTimeEvent()
	{
		GregorianCalendar startTime = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar endTime = new GregorianCalendar(2023,8,28,9,30);		
		OneTimeEvent X = new OneTimeEvent("X", "Olin", startTime, endTime);
		
		
		assertEquals("X", X.getDescription());
		assertEquals("Olin", X.getLocation());
		assertEquals(startTime, X.getStartTime());
		assertEquals(endTime, X.getEndTime());
		
		
		X.scheduleEvent(cal);
		
		
		Meeting meeting= cal.findMeeting(startTime);
		assertEquals("X", meeting.getDescription());
		assertEquals("Olin", meeting.getLocation());
		assertEquals(startTime, meeting.getStartTime());
		assertEquals(endTime, meeting.getEndTime());

	}

	@Test
	void testWeeklyEvent()
	{
		GregorianCalendar startTime = new GregorianCalendar(2023,8,7,8,30);
		GregorianCalendar endTime = new GregorianCalendar(2023,8,7,9,30);
		GregorianCalendar repeatUntil = new GregorianCalendar(2023,8,23,8,30);
		WeeklyEvent Y = new WeeklyEvent("Y", "Young", startTime, endTime, repeatUntil);
		Y.scheduleEvent(cal);

		
		GregorianCalendar secondweek = new GregorianCalendar (2023,8,14,8,30);
		GregorianCalendar secondweekEnd = new GregorianCalendar (2023,8,14,9,30);

		
		GregorianCalendar thirdweek = new GregorianCalendar (2023, 8, 21, 8, 30);
		GregorianCalendar thirdweekEnd = new GregorianCalendar (2023, 8, 21, 9, 30);

		
		GregorianCalendar fourthweek = new GregorianCalendar (2023, 8, 28, 8, 30);
		
		OneTimeEvent oldEvent = new OneTimeEvent("Old", "OldLocation", thirdweek, thirdweekEnd);
		oldEvent.scheduleEvent(cal);
		
		checkMeeting("Y", "Young", startTime, endTime);
		checkMeeting("Y", "Young",secondweek, secondweekEnd);
		checkMeeting("Y", "Young", thirdweek, thirdweekEnd);
		assertNull(cal.findMeeting(fourthweek));
		
		
	}

	@Test
	void testMultiDayPerWeekEvent()
	{
		GregorianCalendar startTime = new GregorianCalendar(2023,8,4,8,30);
		GregorianCalendar endTime = new GregorianCalendar(2023,8,4,9,30);
		GregorianCalendar repeatUntil = new GregorianCalendar(2023,8,10,8,30);	
		
		
		int[] days = {GregorianCalendar.MONDAY, GregorianCalendar.WEDNESDAY, GregorianCalendar.FRIDAY} ;
		
		
		MultiDayPerWeekEvent G = new MultiDayPerWeekEvent("G", "Grant", startTime, endTime, repeatUntil, days);
		G.scheduleEvent(cal);
		
		GregorianCalendar wednesdayStart = new GregorianCalendar (2023,8,6,8,30);
		GregorianCalendar wednesdayEnd = new GregorianCalendar (2023,8,6,9,30);

		
		GregorianCalendar fridayStart = new GregorianCalendar (2023,8,8,8,30);
		GregorianCalendar fridayEnd = new GregorianCalendar (2023,8,8,9,30);

		
		GregorianCalendar tuesday = new GregorianCalendar (2023, 8, 5, 8, 30);
		GregorianCalendar nextMonday = new GregorianCalendar (2023, 8, 11, 8, 30);
	
		
		checkMeeting("G", "Grant", startTime, endTime);
		checkMeeting("G", "Grant",wednesdayStart, wednesdayEnd);
		checkMeeting("G", "Grant", fridayStart, fridayEnd);
		assertNull(cal.findMeeting(tuesday));
		assertNull(cal.findMeeting(nextMonday));

	}

	@Test
	void testPriorityEvent()
	{
		GregorianCalendar oldStart = new GregorianCalendar (2023,8,27,8,30);
		GregorianCalendar oldEnd = new GregorianCalendar (2023,8,27,9,30);

		
		GregorianCalendar priorityStart = new GregorianCalendar (2023,8,27,8,30);
		GregorianCalendar priorityEnd = new GregorianCalendar (2023,8,27,9,30);
		
		Meeting oldMeeting = new Meeting("Old", "OldLocation", oldStart, oldEnd);
		cal.addMeeting(oldMeeting);
		
		
		PriorityEvent N = new PriorityEvent ("N", "Northside", priorityStart, priorityEnd);
		N.scheduleEvent(cal);
		
		checkMeeting("N", "Northside", oldStart, oldEnd);
		
	}
	
	@Test
	void testWeeklyEventDisplaces()
	{
		GregorianCalendar startTime = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar endTime = new GregorianCalendar(2023,8,28,9,30);		
		OneTimeEvent X = new OneTimeEvent("X", "Olin", startTime, endTime);
		X.scheduleEvent(cal);
		
		GregorianCalendar startTimeWeekly = new GregorianCalendar(2023,8,7,8,30);
		GregorianCalendar endTimeWeekly = new GregorianCalendar(2023,8,7,9,30);
		GregorianCalendar repeatUntil = new GregorianCalendar(2023,8,30,8,30);
		WeeklyEvent Y = new WeeklyEvent("Y", "Young", startTimeWeekly, endTimeWeekly, repeatUntil);
		Y.scheduleEvent(cal);
		
		checkMeeting("X", "Olin", startTime, endTime);

	}

}
