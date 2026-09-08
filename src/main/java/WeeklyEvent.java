import java.util.GregorianCalendar;

import calendar.MeetingCalendar;

public class WeeklyEvent extends CalendarEvent
{
	private GregorianCalendar repeatUntil;

	public WeeklyEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar repeatUntil)
	{
		super(description, location, startTime, endTime);
		this.setRepeatUntil(repeatUntil);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		// TODO Auto-generated method stub

	}

	public GregorianCalendar getRepeatUntil()
	{
		return repeatUntil;
	}

	public void setRepeatUntil(GregorianCalendar repeatUntil)
	{
		this.repeatUntil = repeatUntil;
	}

}
