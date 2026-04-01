import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService
{
    private List<Train> trainList = new ArrayList<>();

    private List<Ticket> ticketList = new ArrayList<>();

    public BookingService()
    {
        trainList.add(new Train(101, "Rajdhani Express", "Delhi", "Nagpur", 100));
        trainList.add(new Train(102, "Shatabdi Express", "Mumbai", "Pune", 80));
        trainList.add(new Train(103, "Duronto Express", "Kolkata", "Delhi", 120));
        trainList.add(new Train(104, "Garib Rath", "Patna", "Delhi", 90));
        trainList.add(new Train(105, "Jan Shatabdi", "Ahmedabad", "Mumbai", 70));
        trainList.add(new Train(106, "Tejas Express", "Mumbai", "Goa", 85));
        trainList.add(new Train(107, "Humsafar Express", "Bangalore", "Hyderabad", 95));
        trainList.add(new Train(108, "Intercity Express", "Chennai", "Coimbatore", 60));
        trainList.add(new Train(109, "Superfast Express", "Jaipur", "Delhi", 75));
        trainList.add(new Train(110, "Double Decker", "Lucknow", "Delhi", 110));
    }

    public List<Train> searchTrain(String source, String destination)
    {
        List<Train> res = new ArrayList<>();
        for (Train t: trainList)
        {
            if (t.getSource().equalsIgnoreCase(source) && t.getDestination().equalsIgnoreCase(destination))
            {
                res.add(t);
            }
        }

        return res;
    }

    public Ticket bookTicket(User user,int trainId,int seatCount)
    {
        for (Train train: trainList)
        {
            if (train.getTrainId() == trainId)
            {
                if(train.bookSeat(seatCount))
                {
                    Ticket ticket = new Ticket(user,train,seatCount);
                    ticketList.add(ticket);
                    return ticket;
                }
                else {
                    System.out.println("no enough seats available");
                    return null;
                }
            }
        }
        System.out.println("Train ID not found");
        return null;
    }

    public List<Ticket> getTicketByUser(User user)
    {
        List<Ticket> res = new ArrayList<>();
        for (Ticket ticket: ticketList)
        {
            if (ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername()))
            {
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId,User user)
    {
        Iterator<Ticket> iterator = ticketList.listIterator();
        while (iterator.hasNext())
        {
            Ticket ticket = iterator.next();
            if (ticket.getTicketId() == ticketId && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername()))
            {
                Train train = ticket.getTrain();
                train.cancelSeat(ticket.getSeatBooked());
                iterator.remove();
                System.out.println("Ticket "+ticketId+" cancelled successfully");
                return true;
            }
        }
        System.out.println("Ticket not found or does not belong to current user");
        return false;
    }

    public void listAllTrains()
    {
        System.out.println("Listing of all trains");
        for (Train train: trainList)
        {
            System.out.println(train);
        }
    }
}
