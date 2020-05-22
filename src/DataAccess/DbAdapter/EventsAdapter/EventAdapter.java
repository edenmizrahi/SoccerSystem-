package DataAccess.DbAdapter.EventsAdapter;

import DataAccess.DbAdapter.DbObject;
import Domain.Events.Event;
import Domain.MainSystem;

import java.util.LinkedList;
import java.util.List;

public class EventAdapter implements DbObject<Event> {
    @Override
    public Event ToObj(List<String> fields) throws Exception {

     return null;
    }

    @Override
    public List<String> ToDB(Event obj) {
        LinkedList<String> res=new LinkedList<>();
        res.add(Integer.toString(obj.getId()));
        res.add(MainSystem.simpleDateFormat.format(obj.getDateTime()));
        res.add(obj.getReferee().getUserName());
        //Match identifier ->
        res.add(obj.getMatch().toString());
        res.add(obj.getName());
        res.add(Integer.toString(obj.getMinuteOfMatch()));
        return res;
    }
}
