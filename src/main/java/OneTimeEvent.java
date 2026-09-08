import java.util.GregorianCalendar;

import calendar.MeetingCalendar;

public class OneTimeEvent extends CalendarEvent
{

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		// TODO Auto-generated method stub

	}
	public OneTimeEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime)
	{
		super(location, location, endTime, endTime);

	}

}
