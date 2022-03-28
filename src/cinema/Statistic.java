package cinema;

public class Statistic {

    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public Statistic() {
        this.numberOfAvailableSeats = 81;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets(int numberOfPurchasedTickets) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public void updateStatisticPurchase(int price) {
        currentIncome = getCurrentIncome() + price;
        numberOfAvailableSeats = getNumberOfAvailableSeats() - 1;
        numberOfPurchasedTickets = getNumberOfPurchasedTickets() + 1;
    }

    public void updateStatisticRefund(int price) {
        currentIncome = getCurrentIncome() - price;
        numberOfAvailableSeats = getNumberOfAvailableSeats() + 1;
        numberOfPurchasedTickets = getNumberOfPurchasedTickets() - 1;
    }
}
