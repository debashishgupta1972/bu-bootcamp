import java.util.*;

public class ContactManager {
    public static void main(String[] args){

        // Adding contacts to the HashMap
        HashMap<String, Contacts> contactList = new HashMap<>();
        contactList.put("Ariana Wells", new Contacts("Ariana Wells","512‑555‑0194"));
        contactList.put("Marcus Trent", new Contacts("Marcus Trent","737‑555‑8821"));
        contactList.put("Selena Hart", new Contacts("Selena Hart","469‑555‑4420"));
        contactList.put("Dylan Cross", new Contacts("Dylan Cross","281‑555‑7733"));
        contactList.put("Nina Calder", new Contacts("Nina Calder","214‑555‑6608"));
        contactList.put("Evan Rios", new Contacts("Evan Rios", "830‑555‑1279"));

        // Lookup a contact and if not found throw message.
        String searchContact = args[0];
        if(!contactList.containsKey(searchContact)){
            System.out.println("Contact '" + searchContact + "' not found in Contact List. \n");
        }else{
            System.out.println("Lookup Value: " + contactList.get(searchContact) + "\n");
        }

        // Sorting the list in the HashMap
        ArrayList<Contacts> Sorted = new ArrayList<>(contactList.values());

        Sorted.sort((a,b) -> a.getName().compareTo(b.getName()));
        System.out.println("=".repeat(3) + " All Contacts " + "=".repeat(3));

        // Print the sorted list
        for (Contacts c : Sorted){
            System.out.println(c.getName() + " - " + c.getPhone());
        }

        // Remove a contact from the contact list
        String RemoveContact = args[1];
        removeContact(contactList, RemoveContact);
        Sorted = new ArrayList<>(contactList.values());
        Sorted.sort((a,b) -> a.getName().compareTo(b.getName()));
        System.out.println("\n" + "=".repeat(3) + " new sorted list after removing '" + RemoveContact + "'" + "=".repeat(3));
        for (Contacts c : Sorted){
            System.out.println(c.getName() + " - " + c.getPhone());
        }
    }

    private static void removeContact(HashMap<String, Contacts> map, String name){
        map.remove(name);
    }
}
