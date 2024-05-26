import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void EveryBranchTest() {
        System.out.println("--Every branch test--\n");
        Item item1 = new Item("itemName", "0271", 500, 0.3f);
        Item item2 = new Item(null, "272", 100, 0);
        Item item3 = new Item("invalid1", "barcode1", 0, 0);
        Item item4 = new Item("invalid2", null, 0, 0);
        List<Item> items;
        int payment = 100;
        RuntimeException ex;

        System.out.println("Test 1 -- Null list");
        List<Item> nullItems = null;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(nullItems, 0));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));
        System.out.println("Test 1 -- Done!\n");

        System.out.println("Test 2 -- sum -= 30, sum > payment");
        items = List.of(item1);
        assertFalse(SILab2.checkCart(items, payment));
        System.out.println("Test 2 -- Done!\n");

        System.out.println("Test 3 -- sum <= payment");
        items = List.of(item2);
        assertTrue(SILab2.checkCart(items, payment));
        System.out.println("Test 3 -- Done!\n");

        System.out.println("Test 4 -- Invalid barcode");
        items = List.of(item3);
        List<Item> invalidBarcodeItems = items;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(invalidBarcodeItems, payment));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));
        System.out.println("Test 4 -- Done!\n");

        System.out.println("Test 5 -- Null barcode");
        items = List.of(item4);
        List<Item> nullBarcodeItems = items;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(nullBarcodeItems, payment));
        assertTrue(ex.getMessage().contains("No barcode!"));
        System.out.println("Test 5 -- Done!\n");

    }

    public static boolean conditionsToTestFor(Item item) {
        return item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0';
    }

    @Test
    public void MultipleConditionTest() {
        System.out.println("--Multiple condition test--\n");
        // item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'
        Item item1 = new Item("item1", "0271", 500, 0.3f);
        Item item2 = new Item("item2", "0272", 500, 0);
        Item item3 = new Item("item3", "273", 500, 0.5f);
        Item item4 = new Item("item4", "0274", 100, 0.1f);

        System.out.println("Test 1 -- Price is 300 or lower");
        assertFalse(conditionsToTestFor(item4));
        System.out.println("Test 1 -- Done!\n");

        System.out.println("Test 2 -- Price is greater than 300, no discount");
        assertFalse(conditionsToTestFor(item2));
        System.out.println("Test 2 -- Done!\n");

        System.out.println("Test 3 -- Price is greater than 300, with discount, barcode doesn't start with 0");
        assertFalse(conditionsToTestFor(item3));
        System.out.println("Test 3 -- Done!\n");

        System.out.println("Test 4 -- Price is greater than 300, with discount, barcode starts with 0");
        assertTrue(conditionsToTestFor(item1));
        System.out.println("Test 4 -- Done!\n");
    }
}
