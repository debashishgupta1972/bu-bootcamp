import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactsTest {
    private Contacts contact;
    @BeforeEach
    void setUp(){
        contact = new Contacts("Ada Lovelace", "+1 617 555 0101");
    }

    @Test
    void constructor_setsNameCorrectly(){
        assertEquals("Ada Lovelace", contact.getName());
    }

    @Test
    void constructor_setsPhoneCorrectly(){
        assertEquals("+1 617 555 0101", contact.getPhone());
    }

    @Test
    void toString_containsName(){
        assertTrue(contact.toString().contains("Ada Lovelace"));
    }

    @Test
    void toString_ContainsPhone(){
        assertTrue(contact.toString().contains("555 0101"));
    }

    @Test
    void toString_returnsExpectedFormat(){
        assertEquals("Ada Lovelace | +1 617 555 0101", contact.toString());
    }

    @Test
    void differentContacts_storeIndependentValues(){
        Contacts secondContact = new Contacts("Alan Turing", "+1 512 555 0202");

        assertEquals("Ada Lovelace", contact.getName());
        assertEquals("Alan Turing", secondContact.getName());

        assertEquals("+1 617 555 0101", contact.getPhone());
        assertEquals("+1 512 555 0202", secondContact.getPhone());
    }
}
