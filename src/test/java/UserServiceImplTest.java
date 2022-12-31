import org.junit.jupiter.api.*;
import org.uzoTestLifeCycleClass.io.UsersDatabase;
import org.uzoTestLifeCycleClass.io.UsersDatabaseMapImpl;
import org.uzoTestLifeCycleClass.service.UsersService;
import org.uzoTestLifeCycleClass.service.UsersServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    UsersDatabase usersDatabase;
    UsersService usersService;
    String createdUserId= "";

    @BeforeAll
    void setup(){
        usersDatabase = new UsersDatabaseMapImpl();
        usersDatabase.init();
        usersService = new UsersServiceImpl(usersDatabase);

    }
    @AfterAll
    void cleanup(){
        usersDatabase.close();

    }
    @Test
    @Order(1)
    @DisplayName("Create User Works")
    void testCreateUser_whenProvidedWithValidDetails_returnUserId(){

        Map<String, String > user = new HashMap<>();
        user.put("firstName", "Confidence");
        user.put("lastName", "Samuel");

        //Act
        createdUserId = usersService.createUser(user);

        //Assert
        assertNotNull(createdUserId, "userId should not be Null");


    }
    @Test
    @Order(2)
    @DisplayName("Update User Works")
    void testUpdateUser_whenProvidedWithValidDetails_returnUpdateUserDetails(){
        Map<String, String> newUserDetails= new HashMap<>();
        newUserDetails.put("firstName", "Courage");
        newUserDetails.put("lastName", "Samuel");

        //Act
        Map updateUserDetails= usersService.updateUser(createdUserId, newUserDetails);

        //Assert
        assertEquals(newUserDetails.get("firstName"),updateUserDetails.get("firstName"),
                "Returned value of user's first name was incorrect");
        assertEquals(newUserDetails.get("lastName"),updateUserDetails.get("lastName"),
                "Returned value of user's last name was incorrect");

    }
    @Test
    @Order(3)
    @DisplayName("Find User Works")
    void testGetUserDetails_whenProvidedWithValidDetailsId_returnUserDetails(){
        //Act
        Map userDetails = usersService.getUserDetails(createdUserId);

        //Assert
        assertNotNull(userDetails, "User Details Should not be Null");
        assertEquals(createdUserId, userDetails.get("userId"),
                "Returned user details contains incorrect user Id");

    }
    @Test
    @Order(4)
    @DisplayName("Delete User Works")
    void testDeleteUser_whenProvidedWithValidUserId_returnUserDetails(){
        usersService.deleteUser(createdUserId);

        assertNull(usersService.getUserDetails(createdUserId),
                "User should not be found");
    }
}
