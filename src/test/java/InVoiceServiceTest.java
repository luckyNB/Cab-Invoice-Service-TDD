
import com.cab.InvoiceService;
import com.cab.InvoiceSummary;
import com.cab.Ride;
import com.cab.RideCategory;
import org.junit.Assert;
import org.junit.Test;

public class InVoiceServiceTest {
    InvoiceService invoiceService = new InvoiceService();


    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideCategory.NORMALRIDE,0.1, 1),
                new Ride(RideCategory.NORMALRIDE,2.0, 5)};
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId="abc.com";
        Ride[] rides = {new Ride(RideCategory.NORMALRIDE,0.1, 1),
                new Ride(RideCategory.PREMIUMRIDE,2.0, 5)};
        invoiceService.addRide(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceService(userId, RideCategory.NORMALRIDE);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRidesForPremium_ShouldReturnInvoiceSummary() {
        String userId="abc.com";
        Ride[] rides = {new Ride(RideCategory.NORMALRIDE,0.1, 1),
                new Ride(RideCategory.NORMALRIDE,2.0, 5)};
        invoiceService.addRide(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceService(userId, RideCategory.PREMIUMRIDE);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

}
