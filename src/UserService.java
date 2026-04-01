import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String,User> userMap= new HashMap<>();

    private User currentUser=null;

    public boolean registerUser(String username, String password,String fullName,String contact)
    {
        if(userMap.containsKey(username)) // check kar rahe ki user already tou nahi hai na by ----> containsKey(username)
        {
            System.out.println("Username already exists");
        }
        User user = new User(username,password,fullName,contact);
        userMap.put(username,user);
        System.out.println("Registration successful");
        return true;
    }

    public boolean loginUser(String username, String password)
    {
        if(!userMap.containsKey(username))
        {
            System.out.println("Username not found");
            return false;
        }

        User user = userMap.get(username);
        if (!user.getPassword().equals(password))
        {
            System.out.println("Wrong password");
            return false;
        }
        currentUser = user;
        System.out.println("Welcome : "+currentUser.getFullName());
        return true;
    }

    public void logOutUser()
    {
        if (currentUser != null)
        {
            System.out.println("Logout successful"+currentUser.getFullName());
        }
        currentUser=null;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public boolean isLoggeIn()
    {
        return currentUser!=null;
    }
}
